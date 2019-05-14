package edu.umich.ISELab.optimization.algorithm;

import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

import java.util.Arrays;
import java.util.List;

public class CustomNSGAII extends NSGAII<Solution> {

    private static final long serialVersionUID = -3524738293523396125L;

    protected List<Solution> initialPopulation;
    protected int i = 0;

    public CustomNSGAII(
            Problem<Solution> problem,
            int maxEvaluations,
            int populationSize,
            CrossoverOperator<Solution> crossoverOperator,
            MutationOperator<Solution> mutationOperator,
            SelectionOperator<List<Solution>, Solution> selectionOperator,
            SolutionListEvaluator<Solution> evaluator,
            List<Solution> initialPopulation
    ) {
        super(problem, maxEvaluations, populationSize, crossoverOperator, mutationOperator, selectionOperator, evaluator);

        this.initialPopulation = initialPopulation;
    }

    protected List<Solution> createInitialPopulation() {

        if (this.initialPopulation == null) {
            return super.createInitialPopulation();

        } else {
            int needToFill = getMaxPopulationSize() - initialPopulation.size();
            for (int i = 0; i < needToFill; i++) {
                GroomingSolution newIndividual = (GroomingSolution) getProblem().createSolution();
                initialPopulation.add(newIndividual);
            }

            return initialPopulation;
        }
    }

    @Override
    protected void updateProgress() {
        super.updateProgress();

        		List<Integer> save = Arrays.asList(1, 5, 10, 50, 100, 500);

        		if(save.contains(i)){
        			System.out.println("Saving at "+i);
        			List<Solution> population = getNonDominatedSolutions(getPopulation());
        			new SolutionListOutput(population)
        	        .setSeparator("\t")
        	        .setVarFileOutputContext(new DefaultFileOutputContext("optimization/test/VAR_"+i+".tsv"))
        	        .setFunFileOutputContext(new DefaultFileOutputContext("optimization/test/FUN_"+i+".tsv"))
        	        .print();
        		}
        i++;
    }
}
