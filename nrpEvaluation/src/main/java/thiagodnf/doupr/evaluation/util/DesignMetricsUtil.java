package thiagodnf.doupr.evaluation.util;

import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.evaluation.designmetrics.AbstractDesignMetric;

import java.util.HashMap;
import java.util.Map;

public class DesignMetricsUtil {

    public static Map<String, Double> calculate(ProjectObject project) {

        project.getAttributes().put("MAP_ID_TO_ELEMENT", ProjectObjectUtils.mapElement(project));
        project.getAttributes().put("MAP_ID_TO_CLASS", ProjectObjectUtils.mapParent(project));
        project.getAttributes().put("CLASSNAMES", ProjectObjectUtils.getClassNames(project));

        Map<String, Double> sum = new HashMap<>();
        Map<String, Double> counter = new HashMap<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {

                for (AbstractDesignMetric metric : EvaluationUtils.DESIGN_METRICS) {

                    String designProperty = metric.getDesignProperty();

                    double value = metric.calculate(cls, project);

                    cls.getDesignMetrics().put(designProperty, value);

                    if (!sum.containsKey(designProperty)) {
                        sum.put(designProperty, 0.0);
                    }

                    if (!counter.containsKey(designProperty)) {
                        counter.put(designProperty, 0.0);
                    }

                    if (metric.isValid(cls)) {
                        sum.put(designProperty, sum.get(designProperty) + value);
                        counter.put(designProperty, counter.get(designProperty) + 1);
                    }
                }
            }
        }

        Map<String, Double> designMetrics = new HashMap<>();

        for (AbstractDesignMetric metric : EvaluationUtils.DESIGN_METRICS) {

            String designProperty = metric.getDesignProperty();

            designMetrics.put(designProperty, sum.get(designProperty) / counter.get(designProperty));
        }

        return designMetrics;
    }

    public static double rate(double oldValue, double newValue) {

        if (oldValue == 0.0) {
            return (newValue - oldValue);
        }

        return (newValue - oldValue) / Math.abs(oldValue);
    }
}
