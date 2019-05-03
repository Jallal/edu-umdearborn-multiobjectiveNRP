package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.WorkItem;

import java.util.List;

public class EmptyCondition extends Condition {

    protected List<WorkItem> list;

    public EmptyCondition(List<WorkItem> list) {
        this.list = list;
    }

    public boolean verify(WorkItem item) {
        return list.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "The list cannot be empty";
    }
}
