package thiagodnf.doupr.evaluation.designmetrics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class implements the <b>Direct Class Coupling (DCC)</b> design metric.
 * Number of other classes a class relates to, either through a shared attribute
 * or a parameter in a method. Design Property: <i>Coupling</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODDirectClassCoupling extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Coupling";
    }

    @Override
    public String getAbbreviation() {
        return "DCC";
    }

    @Override
    public String toString() {
        return "Direct Class Coupling";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        @SuppressWarnings("unchecked")
        Map<String, String> classNames = (Map<String, String>) project.getAttributes().get("CLASSNAMES");

        // The non repeated classes
        Set<String> directClasses = new HashSet<>();

        // We should verify if some parameter of this class refers to
        // another class in the system under analysis

        for (AttributeObject attribute : cls.getAttributes()) {
            if (classNames.containsKey(attribute.getType())) {
                directClasses.add(attribute.getType());
            }
        }

        // We should verify if some attribute of some method refers to
        // another class in the system under analysis
        for (MethodObject method : cls.getMethods()) {
            if (method.isContructor()) {
                continue;
            }

            for (ParameterObject parameter : method.getParameters()) {
                if (classNames.containsKey(parameter.getType())) {
                    directClasses.add(parameter.getType());
                }
            }
        }

        return directClasses.size();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
