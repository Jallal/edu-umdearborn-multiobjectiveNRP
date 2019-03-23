package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;


public class DefineActorsForEncapsulateField extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForEncapsulateField.class);

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

        trials = 0;

        AttributeObject attr = (AttributeObject) RandomUtils.getRandomElement(sourceClass.getAttributes());

        while (!attr.isPublic()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            attr = (AttributeObject) RandomUtils.getRandomElement(sourceClass.getAttributes());

            trials++;
        }

        return new Candidate(sourceClass, attr);
    }
}
