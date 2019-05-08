package edu.umich.ISELab.evaluation.designmetrics;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;

public abstract class AbstractDesignMetric {

    public double calculate(Project project) {

       return 0.0;
    }


    public abstract String getDesignProperty();

    public abstract String getAbbreviation();

    public abstract String toString();

    public abstract boolean isValid(WorkItem item);
}
