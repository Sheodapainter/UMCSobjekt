import java.util.ArrayList;
import java.util.List;

public class City extends Polygon{
    public final Point center;
    private String name;
    private boolean port;
    public City(Point center, String name, double width){
        super(new ArrayList<>());
        this.center = center;
        this.name = name;
        List<Point> corners = new ArrayList<>();
        double x=center.x+(width/2);
        double y=center.y+(width/2);
        Point corner1 = new Point(x, y);
        Point corner2 = new Point(x, y-width);
        Point corner3 = new Point(x-width, y-width);
        Point corner4 = new Point(x-width, y);
        corners.add(corner1);
        corners.add(corner2);
        corners.add(corner3);
        corners.add(corner4);
        for(Point p1 : corners) {
            addPoints(p1);
        }
    }
    public void setPort(boolean a) {
        port = a;
    }
    public String getName() {
        return name;
    }
    public void portowe() {
        if(port) System.out.println("Miasto "+getName()+" jest miastem portowym");
        else System.out.println("Miasto "+getName()+" nie jest miastem portowym");
    }
}
