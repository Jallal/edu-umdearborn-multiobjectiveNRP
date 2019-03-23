package thiagodnf.doupr.evaluation.qualityattributes;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;

import java.util.List;

public class NumberOfRefactorings extends Objective {

    public NumberOfRefactorings() {

    }

    public NumberOfRefactorings(NumberOfRefactorings objective) {
        super(objective);
    }

    @Override
    public double getDiff(ProjectObject project, ProjectObject refactored) {
        return 0;
    }

    @Override
    public double getValue(ProjectObject project) {
        return 0;
    }

    public double calculate(ProjectObject original, ProjectObject refactored, List<Refactoring> appliedRefactorings) {

        if (isMinimize()) {
            return (appliedRefactorings.size());
        }

        return -1.0 * (appliedRefactorings.size());
    }

    @Override
    public boolean isMinimize() {
        return true;
    }

    @Override
    public String toString() {
        return "Number Of Refactorings";
    }

    @Override
    public Objective copy() {
        return new NumberOfRefactorings(this);
    }
}
