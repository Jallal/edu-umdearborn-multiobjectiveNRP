package thiagodnf.doupr.execution;

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
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.factory.RefactoringFactory;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.qualityattributes.Cohesion;
import thiagodnf.doupr.evaluation.qualityattributes.Complexity;
import thiagodnf.doupr.evaluation.qualityattributes.Coupling;
import thiagodnf.doupr.evaluation.qualityattributes.NumberOfNRPOptimization;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODEffectiveness;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODExtendibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFunctionality;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODUnderstandability;
import thiagodnf.doupr.execution.util.CommandLineValues;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAII;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAIII;
import thiagodnf.doupr.optimization.operators.crossovers.SinglePointCrossover;
import thiagodnf.doupr.optimization.operators.mutations.BitFlipMutation;
import thiagodnf.doupr.optimization.operators.selections.BinaryTournamentSelection;
import thiagodnf.doupr.optimization.problem.RefactoringProblem;
import thiagodnf.doupr.optimization.solution.RefactoringSolution;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

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
        objectives.add(new Coupling());
        objectives.add(new Cohesion());
        objectives.add(new Complexity());
        objectives.add(new NumberOfNRPOptimization());

        List<Refactoring> selectedRefactorings = new ArrayList<>();

        selectedRefactorings.add(RefactoringFactory.getRefactoring("Move Method"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Move Field"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Extract Class"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Push Down Field"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Push Down Method"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Extract Sub Class"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Pull Up Field"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Pull Up Method"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Extract Super Class"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Encapsulate Field"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Increase Field Security"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Decrease Field Security"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Increase Method Security"));
        selectedRefactorings.add(RefactoringFactory.getRefactoring("Decrease Method Security"));

        // Initiate the problem
        RefactoringProblem problem = new RefactoringProblem(file, objectives, selectedRefactorings);

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

    public static void printResults(RefactoringProblem problem, ProjectObject originalProject,
                                    List<RefactoringSolution> paretoFront) throws IOException {

        for (int i = 0; i < paretoFront.size(); i++) {

            RefactoringSolution solution = paretoFront.get(i);

            System.out.println(solution);

            List<Refactoring> refactorings = ((RefactoringVariable) solution.getVariableValue(0)).getRefactorings();

//			ProjectObject refactored = RefactoringUtils.applyRefactorings(originalProject, refactorings);
//			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

//			problem.calculateFitnessFunction(solution, refactored);

            System.out.println(solution);
            System.out.println("==================");
        }
    }
}
