package thiagodnf.doupr.core.refactoring.defineactor;


import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForIncreaseOrDecreaseMethodSecurity extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForIncreaseOrDecreaseMethodSecurity.class);

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

        MethodObject m = (MethodObject) RandomUtils.getRandomElement(sourceClass.getMethods());

        return new Candidate(sourceClass, m);
    }
}
