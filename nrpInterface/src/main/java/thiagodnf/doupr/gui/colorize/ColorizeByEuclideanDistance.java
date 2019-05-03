package thiagodnf.doupr.gui.colorize;

import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.DistanceUtils;
import edu.umich.ISELab.optimization.util.NormalizerUtils;

import java.util.List;

public class ColorizeByEuclideanDistance extends Colorize {

    @Override
    public double[][] getDistance(List<Solution> targets, List<Solution> paretoFront) {

        double[][] distances = new double[targets.size()][paretoFront.size()];

        for (int i = 0; i < targets.size(); i++) {
            for (int j = 0; j < paretoFront.size(); j++) {
                distances[i][j] += DistanceUtils.euclideanDistance(paretoFront.get(j), targets.get(i));
            }
        }

        for (int i = 0; i < targets.size(); i++) {
            distances[i] = NormalizerUtils.normalize(distances[i]);
        }

        return distances;
    }

    @Override
    public String toString() {
        return "Euclidean Distance";
    }
}
