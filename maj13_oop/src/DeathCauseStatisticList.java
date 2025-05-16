import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DeathCauseStatisticList {
    private List<DeathCauseStatistic> DCSList;
    public DeathCauseStatisticList() {
        DCSList = new ArrayList<>();
    }

    public void repopulate(String filepath) {
        DCSList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            reader.readLine();
            reader.readLine();
            while((line = reader.readLine()) != null) {
                    DeathCauseStatistic DCSnew = DeathCauseStatistic.fromCsvLine(line);
                    DCSList.add(DCSnew);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n) {
        if(n > DCSList.size()) {
            System.err.println("n jest zbyt duze");
        }
        int index = age/5;
        List<DeathCauseStatistic> Deadliest = new ArrayList<>();
        Deadliest.sort(Comparator.comparingInt((DeathCauseStatistic d) ->d.getDeathCount()[index]).reversed());
        while(Deadliest.size() > n) {
            Deadliest.removeLast();
        }
        return Deadliest;
    }
}
