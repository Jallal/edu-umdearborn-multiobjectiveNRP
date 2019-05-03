package edu.umich.ISELab.core.util;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.backlog.WorkItemDefinition;

public class ProjectObjectUtils {

    public static WorkItem findByType(Project project, WorkItemDefinition definition) {

        for (WorkItem item : project.getWorkItemList()) {
                if (item.getWorkItemDefinition().equals(definition.name())) {
                    return item;
                }

        }

        return null;
    }
}
