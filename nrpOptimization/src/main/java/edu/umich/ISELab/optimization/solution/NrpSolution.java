package edu.umich.ISELab.optimization.solution;

import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.variables.NrpVariable;

import java.util.List;

public class NrpSolution extends Solution{
    private static final long serialVersionUID = -146503776791357509L;

    /**
     * Constructor
     *
     * @param problem the problem that this solution is part of
     */
    public NrpSolution(Problem problem) {
        super(problem);
    }

    /**
     * Copy Constructor
     *
     * @param solution The solution to be cloned
     */
    public NrpSolution(NrpSolution solution) {
        super(solution);
    }

    public String getVariableValueString(int index) {
        return getVariableValue(index).toString();
    }

    public NrpSolution copy() {
        return new NrpSolution(this);
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

        List<NrpBase> refactorings = ((NrpVariable) getVariableValue(0)).getRefactorings();

        if (refactorings.isEmpty()) {
            return 0.0;
        }

        double userFeedback = 0.0;

        for (NrpBase nrpBase : refactorings) {
            userFeedback += nrpBase.getUserFeedback();
        }

        return userFeedback / (double) refactorings.size();
    }

    @Override
    public void clearAttributes() {
        super.clearAttributes();

        List<NrpBase> refactorings = ((NrpVariable) getVariableValue(0)).getRefactorings();

        for (NrpBase nrpBase : refactorings) {
            nrpBase.getProperties().clear();
        }
    }
}
