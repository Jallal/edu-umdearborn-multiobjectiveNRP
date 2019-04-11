package thiagodnf.doupr.core.util;

import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.PackageObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.factory.NrpFactory;
import thiagodnf.doupr.core.refactoring.NrpBase;
import vahid.ML.Clustering;
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

	public static List<MethodObject> getRandomMethods(ClassObject cls, int maxValue) {

		if (maxValue == 0) {
			return new ArrayList<>();
		}

		List<MethodObject> possibleMethods = new ArrayList<>();

		for (MethodObject m : cls.getMethods()) {

			if (m.isContructor()) {
				continue;
			}

			possibleMethods.add(m);
		}

		if (possibleMethods.isEmpty()) {
			return new ArrayList<>();
		}

		int amount = JMetalRandom.getInstance().nextInt(1, maxValue);

		List<MethodObject> selectedMethod = new ArrayList<>();

		while (selectedMethod.size() != amount) {

			MethodObject m = (MethodObject) getRandomElement(possibleMethods);

			if (!selectedMethod.contains(m)) {
				selectedMethod.add(m);
			}
		}

		return selectedMethod;
	}

	public static List<AttributeObject> getRandomAttributes(ClassObject cls, int maxValue) {

		if (maxValue == 0) {
			return new ArrayList<>();
		}

		if (!cls.hasAttributes()) {
			return new ArrayList<>();
		}

		int amount = JMetalRandom.getInstance().nextInt(1, maxValue);

		List<AttributeObject> selectedAttributes = new ArrayList<>();

		while (selectedAttributes.size() != amount) {

			AttributeObject attr = (AttributeObject) getRandomElement(cls.getAttributes());

			if (!selectedAttributes.contains(attr)) {
				selectedAttributes.add(attr);
			}
		}

		return selectedAttributes;
	}

	public static ClassObject getRandomClass(ProjectObject project) {

		PackageObject pkgSource = (PackageObject) getRandomElement(project.getPackages());

		while (!pkgSource.hasClasses()) {
			pkgSource = (PackageObject) getRandomElement(project.getPackages());
		}

		return (ClassObject) getRandomElement(pkgSource.getClasses());
	}

	public static NrpBase getRandomRefactoring(List<NrpBase> possibleRefactorings) {

		if (Clustering.instanceCL == null) {

			//return getRandomElement(possibleRefactorings).copy();
			return null;

		} else {

			String randomRefactoringName = HashUtil.getRandomElementFromDist(Clustering.instanceCL.operationsProbHash);
			return NrpFactory.getNrpOptimization(randomRefactoringName);

		}
	}
}

