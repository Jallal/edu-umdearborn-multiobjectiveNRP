package vahid;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.factory.NrpFactory;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.qualityattributes.NumberOfNRPOptimization;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODEffectiveness;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODExtendibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFunctionality;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODUnderstandability;
import thiagodnf.doupr.optimization.algorithm.builder.Builder;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAII;
import thiagodnf.doupr.optimization.algorithm.builder.BuilderCustomNSGAIII;
import thiagodnf.doupr.optimization.operators.crossovers.SinglePointCrossover;
import thiagodnf.doupr.optimization.operators.mutations.BitFlipMutation;
import thiagodnf.doupr.optimization.operators.selections.BinaryTournamentSelection;
import thiagodnf.doupr.optimization.problem.NrpProblem;
import thiagodnf.doupr.optimization.solution.NrpSolution;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.NrpVariable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vahid Alizadeh
 * @version 1.0.0
 * @since 07-09-2018
 */
public class ExecutionTest {

	protected static final Logger LOGGER = Logger.getLogger(ExecutionTest.class);

	public static int minRefatorings = 2;

	public static int maxRefatorings = 10;

	public static String algorithm = "NSGA2";

	public static int populationSize = 100;

	public static int maxEvaluations = 1000;

	public static double crossoverProbability = 0.9;

	public static double mutationProbability = 0.1;


	public static void main(String[] args) throws IOException {

		LogManager.getRootLogger().setLevel(Level.INFO);


		String instanceFile = "C:\\Users\\valizadeh\\Desktop\\SootParser\\groceryJar.blocks";

		File file = new File(instanceFile);

		// The list of objectives used to optimize the problem
		List<Objective> objectives = new ArrayList<>();

		objectives.add(new QMOODEffectiveness());
		objectives.add(new QMOODExtendibility());
		objectives.add(new QMOODFlexibility());
		objectives.add(new QMOODFunctionality());
		objectives.add(new QMOODReusability());
		objectives.add(new QMOODUnderstandability());
		//objectives.add(new Coupling());
		//objectives.add(new Cohesion());
		//objectives.add(new Complexity());
     objectives.add(new NumberOfNRPOptimization());

		// The list of Refactorings used to optimize the problem
		List<NrpBase> selectedRefactorings = new ArrayList<>();

		/*selectedRefactorings.add(RefactoringFactory.getRefactoring("Move Method"));
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
		selectedRefactorings.add(RefactoringFactory.getRefactoring("Decrease Method Security"));*/

		selectedRefactorings.add(NrpFactory.getNrpOptimization("Optimize NRP"));


		// Initiate the problem
		NrpProblem problem = new NrpProblem(file, objectives, selectedRefactorings);

		problem.setMinSolutionSize(minRefatorings);
		problem.setMaxSolutionSize(maxRefatorings);

		// Initiate the algorithm
		Builder builder = null;

		if (algorithm.equalsIgnoreCase("NSGA2")) {
			builder = new BuilderCustomNSGAII();
		} else if (algorithm.equalsIgnoreCase("NSGA3")) {
			builder = new BuilderCustomNSGAIII();
		}

		// Define its parameters
		builder.setProblem(problem);
		builder.setPopulationSize(populationSize);
		builder.setMaxEvalutions(maxEvaluations);
		builder.setCrossover(new SinglePointCrossover(crossoverProbability));
		builder.setMutation(new BitFlipMutation(mutationProbability));
		builder.setSelection(new BinaryTournamentSelection());

		AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder.build();

		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

		@SuppressWarnings("unused")
		long computingTime = algorithmRunner.getComputingTime();

		List<Solution> paretoFront = algorithm.getResult();

//
//        File outputFolder = new File("output");
//
//        if (!outputFolder.exists()) {
//            outputFolder.mkdirs();
//        }

		String filename = FilenameUtils.getBaseName(instanceFile);

		new SolutionListOutput(paretoFront)
				.setSeparator("\t")
				.setVarFileOutputContext(new DefaultFileOutputContext("C:\\Users\\valizadeh\\Desktop\\" + "output" + "-" + filename + "-var.txt"))
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
