import java.time.LocalTime;

public class MinuteHand extends ClockHand {
    protected double angle = 0;

    @Override
    public void setTime(LocalTime time) {
        this.angle = time.getMinute()*6 + time.getSecond()*0.1;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" stroke=\"black\" stroke-width=\"2\" transform=\"rotate(" + Double.toString(angle) + ")\" />";
    }
}
