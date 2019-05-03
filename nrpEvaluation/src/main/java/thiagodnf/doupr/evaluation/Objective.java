package thiagodnf.doupr.evaluation;

import thiagodnf.doupr.core.refactoring.NrpBase;

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

    public abstract double getValue(ProjectObject project);

    public abstract double getDiff(ProjectObject original, ProjectObject refactored);

    public abstract double calculate(ProjectObject original, ProjectObject refactored, List<NrpBase> appliedRefactorings);

    public abstract boolean isMinimize();

    public abstract String toString();

    public abstract Objective copy();
}
