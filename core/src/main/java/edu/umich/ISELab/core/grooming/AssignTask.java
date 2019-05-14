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

import static java.lang.Boolean.TRUE;

public class AssignTask extends Grooming {
    protected List<WorkItem> workItems;
    protected List<Person> resources;
    protected Project project;
    protected Candidate candidate;


   public AssignTask() {
        super();
    }

    public AssignTask(AssignTask assignTask) {

        super(assignTask);
        this.workItems = assignTask.getWorkItems();
        this.resources = assignTask.getResources();
        this.project = assignTask.getProject();
        this.candidate=assignTask.getCandidate();
    }


    public AssignTask(List<WorkItem> items, List<Person> resources, Project project) {
        super(items, resources, project);
        this.workItems = items;
        this.resources = resources;
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public List<Condition> getPreConditions(Project project) {

        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ExistCondition(project));
        conditions.add(new DefineCondition(project));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(Project project) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ExistCondition(project));
        conditions.add(new DefineCondition(project));
        //conditions.add(new isAvailableCondition(attr, Visibility.PRIVATE));

        return conditions;
    }


    @Override
    public void execute(Project project) {


        for (WorkItem item : project.getWorkItemList()) {
            for (Person person : project.getPersonList()) {
                if (!item.isAssigned() && !person.isAssigned()) {
                    item.setPerson(person);
                    item.setAssigned(TRUE);
                    person.setAssigned(TRUE);
                    person.setItem(item);
                } else {
                    //do nothing
                }

            }

        }
    }

    @Override
    public DefineActors getDefineActors() {
        return new Actor();
    }


    @Override
    public String getName() {
        return "Assigned tasks";
    }

    @Override
    public Grooming copy() {
        return new AssignTask(this);
    }

    @Override
    public List<WorkItem> getWorkItems() {
        return this.workItems;
    }

    @Override
    public void setWorkItems(List<WorkItem> workItems) {
        this.workItems = workItems;

    }

    @Override
    public List<Person> getResources() {
        return this.resources;
    }

    @Override
    public void setResources(List<Person> resources) {
        this.resources = resources;

    }

    @Override
    public Candidate loadActors(Project project) {
        this.candidate = this.getDefineActors().execute(project);
        return this.candidate;
    }

    @Override
    public Candidate getCandidate() {
        return this.candidate;
    }

    @Override
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;

    }


}
