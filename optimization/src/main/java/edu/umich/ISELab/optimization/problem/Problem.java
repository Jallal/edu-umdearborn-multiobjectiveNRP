package edu.umich.ISELab.optimization.problem;


import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;
import edu.umich.ISELab.evaluation.Objective;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Problem extends AbstractGenericProblem<Solution> {

    private static final long serialVersionUID = -2250578013425191599L;

    /**
     * List of objectives used to optimize the solution
     */
    protected List<Objective> objectives;

    public Problem(List<Objective> objectives) {

        checkNotNull(objectives, "The list of objective cannot be null");
        checkArgument(!objectives.isEmpty(), "The list of objective cannot be empty");

        this.objectives = objectives;

        setNumberOfObjectives(objectives.size());
        setName(getName());
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    /**
     * Return the name of the addressed problem
     */
    public abstract String getName();
}
