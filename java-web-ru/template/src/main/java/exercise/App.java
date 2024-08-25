package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            try {
                var id = Long.parseLong(ctx.pathParam("id"));
                var user = Data.getUsers()
                        .stream()
                        .filter(u -> u.getId() == id)
                        .findFirst()
                        .get();
                var page = new UserPage(user);
                ctx.render("users/show.jte", model("page", page));
            } catch (Exception e) {
                throw new NotFoundResponse("User not found");
            }
        });

        app.get("/users", ctx -> {
            var users = Data.getUsers()
                    .stream()
                    .sorted((user1, user2) -> Long.compare(user1.getId(), user2.getId()))
                    .toList();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
