import java.time.LocalTime;

public abstract class Clock {
    private LocalTime time;
    private City city;
    public Clock(LocalTime time, City city) {
        this.time = time;
        this.city = city;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setCurrentTime() {

        time = LocalTime.now();
        updateHands(time);
    }
    public void setTime(int s, int m, int h) {
        if (s > 59) {
            throw new IllegalArgumentException("Liczba sekund zbyt duza");
        }
        if (s < 0) {
            throw new IllegalArgumentException("Liczba sekund zbyt mala");
        }
        if (m > 59) {
            throw new IllegalArgumentException("Liczba minut zbyt duza");
        }
        if (m < 0) {
            throw new IllegalArgumentException("Liczba minut zbyt mala");
        }
        if (h > 23) {
            throw new IllegalArgumentException("Liczba godzin zbyt duza");
        }
        if (h < 0) {
            throw new IllegalArgumentException("Liczba godzin zbyt mala");
        }
        this.time = LocalTime.of(h, m, s);
        updateHands(time);
    }
    public void setCity(City newCity) {
        int diff = newCity.getTimezone() - this.city.getTimezone();
        int h = time.getHour() + diff;
        if(h > 23) {
            h=h-24;
        } else if(h<0) {
            h=h+24;
        }
        setTime(this.time.getSecond(), this.time.getMinute(), h);
        this.city = newCity;
    }
    protected abstract void updateHands(LocalTime time);
    public LocalTime localMeanTime() {
        int h = this.time.getHour() - this.city.getTimezone();
        LocalTime nowyczas;
        if(h > 23) {
            h = h - 24;
        } else if(h < 0) {
            h = h + 24;
        }
        nowyczas = LocalTime.of(h, this.time.getMinute(), this.time.getSecond());
        double realtime;
        String len = this.city.getLength();
        if(len.contains("E")) {
            realtime = Float.parseFloat(len.substring(0, len.length()-1));
        } else {
            len="-"+len;
            realtime = Float.parseFloat(len.substring(0, len.length()-1));
        }
        int hours = 0, minutes = 0, seconds = 0;
        while(realtime >= 15) {
            realtime=realtime-15;
            hours++;
        } while(realtime >= 0.25) {
            realtime=realtime-0.25;
            minutes++;
        } while(realtime >=0.0042) {
            realtime=realtime-0.0042;
            seconds++;
        }
        while(realtime <= -15) {
            realtime=realtime+15;
            hours--;
        } while(realtime <= -0.25) {
            realtime=realtime+0.25;
            minutes--;
        } while(realtime <= -0.0042) {
            realtime=realtime+0.0042;
            seconds--;
        }
        seconds=seconds+nowyczas.getSecond();
        minutes=minutes+nowyczas.getMinute();
        hours=hours+nowyczas.getHour();
        if(seconds > 59) {
            seconds = seconds - 60;
            minutes++;
        }
        if(seconds < 0) {
            seconds = seconds + 60;
            minutes--;
        }
        if(minutes > 59) {
            minutes = minutes - 60;
            hours++;
        }
        if(minutes < 0) {
            minutes = minutes + 60;
            hours--;
        }
        if(hours > 23) {
            hours=hours-24;
        }
        if(hours < 0) {
            hours=hours+24;
        }
        nowyczas = LocalTime.of(hours, minutes, seconds);
        updateHands(nowyczas);
        return nowyczas;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
    }
}