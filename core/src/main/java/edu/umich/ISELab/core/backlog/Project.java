package edu.umich.ISELab.core.backlog;

import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Project {
    protected Map<String, Double> designMetrics;
    List<WorkItem> workItemList = new ArrayList<>();
    List<Person> personList = new ArrayList<>();
    Candidate candidate = null;

    public Project() {

    }

    public Project(Project project) {
        this.workItemList = project.getWorkItemList();
        this.personList = project.getPersonList();
        this.candidate= new Candidate(this.workItemList,this.personList);
    }


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

    public Map<String, Double> getDesignMetrics() {
        return designMetrics;
    }

    public void setDesignMetrics(Map<String, Double> designMetrics) {
        this.designMetrics = designMetrics;
    }
    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
