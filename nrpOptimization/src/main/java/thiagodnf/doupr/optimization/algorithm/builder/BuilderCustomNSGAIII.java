package thiagodnf.doupr.optimization.algorithm.builder;

import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import thiagodnf.doupr.optimization.algorithm.CustomNSGAIII;
import thiagodnf.doupr.optimization.solution.Solution;

import java.util.List;

public class BuilderCustomNSGAIII extends Builder {

    public AbstractEvolutionaryAlgorithm<Solution, List<Solution>> build() {

        super.evaluator = new SequentialSolutionListEvaluator<Solution>();

        return new CustomNSGAIII(
                getProblem(),
                getMaxEvalutions(),
                getPopulationSize(),
                getCrossover(),
                getMutation(),
                getSelection(),
                getEvaluator(),
                getInitialPopulation()
        );
    }
}
