package thiagodnf.doupr.optimization.algorithm;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;
import thiagodnf.doupr.optimization.solution.RefactoringSolution;

import java.util.List;

public abstract class AbstractAlgorithm {

    protected int maxEvalutions;

    protected int populationSize;

    protected CrossoverOperator<RefactoringSolution> crossover;

    protected MutationOperator<RefactoringSolution> mutation;

    protected SelectionOperator<List<RefactoringSolution>, RefactoringSolution> selection;

    protected RefactoringProblem problem;

    public CrossoverOperator<RefactoringSolution> getCrossover() {
        return crossover;
    }

    public void setCrossover(CrossoverOperator<RefactoringSolution> crossover) {
        this.crossover = crossover;
    }

    public MutationOperator<RefactoringSolution> getMutation() {
        return mutation;
    }

    public void setMutation(MutationOperator<RefactoringSolution> mutation) {
        this.mutation = mutation;
    }

    public SelectionOperator<List<RefactoringSolution>, RefactoringSolution> getSelection() {
        return selection;
    }

    public void setSelection(SelectionOperator<List<RefactoringSolution>, RefactoringSolution> selection) {
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

    public abstract List<RefactoringSolution> execute();
}
