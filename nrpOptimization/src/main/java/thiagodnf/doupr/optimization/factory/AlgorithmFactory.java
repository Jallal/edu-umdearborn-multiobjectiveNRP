package thiagodnf.doupr.optimization.factory;

import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAII;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAIII;

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
