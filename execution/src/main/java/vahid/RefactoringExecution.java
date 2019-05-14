package vahid;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.core.sys.LOGGER;
import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.optimization.algorithm.builder.Builder;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAII;
import edu.umich.ISELab.optimization.algorithm.builder.BuilderCustomNSGAIII;
import edu.umich.ISELab.optimization.operators.crossovers.SinglePointCrossover;
import edu.umich.ISELab.optimization.operators.mutations.BitFlipMutation;
import edu.umich.ISELab.optimization.operators.selections.BinaryTournamentSelection;
import edu.umich.ISELab.optimization.problem.GroomingProblem;
import edu.umich.ISELab.optimization.solution.Solution;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import vahid.util.ParetoObjectCRUD;
import vahid.util.SolutionListOutputVahid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RefactoringExecution {


	public static int minRefatorings = 15;

	public static int maxRefatorings = 30;

	public static String algorithm = "NSGA2";

	public static int populationSize = 100;

	public static int maxEvaluations = 10000;

	public static double crossoverProbability = 0.8;

	public static double mutationProbability = 0.2;

	private final String instanceFile;
	private final int execution_id;
	private final int iteration_id;
	private final Long project_id;
	private String outputPath;
	private boolean saveResult;
	private String objectPath = null;
	private List<Boolean> isMinimizeList;


	public RefactoringExecution(String blockFile, String sourcePath, Long project_id, int execution_id, int iteration_id, boolean saveResult) {

		this.instanceFile = blockFile;
		this.outputPath = sourcePath + "/VahidResults/REFACTORING";
		this.project_id = project_id;
		this.execution_id = execution_id;
		this.iteration_id = iteration_id;
		this.saveResult = saveResult;

	}

	public List<Solution> run() throws IOException {

		LOGGER.info("Starting the Grooming process...");
		LOGGER.info("Setting up the parameters...");
		File file = new File(instanceFile);

		// The list of objectives used to optimize the problem
		List<Objective> objectives = new ArrayList<>();

		/*objectives.add(new QMOODEffectiveness());
		objectives.add(new QMOODExtendibility());
		objectives.add(new QMOODFlexibility());
		objectives.add(new QMOODFunctionality());
		objectives.add(new QMOODReusability());
		objectives.add(new QMOODUnderstandability());*/
//        objectives.add(new Coupling());
//        objectives.add(new Cohesion());
//        objectives.add(new Complexity());
//        objectives.add(new Optimization());

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

		// Initiate the problem
		GroomingProblem problem = new GroomingProblem(new Project(),objectives, selectedRefactorings);

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

		LOGGER.info("Starting the optimization");
		AbstractEvolutionaryAlgorithm<Solution, List<Solution>> algorithm = builder.build();

		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

		@SuppressWarnings("unused")
		long computingTime = algorithmRunner.getComputingTime();

		List<Solution> paretoFront = algorithm.getResult();

//        String filename = FilenameUtils.getBaseName(instanceFile);
//
//        new SolutionListOutput(paretoFront)
//                .setSeparator("\t")
//                .setVarFileOutputContext(new DefaultFileOutputContext("C:\\Users\\valizadeh\\Desktop\\"+"output"+"-"+filename+"-var.txt"))
//                .setFunFileOutputContext(new DefaultFileOutputContext("output"+"-"+filename+"-fun.txt"))
//                //.setFunFileOutputContext(new DefaultFileOutputContext("output"+File.separator+filename+"-fun.txt"))
//                .print();


		//Writing pareto front object to a file
		if (saveResult) {
			ParetoObjectCRUD paretoObjectCRUD = new ParetoObjectCRUD(outputPath, project_id, execution_id, iteration_id);
			objectPath = paretoObjectCRUD.saveParetoObject(paretoFront);
		}
		LOGGER.info("Refactoring process is Done");

		LOGGER.info("Exporting the paretoFront into a CSV file to read later for Clustering");
		try {
			new File(outputPath).mkdirs();
			File outputObjCSV = new File(outputPath + "/obj-" + project_id + "-" + execution_id + "-" + iteration_id + ".csv");
			outputObjCSV.createNewFile();

			// create a list of isMinimize for all objectives
			List<Boolean> isMinimizeList = new ArrayList<>();
			for (int i = 0; i < problem.getObjectives().size(); i++) {
				isMinimizeList.add(problem.getObjectives().get(i).isMinimize());
			}
			this.isMinimizeList = isMinimizeList;

			DefaultFileOutputContext outputContext = new DefaultFileOutputContext(outputObjCSV.getAbsolutePath());
			outputContext.setSeparator(",");

			new SolutionListOutputVahid(paretoFront)
					.printObjectivesToFile(outputContext, paretoFront, isMinimizeList);

//            new SolutionListOutputVahid(paretoFront)
//                    .setSeparator(",")
//                    .setObjectiveMinimizingObjectiveList(isMinimizeList)
//                    .printObjectivesToFile(outputObjCSV.getAbsolutePath());
		} catch (Exception e) {
			//LOGGER.error("Error on saving the csv of objectives.");
			e.printStackTrace();
		}


		return paretoFront;
	}

	public String getObjectPath() {
		return objectPath;
	}

	public List<Boolean> getIsMinimizeList() {
		return isMinimizeList;
	}


	//
//    public static void printResults(RefactoringProblem problem, ProjectObject originalProject,
//                                    List<RefactoringSolution> paretoFront) throws IOException {
//
//        for (int i = 0; i < paretoFront.size(); i++) {
//
//            RefactoringSolution solution = paretoFront.get(i);
//
//            System.out.println(solution);
//
//            List<Refactoring> refactorings = ((RefactoringVariable)solution.getVariableValue(0)).getRefactorings();
//
////			ProjectObject refactored = NrpUtils.applyRefactorings(originalProject, refactorings);
////			refactored.setDesignMetrics(DesignMetricsUtil.calculate(refactored));
//
////			problem.calculateFitnessFunction(solution, refactored);
//
//            System.out.println(solution);
//            System.out.println("==================");
//        }
//    }


}
