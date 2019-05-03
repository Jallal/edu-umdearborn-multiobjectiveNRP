package edu.umich.ISELab.core.backlog;

public abstract class WorkItem{

    private int weight;
    private boolean isAssigned=false;
    private boolean isReadyForImplementation=false;
    private Priority priority;
    private WorkItemDefinition workItemDefinition;

    public WorkItemDefinition getWorkItemDefinition() {
        return workItemDefinition;
    }

    public void setWorkItemDefinition(WorkItemDefinition workItemDefinition) {
        this.workItemDefinition = workItemDefinition;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public boolean isReadyForImplementation() {
        return isReadyForImplementation;
    }

    public void setReadyForImplementation(boolean readyForImplementation) {
        isReadyForImplementation = readyForImplementation;
    }


}
