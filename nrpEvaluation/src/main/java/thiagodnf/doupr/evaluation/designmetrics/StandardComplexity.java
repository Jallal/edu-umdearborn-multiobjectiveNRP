package thiagodnf.doupr.evaluation.designmetrics;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

/**
 * The degree of understanding and the easiness of learning the design
 * implementation details
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-19
 */
public class StandardComplexity extends AbstractDesignMetric {

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

        if (!cls.hasMethods()) {
            return 0.0;
        }

        double localCoupling = cls.getDesignMetrics().get(new StandardCoupling().getDesignProperty());
        double localCohesion = cls.getDesignMetrics().get(new StandardCohesion().getDesignProperty());

        return (localCoupling + localCohesion) / (double) cls.getNumberOfMethods();
    }

    @Override
    public String getDesignProperty() {
        return "Standard Complexity";
    }

    @Override
    public String getAbbreviation() {
        return "COM";
    }

    @Override
    public String toString() {
        return "Complexity";
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
