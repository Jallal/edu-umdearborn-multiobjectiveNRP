package edu.umich.ISELab.evaluation.qualityattributes;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.evaluation.Objective;

import java.util.ArrayList;
import java.util.List;

public class Optimization extends AbstractQualityAttribute {
    public Optimization() {

    }

    @Override
    public double getValue(Project project) {
        return 0;
    }

    public Optimization(Optimization cohesion) {
        super(cohesion);
    }

    @Override
    public double getDiff(Project project, Project refactored) {
        return getRate(project, refactored, "Number Of Refactorings");
    }

    @Override
    public String toString() {
        return "Optimization";
    }

    @Override
    public boolean isMinimize() {
        return false;
    }

    @Override
    public Objective copy() {
        return new Optimization(this);
    }

    @Override
    public List<Objective> getObjectives(){
        List<Objective> objectives = new ArrayList<>();
        objectives.add(this);
        return objectives;
    }


}
