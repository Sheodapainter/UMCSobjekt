import java.time.LocalTime;

public class HourHand extends ClockHand{
    protected double angle = 0;

    @Override
    public void setTime(LocalTime time) {
        this.angle = (time.getHour()%12)*30+time.getMinute()*0.5+time.getSecond()*0.0083;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" stroke=\"black\" stroke-width=\"4\" transform=\"rotate(" + Double.toString(angle) + ")\" />";
    }
}
