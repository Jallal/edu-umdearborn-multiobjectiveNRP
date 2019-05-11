package edu.umich.ISELab.core.grooming.condition;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public class DefineCondition extends Condition {

    Project project;


    public DefineCondition(Project project) {
        this.project=project;
    }

    @Override
    public boolean verify(Project project) {

        if (verifyItemIsAvailable(project)) {
            return  true;
        }

        return false;
    }

    protected boolean verifyItemIsAvailable(Project project) {

        for(WorkItem item: project.getWorkItemList()) {
            for (Person person : project.getPersonList()) {
                if (!item.isAssigned() && (!person.isAssigned())) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String getErrorMessage() {
        return "The item can't be assigned";
    }
}
