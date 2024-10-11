package exercise.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    private Task getNewSavedTask() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        taskRepository.save(task);
        return task;
    }


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = getNewSavedTask();

        var request = get("/tasks/" + task.getId());

        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var responseJson = response.getResponse().getContentAsString();

        assertThatJson(responseJson).and(
                o -> o.node("id").isEqualTo(task.getId()),
                o -> o.node("title").isEqualTo(task.getTitle()),
                o -> o.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testCreate() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().paragraph();

        var data = new HashMap<>();
        data.put("title", title);
        data.put("description", description);

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var response = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var responseJson = response.getResponse().getContentAsString();

        assertThatJson(responseJson).and(
                o -> o.node("title").isEqualTo(title),
                o -> o.node("description").isEqualTo(description)
        );

        Long id = new JSONObject(responseJson).getLong("id");

        var task = taskRepository.findById(id).get();

        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getDescription()).isEqualTo(description);
    }

    @Test
    public void testUpdate() throws Exception {
        var task = getNewSavedTask();

        var newTitle = "new " + task.getTitle();
        var newDescription = "new " + task.getDescription();

        var data = new HashMap<>();
        data.put("title", newTitle);
        data.put("description", newDescription);

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var responseJson = response.getResponse().getContentAsString();

        assertThatJson(responseJson).and(
                o -> o.node("id").isEqualTo(task.getId()),
                o -> o.node("title").isEqualTo(newTitle),
                o -> o.node("description").isEqualTo(newDescription)
        );

        var updatedTask = taskRepository.findById(task.getId()).get();

        assertThat(updatedTask.getTitle()).isEqualTo(newTitle);
        assertThat(updatedTask.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void testDestroy() throws Exception {
        var task = getNewSavedTask();

        assertTrue(taskRepository.existsById(task.getId()));

        var request = delete("/tasks/" + task.getId());
        mockMvc.perform(request)
                .andExpect(status().isOk());

        assertFalse(taskRepository.existsById(task.getId()));
    }
    // END
}
