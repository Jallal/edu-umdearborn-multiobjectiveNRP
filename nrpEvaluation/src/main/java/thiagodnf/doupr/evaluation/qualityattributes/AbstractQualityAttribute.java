package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;

import java.util.List;

public abstract class AbstractQualityAttribute extends Objective {

    public AbstractQualityAttribute() {

    }

    public AbstractQualityAttribute(Objective objective) {
        super(objective);
    }

    public double calculate(ProjectObject original, ProjectObject refactored, List<Refactoring> appliedRefactorings) {

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
