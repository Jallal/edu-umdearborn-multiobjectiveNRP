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


    protected WorkItem workItem;
    protected Person person;



    public Grooming() {

    }


    public Grooming(WorkItem item , Person person) {
        this.workItem = item;
        this.person = person;
    }

    public Grooming(Grooming nrp) {
        this.workItem = nrp.getWorkItem();
        this.person = nrp.getPerson();
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
               throw new Exception(getName()+": "+condition.getError());
            }
        }

        return true;
    }


    public void reset() {
        this.workItem = null;
        this.person = null;
    }


    public void defineActors(Project project) throws Exception {
        DefineActors defineActors = getDefineActors();
        reset();
        Candidate candidate = defineActors.execute(project);
        if (candidate == null) {
            throw new Exception("Cannot define the actors for " + getName());
        }
        this.workItem = candidate.getWorkItem() != null ? candidate.getWorkItem() : null;
        this.person = candidate.getPerson() != null ? candidate.getPerson() : null;
        return;
    }


    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public abstract void loadActors(Project project);
    public abstract List<Condition> getPreConditions(Project project);
    public abstract List<Condition> getPostConditions(Project project);
    public abstract void execute(Project project) throws Exception;
    public abstract DefineActors getDefineActors();
    public abstract String getName();
    public abstract Grooming copy();
    public abstract Person getPerson();
    public abstract WorkItem getWorkItem();


}
