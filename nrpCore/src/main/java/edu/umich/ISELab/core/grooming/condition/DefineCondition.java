package edu.umich.ISELab.core.grooming.condition;

import edu.umich.ISELab.core.backlog.WorkItem;

public class DefineCondition extends Condition {

    private WorkItem  workItem;


    public DefineCondition(WorkItem item) {

        this.workItem = item;
    }

    @Override
    public boolean verify(WorkItem item) {

        if (item instanceof WorkItem) {
            return verifyItemIsAvailable(item);
        }

        return false;
    }

    protected boolean verifyItemIsAvailable(WorkItem item) {
        if (!item.isAssigned()) {
                return true;

        }

        return false;
    }


    @Override
    public String getErrorMessage() {
        return "The item can't be assigned";
    }
}
