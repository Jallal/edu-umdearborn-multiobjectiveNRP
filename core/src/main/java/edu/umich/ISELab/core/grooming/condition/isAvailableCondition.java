package edu.umich.ISELab.core.grooming.condition;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public class isAvailableCondition extends Condition {
    protected Person person;
    protected WorkItem workItem;

    public isAvailableCondition(Person person, WorkItem item) {
        this.person = person;
        this.workItem = item;
    }


    @Override
    public boolean verify(Project project) {

        if (!person.isAssigned() || !workItem.isAssigned()) {
            return true;
        }
        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The element " + person.getName() + " is assigned status : " + person.isAssigned();
    }
}
