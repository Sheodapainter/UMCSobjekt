public class DeathCauseStatistic {
    private String ico10;
    private int[] deathCount;

    public DeathCauseStatistic(String ico10, int[] deathCount) {
        this.ico10 = ico10;
        this.deathCount = deathCount;
    }
    public String getIco10() {
        return ico10;
    }

    public int[] getDeathCount() {
        return deathCount;
    }

    public static DeathCauseStatistic fromCsvLine(String line) {
        String[] parts = line.split(",");
        int[] dcount = new int[20];
        String ico = parts[0];
        int i=0;
        while(i<20) {
            if(parts[i+2].equals("-")) {
                dcount[i]=0;
            }else {
                dcount[i] = Integer.parseInt(parts[i + 2].trim());
            }
            i++;
        }
        return new DeathCauseStatistic(ico, dcount);
    }
    public class AgeBracketDeaths{
        public final int old;
        public final int young;
        public final int deathCount;

        public AgeBracketDeaths(int old, int young, int deathCount) {
            this.old = old;
            this.young = young;
            this.deathCount = deathCount;
        }

    }
    public AgeBracketDeaths get(int age) {
        int index=age/5;
        if(age>=100) {
            index=19;
        }
        int min = index*5;
        int max = min+4;
        return new AgeBracketDeaths(max, min, this.deathCount[index]);
    }
}
