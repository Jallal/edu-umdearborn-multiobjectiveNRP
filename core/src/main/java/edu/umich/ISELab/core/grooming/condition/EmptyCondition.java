package edu.umich.ISELab.core.grooming.condition;


import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.List;

public class EmptyCondition extends Condition {

    protected List<WorkItem> list;

    public EmptyCondition(List<WorkItem> list) {
        this.list = list;
    }

    public boolean verify(WorkItem item, Person person ) {
        return list.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "The list cannot be empty";
    }
}
