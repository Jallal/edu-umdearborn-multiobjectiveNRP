package edu.umich.ISELab.optimization.algorithm;


import edu.umich.ISELab.optimization.problem.GroomingProblem;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;

import java.util.List;

public abstract class AbstractAlgorithm {

    protected int maxEvalutions;

    protected int populationSize;

    protected CrossoverOperator<GroomingSolution> crossover;

    protected MutationOperator<GroomingSolution> mutation;

    protected SelectionOperator<List<GroomingSolution>, GroomingSolution> selection;

    protected GroomingProblem problem;

    public CrossoverOperator<GroomingSolution> getCrossover() {
        return crossover;
    }

    public void setCrossover(CrossoverOperator<GroomingSolution> crossover) {
        this.crossover = crossover;
    }

    public MutationOperator<GroomingSolution> getMutation() {
        return mutation;
    }

    public void setMutation(MutationOperator<GroomingSolution> mutation) {
        this.mutation = mutation;
    }

    public SelectionOperator<List<GroomingSolution>, GroomingSolution> getSelection() {
        return selection;
    }

    public void setSelection(SelectionOperator<List<GroomingSolution>, GroomingSolution> selection) {
        this.selection = selection;
    }

    public GroomingProblem getProblem() {
        return problem;
    }

    public void setProblem(GroomingProblem problem) {
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

    public abstract List<GroomingSolution> execute();
}
