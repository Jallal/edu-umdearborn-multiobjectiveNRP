package edu.umich.ISELab.core.grooming.util;


import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.List;

public class Candidate {

    protected List<WorkItem> workItems;
    protected List<Person> resources;

    public Candidate(List<WorkItem> item, List<Person> person) {
        this.workItems = item;
        this.resources = person;
    }
    public List<WorkItem> getWorkItems() {
        return workItems;
    }

    public void setWorkItems(List<WorkItem> workItems) {
        this.workItems = workItems;
    }

    public List<Person> getResources() {
        return resources;
    }

    public void setResources(List<Person> resources) {
        this.resources = resources;
    }



}
