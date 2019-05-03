package edu.umich.ISELab.evaluation.designmetrics;

import edu.umich.ISELab.core.util.ProjectObjectUtils;

/**
 * This class implements the <b>Measure of Functional Abstraction (MFA)</b>
 * design metric. It is the number of methods declared in a class. Ratio of the
 * number of inherited methods per the total number of methods within a class
 * Design Property: <i>Inheritance</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODMeasureOfFunctionalAbstraction extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Inheritance";
    }

    @Override
    public String getAbbreviation() {
        return "MFA";
    }

    @Override
    public String toString() {
        return "Measure of Functional Abstraction";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        int numInheritedMethods = 0;

        for (String className : cls.getSuperClasses()) {

            ClassObject superCls = ProjectObjectUtils.findByType(project, className);

            for (MethodObject method : superCls.getMethods()) {

                // Ignore the constructor
                if (method.isContructor()) {
                    continue;
                }

                // Ignore the static methods
                if (method.isStatic()) {
                    continue;
                }

                if (!method.isPrivate()) {
                    numInheritedMethods++;
                }
            }
        }

        int methodsInClass = cls.getNumberOfMethods();

        int totalNumMethodsInClass = methodsInClass + numInheritedMethods;

        if (totalNumMethodsInClass == 0) {
            return 0.0;
        }

        return (double) numInheritedMethods / (double) totalNumMethodsInClass;
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
