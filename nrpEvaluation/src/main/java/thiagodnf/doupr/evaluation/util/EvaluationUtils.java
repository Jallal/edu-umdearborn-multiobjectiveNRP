package thiagodnf.doupr.evaluation.util;

import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.designmetrics.AbstractDesignMetric;
import thiagodnf.doupr.evaluation.designmetrics.QMOODAverageNumberOfAncestors;
import thiagodnf.doupr.evaluation.designmetrics.QMOODClassInterfaceSize;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODEffectiveness;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODExtendibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFunctionality;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;

import java.util.ArrayList;
import java.util.List;

public class EvaluationUtils {

    public static List<AbstractDesignMetric> DESIGN_METRICS = new ArrayList<>();

    public static List<Objective> OBJECTIVES = new ArrayList<>();

    static {
        DESIGN_METRICS.add(new QMOODAverageNumberOfAncestors());
        DESIGN_METRICS.add(new QMOODClassInterfaceSize());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODCohesionAmongMethodOfClass());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODDataAccessMetric());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODDesignSizeInClasses());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODDirectClassCoupling());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODMeasureOfAggregation());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODMeasureOfFunctionalAbstraction());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODNumberOfHierarchies());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODNumberOfMethods());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.QMOODNumberOfPolymorphicMethods());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.StandardCohesion());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.StandardCoupling());
        DESIGN_METRICS.add(new thiagodnf.doupr.evaluation.designmetrics.StandardComplexity());
    }

    static {
        OBJECTIVES.add(new QMOODEffectiveness());
        OBJECTIVES.add(new QMOODExtendibility());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility());
        OBJECTIVES.add(new QMOODFunctionality());
        OBJECTIVES.add(new QMOODReusability());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.QMOODUnderstandability());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Coupling());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Cohesion());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Complexity());
        OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.NumberOfRefactorings());
    }
}
