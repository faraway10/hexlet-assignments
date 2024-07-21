package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            int square = (int) Math.round(circle.getSquare());

            System.out.print(square);
        } catch (Exception e) {
            System.out.print("\nНе удалось посчитать площадь");
        } finally {
            System.out.print("\nВычисление окончено");
        }
    }
}
// END
