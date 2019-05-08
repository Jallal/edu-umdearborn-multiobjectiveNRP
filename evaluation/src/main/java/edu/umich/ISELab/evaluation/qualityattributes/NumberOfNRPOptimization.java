package edu.umich.ISELab.evaluation.qualityattributes;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.evaluation.Objective;

import java.util.List;

public class NumberOfNRPOptimization extends AbstractQualityAttribute {

    public NumberOfNRPOptimization() {

    }

    public NumberOfNRPOptimization(NumberOfNRPOptimization objective) {
        super(objective);
    }

    @Override
    public double getDiff(Project project, Project groomedProject) {
        return 0;
    }

    @Override
    public double getValue(Project project) {
        return 0;
    }

    public double calculate(Project original, Project groomedProject, List<NrpBase> appliedGrooming) {

        if (isMinimize()) {
            return (appliedGrooming.size());
        }

        return -1.0 * (appliedGrooming.size());
    }

    @Override
    public boolean isMinimize() {
        return true;
    }

    @Override
    public String toString() {
        return "Number Of Grooming";
    }

    @Override
    public Objective copy() {
        return new NumberOfNRPOptimization (this);
    }
}
