import java.time.LocalTime;

public class DigitalClock extends Clock {
    public enum clockType {
        H24, H12
    }
    protected void updateHands(LocalTime time){
        //filler
    }
    private clockType type;

    public DigitalClock(LocalTime time, City city, clockType type) {
        super(time, city);
        this.type = type;
    }

    public clockType getType() {
        return type;
    }
    @Override
    public String toString() {
        if(this.type == clockType.H24) {
            return super.toString();
        } else {
            if(this.getTime().getHour() == 0) {
                return String.format("%2d:%02d:%02d AM", getTime().getHour()+12, getTime().getMinute(), getTime().getSecond());
            } else if(this.getTime().getHour() > 12) {
                return String.format("%2d:%02d:%02d PM", getTime().getHour()-12, getTime().getMinute(), getTime().getSecond());
            } else if(this.getTime().getHour() < 12) {
                return String.format("%2d:%02d:%02d AM", getTime().getHour(), getTime().getMinute(), getTime().getSecond());
            } else {
                return String.format("%2d:%02d:%02d PM", getTime().getHour(), getTime().getMinute(), getTime().getSecond());
            }
        }
    }
}