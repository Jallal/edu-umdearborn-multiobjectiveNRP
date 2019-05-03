/*
 * Copyright (c) 2019.
 * Author: Vahid Alizadeh
 * Email: alizadeh@umich.edu
 */

package vahid;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import edu.umich.ISELab.optimization.problem.NrpProblem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vahid Alizadeh
 * @version 1.0.0
 * @since 07-09-2018
 */
public class MetricsCalculation {

	protected static final Logger LOGGER = Logger.getLogger(MetricsCalculation.class);

	public static int minRefatorings = 2;

	public static int maxRefatorings = 10;

	public static String algorithm = "NSGA2";

	public static int populationSize = 100;

	public static int maxEvaluations = 1000;

	public static double crossoverProbability = 0.9;

	public static double mutationProbability = 0.1;

	public String instanceFile;

	public NrpProblem problem;

	public MetricsCalculation(String BlockFile) {
		this.instanceFile = BlockFile;

		LogManager.getRootLogger().setLevel(Level.INFO);

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

		selectedRefactorings.add(NrpFactory.getNrpOptimization("Optimizing the NRP"));


		LOGGER.info("calculating the metrics....");
		// Initiate the problem
		this.problem = new NrpProblem(file, objectives, selectedRefactorings);

	}


}
