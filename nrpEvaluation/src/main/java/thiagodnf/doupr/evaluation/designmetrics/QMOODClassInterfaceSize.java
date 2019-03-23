package thiagodnf.doupr.evaluation.designmetrics;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;

/**
 * This class implements the <b>Class Interface Size (CIS)</b> design metric. It
 * is the number of public methods in class. Design Property: <i>Messaging</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-17
 */
public class QMOODClassInterfaceSize extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Messaging";
    }

    @Override
    public String getAbbreviation() {
        return "CIS";
    }

    @Override
    public String toString() {
        return "Class Interface Size";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        int publicMethods = 0;

        for (MethodObject method : cls.getMethods()) {
            if (method.isContructor()) {
                continue;
            }

            if (method.isPublic()) {
                publicMethods++;
            }
        }

        return publicMethods;
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
