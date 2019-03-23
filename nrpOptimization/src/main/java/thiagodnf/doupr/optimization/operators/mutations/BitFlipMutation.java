package thiagodnf.doupr.optimization.operators.mutations;

import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.RandomUtils;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Bit Flip Mutation Class
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class BitFlipMutation implements MutationOperator<Solution> {

    private static final long serialVersionUID = -7861132327296903952L;

    /**
     * The probability that defines if a mutation will or not be applied
     */
    protected double probability;

    /**
     * Constructor
     *
     * @param probability The probability that defines if a mutation will or not be applied. The value should be between [0:1]
     */
    public BitFlipMutation(double probability) {
        // Verify the arguments
        checkArgument(probability >= 0.0, "The probability should be greater or equal than 0.0");
        checkArgument(probability <= 1.0, "The probability should be less or equal than 1.0");

        this.probability = probability;
    }

    public double getMutationProb() {
        return this.probability;
    }

    @Override
    public Solution execute(Solution solution) {
        // Verify the arguments
        checkNotNull(solution, "The solution should not be null");

        // Perform the mutation
        if (JMetalRandom.getInstance().nextDouble() <= probability) {
            doMutation(solution);
        }

        return solution;
    }

    /**
     * Perform the mutation operation
     *
     * @param solution The solution to mutate
     */
    public void doMutation(Solution solution) {

        // Create a refactoring variable for saving the list of refactorings
        RefactoringVariable variable = (RefactoringVariable) solution.getVariableValue(0);

        List<Refactoring> refactorings = variable.getRefactorings();

        if (refactorings.isEmpty()) {
            return;
        }

        RefactoringProblem problem = (RefactoringProblem) solution.getProblem();

        int numberOfChanges = 1;

        if (refactorings.size() >= 2) {
            numberOfChanges = RandomUtils.getRandomInteger(1, refactorings.size() / 2);
        }

        for (int i = 0; i < numberOfChanges; i++) {

            int position = RandomUtils.getRandomInteger(0, refactorings.size() - 1);

            // Instantiate the refactoring operation
            Refactoring refactoring = RandomUtils.getRandomRefactoring(problem.getSelectedRefactorings());

            // Add the refactoring in the variable
            refactorings.set(position, refactoring);
        }
    }
}
