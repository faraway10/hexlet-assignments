package exercise.daytime;

// Интерфейс содержит метод, возвращающий название времени суток
// Реализация методов представлена в классах Morning, Day, Evening, Night,
// которые реализуют этот интерфейс

import org.springframework.stereotype.Component;

@Component
public interface Daytime {

    String getName();
}
