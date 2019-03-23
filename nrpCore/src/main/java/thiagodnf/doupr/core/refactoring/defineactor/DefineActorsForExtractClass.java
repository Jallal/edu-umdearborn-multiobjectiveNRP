package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForExtractClass extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForExtractClass.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (sourceClass.isInterface() || sourceClass.getMethods().size() < 2) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Source class: " + sourceClass.getSimpleName());
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the methods");

        int numberOfMethods = sourceClass.getMethods().size();
        int numberOfAttributes = sourceClass.getAttributes().size();

        Candidate candidate = new Candidate(sourceClass);

        candidate.setMethods(RandomUtils.getRandomMethods(sourceClass, numberOfMethods / 2));
        candidate.setAttributes(RandomUtils.getRandomAttributes(sourceClass, numberOfAttributes / 2));

        return candidate;
    }
}
