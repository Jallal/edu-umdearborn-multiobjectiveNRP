package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.evaluation.Objective;

/**
 * The degree of understanding and the easiness of learning the design
 * implementation details
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class Complexity extends AbstractQualityAttribute {

    public Complexity() {

    }

    public Complexity(Complexity objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {
        return getRate(project, refactored, "Standard Complexity");
    }

    @Override
    public double getValue(ProjectObject project) {
        return project.getDesignMetrics().get("Standard Complexity");
    }

    @Override
    public String toString() {
        return "Complexity";
    }

    @Override
    public boolean isMinimize() {
        return true;
    }

    @Override
    public Objective copy() {
        return new Complexity(this);
    }
}
