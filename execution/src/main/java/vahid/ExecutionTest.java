package vahid;

import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.evaluation.qualityattributes.Optimization;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;
import edu.umich.ISELab.optimization.operators.crossovers.SinglePointCrossover;
import edu.umich.ISELab.optimization.operators.mutations.BitFlipMutation;
import edu.umich.ISELab.optimization.operators.selections.BinaryTournamentSelection;
import edu.umich.ISELab.optimization.problem.GroomingProblem;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.GroomingVariable;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.factory.GroomingFactory;
import edu.umich.ISELab.evaluation.Objective;

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

		/*objectives.add(new QMOODEffectiveness());
		objectives.add(new QMOODExtendibility());
		objectives.add(new QMOODFlexibility());
		objectives.add(new QMOODFunctionality());
		objectives.add(new QMOODReusability());
		objectives.add(new QMOODUnderstandability());*/
		//objectives.add(new Coupling());
		//objectives.add(new Cohesion());
		//objectives.add(new Complexity());
     objectives.add(new Optimization());

		// The list of Refactorings used to optimize the problem
		List<Grooming> selectedRefactorings = new ArrayList<>();

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

		selectedRefactorings.add(GroomingFactory.getNrpOptimization("Optimize NRP"));


		// Initiate the problem
		GroomingProblem problem = new GroomingProblem(file, objectives, selectedRefactorings);

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

	public static void printResults(GroomingProblem problem, Project originalProject,
									List<GroomingSolution> paretoFront) throws IOException {

		for (int i = 0; i < paretoFront.size(); i++) {

			GroomingSolution solution = paretoFront.get(i);

			System.out.println(solution);

			List<Grooming> refactorings = ((GroomingVariable) solution.getVariableValue(0)).getRefactorings();

//			ProjectObject refactored = NrpUtils.applyRefactorings(originalProject, refactorings);
//			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));

//			problem.calculateFitnessFunction(solution, refactored);

			System.out.println(solution);
			System.out.println("==================");
		}
	}
}
