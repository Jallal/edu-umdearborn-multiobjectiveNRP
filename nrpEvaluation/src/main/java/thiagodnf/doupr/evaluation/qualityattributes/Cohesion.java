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
public class Cohesion extends AbstractQualityAttribute {

    public Cohesion() {

    }

    public Cohesion(Cohesion cohesion) {
        super(cohesion);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {
        return getRate(project, refactored, "Standard Cohesion");
    }

    @Override
    public double getValue(ProjectObject project) {
        return project.getDesignMetrics().get("Standard Cohesion");
    }

    @Override
    public String toString() {
        return "Cohesion";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new Cohesion(this);
    }
}
