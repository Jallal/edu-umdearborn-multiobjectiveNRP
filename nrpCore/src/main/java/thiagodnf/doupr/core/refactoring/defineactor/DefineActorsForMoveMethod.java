package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForMoveMethod extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForMoveMethod.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (sourceClass.isInterface() || !sourceClass.hasMethods()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Source class: " + sourceClass.getSimpleName());
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the method");

        trials = 0;

        MethodObject m = (MethodObject) RandomUtils.getRandomElement(sourceClass.getMethods());

        while (m.isContructor()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            m = (MethodObject) RandomUtils.getRandomElement(sourceClass.getMethods());

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Method name: " + m.getName());
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the target class");

        trials = 0;

        ClassObject targetClass = RandomUtils.getRandomClass(project);

        while (targetClass == sourceClass || targetClass.isInterface()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            targetClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Target class: " + targetClass.getSimpleName());

        return new Candidate(sourceClass, targetClass, m);
    }

//	private List<String> getPossibleTargetClassesByAttribute(List<String> classNames, ClassObject cls) {
//
//		List<String> possibleTargets = new ArrayList<>();
//
//		for (AttributeObject attr : cls.getAttributes()) {
//			if (classNames.contains(attr.getType())) {
//				possibleTargets.add(attr.getType());
//			}
//		}
//
//		return possibleTargets;
//	}
//
//	public List<MethodObject> getPossibleMethods(List<ClassObject> classes, List<String> classNames, ClassObject cls) {
//
//		List<MethodObject> availableMethods = new ArrayList<>();
//
//		for (MethodObject method : cls.getMethods()) {
//
//			if (MethodObjectUtils.hasPolymorphism(classes, cls, method)) {
//				continue;
//			}
//
//			List<String> projectClasses = getCustomClasses(classNames, method);
//
//			if (projectClasses.isEmpty()) {
//				continue;
//			}
//
//			availableMethods.add(method);
//		}
//
//		return availableMethods;
//	}
//	
//	private List<String> getCustomClasses(List<String> classNames, MethodObject method) {
//
//		List<String> cls = new ArrayList<>();
//
//		for (ParameterObject p : method.getParameters()) {
//			if (classNames.contains(p.getType())) {
//				cls.add(p.getType());
//			}
//		}
//
//		return cls;
//	}
//
//	protected List<ClassObject> getConcreteClasses(List<ClassObject> classes) {
//		List<ClassObject> results = new ArrayList<>();
//
//		for (ClassObject cls : classes) {
//			if (cls.isAbstract() || cls.isInterface()) {
//				continue;
//			}
//
//			results.add(cls);
//		}
//
//		return results;
//	}
}
