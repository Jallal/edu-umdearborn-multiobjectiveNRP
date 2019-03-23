package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForExtractSubClass extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForExtractSubClass.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (!sourceClass.isConcrete() || !sourceClass.getSubClasses().isEmpty() || sourceClass.getMethods().size() < 2) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Source class: " + sourceClass.getSimpleName());
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the methods");

        int nMethods = sourceClass.getMethods().size();
        int nAttributes = sourceClass.getAttributes().size();

        Candidate candidate = new Candidate(sourceClass);

        candidate.setMethods(RandomUtils.getRandomMethods(sourceClass, nMethods / 2));
        candidate.setAttributes(RandomUtils.getRandomAttributes(sourceClass, nAttributes / 2));

        return candidate;
    }
}
