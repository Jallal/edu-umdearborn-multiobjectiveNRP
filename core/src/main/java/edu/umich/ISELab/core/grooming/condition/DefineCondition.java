package edu.umich.ISELab.core.grooming.condition;


import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public class DefineCondition extends Condition {

    private WorkItem workItem;
    private Person person;


    public DefineCondition(WorkItem item, Person person) {

        this.workItem = item;
        this.person = person;
    }

    @Override
    public boolean verify(WorkItem item, Person person ) {

        if ((item instanceof WorkItem) && (person instanceof  Person)) {
            return verifyItemIsAvailable(item, person);
        }

        return false;
    }

    protected boolean verifyItemIsAvailable(WorkItem item, Person person) {
        if (!item.isAssigned()&&(!person.isAssigned())) {
                return true;
        }

        return false;
    }


    @Override
    public String getErrorMessage() {
        return "The item can't be assigned";
    }
}
