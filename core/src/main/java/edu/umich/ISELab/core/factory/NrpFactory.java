package edu.umich.ISELab.core.factory;


import edu.umich.ISELab.core.grooming.AssignTask;

public class NrpFactory {
    public static AssignTask getNrpOptimization(String name) {

        if (name.equalsIgnoreCase("Assign Task") || name.equalsIgnoreCase(AssignTask.class.getSimpleName())) {
            return new AssignTask();
        }
        return null;
    }
}
