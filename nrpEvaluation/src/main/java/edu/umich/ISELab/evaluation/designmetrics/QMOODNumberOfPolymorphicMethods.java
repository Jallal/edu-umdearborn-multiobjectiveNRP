package edu.umich.ISELab.evaluation.designmetrics;

/**
 * This class implements the <b>Number of Polymorphic Methods (NOP)</b> design
 * metric. It is Any method that can be used by a class and its descendants.
 * Counts of the number of methods in a class excluding private, static and
 * final ones. Design Property: <i>Polymorphism</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODNumberOfPolymorphicMethods extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Polymorphism";
    }

    @Override
    public String getAbbreviation() {
        return "NOP";
    }

    @Override
    public String toString() {
        return "Number of Polymorphic Methods";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        int numberOfMethods = 0;

        for (MethodObject method : cls.getMethods()) {

            if (method.isContructor()) {
                continue; // Ignore the constructor method
            }

            if (method.isStatic()) {
                continue; // Ignore the static methods
            }

            if (method.isPrivate()) {
                continue; // Ignore the private methods
            }

            numberOfMethods++;
        }

//		return numberOfMethods;
        if (cls.getNumberOfMethods() == 0) return numberOfMethods;
        return numberOfMethods / cls.getNumberOfMethods(); //TODO change of polymorphism -vahid
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
