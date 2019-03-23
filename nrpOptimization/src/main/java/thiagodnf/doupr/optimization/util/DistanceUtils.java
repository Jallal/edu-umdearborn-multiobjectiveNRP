package thiagodnf.doupr.optimization.util;

import org.apache.commons.text.similarity.LevenshteinDistance;
import thiagodnf.doupr.optimization.solution.Solution;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This utility class calculates several distances such as Euclidean Distance.
 *
 * @author Thiago Ferreira
 * @version 1.0.0
 * @since 2017-10-09
 */
public class DistanceUtils {

    /**
     * This method calculates the weighted euclidean distance between two given solutions
     * by using 1.0 as weight for each dimension.
     *
     * @param s1 solution one
     * @param s2 solution two
     * @return the euclidean distance between two solutions
     */
    public static double euclideanDistance(Solution s1, Solution s2) {
        return euclideanDistance(s1, s2, 1.0);
    }


    /**
     * This method calculates the weighted euclidean distance between two given solutions
     * by using a specific value for each dimension.
     *
     * @param s1     solution one
     * @param s2     solution two
     * @param weight the weight applied in all dimensions
     * @return the euclidean distance between two solutions
     */
    public static double euclideanDistance(Solution s1, Solution s2, double weight) {
        return euclideanDistance(s1, s2, ArraysUtils.fill(s1.getNumberOfObjectives(), weight));
    }

    /**
     * This method calculates the weighted euclidean distance between two given solutions
     * by using an weight for each dimension. To use that, the solutions and the weights
     * should have the same size, that is, the same number of objectives
     *
     * @param s1      solution one
     * @param s2      solution two
     * @param weights the array by containing the weights
     * @return the euclidean distance between two solutions
     */
    public static double euclideanDistance(Solution s1, Solution s2, double[] weights) {

        checkNotNull(s1, "The solution s1 cannot be null");
        checkNotNull(s2, "The solution s2 cannot be null");
        checkNotNull(weights, "The weights cannot be null");
        checkArgument(s1.getNumberOfObjectives() == s2.getNumberOfObjectives(), "The number of objectives should be the same");
        checkArgument(s1.getNumberOfObjectives() == weights.length, "The number of weights should be the same");

        double distance = 0.0;

        for (int i = 0; i < s1.getNumberOfObjectives(); i++) {
            distance += weights[i] * Math.pow(s1.getObjective(i) - s2.getObjective(i), 2.0);
        }

        return Math.sqrt(distance);
    }


    public static double levenshteinDistance(Solution s1, Solution s2) {
        return LevenshteinDistance.getDefaultInstance().apply(s1.toString(), s2.toString());
    }
}
