package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.evaluation.Objective;

/**
 * A design with low coupling and high cohesion is easily reused by other designs.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class QMOODReusability extends AbstractQualityAttribute {

    public QMOODReusability() {

    }

    public QMOODReusability(QMOODReusability objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double coupling = getRate(project, refactored, "Coupling");
        double cohesion = getRate(project, refactored, "Cohesion");
        double messaging = getRate(project, refactored, "Messaging");
        double designSize = getRate(project, refactored, "Design Size");

//		return -0.25 * coupling + 0.25 * cohesion + 0.5 * messaging + 0.5 * designSize;
        return -0.25 * coupling + 0.25 * cohesion + 0.5 * messaging + 0.5 * (designSize / 5000); //TODO changing reusability -vahid
    }

    @Override
    public double getValue(ProjectObject project) {

        double coupling = project.getDesignMetrics().get("Coupling");
        double cohesion = project.getDesignMetrics().get("Cohesion");
        double messaging = project.getDesignMetrics().get("Messaging");
        double designSize = project.getDesignMetrics().get("Design Size");

//		return -0.25 * coupling + 0.25 * cohesion + 0.5 * messaging + 0.5 * designSize;
        return -0.25 * coupling + 0.25 * cohesion + 0.5 * messaging + 0.5 * (designSize / 5000); //TODO changing reusability -vahid

    }

    @Override
    public String toString() {
        return "Reusability";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODReusability(this);
    }
}
