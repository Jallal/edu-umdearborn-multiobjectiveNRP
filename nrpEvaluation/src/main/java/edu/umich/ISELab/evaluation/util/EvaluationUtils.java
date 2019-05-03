package edu.umich.ISELab.evaluation.util;

import edu.umich.ISELab.evaluation.designmetrics.QMOODCohesionAmongMethodOfClass;
import edu.umich.ISELab.evaluation.designmetrics.QMOODDataAccessMetric;
import edu.umich.ISELab.evaluation.designmetrics.QMOODDesignSizeInClasses;
import edu.umich.ISELab.evaluation.designmetrics.QMOODDirectClassCoupling;
import edu.umich.ISELab.evaluation.designmetrics.QMOODMeasureOfAggregation;
import edu.umich.ISELab.evaluation.designmetrics.QMOODMeasureOfFunctionalAbstraction;
import edu.umich.ISELab.evaluation.designmetrics.QMOODNumberOfHierarchies;
import edu.umich.ISELab.evaluation.designmetrics.QMOODNumberOfMethods;
import edu.umich.ISELab.evaluation.designmetrics.QMOODNumberOfPolymorphicMethods;
import edu.umich.ISELab.evaluation.designmetrics.StandardCohesion;
import edu.umich.ISELab.evaluation.designmetrics.StandardComplexity;
import edu.umich.ISELab.evaluation.designmetrics.StandardCoupling;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFlexibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODUnderstandability;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.designmetrics.AbstractDesignMetric;
import edu.umich.ISELab.evaluation.designmetrics.QMOODAverageNumberOfAncestors;
import edu.umich.ISELab.evaluation.designmetrics.QMOODClassInterfaceSize;
import edu.umich.ISELab.evaluation.qualityattributes.NumberOfNRPOptimization;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODEffectiveness;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODExtendibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFunctionality;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODReusability;

import java.util.ArrayList;
import java.util.List;

public class EvaluationUtils {

    public static List<AbstractDesignMetric> DESIGN_METRICS = new ArrayList<>();

    public static List<Objective> OBJECTIVES = new ArrayList<>();

    static {
        DESIGN_METRICS.add(new QMOODAverageNumberOfAncestors());
        DESIGN_METRICS.add(new QMOODClassInterfaceSize());
        DESIGN_METRICS.add(new QMOODCohesionAmongMethodOfClass());
        DESIGN_METRICS.add(new QMOODDataAccessMetric());
        DESIGN_METRICS.add(new QMOODDesignSizeInClasses());
        DESIGN_METRICS.add(new QMOODDirectClassCoupling());
        DESIGN_METRICS.add(new QMOODMeasureOfAggregation());
        DESIGN_METRICS.add(new QMOODMeasureOfFunctionalAbstraction());
        DESIGN_METRICS.add(new QMOODNumberOfHierarchies());
        DESIGN_METRICS.add(new QMOODNumberOfMethods());
        DESIGN_METRICS.add(new QMOODNumberOfPolymorphicMethods());
        DESIGN_METRICS.add(new StandardCohesion());
        DESIGN_METRICS.add(new StandardCoupling());
        DESIGN_METRICS.add(new StandardComplexity());
    }

    static {
        OBJECTIVES.add(new QMOODEffectiveness());
        OBJECTIVES.add(new QMOODExtendibility());
        OBJECTIVES.add(new QMOODFlexibility());
        OBJECTIVES.add(new QMOODFunctionality());
        OBJECTIVES.add(new QMOODReusability());
        OBJECTIVES.add(new QMOODUnderstandability());
       // OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Coupling());
        //OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Cohesion());
        //OBJECTIVES.add(new thiagodnf.doupr.evaluation.qualityattributes.Complexity());
        OBJECTIVES.add(new NumberOfNRPOptimization());
    }
}
