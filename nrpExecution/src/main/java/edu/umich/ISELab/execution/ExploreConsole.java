package edu.umich.ISELab.execution;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import edu.umich.ISELab.core.factory.NrpFactory;
import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.qualityattributes.NumberOfNRPOptimization;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODEffectiveness;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODExtendibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFlexibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFunctionality;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODReusability;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODUnderstandability;
import edu.umich.ISELab.execution.util.CommandLineValues;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;
import edu.umich.ISELab.optimization.operators.crossovers.SinglePointCrossover;
import edu.umich.ISELab.optimization.operators.mutations.BitFlipMutation;
import edu.umich.ISELab.optimization.operators.selections.BinaryTournamentSelection;
import edu.umich.ISELab.optimization.problem.NrpProblem;
import edu.umich.ISELab.optimization.solution.NrpSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.NrpVariable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for executing the NSGA-II algorithm
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class ExploreConsole {

    protected static final Logger LOGGER = Logger.getLogger(ExploreConsole.class);

    public static void main(String[] args) throws IOException {

        CommandLineValues values = new CommandLineValues(args);
        CmdLineParser parser = new CmdLineParser(values);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.exit(1);
        }


        LogManager.getRootLogger().setLevel(Level.INFO);

        if (LOGGER.isInfoEnabled()) LOGGER.info("Running " + values.algorithm);

        // The name of the instance file
//		String instanceFile = "src/main/resources/ganttproject.blocks";
//		String instanceFile = "src/main/resources/argouml-v0.26/argouml.blocks";

        File file = new File(values.instanceFile);

        // The list of objectives used to optimize the problem
        List<Objective> objectives = new ArrayList<>();

        objectives.add(new QMOODEffectiveness());
        objectives.add(new QMOODExtendibility());
        objectives.add(new QMOODFlexibility());
        objectives.add(new QMOODFunctionality());
        objectives.add(new QMOODReusability());
        objectives.add(new QMOODUnderstandability());
        /*objectives.add(new Coupling());
        objectives.add(new Cohesion());
        objectives.add(new Complexity());*/
        objectives.add(new NumberOfNRPOptimization());

        List<NrpBase> selectedRefactorings = new ArrayList<>();

        selectedRefactorings.add(NrpFactory.getNrpOptimization("Move Method"));
        /*selectedRefactorings.add(NrpFactory.getRefactoring("Move Field"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Extract Class"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Push Down Field"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Push Down Method"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Extract Sub Class"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Pull Up Field"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Pull Up Method"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Extract Super Class"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Encapsulate Field"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Increase Field Security"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Decrease Field Security"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Increase Method Security"));
        selectedRefactorings.add(NrpFactory.getRefactoring("Decrease Method Security"));*/

        // Initiate the problem
        NrpProblem problem = new NrpProblem(file, objectives, selectedRefactorings);

        problem.setMinSolutionSize(values.minRefatorings);
        problem.setMaxSolutionSize(values.maxRefatorings);

        // Initiate the algorithm
        Builder builder = null;

        if (values.algorithm.equalsIgnoreCase("NSGA2")) {
            builder = new BuilderCustomNSGAII();
        } else if (values.algorithm.equalsIgnoreCase("NSGA3")) {
            builder = new BuilderCustomNSGAIII();
        }

        // Define its parameters
        builder.setProblem(problem);
        builder.setPopulationSize(values.populationSize);
        builder.setMaxEvalutions(values.maxEvaluations);
        builder.setCrossover(new SinglePointCrossover(values.crossoverProbability));
        builder.setMutation(new BitFlipMutation(values.mutationProbability));
        builder.setSelection(new BinaryTournamentSelection());

        AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder.build();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

        @SuppressWarnings("unused")
        long computingTime = algorithmRunner.getComputingTime();

        List<Solution> paretoFront = algorithm.getResult();


        File outputFolder = new File("output");

        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        String filename = FilenameUtils.getBaseName(values.instanceFile);

        new SolutionListOutput(paretoFront)
                .setSeparator("\t")
                .setVarFileOutputContext(new DefaultFileOutputContext("output" + "-" + filename + "-var.txt"))
                .setFunFileOutputContext(new DefaultFileOutputContext("output" + "-" + filename + "-fun.txt"))
                //.setFunFileOutputContext(new DefaultFileOutputContext("output"+File.separator+filename+"-fun.txt"))
                .print();

        if (LOGGER.isInfoEnabled()) LOGGER.info("Done");
    }

    public static void printResults(NrpProblem problem, ProjectObject originalProject,
                                    List<NrpSolution> paretoFront) throws IOException {

        for (int i = 0; i < paretoFront.size(); i++) {

            NrpSolution solution = paretoFront.get(i);

            System.out.println(solution);

            List<NrpBase> refactorings = ((NrpVariable) solution.getVariableValue(0)).getRefactorings();

//			ProjectObject refactored = NrpUtils.applyRefactorings(originalProject, refactorings);
//			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

//			problem.calculateFitnessFunction(solution, refactored);

            System.out.println(solution);
            System.out.println("==================");
        }
    }
}
