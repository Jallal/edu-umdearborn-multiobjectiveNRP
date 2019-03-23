package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.evaluation.Objective;

/**
 * Design efficiency in fulfilling the required functionality
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class QMOODEffectiveness extends AbstractQualityAttribute {

    public QMOODEffectiveness() {

    }

    public QMOODEffectiveness(QMOODEffectiveness objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double encapsulation = getRate(project, refactored, "Encapsulation");
        double polymorphism = getRate(project, refactored, "Polymorphism");
        double composition = getRate(project, refactored, "Composition");
        double inheritance = getRate(project, refactored, "Inheritance");
        double abstraction = getRate(project, refactored, "Abstraction");

        return 0.2 * abstraction + 0.2 * encapsulation + 0.2 * composition + 0.2 * inheritance + 0.2 * polymorphism;
    }

    @Override
    public double getValue(ProjectObject project) {

        double encapsulation = project.getDesignMetrics().get("Encapsulation");
        double polymorphism = project.getDesignMetrics().get("Polymorphism");
        double composition = project.getDesignMetrics().get("Composition");
        double inheritance = project.getDesignMetrics().get("Inheritance");
        double abstraction = project.getDesignMetrics().get("Abstraction");

        return 0.2 * abstraction + 0.2 * encapsulation + 0.2 * composition + 0.2 * inheritance + 0.2 * polymorphism;
    }

    @Override
    public String toString() {
        return "Effectiveness";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODEffectiveness(this);
    }
}
