package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForPullUpMethod extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForPullUpMethod.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (!sourceClass.isConcrete() || !sourceClass.hasMethods() || !sourceClass.hasSuperClass()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        trials = 0;

        MethodObject m = (MethodObject) RandomUtils.getRandomElement(sourceClass.getMethods());

        while (m.isContructor()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            m = (MethodObject) RandomUtils.getRandomElement(sourceClass.getMethods());

            trials++;
        }

        ClassObject targetCls = ProjectObjectUtils.findByName(project, sourceClass.getSuperClass());

        return new Candidate(sourceClass, targetCls, m);
    }
}
