package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.evaluation.Objective;

/**
 * Classes with given functions that are publically stated in interfaces to be
 * used by others.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class QMOODFunctionality extends AbstractQualityAttribute {

    public QMOODFunctionality() {

    }

    public QMOODFunctionality(QMOODFunctionality objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double cohesion = getRate(project, refactored, "Cohesion");
        double designSize = getRate(project, refactored, "Design Size");
        double polymorphism = getRate(project, refactored, "Polymorphism");
        double messaging = getRate(project, refactored, "Messaging");
        double hierarchies = getRate(project, refactored, "Hierarchies");

//		return 0.12 * cohesion + 0.22 * polymorphism + 0.22 * messaging + 0.22 * designSize + 0.22 * hierarchies;
        return 0.12 * cohesion + 0.22 * polymorphism + 0.22 * messaging + 0.22 * (designSize / 5000) + 0.22 * hierarchies; //TODO changing functionality -vahid

    }

    @Override
    public double getValue(ProjectObject project) {

        double cohesion = project.getDesignMetrics().get("Cohesion");
        double designSize = project.getDesignMetrics().get("Design Size");
        double polymorphism = project.getDesignMetrics().get("Polymorphism");
        double messaging = project.getDesignMetrics().get("Messaging");
        double hierarchies = project.getDesignMetrics().get("Hierarchies");

//		return 0.12 * cohesion + 0.22 * polymorphism + 0.22 * messaging + 0.22 * designSize + 0.22 * hierarchies;
        return 0.12 * cohesion + 0.22 * polymorphism + 0.22 * messaging + 0.22 * (designSize / 5000) + 0.22 * hierarchies; //TODO changing functionality -vahid
    }

    @Override
    public String toString() {
        return "Functionality";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODFunctionality(this);
    }
}
