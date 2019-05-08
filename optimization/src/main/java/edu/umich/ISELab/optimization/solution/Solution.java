package edu.umich.ISELab.optimization.solution;

import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.util.constants.SolutionConstants;
import edu.umich.ISELab.optimization.variables.Variable;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;

import java.util.HashMap;
import java.util.Map;

public abstract class Solution extends AbstractGenericSolution<Variable, Problem> {

    private static final long serialVersionUID = -4899717150317371608L;

    protected Solution(Problem problem) {
        super(problem);
    }

    /**
     * Copy Constructor
     *
     * @param solution The solution to be cloned
     */
    public Solution(Solution solution) {
        super(solution.problem);

        // Copy the variable values
        for (int i = 0; i < problem.getNumberOfVariables(); i++) {
            setVariableValue(i, solution.getVariableValue(i).copy());
        }

        // Copy the objective values
        for (int i = 0; i < problem.getNumberOfObjectives(); i++) {
            setObjective(i, solution.getObjective(i));
        }

        // Copy the attributes
        super.attributes = new HashMap<>(solution.attributes);
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }

    public Problem getProblem() {
        return this.problem;
    }

    public void setUserSelection() {
        setAttribute(SolutionConstants.USER_SELECTION, true);
    }

    public boolean getUserSelection() {
        return getAttribute(SolutionConstants.USER_SELECTION) != null;
    }

    public double getUserFeedback() {
        return 0.0;
    }

    public void clearAttributes() {
        this.getAttributes().clear();
    }

    /**
     * Copy a given solution. As each solution has its own behavior,
     * all derived classes should implement this method.
     */
    public abstract Solution copy();
}
