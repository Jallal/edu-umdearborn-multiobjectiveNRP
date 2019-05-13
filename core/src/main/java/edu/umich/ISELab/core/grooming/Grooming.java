package edu.umich.ISELab.core.grooming;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.condition.Condition;
import edu.umich.ISELab.core.grooming.defineActor.DefineActors;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;

import java.io.Serializable;
import java.util.List;

public abstract class Grooming implements Serializable {

    protected List<WorkItem> workItems;
    protected List<Person>   resources;
    protected  Project project;
    private Candidate candidate;

    public Grooming() {}

    public Grooming(List<WorkItem> item , List<Person> person, Project project) {
        this.workItems = item;
        this.resources = person;
        this.project=project;
    }

    public Grooming(Grooming nrp) {
        this.workItems = nrp.getWorkItems();
        this.resources = nrp.getResources();
        this.project=nrp.getProject();
        this.candidate=nrp.getCandidate();
    }

    public static Condition NOT(Condition condition) {

        condition.setNegate(true);
        return condition;
    }


    public boolean verifyPreConditions(Project project) throws Exception {



        List<Condition> conditions = getPreConditions(project);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(project);


            if (!isValid) {
                //throw new Exception(getName() + ": " + condition.getError());
                return false;
            }
        }


        return true;
    }

    public boolean verifyPostCondition(Project project) throws Exception {

        List<Condition> conditions = getPostConditions(project);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(project);

            if (!isValid) {
               //throw new Exception(getName()+": "+condition.getError());
                return false;
            }
        }

        return true;
    }


    public void reset() {
        this.workItems = null;
        this.resources = null;
        this.project=null;
    }


    public void defineActors(Project project) throws Exception {
        DefineActors defineActors = getDefineActors();
        reset();
        Candidate candidate = defineActors.execute(project);
        if (candidate == null) {
            //throw new Exception("Cannot define the actors for " + getName());
            return;
        }
        this.workItems = candidate.getWorkItems() != null ? candidate.getWorkItems() : null;
        this.resources = candidate.getResources() != null ? candidate.getResources() : null;
        return;
    }

    public abstract  List<WorkItem> getWorkItems();
    public abstract void setWorkItems(List<WorkItem> workItems);
    public abstract List<Person> getResources();
    public abstract void setResources(List<Person> resources);
    public abstract Candidate loadActors(Project project);
    public abstract List<Condition> getPreConditions(Project project);
    public abstract List<Condition> getPostConditions(Project project);
    public abstract void execute(Project project) throws Exception;
    public abstract DefineActors getDefineActors();
    public abstract String getName();
    public abstract Grooming copy();
    public abstract Project getProject();
    public abstract void setProject(Project project);
    public abstract Candidate getCandidate();
    public abstract void setCandidate(Candidate candidate);
}
