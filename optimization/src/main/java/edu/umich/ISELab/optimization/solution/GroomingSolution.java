package edu.umich.ISELab.optimization.solution;

import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.variables.GroomingVariable;

import java.util.List;

public class GroomingSolution extends Solution {
    private static final long serialVersionUID = -146503776791357509L;

    /**
     * Constructor
     *
     * @param problem the problem that this solution is part of
     */
    public GroomingSolution(Problem problem) {
        super(problem);
    }

    /**
     * Copy Constructor
     *
     * @param solution The solution to be cloned
     */
    public GroomingSolution(GroomingSolution solution) {
        super(solution);
    }

    public String getVariableValueString(int index) {
        return getVariableValue(index).toString();
    }

    public GroomingSolution copy() {
        return new GroomingSolution(this);
    }


    /*@Override
    public double getUserFeedback() {

        List<Grooming> refactorings = ((GroomingVariable) getVariableValue(0)).getRefactorings();

        if (refactorings.isEmpty()) {
            return 0.0;
        }

        double userFeedback = 0.0;

        for (Grooming nrpBase : refactorings) {
            userFeedback += nrpBase.getUserFeedback();
        }

        return userFeedback / (double) refactorings.size();
    }*/

    @Override
    public void clearAttributes() {
        super.clearAttributes();

        List<Grooming> groomings = ((GroomingVariable) getVariableValue(0)).getRefactorings();

        for (Grooming grooming : groomings) {
           grooming.reset();
        }
    }
}
