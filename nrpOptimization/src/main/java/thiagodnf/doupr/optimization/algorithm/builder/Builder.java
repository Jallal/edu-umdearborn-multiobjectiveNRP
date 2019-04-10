package thiagodnf.doupr.optimization.algorithm.builder;

import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.optimization.solution.Solution;

import java.util.List;

public abstract class Builder {

    protected int maxEvalutions;

    protected int populationSize;

    protected CrossoverOperator<Solution> crossover;

    protected MutationOperator<Solution> mutation;

    protected SelectionOperator<List<Solution>, Solution> selection;

    protected RefactoringProblem problem;

    protected List<Solution> initialPopulation;

    protected SolutionListEvaluator<Solution> evaluator;

    protected List<Solution> paretoFront;

    protected List<Objective> objectives;

    protected long computingTime;

    public CrossoverOperator<Solution> getCrossover() {
        return crossover;
    }

    public void setCrossover(CrossoverOperator<Solution> crossover) {
        this.crossover = crossover;
    }

    public MutationOperator<Solution> getMutation() {
        return mutation;
    }

    public void setMutation(MutationOperator<Solution> mutation) {
        this.mutation = mutation;
    }

    public SelectionOperator<List<Solution>, Solution> getSelection() {
        return selection;
    }

    public void setSelection(SelectionOperator<List<Solution>, Solution> selection) {
        this.selection = selection;
    }

    public RefactoringProblem getProblem() {
        return problem;
    }

    public void setProblem(RefactoringProblem problem) {
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

    public List<Solution> getInitialPopulation() {
        return initialPopulation;
    }

    public void setInitialPopulation(List<Solution> initialPopulation) {
        this.initialPopulation = initialPopulation;
    }

    public SolutionListEvaluator<Solution> getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(SolutionListEvaluator<Solution> evaluator) {
        this.evaluator = evaluator;
    }

    public List<Solution> getParetoFront() {
        return paretoFront;
    }

    public void setParetoFront(List<Solution> paretoFront) {
        this.paretoFront = paretoFront;
    }

    public long getComputingTime() {
        return computingTime;
    }

    public void setComputingTime(long computingTime) {
        this.computingTime = computingTime;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    @Override
    public String toString() {
        return "Builder [maxEvalutions=" + maxEvalutions + ", populationSize=" + populationSize + ", crossover="
                + crossover + ", mutation=" + mutation + ", selection=" + selection + ", problem=" + problem
                + ", initialPopulation=" + initialPopulation + ", evaluator=" + evaluator + ", paretoFront="
                + paretoFront + ", objectives=" + objectives + ", computingTime=" + computingTime + "]";
    }

    public abstract AbstractEvolutionaryAlgorithm<Solution, List<Solution>> build();
}
