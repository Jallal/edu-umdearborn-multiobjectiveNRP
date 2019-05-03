package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.Project;
import thiagodnf.doupr.core.base.WorkItem;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

import static thiagodnf.doupr.core.util.RandomUtils.getRandomElement;

public class AssignWorkItem extends DefineActors{

    protected static final Logger LOGGER = Logger.getLogger(AssignWorkItem.class);

    public Candidate execute(Project project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        WorkItem sourceClass = RandomUtils.getRandomItem(project);

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

        AttributeObject attr = (AttributeObject)getRandomElement(sourceClass.getAttributes());

        while (!attr.isPublic()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            attr = (AttributeObject) getRandomElement(sourceClass.getAttributes());

            trials++;
        }

        return new Candidate(sourceClass, attr);
    }
}
