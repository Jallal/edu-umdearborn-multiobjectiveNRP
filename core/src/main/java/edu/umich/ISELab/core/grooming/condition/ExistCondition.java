package edu.umich.ISELab.core.grooming.condition;


import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public class ExistCondition extends Condition {

    protected WorkItem item;
    protected Person person;


    public ExistCondition(WorkItem item, Person person) {
        this.item = item;
        this.person = person;
    }

    @Override
    public boolean verify(WorkItem item, Person person ) {

        if (item == null || person ==null) {
            return false;
        }
        if(!item.getPerson().equals(person)){
            return false;
        }
        if(!item.isAssigned()||!person.isAssigned()){
            return false;
        }

        return true;
    }

    @Override
    protected String getErrorMessage() {
        return "The " + this.item + "%IS%required";
    }
}
