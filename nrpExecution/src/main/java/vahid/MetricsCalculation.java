/*
 * Copyright (c) 2019.
 * Author: Vahid Alizadeh
 * Email: alizadeh@umich.edu
 */

package main.java.vahid;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

	public RefactoringProblem problem;

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
		objectives.add(new Coupling());
		objectives.add(new Cohesion());
		objectives.add(new Complexity());
		objectives.add(new NumberOfNRPOptimization());

		// The list of Refactorings used to optimize the problem
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

		LOGGER.info("calculating the metrics....");
		// Initiate the problem
		this.problem = new RefactoringProblem(file, objectives, selectedRefactorings);

	}


}
