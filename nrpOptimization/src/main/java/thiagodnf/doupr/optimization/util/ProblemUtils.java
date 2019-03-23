package thiagodnf.doupr.optimization.util;

import thiagodnf.doupr.optimization.problem.Problem;

public class ProblemUtils {

    public static int getObjectiveIndex(Problem problem, String objectiveName) {

        if (objectiveName == null || objectiveName.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < problem.getObjectives().size(); i++) {
            if (problem.getObjectives().get(i).toString().equalsIgnoreCase(objectiveName)) {
                return i;
            }
        }

        return -1;
    }

}
