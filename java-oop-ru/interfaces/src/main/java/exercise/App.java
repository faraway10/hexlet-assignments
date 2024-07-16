package exercise;

import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream()
                                .sorted((home1, home2) -> Double.compare(home1.getArea(), home2.getArea()))
                                .limit(n)
                                .map(Home::toString)
                                .toList();
    }
}
// END
