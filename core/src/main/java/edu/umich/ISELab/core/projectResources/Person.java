package edu.umich.ISELab.core.projectResources;


import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.util.Skills;

import java.util.List;

public class Person {

    private boolean isAssigned = false;
    private WorkItem item;
    private String name;
    private String occupation;
    private String resourceId;
    private List<Skills> skills;


    public Person() {

    }

    public Person(String id, String time_stamp, String name, String occupation) {
        super();
        this.setAssigned(false);
        this.setOccupation(occupation);
        this.setName(name);
        this.setResourceId(id);
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public WorkItem getItem() {
        return item;
    }

    public void setItem(WorkItem item) {
        this.item = item;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public List<Skills> getSkills() {return skills; }

    public void setSkills(List<Skills> skills) { this.skills = skills; }
}
