import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeathCauseStatistic.fromCsvLine("A04.7          ,758,-,-,-,-,-,1,-,1,3,5,9,12,30,58,64,94,161,192,95,33");
        System.out.println("Hello world!");
        DeathCauseStatisticList l = new DeathCauseStatisticList();
        l.repopulate("zgony.csv");
        System.out.println(l.mostDeadlyDiseases(3, 5));
    }
}