package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.Project;
import thiagodnf.doupr.core.base.WorkItem;
import thiagodnf.doupr.core.base.WorkItemDefinition;

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
