package thiagodnf.doupr.gui.colorize;

import edu.umich.ISELab.optimization.solution.Solution;

import java.util.List;

public class NoColorize extends Colorize {

    @Override
    public double[][] getDistance(List<Solution> targets, List<Solution> paretoFront) {
        return new double[targets.size()][paretoFront.size()];
    }

    @Override
    public String toString() {
        return "No Colorize";
    }
}
