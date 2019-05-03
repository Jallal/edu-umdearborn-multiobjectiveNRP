package edu.umich.ISELab.optimization.algorithm;

import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.util.ReferencePoint;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class CustomNSGAIII extends NSGAIII<Solution> {

    private static final long serialVersionUID = 5429624678165794076L;

    protected List<Solution> initialPopulation;

    public CustomNSGAIII(
            Problem<Solution> problem,
            int maxEvaluations,
            int populationSize,
            CrossoverOperator<Solution> crossoverOperator,
            MutationOperator<Solution> mutationOperator,
            SelectionOperator<List<Solution>, Solution> selectionOperator,
            SolutionListEvaluator<Solution> evaluator,
            List<Solution> initialPopulation
    ) {
        super(getBuilder(problem, maxEvaluations, populationSize, crossoverOperator, mutationOperator, selectionOperator, evaluator));

        this.initialPopulation = initialPopulation;

        // Create the weight vector

        int numberOfObjectives = problem.getNumberOfObjectives();

//		String filename = "src/main/resources/weights/" + numberOfObjectives + "-obj.txt";
        String filename = "sbse-Refactoring/doupr-execution/src/main/resources/weights/" + numberOfObjectives + "-obj.txt";

        File file = new File(filename);

        List<double[]> weights = null;

        try {
            weights = FileReaderUtils.readWeights(file);
        } catch (IOException e) {
            try {
                weights = FileReaderUtils.readWeights(new File("input" + File.separator + "weights" + File.separator + numberOfObjectives + "-obj.txt"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        this.referencePoints = getReferecePoints(weights, numberOfObjectives);

        int newPopulationSize = populationSize;

        while (newPopulationSize % 4 > 0) {
            newPopulationSize++;
        }

        setMaxPopulationSize(newPopulationSize);
    }

    protected static NSGAIIIBuilder<Solution> getBuilder(
            Problem<Solution> problem,
            int maxEvaluations,
            int populationSize,
            CrossoverOperator<Solution> crossoverOperator,
            MutationOperator<Solution> mutationOperator,
            SelectionOperator<List<Solution>,
                    Solution> selectionOperator,
            SolutionListEvaluator<Solution> evaluator
    ) {

        NSGAIIIBuilder<Solution> builder = new NSGAIIIBuilder<>(problem);

        builder.setMaxIterations(maxEvaluations);
        builder.setPopulationSize(populationSize);
        builder.setCrossoverOperator(crossoverOperator);
        builder.setMutationOperator(mutationOperator);
        builder.setSelectionOperator(selectionOperator);
        builder.setSolutionListEvaluator(evaluator);

        return builder;
    }

    protected Vector<ReferencePoint<Solution>> getReferecePoints(List<double[]> weights, int numberOfObjectives) {

        Vector<ReferencePoint<Solution>> referencePoints = new Vector<>();

        for (double[] array : weights) {

            ReferencePoint<Solution> point = new ReferencePoint<>(numberOfObjectives);

            for (int i = 0; i < array.length; i++) {
                point.position.set(i, array[i]);
            }

            referencePoints.add(point);
        }

        return referencePoints;
    }

    @Override
    protected List<Solution> createInitialPopulation() {

        if (this.initialPopulation == null) {
            return super.createInitialPopulation();
        }

        return initialPopulation;
    }

    @Override
    protected void updateProgress() {
        super.updateProgress();

        //System.out.println("Oi");


        // get the current population
        // population

//		getPopulation();
        //System.out.println(i);
    }
}
