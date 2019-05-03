package edu.umich.ISELab.core.grooming.util;

import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public class Candidate {

    protected WorkItem workItem;
    protected Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



    public Candidate(WorkItem item, Person person) {
        this.workItem = item;
        this.person = person;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

}
