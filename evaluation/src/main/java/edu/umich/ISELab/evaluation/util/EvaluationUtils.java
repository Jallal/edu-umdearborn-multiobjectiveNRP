package edu.umich.ISELab.evaluation.util;


import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.designmetrics.AbstractDesignMetric;
import edu.umich.ISELab.evaluation.designmetrics.groomingDesignMetric;
import edu.umich.ISELab.evaluation.qualityattributes.Optimization;

import java.util.ArrayList;
import java.util.List;

public class EvaluationUtils {

    public static List<AbstractDesignMetric> DESIGN_METRICS = new ArrayList<>();

    public static List<Objective> OBJECTIVES = new ArrayList<>();

    static {
        DESIGN_METRICS.add(new groomingDesignMetric());
    }

    static {
        OBJECTIVES.add(new Optimization());
    }
}
