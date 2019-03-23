package thiagodnf.doupr.optimization.solution;

import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

import java.util.List;

public class RefactoringSolution extends Solution {

    private static final long serialVersionUID = -146503776791357509L;

    /**
     * Constructor
     *
     * @param problem the problem that this solution is part of
     */
    public RefactoringSolution(Problem problem) {
        super(problem);
    }

    /**
     * Copy Constructor
     *
     * @param solution The solution to be cloned
     */
    public RefactoringSolution(RefactoringSolution solution) {
        super(solution);
    }

    public String getVariableValueString(int index) {
        return getVariableValue(index).toString();
    }

    public RefactoringSolution copy() {
        return new RefactoringSolution(this);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < getNumberOfVariables(); i++) {
            builder.append(getVariableValueString(i));
        }

        builder.append("\n");
        builder.append("Objectives: (");

        for (int i = 0; i < getNumberOfObjectives(); i++) {

            builder.append(getObjective(i));

            if (i + 1 != getNumberOfObjectives()) {
                builder.append(",");
            }
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public double getUserFeedback() {

        List<Refactoring> refactorings = ((RefactoringVariable) getVariableValue(0)).getRefactorings();

        if (refactorings.isEmpty()) {
            return 0.0;
        }

        double userFeedback = 0.0;

        for (Refactoring refactoring : refactorings) {
            userFeedback += refactoring.getUserFeedback();
        }

        return userFeedback / (double) refactorings.size();
    }

    @Override
    public void clearAttributes() {
        super.clearAttributes();

        List<Refactoring> refactorings = ((RefactoringVariable) getVariableValue(0)).getRefactorings();

        for (Refactoring refactoring : refactorings) {
            refactoring.getProperties().clear();
        }
    }
}
