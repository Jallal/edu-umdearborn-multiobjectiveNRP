package thiagodnf.doupr.gui.colorize;

import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.NormalizerUtils;

import java.util.List;

public abstract class Colorize implements Comparable<Colorize> {

    public double[] getColor(List<Solution> targets, List<Solution> paretoFront) {

        if (targets.isEmpty() || this instanceof NoColorize) {
            return new double[paretoFront.size()];
        }

        double[][] distances = getDistance(targets, paretoFront);

        for (int i = 0; i < targets.size(); i++) {
            for (int j = 0; j < paretoFront.size(); j++) {
                if (targets.get(i).getUserFeedback() < 0) {
                    distances[i][j] = (1.0 - distances[i][j]);
                }
            }
        }

        double[][] normalized = new double[targets.size()][paretoFront.size()];

        for (int i = 0; i < targets.size(); i++) {
            normalized[i] = NormalizerUtils.normalize(distances[i]);
        }

        double[] colors = new double[paretoFront.size()];

        for (int i = 0; i < paretoFront.size(); i++) {

            for (int j = 0; j < targets.size(); j++) {
                colors[i] += normalized[j][i];
            }

            colors[i] /= targets.size();
        }

        return NormalizerUtils.normalize(colors);
    }

    public int compareTo(Colorize colorize) {
        return toString().compareTo(colorize.toString());
    }

    public abstract String toString();

    public abstract double[][] getDistance(List<Solution> targets, List<Solution> paretoFront);
}
