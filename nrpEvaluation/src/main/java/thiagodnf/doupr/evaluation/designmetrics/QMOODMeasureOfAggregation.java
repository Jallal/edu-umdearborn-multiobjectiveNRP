package thiagodnf.doupr.evaluation.designmetrics;

import java.util.Map;

/**
 * This class implements the <b>Measure Of Aggregation (MOA)</b> design metric.
 * It counts of number of attributes whose type is user defined class(es).
 * Design Property: <i>Composition</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-17
 */
public class QMOODMeasureOfAggregation extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Composition";
    }

    @Override
    public String getAbbreviation() {
        return "MOA";
    }

    @Override
    public String toString() {
        return "Measure Of Aggregation";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        @SuppressWarnings("unchecked")
        Map<String, String> classNames = (Map<String, String>) project.getAttributes().get("CLASSNAMES");

        int userDefinedClass = 0;

        for (AttributeObject attribute : cls.getAttributes()) {
            if (classNames.containsKey(attribute.getType())) {
                userDefinedClass++;
            }
        }

        return userDefinedClass;
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
