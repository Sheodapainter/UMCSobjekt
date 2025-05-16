import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular {
    private Map<String, String> desc;


    public ICDCodeTabularOptimizedForTime(String filename)  {
        desc = new HashMap<>();
        String key, d;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int i=0;
            String line;
            while(i<87) {
                reader.readLine();
                i++;
            }
            while((line = reader.readLine()) != null) {
                line = line.trim();
                try {
                    if(Character.isLetter(line.charAt(0))&&Character.isDigit(line.charAt(1))&&Character.isDigit(line.charAt(2))) {
                            String[] parts = line.split(" ", 2);
                            key = parts[0];
                            d = parts[1];
                        desc.put(key, d);
                    }
                } catch (Exception e) {
                    System.err.println("Błąd w linii, pominięto");
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd w otwieraniu");
        }
    }

    @Override
    public String getDescription(String ICD) {
            if(desc.containsKey(ICD)) {
                return desc.get(ICD);
            } else {
                System.out.println("Danego kodu nie ma.");
            }
        return null;
    }
}
