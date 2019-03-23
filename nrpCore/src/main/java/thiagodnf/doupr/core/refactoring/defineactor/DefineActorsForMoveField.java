package thiagodnf.doupr.core.refactoring.defineactor;


import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForMoveField extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForMoveField.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (sourceClass.isInterface() || !sourceClass.hasAttributes()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Source class: " + sourceClass.getSimpleName());
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the attribute");

        AttributeObject attr = (AttributeObject) RandomUtils.getRandomElement(sourceClass.getAttributes());

        LOGGER.debug("Attribute name: " + attr.getName());
        LOGGER.debug("Finding the target class");

        trials = 0;

        ClassObject targetClass = RandomUtils.getRandomClass(project);

        while (targetClass == sourceClass || targetClass.isInterface() || attr.getType().equalsIgnoreCase(targetClass.getName())) {

            if (trials > MAX_TRIES) {
                return null;
            }

            targetClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        LOGGER.debug("Target class: " + targetClass.getSimpleName());

        return new Candidate(sourceClass, targetClass, attr);
    }
}
