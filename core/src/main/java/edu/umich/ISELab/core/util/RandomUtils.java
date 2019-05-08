package edu.umich.ISELab.core.util;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.factory.NrpFactory;
import edu.umich.ISELab.core.grooming.grooming;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import src.main.java.vahid.ML.Clustering;
import vahid.util.HashUtil;

import java.util.ArrayList;
import java.util.List;

public class RandomUtils {

	public static int getRandomIndex(List<?> list) {
		return JMetalRandom.getInstance().nextInt(0, list.size() - 1);
	}

	public static Object getRandomElement(List<?> list) {
		return list.get(getRandomIndex(list));
	}

	public static int getRandomInteger(int minValue, int maxValue) {
		return JMetalRandom.getInstance().nextInt(minValue, maxValue);
	}

	public static List<WorkItem> getRandomWorkItem(Project cls, int maxValue) {

		if (maxValue == 0) {
			return new ArrayList<>();
		}

		List<WorkItem> possibleWorkItem = new ArrayList<>();

		for (WorkItem m : cls.getWorkItemList()) {

			if (m.isAssigned()) {
				continue;
			}

			possibleWorkItem.add(m);
		}

		if (possibleWorkItem .isEmpty()) {
			return new ArrayList<>();
		}

		int amount = JMetalRandom.getInstance().nextInt(1, maxValue);

		List<WorkItem> selectedMethod = new ArrayList<>();

		while (selectedMethod.size() != amount) {

			WorkItem m = (WorkItem) getRandomElement(possibleWorkItem);

			if (!selectedMethod.contains(m)) {
				selectedMethod.add(m);
			}
		}

		return selectedMethod;
	}


	public static WorkItem getRandomItem(Project project) {

		WorkItem pkgSource = (WorkItem) getRandomElement(project.getWorkItemList());

		while (pkgSource.isAssigned()) {
			pkgSource = (WorkItem) getRandomElement(project.getWorkItemList());
		}

		return pkgSource;
	}

	public static grooming getRandomRefactoring(List<grooming> possibleRefactorings) {

		if (Clustering.instanceCL == null) {
			//return getRandomElement(possibleRefactorings).copy();
			return ((grooming)getRandomElement(possibleRefactorings)).copy();

		} else {

			String randomRefactoringName = HashUtil.getRandomElementFromDist(Clustering.instanceCL.operationsProbHash);
			return NrpFactory.getNrpOptimization(randomRefactoringName);

		}
	}
}

