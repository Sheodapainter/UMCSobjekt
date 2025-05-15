import java.time.LocalTime;
import java.util.List;

public class AnalogClock extends Clock{
    private final List<ClockHand> hands;
    public AnalogClock(LocalTime time, City city) {
        super(time, city);
        this.hands = List.of(
                new HourHand(),
                new MinuteHand(),
                new SecondHand()
        );
        hands.forEach((ClockHand hand) -> hand.setTime(time));
    }

    @Override
    protected void updateHands(LocalTime time) {
        hands.forEach((ClockHand hand) -> hand.setTime(time));
    }

    public String toSvg() {
        return "<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\"> \n " +
                " <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" /> \n " +
                "<g text-anchor=\"middle\"> \n " +
                "<text x=\"0\" y=\"-80\" dy=\"6\">12</text> \n " +
                "<text x=\"80\" y=\"0\" dy=\"4\">3</text> \n " +
                "<text x=\"0\" y=\"80\" dy=\"6\">6</text> \n " +
                "<text x=\"-80\" y=\"0\" dy=\"4\">9</text> \n " +
                "</g> \n \n" + hands.getFirst().toSvg()
                + "\n" + hands.get(1).toSvg()
                + "\n" + hands.get(2).toSvg()
                + "</svg>";
    }
}
