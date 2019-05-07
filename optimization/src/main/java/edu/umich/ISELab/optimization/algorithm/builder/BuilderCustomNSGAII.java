package edu.umich.ISELab.optimization.algorithm.builder;

import edu.umich.ISELab.optimization.algorithm.CustomNSGAII;
import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

import java.util.List;

public class BuilderCustomNSGAII extends Builder {

    public AbstractEvolutionaryAlgorithm<Solution, List<Solution>> build() {

        super.evaluator = new SequentialSolutionListEvaluator<Solution>();

        return new CustomNSGAII(
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
