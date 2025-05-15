import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, City> cities = City.parseFile("strefy.csv");
            cities.forEach((name, city) -> System.out.println(name + ": " + city));
            List<City> cities2 = new ArrayList<>();
            cities.forEach((name, city) -> cities2.add(city));
            cities2.sort(City.worstTimezoneFit());
            cities2.forEach(city -> System.out.println(city.getCapital() + " "));
            DigitalClock clock1 = new DigitalClock(LocalTime.now(), cities.get("Warszawa"), DigitalClock.clockType.H24);
            DigitalClock clock2 = new DigitalClock(LocalTime.now(), cities.get("Warszawa"), DigitalClock.clockType.H12);
            AnalogClock clock3 = new AnalogClock(LocalTime.now(), cities.get("Lublin"));
            clock1.setTime(30, 30, 23);
            clock2.setTime(30, 30, 23);
            clock3.setCurrentTime();
            City city = cities.get("Lublin");
            city.generateAnalogClocks(cities2, clock3);
            clock1.setCity(cities.get("Kijów"));
            System.out.println(clock1.toString());
            System.out.println(clock2.toString());
            System.out.println(clock3.localMeanTime().toString());
            System.out.println(clock3.toSvg());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}