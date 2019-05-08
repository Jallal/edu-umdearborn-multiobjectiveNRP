package edu.umich.ISELab.optimization.solution;

import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.variables.NrpVariable;
import edu.umich.ISELab.core.grooming.grooming;

import java.util.List;

public class NrpSolution extends Solution {
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


    /*@Override
    public double getUserFeedback() {

        List<grooming> refactorings = ((NrpVariable) getVariableValue(0)).getRefactorings();

        if (refactorings.isEmpty()) {
            return 0.0;
        }

        double userFeedback = 0.0;

        for (grooming nrpBase : refactorings) {
            userFeedback += nrpBase.getUserFeedback();
        }

        return userFeedback / (double) refactorings.size();
    }*/

    @Override
    public void clearAttributes() {
        super.clearAttributes();

        List<grooming> refactorings = ((NrpVariable) getVariableValue(0)).getRefactorings();

        for (grooming grooming : refactorings) {
           grooming.reset();
        }
    }
}
