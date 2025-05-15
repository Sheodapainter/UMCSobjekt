import java.util.ArrayList;
import java.util.List;

public class Land extends Polygon{
    private List<City> cities;
    public Land(List<Point> points){
        super(new ArrayList<>(points));
        this.cities = new ArrayList<>();
    }
    public void addCity(City a) {
        if(this.inside(a.center)) {
            this.cities.add(a);
            for(Point p1 : a.getPoints()) {
                if (!inside(p1)) {
                    a.setPort(true);
                    break;
                }
            }
        } else {
            throw new RuntimeException(a.getName() + " zostało postawione w wodzie");
        }
    }
}
