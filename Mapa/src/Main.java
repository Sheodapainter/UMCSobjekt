import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> poly = new ArrayList<>();
        Point a = new Point(1, 1);
        Point b = new Point(10, 1);
        Point c = new Point(12, 9);
        Point d = new Point(9, 13);
        Point e = new Point(1, 11);
        Point x = new Point(11, 9);
        poly.add(a);
        poly.add(b);
        poly.add(c);
        poly.add(d);
        poly.add(e);
        Land mapa = new Land(poly);
        City lbl = new City(x, "Lublin", 2);
        mapa.addCity(lbl);
        lbl.portowe();
    }
}