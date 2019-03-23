package thiagodnf.doupr.evaluation.designmetrics;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

import java.util.List;

/**
 * This class implements the <b>Data Access Metric (DAM)</b> design metric. It
 * is the ratio of the number of private and protected attributes to the total
 * number of attributes in a class. Design Property: <i>Encapsulation</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODDataAccessMetric extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Encapsulation";
    }

    @Override
    public String getAbbreviation() {
        return "DAM";
    }

    @Override
    public String toString() {
        return "Data Access Metric";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        List<AttributeObject> attributes = cls.getAttributes();

        if (attributes.isEmpty()) {
            return 0.0;
        }

        int nonPublicAttributes = 0;

        for (AttributeObject attribute : attributes) {

            if (!attribute.isPublic()) {
                nonPublicAttributes++;
            }
        }

        return (double) nonPublicAttributes / (double) attributes.size();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return cls.hasAttributes();
    }
}
