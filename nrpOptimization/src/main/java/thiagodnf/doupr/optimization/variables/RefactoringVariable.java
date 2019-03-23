package thiagodnf.doupr.optimization.variables;

import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RefactoringVariable extends Variable {

    protected List<Refactoring> refactorings;

    public RefactoringVariable(RefactoringVariable variable) {

        this.refactorings = new ArrayList<>();

        for (Refactoring refactoring : variable.getRefactorings()) {
            this.refactorings.add(refactoring.copy());
        }
    }

    public RefactoringVariable() {
        this.refactorings = new ArrayList<>();
    }

    public List<Refactoring> getRefactorings() {
        return refactorings;
    }

    public void setRefatorings(List<Refactoring> refactorings) {

        this.refactorings = refactorings;
    }

    @Override
    public String toString() {

        List<String> lines = new ArrayList<String>();

        for (int i = 0; i < refactorings.size(); i++) {
            lines.add(refactorings.get(i).toString());
        }

        return StringUtils.join(lines, ",");
    }

    public RefactoringVariable copy() {
        return new RefactoringVariable(this);
    }
}
