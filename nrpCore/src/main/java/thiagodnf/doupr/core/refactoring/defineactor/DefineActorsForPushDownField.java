package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForPushDownField extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForPushDownField.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject targetCls = RandomUtils.getRandomClass(project);

        while (!targetCls.isConcrete() || !targetCls.hasSuperClass()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            targetCls = RandomUtils.getRandomClass(project);

            trials++;
        }

        ClassObject sourceCls = ProjectObjectUtils.findByName(project, targetCls.getSuperClass());

        if (sourceCls == null) {
            return null;
        }

        if (!sourceCls.hasAttributes()) {
            return null;
        }

        AttributeObject attr = (AttributeObject) RandomUtils.getRandomElement(sourceCls.getAttributes());

        return new Candidate(sourceCls, targetCls, attr);
    }
}
