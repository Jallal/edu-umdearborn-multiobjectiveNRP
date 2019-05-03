package edu.umich.ISELab.optimization.factory;

import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;

public class AlgorithmFactory {

    public static Builder getAlgorithm(String name) {

        if (name.equalsIgnoreCase("NSGA-II")) {
            return new BuilderCustomNSGAII();
        } else if (name.equalsIgnoreCase("NSGA-III")) {
            return new BuilderCustomNSGAIII();
        }

        return null;
    }

}
