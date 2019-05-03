package thiagodnf.doupr.gui.asynctask;

import org.apache.log4j.Logger;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.SolutionListUtils;

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

        builder.getProblem().addEvolutionListener(new NrpProblem.EvolutionListener() {

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
