package edu.umich.ISELab.evaluation.qualityattributes;

import edu.umich.ISELab.evaluation.Objective;

/**
 * The degree of allowance of changes in the design
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class QMOODFlexibility extends AbstractQualityAttribute {

    public QMOODFlexibility() {

    }

    public QMOODFlexibility(QMOODFlexibility objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {

        double encapsulation = getRate(project, refactored, "Encapsulation");
        double coupling = getRate(project, refactored, "Coupling");
        double composition = getRate(project, refactored, "Composition");
        double polymorphism = getRate(project, refactored, "Polymorphism");

        return 0.25 * encapsulation - 0.25 * coupling + 0.5 * composition + 0.5 * polymorphism;
    }

    @Override
    public double getValue(ProjectObject project) {

        double encapsulation = project.getDesignMetrics().get("Encapsulation");
        double coupling = project.getDesignMetrics().get("Coupling");
        double composition = project.getDesignMetrics().get("Composition");
        double polymorphism = project.getDesignMetrics().get("Polymorphism");

        return 0.25 * encapsulation - 0.25 * coupling + 0.5 * composition + 0.5 * polymorphism;
    }

    @Override
    public String toString() {
        return "Flexibility";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new QMOODFlexibility(this);
    }
}
