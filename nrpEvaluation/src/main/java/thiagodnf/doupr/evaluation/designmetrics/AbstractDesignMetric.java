package thiagodnf.doupr.evaluation.designmetrics;

/**
 * This class implements the main methods that a Design Metric should implement
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-12
 */
public abstract class AbstractDesignMetric {

    public double calculate(ProjectObject project) {

        double counter = 0;

        double sum = 0;

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {

                double value = calculate(cls, project);

                cls.getDesignMetrics().put(getDesignProperty(), value);

                if (isValid(cls)) {
                    sum += value;
                    counter++;
                }
            }
        }

        project.getDesignMetrics().put(getDesignProperty(), sum / counter);

        return sum / counter;
    }

    public abstract double calculate(ClassObject cls, ProjectObject project);

    public abstract String getDesignProperty();

    public abstract String getAbbreviation();

    public abstract String toString();

    public abstract boolean isValid(ClassObject cls);
}
