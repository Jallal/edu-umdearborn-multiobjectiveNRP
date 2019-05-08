package edu.umich.ISELab.evaluation.qualityattributes;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.grooming;
import edu.umich.ISELab.evaluation.Objective;

import java.util.List;

public abstract class AbstractQualityAttribute extends Objective {

    public AbstractQualityAttribute() {

    }

    public AbstractQualityAttribute(Objective objective) {
        super(objective);
    }

    public double calculate(Project original, Project groomedProject, List<grooming> appliedGrooming) {

        double newValue = getDiff(original, groomedProject);

        if (isMinimize()) {
            return (newValue);
        }

        return -1.0 * (newValue);
    }

    protected double getRate(Project project, Project groomedProject, String name) {
        // return DesignMetricsUtil.rate(project.getDesignMetrics().get(name), refactored.getDesignMetrics().get(name));
        return 0.0;
    }
}
