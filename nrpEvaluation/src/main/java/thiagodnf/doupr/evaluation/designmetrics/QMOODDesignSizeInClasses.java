package thiagodnf.doupr.evaluation.designmetrics;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

import java.util.Map;

/**
 * This class implements the <b>Design Size in Classes (DSC)</b> design metric.
 * It is total number of classes in the design. Design Property: <i>Design
 * Size</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-17
 */
public class QMOODDesignSizeInClasses extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Design Size";
    }

    @Override
    public String getAbbreviation() {
        return "DSC";
    }

    @Override
    public String toString() {
        return "Design Size in Classes";
    }

    @SuppressWarnings("unchecked")
    @Override
    public double calculate(ClassObject cls, ProjectObject project) {
        return ((Map<String, String>) project.getAttributes().get("CLASSNAMES")).size();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
