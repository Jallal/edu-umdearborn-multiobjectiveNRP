package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.WorkItem;

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
