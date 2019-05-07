package edu.umich.ISELab.core.backlog;

import edu.umich.ISELab.core.projectResources.Person;

import java.util.ArrayList;
import java.util.List;

public class Project {
    List<WorkItem> workItemList = new ArrayList<>();
    List<Person> personList = new ArrayList<>();

    public List<WorkItem> getWorkItemList() {
        return workItemList;
    }

    public void setWorkItemList(List<WorkItem> workItemList) {
        this.workItemList = workItemList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


}
