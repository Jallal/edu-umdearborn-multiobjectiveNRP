package edu.umich.ISELab.core.grooming;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.condition.Condition;
import edu.umich.ISELab.core.grooming.condition.DefineCondition;
import edu.umich.ISELab.core.grooming.condition.ExistCondition;
import edu.umich.ISELab.core.grooming.defineActor.Actor;
import edu.umich.ISELab.core.grooming.defineActor.DefineActors;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public class AssignTask extends Grooming {
    protected WorkItem activeItem;
    protected Person activePerson;
    private  Map<WorkItem, Person> projectActors;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private Project project;


    public WorkItem getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(WorkItem activeItem) {
        this.activeItem = activeItem;
    }

    public Person getActivePerson() {
        return activePerson;
    }

    public void setActivePerson(Person activePerson) {
        this.activePerson = activePerson;
    }



    public AssignTask() {
        super();
    }

    public AssignTask(AssignTask assignTask) {
        super(assignTask);
    }

    public AssignTask(WorkItem item , Person person) {
        super(item,person);
        this.activePerson=person;
        this.activeItem=item;
    }

    @Override
    public void loadActors(Project project) {

            Candidate execute = this.getDefineActors().execute(project);
            if(execute.getWorkItem()!=null&&execute.getPerson()!=null) {
                this.activeItem = execute.getWorkItem();
                this.activePerson = execute.getPerson();
                this.assignedTasks(execute.getWorkItem(), execute.getPerson());
            }

    }

    @Override
    public List<Condition> getPreConditions(WorkItem item, Person person) {

        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ExistCondition(item, person));
        conditions.add(new DefineCondition(item, person));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(WorkItem item, Person person) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ExistCondition(item, person));
        conditions.add(new DefineCondition(item, person));
        //conditions.add(new thiagodnf.doupr.core.Grooming.condition.HasVisibilityCondition(attr, Visibility.PRIVATE));

        return conditions;
    }



    @Override
    public void execute(Project project) {
        //while (ProjectObjectUtils.findPair(project)!=null) {
        this.loadActors(project);
        //Set<Map.Entry<WorkItem, Person>> st = this.projectActors.entrySet();
       // for (Map.Entry<WorkItem, Person> me : st) {
            //this.assignedTasks(me.getKey(), me.getValue());
       // }
   // }

    }

    @Override
    public DefineActors getDefineActors() {
        return new Actor();
    }


    public void assignedTasks(WorkItem item , Person person){
        this.activeItem.setPerson(person);
        this.activePerson.setItem(item);
        this.activeItem.setAssigned(TRUE);
        this.activePerson.setAssigned(TRUE);
    }


    @Override
    public String getName() {
        return "Encapsulate Field";
    }

    @Override
    public Grooming copy() {
        return new AssignTask(this);
    }

    @Override
    public Person getPerson() {
        return this.activePerson;
    }

    @Override
    public WorkItem getWorkItem() {
        return this.activeItem;
    }

}
