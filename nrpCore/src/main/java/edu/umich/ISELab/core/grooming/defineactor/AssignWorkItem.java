package edu.umich.ISELab.core.grooming.defineactor;

import org.apache.log4j.Logger;
import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.util.RandomUtils;

import static edu.umich.ISELab.core.util.RandomUtils.getRandomElement;

public class AssignWorkItem extends DefineActors{

    protected static final Logger LOGGER = Logger.getLogger(AssignWorkItem.class);

    public Candidate execute(Project project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the source class");

        int trials = 0;

        WorkItem sourceClass = RandomUtils.getRandomItem(project);

        while (sourceClass.isAssigned()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            sourceClass = RandomUtils.getRandomItem(project);

            trials++;
        }

       // if (LOGGER.isDebugEnabled()) LOGGER.debug("Source class: " + sourceClass.getSimpleName());
        //if (LOGGER.isDebugEnabled()) LOGGER.debug("Finding the attribute");

        trials = 0;

        WorkItem attr = (WorkItem) getRandomElement(project.getWorkItemList());

        while (!attr.isAssigned()) {

            if (trials > MAX_TRIES) {
                return null;
            }

            attr = (WorkItem) getRandomElement(project.getWorkItemList());

            trials++;
        }

        return new Candidate(attr);
    }
}
