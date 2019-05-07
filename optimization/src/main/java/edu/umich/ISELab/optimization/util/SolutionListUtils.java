package edu.umich.ISELab.optimization.util;

import edu.umich.ISELab.optimization.solution.Solution;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SolutionListUtils {

    private SolutionListUtils() {
    }

    /**
     * Copy a list of solutions
     *
     * @param solutions list of solutions should be copied
     * @return a copy of the list of solutions
     */
    public static List<Solution> copy(List<Solution> solutions) {

        checkNotNull(solutions, "The list of solutions cannot be null");

        List<Solution> copy = new ArrayList<>();

        for (Solution solution : solutions) {
            copy.add(solution.copy());
        }

        return copy;
    }

    /**
     * Clear a set of attributes in a list of solutions
     *
     * @param solutions list of solutions to be cleaned
     */
    public static void clearAttributes(List<Solution> solutions) {

        checkNotNull(solutions, "The list of solutions cannot be null");

        for (Solution solution : solutions) {
            solution.clearAttributes();
        }
    }

    /**
     * Remove the repeated solutions. To do that, we take into account
     * the objective values into a solution. If the list contains solutions
     * with the same objective values we removed it
     *
     * @param solutions list of solutions we have to remove the repeated one
     * @return a new list contains just non-repeated solutions.
     */
    public static List<Solution> removeRepeated(List<Solution> solutions) {

        checkNotNull(solutions, "The list of solutions cannot be null");

        List<Solution> nonRepeated = new ArrayList<>();

        for (Solution solution : solutions) {

            if (!contains(nonRepeated, solution)) {
                nonRepeated.add(solution);
            }
        }

        return nonRepeated;
    }

    /**
     * This method verifies if a given solution is in a list of solutions.
     * We taking into account the objective values for considering if a
     * solution is in the list.
     *
     * @param solutions list of solutions
     * @param s         a given solution
     * @return true if the list of solutions contains the given solution. False, otherwise
     */
    public static boolean contains(List<Solution> solutions, Solution s) {

        checkNotNull(solutions, "The list of solutions cannot be null");
        checkNotNull(s, "The solution 's' cannot be null");

        for (Solution solution : solutions) {

            if (equals(s, solution)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method verifies if two solutions have the same value for
     * all objectives. If they have then they are equals
     *
     * @param a the solution 'a'
     * @param b the solution 'b'
     * @return true if the solution are the same. False, otherwise.
     */
    public static boolean equals(Solution a, Solution b) {

        checkNotNull(a, "The solution 'a' cannot be null");
        checkNotNull(b, "The solution 'b' cannot be null");

        if (a.getNumberOfObjectives() != b.getNumberOfObjectives()) {
            return false;
        }

        for (int i = 0; i < a.getNumberOfObjectives(); i++) {

            if (a.getObjective(i) != b.getObjective(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method returns a list of solutions that were selected
     * by the user
     *
     * @param solutions the list of solutions
     * @return a list of solutions selected by the user
     */
    public static List<Solution> getSolutionsSelectedByTheUser(List<Solution> solutions) {

        checkNotNull(solutions, "The list of solutions cannot be null");

        List<Solution> selected = new ArrayList<>();

        for (Solution solution : solutions) {

            if (solution.getUserSelection()) {
                selected.add(solution);
            }
        }

        return selected;
    }
}
