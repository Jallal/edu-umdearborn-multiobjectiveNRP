package thiagodnf.doupr.gui.asynctask;

import org.apache.log4j.Logger;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.problem.RefactoringProblem.EvolutionListener;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.util.SolutionListUtils;

import java.util.List;

public class OptimizeAsyncTask extends AsyncTask<Builder> {

    protected static final Logger LOGGER = Logger.getLogger(OptimizeAsyncTask.class);
    protected Builder builder;

    public OptimizeAsyncTask(Builder builder) {
        super("Optimizing...", builder.getMaxEvalutions());

        this.builder = builder;
    }

    @Override
    protected Builder doInBackground() throws Exception {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Starting the search process");

        AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder.build();

        builder.getProblem().addEvolutionListener(new EvolutionListener() {

            @Override
            public void evaluated() {
                publish(progress++);
            }
        });

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

        builder.setComputingTime(algorithmRunner.getComputingTime());
        builder.setParetoFront(SolutionListUtils.removeRepeated(algorithm.getResult()));

        return builder;
    }
}
