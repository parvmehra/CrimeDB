package dsa;
import models.Case;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CaseSorter {
    public static void sortByUrgency(List<Case> cases) {
        Collections.sort(cases, Comparator.comparing(Case::getUrgency));
    }

    public static void sortByDate(List<Case> cases) {
        Collections.sort(cases, Comparator.comparing(Case::getDate));
    }

}
