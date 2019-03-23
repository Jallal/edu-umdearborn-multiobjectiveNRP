package thiagodnf.doupr.gui.colorize;

import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.util.DistanceUtils;
import thiagodnf.doupr.optimization.util.NormalizerUtils;

import java.util.List;

public class ColorizeByLevenshteinDistance extends Colorize {

    @Override
    public double[][] getDistance(List<Solution> targets, List<Solution> paretoFront) {

        double[][] distances = new double[targets.size()][paretoFront.size()];

        for (int i = 0; i < targets.size(); i++) {
            for (int j = 0; j < paretoFront.size(); j++) {
                distances[i][j] += DistanceUtils.levenshteinDistance(paretoFront.get(j), targets.get(i));
            }
        }

        for (int i = 0; i < targets.size(); i++) {
            distances[i] = NormalizerUtils.normalize(distances[i]);
        }

        return distances;
    }

    @Override
    public String toString() {
        return "Levenshtein Distance";
    }
}
