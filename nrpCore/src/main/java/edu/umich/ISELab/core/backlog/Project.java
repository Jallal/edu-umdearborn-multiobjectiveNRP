package edu.umich.ISELab.core.backlog;

import java.util.ArrayList;
import java.util.List;

public class Project {
    List<WorkItem> workItemList= new ArrayList<>();


    public List<WorkItem> getWorkItemList() {
        return workItemList;
    }

    public void setWorkItemList(List<WorkItem> workItemList) {
        this.workItemList = workItemList;
    }


}
