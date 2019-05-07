package edu.umich.ISELab.optimization.operators.crossovers;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.optimization.solution.NrpSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.NrpVariable;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Single Point Crossover Class
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class SinglePointCrossover implements CrossoverOperator<Solution> {

    private static final long serialVersionUID = 1187246602354151626L;

    /**
     * The probability that defines if a crossover will or not be applied
     */
    protected double probability;

    /**
     * Constructor
     *
     * @param probability The probability that defines if a crossover will or not be
     *                    applied. The value should be between [0:1]
     */
    public SinglePointCrossover(double probability) {
        // Verify the arguments
        checkArgument(probability >= 0.0, "The probability should be greater or equal than 0.0");
        checkArgument(probability <= 1.0, "The probability should be less or equal than 1.0");

        this.probability = probability;
    }

    @Override
    public List<Solution> execute(List<Solution> solutions) {
        // Verify the arguments
        checkNotNull(solutions, "The solutions cannot be null");
        checkArgument(solutions.size() == 2, "The number of parents must be two instead of %s", solutions.size());

        // We perform the crossover with two parents
        return doCrossover(solutions.get(0), solutions.get(1));
    }

    /**
     * Method that performs the crossover between two solutions
     *
     * @param parent1 The first parent
     * @param parent2 The second parent
     * @return a list of offispring
     */
    public List<Solution> doCrossover(Solution parent1, Solution parent2) {

        // Verify the arguments
        checkNotNull(parent1, "The parent1 cannot be null");
        checkNotNull(parent2, "The parent2 cannot be null");

        // Before it starts, we need to do a copy of the parents
        List<Solution> offspring = new ArrayList<>(2);

        offspring.add((NrpSolution) parent1.copy());
        offspring.add((NrpSolution) parent2.copy());

        if (JMetalRandom.getInstance().nextDouble() < probability) {

            List<NrpBase> c1 = ((NrpVariable) offspring.get(0).getVariableValue(0)).getRefactorings();
            List<NrpBase> c2 = ((NrpVariable) offspring.get(1).getVariableValue(0)).getRefactorings();

            int minimum = Math.min(c1.size(), c2.size());

            if (minimum < 3) {
                return offspring;
            }

            int cut = JMetalRandom.getInstance().nextInt(1, minimum - 2);

            List<NrpBase> newC1 = new ArrayList<>();
            List<NrpBase> newC2 = new ArrayList<>();

            for (int i = 0; i < cut; i++) {
                newC1.add(c1.get(i));
            }

            for (int i = cut; i < c2.size(); i++) {
                newC1.add(c2.get(i));
            }

            for (int i = 0; i < cut; i++) {
                newC2.add(c2.get(i));
            }

            for (int i = cut; i < c1.size(); i++) {
                newC2.add(c1.get(i));
            }

            ((NrpVariable) offspring.get(0).getVariableValue(0)).setRefatorings(newC1);
            ((NrpVariable) offspring.get(1).getVariableValue(0)).setRefatorings(newC2);
        }

        return offspring;
    }

    /**
     * Two parents are required to apply this operator.
     *
     * @return the number of required parents
     */
    @Override
    public int getNumberOfParents() {
        return 2;
    }
}
