package edu.umich.ISELab.core.projectResources;


import edu.umich.ISELab.core.backlog.WorkItem;

public class Person {
   private boolean isAssigned=false;
   private WorkItem item;

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

   private String name;
   private String occupation;

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
