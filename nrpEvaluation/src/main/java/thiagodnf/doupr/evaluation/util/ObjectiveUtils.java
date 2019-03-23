package thiagodnf.doupr.evaluation.util;

import thiagodnf.doupr.evaluation.Objective;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveUtils {

    public static List<Objective> copy(List<Objective> objectives) {

        List<Objective> copy = new ArrayList<>();

        for (Objective solution : objectives) {
            copy.add(solution.copy());
        }

        return copy;
    }

}
