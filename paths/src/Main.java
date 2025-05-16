import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("data", "nonfood", "benzyna.csv");
        NonFoodProduct gas = NonFoodProduct.fromCsv(path);
        double cena = gas.getPrice(2010, 9);
        System.out.printf("Hello and welcome!" + cena);
    }
}