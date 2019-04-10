package thiagodnf.doupr.core.factory;

import thiagodnf.doupr.core.refactoring.AssignTaskToDeveloper;
import thiagodnf.doupr.core.refactoring.NrpBase;

public class NrpFactory {
    public static NrpBase getNrpOptimization(String name) {

        if (name.equalsIgnoreCase("Move Method") || name.equalsIgnoreCase(AssignTaskToDeveloper.class.getSimpleName())) {
            return new AssignTaskToDeveloper();
        }
        return null;
    }
}
