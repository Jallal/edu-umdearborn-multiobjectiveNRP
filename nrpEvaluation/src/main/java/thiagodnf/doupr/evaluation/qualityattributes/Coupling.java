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
public class Coupling extends AbstractQualityAttribute {

    public Coupling() {

    }

    public Coupling(Coupling objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {
        return getRate(project, refactored, "Standard Coupling");
    }

    @Override
    public double getValue(ProjectObject project) {
        return project.getDesignMetrics().get("Standard Coupling");
    }

    @Override
    public String toString() {
        return "Coupling";
    }

    @Override
    public boolean isMinimize() {
        return true;
    }

    @Override
    public Objective copy() {
        return new Coupling(this);
    }
}
