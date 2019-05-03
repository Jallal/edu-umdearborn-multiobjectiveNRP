package edu.umich.ISELab.core.factory;

import edu.umich.ISELab.core.grooming.AssignTaskToDeveloper;
import edu.umich.ISELab.core.grooming.NrpBase;

public class NrpFactory {
    public static NrpBase getNrpOptimization(String name) {

        if (name.equalsIgnoreCase("Move Method") || name.equalsIgnoreCase(AssignTaskToDeveloper.class.getSimpleName())) {
            return new AssignTaskToDeveloper();
        }
        return null;
    }
}
