package edu.umich.ISELab.optimization.algorithm;


import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.NrpSolution;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;

import java.util.List;

public abstract class AbstractAlgorithm {

    protected int maxEvalutions;

    protected int populationSize;

    protected CrossoverOperator<NrpSolution> crossover;

    protected MutationOperator<NrpSolution> mutation;

    protected SelectionOperator<List<NrpSolution>, NrpSolution> selection;

    protected NrpProblem problem;

    public CrossoverOperator<NrpSolution> getCrossover() {
        return crossover;
    }

    public void setCrossover(CrossoverOperator<NrpSolution> crossover) {
        this.crossover = crossover;
    }

    public MutationOperator<NrpSolution> getMutation() {
        return mutation;
    }

    public void setMutation(MutationOperator<NrpSolution> mutation) {
        this.mutation = mutation;
    }

    public SelectionOperator<List<NrpSolution>, NrpSolution> getSelection() {
        return selection;
    }

    public void setSelection(SelectionOperator<List<NrpSolution>, NrpSolution> selection) {
        this.selection = selection;
    }

    public NrpProblem getProblem() {
        return problem;
    }

    public void setProblem(NrpProblem problem) {
        this.problem = problem;
    }

    public int getMaxEvalutions() {
        return maxEvalutions;
    }

    public void setMaxEvalutions(int maxEvalutions) {
        this.maxEvalutions = maxEvalutions;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public abstract List<NrpSolution> execute();
}
