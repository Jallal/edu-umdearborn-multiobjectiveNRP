package edu.umich.ISELab.evaluation.qualityattributes;

import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.evaluation.util.DesignMetricsUtil;
import edu.umich.ISELab.evaluation.Objective;

import java.util.List;

public abstract class AbstractQualityAttribute extends Objective {

    public AbstractQualityAttribute() {

    }

    public AbstractQualityAttribute(Objective objective) {
        super(objective);
    }

    public double calculate(ProjectObject original, ProjectObject refactored, List<NrpBase> appliedRefactorings) {

        double newValue = getDiff(original, refactored);

        if (isMinimize()) {
            return (newValue);
        }

        return -1.0 * (newValue);
    }

    protected double getRate(ProjectObject project, ProjectObject refactored, String name) {
        return DesignMetricsUtil.rate(project.getDesignMetrics().get(name), refactored.getDesignMetrics().get(name));
    }
}
