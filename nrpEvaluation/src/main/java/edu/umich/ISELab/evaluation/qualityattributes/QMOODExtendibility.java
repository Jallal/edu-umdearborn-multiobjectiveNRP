package edu.umich.ISELab.evaluation.qualityattributes;

import edu.umich.ISELab.evaluation.Objective;

/**
 * Measurement of design's allowance to incorporate new functional requirements.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class QMOODExtendibility extends AbstractQualityAttribute {

    public QMOODExtendibility() {

    }

    public QMOODExtendibility(QMOODExtendibility objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double coupling = getRate(project, refactored, "Coupling");
        double polymorphism = getRate(project, refactored, "Polymorphism");
        double abstraction = getRate(project, refactored, "Abstraction");
        double inheritance = getRate(project, refactored, "Inheritance");

        return 0.5 * abstraction - 0.5 * coupling + 0.5 * inheritance + 0.5 * polymorphism;
    }

    @Override
    public double getValue(ProjectObject project) {

        double coupling = project.getDesignMetrics().get("Coupling");
        double polymorphism = project.getDesignMetrics().get("Polymorphism");
        double abstraction = project.getDesignMetrics().get("Abstraction");
        double inheritance = project.getDesignMetrics().get("Inheritance");

        return 0.5 * abstraction - 0.5 * coupling + 0.5 * inheritance + 0.5 * polymorphism;
    }

    @Override
    public String toString() {
        return "Extendibility";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODExtendibility(this);
    }
}
