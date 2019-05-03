package thiagodnf.doupr.evaluation.designmetrics;

import java.util.List;
import java.util.Map;

/**
 * The degree of understanding and the easiness of learning the design
 * implementation details
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class StandardCoupling extends AbstractDesignMetric {

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        @SuppressWarnings("unchecked")
        Map<String, ClassObject> map = (Map<String, ClassObject>) project.getAttributes().get("MAP_ID_TO_CLASS");

        double localCoupling = 0.0;

        for (MethodObject m : cls.getMethods()) {

            List<String> targetIds = project.getCallGraph().getCallsFrom(m.getIdentifier());

            for (String targetId : targetIds) {

                if (!map.get(targetId).getName().equalsIgnoreCase(cls.getName())) {
                    localCoupling++;
                }
            }
        }

        return localCoupling;
    }

    @Override
    public String getDesignProperty() {
        return "Standard Coupling";
    }

    @Override
    public String getAbbreviation() {
        return "COU";
    }

    @Override
    public String toString() {
        return "Coupling";
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
