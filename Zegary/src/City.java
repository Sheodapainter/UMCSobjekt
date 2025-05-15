import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class City {
    private String capital;
    private int timezone;
    private String width, length;

    public City(String capital, int timezone, String width, String length) {
        this.capital = capital;
        this.timezone = timezone;
        this.width = width;
        this.length = length;
    }

    public String getCapital() {
        return capital;
    }
    public int getTimezone() {
        return timezone;
    }
    public String getLength() {
        return length;
    }

    private static City parseLine(String line) {
        String[] parts = line.split(",");
        String capital = parts[0].trim();
        int timezone = Integer.parseInt(parts[1].trim());
        String width = parts[2].trim();
        String length = parts[3].trim();
        return new City(capital, timezone, width, length);
    }
    public static Map<String, City> parseFile (String filePath) throws IOException {
        Map<String, City> cityMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                try {
                    City city = parseLine(line);
                    cityMap.put(city.getCapital(), city);
                } catch (Exception e) {
                    System.err.println("Błąd w linii: \"" + line + "\" — pominięto.");
                }
            }
        }
        return cityMap;
    }
    public static Comparator<City> worstTimezoneFit() {
        return (c1, c2) -> {
            double temp;
            String len = c1.getLength();
            if(len.contains("E")) {
                temp = Float.parseFloat(len.substring(0, len.length()-1));
            } else {
                len="-"+len;
                temp = Float.parseFloat(len.substring(0, len.length()-1));
            }
            double diff1 = Math.abs(c1.getTimezone()*15-temp);
            len = c2.getLength();
            if(len.contains("E")) {
                temp = Float.parseFloat(len.substring(0, len.length()-1));
            } else {
                len="-"+len;
                temp = Float.parseFloat(len.substring(0, len.length()-1));
            }
            double diff2 = Math.abs(c2.getTimezone()*15-temp);
            return Double.compare(diff2, diff1);
        };
    }
    public void generateAnalogClocks(List<City> cities, AnalogClock aclock) {
        Path folderPath = Paths.get(aclock.toString().replaceAll(":", "_"));
        if(!Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                System.err.println("Nie udalo sie utworzyc folderu: " + e.getMessage());
                return;
            }
        }
        for(City city : cities) {
            try {
                aclock.setCity(city);
                String svg = aclock.toSvg();
                String filename = city.getCapital()+".svg";
                Path filepath = folderPath.resolve(filename);
                Files.writeString(filepath, svg);
                System.out.println("Zapisano zegar dla "+city.getCapital());
            } catch (IOException e) {
                System.err.println("Blad zapisu pliku dla "+city.getCapital()+": "+e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "name=" + capital +
                ", timezone " + timezone +
                ", width " + width +
                ", length " + length;
    }
}
