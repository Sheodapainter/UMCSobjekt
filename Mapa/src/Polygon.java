import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Point> points;
    public Polygon(List<Point> points) {
        this.points = new ArrayList<>();
        for(Point p1 : points) {
            this.points.add(p1);
        }
    }
    protected void addPoints(Point a) {
        this.points.add(a);
    }
    protected List<Point> getPoints() {
        return points;
    }
    public boolean inside(Point point) {
        int counter = 0;
        double a, b, d, x;
        for(Point pa : this.points) {
            Point check = this.points.getLast();
            Point pb;
            if(pa == check){
                pb = this.points.getFirst();
            } else {
                pb = this.points.get(points.indexOf(pa) + 1);
            }
            if(pa.y > pb.y) {
                check=pa;
                pa=pb;
                pb=check;
            }
            if(pa.y < point.y) {
                if(point.y < pb.y) {
                    d = pb.x - pa.x;
                    if (d == 0) {
                        x = pa.x;
                    } else {
                        a = (pb.y - pa.y) / d;
                        b = pa.y - a * pa.x;
                        x = (point.y - b) / a;
                    }
                    if (x < point.x) {
                        counter++;
                    }
                }
            }
        }
        if((counter%2)==1) {
            return true;
        } else {
            return false;
        }
    }
}
