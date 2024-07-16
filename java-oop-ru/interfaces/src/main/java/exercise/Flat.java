package exercise;

// BEGIN
public class Flat implements Home {
    private final double area;
    private final int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area + balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area;
    }

    public int compareTo(Home another) {
        return Double.compare(area, another.getArea());
    }

    @Override
    public String toString() {
        return "Квартира площадью " + area + " метров на " + floor + " этаже";
    }
}
// END
