package thiagodnf.doupr.evaluation.designmetrics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class implements the <b>Cohesion Among Methods in Class (CAM)</b> design
 * metric. It computes the relatedness among methods of a class based upon the
 * parameter list of the methods. Design Property: <i>Cohesion</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODCohesionAmongMethodOfClass extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Cohesion";
    }

    @Override
    public String getAbbreviation() {
        return "CAM";
    }

    @Override
    public String toString() {
        return "Cohesion Among Methods Of Class";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        // The non repeated parameters
        Set<String> differentParameters = new HashSet<>();

        List<MethodObject> methods = cls.getMethods();

        if (methods.isEmpty()) {
            return 0.0;
        }

        for (MethodObject method : methods) {
            for (ParameterObject parameter : method.getParameters()) {
                differentParameters.add(parameter.toString());
            }
        }

        if (differentParameters.isEmpty()) {
            return 0.0;
        }

        int numParameterTypes = 0;

        for (MethodObject method : methods) {
            numParameterTypes += getNumberOfDifferentParameterTypes(method);
        }

        int numberOfMethods = methods.size();

        return (double) numParameterTypes / ((double) numberOfMethods * (double) differentParameters.size());
    }

    public int getNumberOfDifferentParameterTypes(MethodObject method) {

        if (method.isContructor()) {
            return 0;
        }

        Set<String> differentParameters = new HashSet<>();

        for (ParameterObject parameter : method.getParameters()) {
            differentParameters.add(parameter.toString());
        }

        return differentParameters.size();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
