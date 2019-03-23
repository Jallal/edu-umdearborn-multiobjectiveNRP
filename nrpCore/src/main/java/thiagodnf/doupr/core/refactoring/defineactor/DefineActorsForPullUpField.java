package thiagodnf.doupr.core.refactoring.defineactor;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RandomUtils;

public class DefineActorsForPullUpField extends DefineActors {

    protected static final Logger LOGGER = Logger.getLogger(DefineActorsForPullUpField.class);

    public Candidate execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        ClassObject sourceClass = RandomUtils.getRandomClass(project);

        while (!sourceClass.isConcrete() || !sourceClass.hasAttributes() || !sourceClass.hasSuperClass()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomClass(project);

            trials++;
        }

        AttributeObject attr = (AttributeObject) RandomUtils.getRandomElement(sourceClass.getAttributes());

        ClassObject targetCls = ProjectObjectUtils.findByName(project, sourceClass.getSuperClass());

        return new Candidate(sourceClass, targetCls, attr);
    }
}
