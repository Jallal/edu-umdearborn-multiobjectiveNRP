package edu.umich.ISELab.core.projectResources;

import edu.umich.ISELab.core.backlog.WorkItem;

public class Person {
   private boolean isAssigned=false;
   private WorkItem item;

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


}
