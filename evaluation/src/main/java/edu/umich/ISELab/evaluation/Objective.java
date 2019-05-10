package edu.umich.ISELab.evaluation;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.Grooming;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Objective implements Serializable {

    protected Map<Object, Object> attributes;

    public Objective() {
        this.attributes = new HashMap<>();
    }

    public Objective(Objective objective) {
        this.attributes = new HashMap<>(objective.attributes);
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract double getValue(Project project);

    public abstract double getDiff(Project original, Project refactored);

    public abstract double calculate(Project original, Project refactored, List<Grooming> appliedRefactorings);

    public abstract boolean isMinimize();

    public abstract String toString();

    public abstract Objective copy();

    public abstract List<Objective> getObjectives();
}
