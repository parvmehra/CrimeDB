package dsa;
import models.Case;
import java.util.Comparator;
import java.util.List;

public class CaseSorter {
    public static void sortByUrgency(List<Case> cases) {
        cases.sort(Comparator.comparingInt(c -> {
            switch (c.getUrgency().trim().toLowerCase()) {
                case "high": return 0;
                case "medium": return 1;
                case "low": return 2;
                default: return 3;
            }
        }));


    }


}