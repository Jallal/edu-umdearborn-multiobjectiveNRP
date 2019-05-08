package edu.umich.ISELab.core.grooming;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.condition.Condition;
import edu.umich.ISELab.core.grooming.defineActor.DefineActors;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NrpBase implements Serializable {

    protected WorkItem workItem;
    protected Person person;
    protected boolean mustDefineActors;

    protected Map<Object, Object> properties;

    public NrpBase() {
        this.mustDefineActors = true;
        this.properties = new HashMap<>();
    }


    public NrpBase(WorkItem item , Person person) {
        this.workItem = item;
        this.person = person;
        this.mustDefineActors = false;
        this.properties = new HashMap<>();
    }

    public NrpBase(NrpBase nrp) {
        this.workItem = nrp.getWorkItem();
        this.person = nrp.getPerson();
        this.mustDefineActors = nrp.isMustDefineActors();

        // Copy the properties
        this.properties = new HashMap<>(nrp.getProperties());
    }

    public static Condition NOT(Condition condition) {

        condition.setNegate(true);
        return condition;
    }

    public boolean verifyPreConditions(WorkItem activeItem, Person person) throws Exception {


        List<Condition> conditions = getPreConditions(activeItem, person);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(activeItem, person);


            if (!isValid) {
                throw new Exception(getName() + ": " + condition.getError());
            }
        }


        return true;
    }

    public boolean verifyPostCondition(WorkItem item, Person person) throws Exception {


        List<Condition> conditions = getPostConditions(item,person);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(item, person);


            if (!isValid) {
                throw new Exception(getName() + ": " + condition.getError());
            }
        }


        return true;
    }


    public void reset() {
        this.workItem = null;
        this.person = null;
        this.properties = new HashMap<>();
    }


    public void defineActors(Project project) throws Exception {

        if (!isMustDefineActors()) {
            return;
        }

        DefineActors defineActors = getDefineActors();


        reset();

        Candidate candidate = defineActors.execute(project);

        if (candidate == null) {
            throw new Exception("Cannot define the actors for " + getName());
        }

        //this.workItem = candidate.getWorkItem() != null ? candidate.getWorkItem() : null;
        //this.person = candidate.getPerson() != null ? candidate.getPerson() : null;
        this.mustDefineActors = false;

        return;
    }

    public boolean isMustDefineActors() {
        return mustDefineActors;
    }

    public void setMustDefineActors(boolean mustDefineActors) {
        this.mustDefineActors = mustDefineActors;
    }

    public Map<Object, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<Object, Object> properties) {
        this.properties = properties;
    }

    public double getUserFeedback() {

        if (getProperties().containsKey("USER_FEEDBACK")) {
            return (double) getProperties().get("USER_FEEDBACK");
        }

        return 0.0;
    }

    public void setUserFeedback(double value) {
        getProperties().put("USER_FEEDBACK", value);
    }
    public abstract void loadActors(Project project);
    public abstract List<Condition> getPreConditions(WorkItem item, Person person);
    public abstract List<Condition> getPostConditions(WorkItem item, Person person);
    public abstract void execute(Project project) throws Exception;
    public abstract DefineActors getDefineActors();
    public abstract String getName();
    public abstract NrpBase copy();
    public abstract Person getPerson();
    public abstract WorkItem getWorkItem();


}
