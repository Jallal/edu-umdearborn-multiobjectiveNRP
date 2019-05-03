package edu.umich.ISELab.evaluation.designmetrics;

/**
 * This class implements the <b>Average Number of Ancestors (ANA)</b> design
 * metric. It is the average number of classes in the inheritance tree for each class.
 * Design Property: <i>Abstraction</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODAverageNumberOfAncestors extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Abstraction";
    }

    @Override
    public String getAbbreviation() {
        return "ANA";
    }

    @Override
    public String toString() {
        return "Average Number of Ancestors";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {
        return cls.getSuperClasses().size();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
