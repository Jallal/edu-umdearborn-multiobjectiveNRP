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
public class QMOODUnderstandability extends AbstractQualityAttribute {

    public QMOODUnderstandability() {

    }

    public QMOODUnderstandability(QMOODUnderstandability objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double coupling = getRate(project, refactored, "Coupling");
        double cohesion = getRate(project, refactored, "Cohesion");
        double designSize = getRate(project, refactored, "Design Size");
        double abstraction = getRate(project, refactored, "Abstraction");
        double encapsulation = getRate(project, refactored, "Encapsulation");
        double polymorphism = getRate(project, refactored, "Polymorphism");
//		double complexity = getRate(project, refactored, "Complexity");
        double complexity = getRate(project, refactored, "Standard Complexity");
        //TODO change of complexity -vahid

//		return -0.33 * abstraction + 0.33 * encapsulation - 0.33 * coupling + 0.33 * cohesion - 0.33 * polymorphism
//				- 0.33 * complexity - 0.33 * designSize;
        return -0.33 * abstraction + 0.33 * encapsulation - 0.33 * coupling + 0.33 * cohesion - 0.33 * polymorphism
                - 0.33 * complexity - 0.33 * (designSize / 5000); //TODO changing understandability -vahid
    }

    @Override
    public double getValue(ProjectObject project) {

        double coupling = project.getDesignMetrics().get("Coupling");
        double cohesion = project.getDesignMetrics().get("Cohesion");
        double designSize = project.getDesignMetrics().get("Design Size");
        double abstraction = project.getDesignMetrics().get("Abstraction");
        double encapsulation = project.getDesignMetrics().get("Encapsulation");
        double polymorphism = project.getDesignMetrics().get("Polymorphism");
//		double complexity = project.getDesignMetrics().get("Complexity");
        double complexity = project.getDesignMetrics().get("Standard Complexity");
        //TODO change of complexity -vahid

//		return -0.33 * abstraction + 0.33 * encapsulation - 0.33 * coupling + 0.33 * cohesion - 0.33 * polymorphism
//				- 0.33 * complexity - 0.33 * designSize;
        return -0.33 * abstraction + 0.33 * encapsulation - 0.33 * coupling + 0.33 * cohesion - 0.33 * polymorphism
                - 0.33 * complexity - 0.33 * (designSize / 5000); //TODO changing understandability -vahid
    }

    @Override
    public String toString() {
        return "Understandability";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODUnderstandability(this);
    }
}
