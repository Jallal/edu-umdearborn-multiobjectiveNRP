package thiagodnf.doupr.evaluation.comparator;

import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.util.constants.ObjectiveConstants;

import java.util.Comparator;

public class SortObjectivesByRankingComparator implements Comparator<Objective> {

    @Override
    public int compare(Objective o1, Objective o2) {

        double rank1 = (double) o1.getAttributes().get(ObjectiveConstants.RANKING);
        double rank2 = (double) o2.getAttributes().get(ObjectiveConstants.RANKING);

        if (rank1 > rank2) {
            return -1;
        } else if (rank1 < rank2) {
            return 1;
        }

        return 0;
    }
}
