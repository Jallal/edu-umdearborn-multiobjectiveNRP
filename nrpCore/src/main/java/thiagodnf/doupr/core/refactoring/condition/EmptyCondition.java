package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ProjectObject;

import java.util.List;

public class EmptyCondition extends Condition {

    protected List<?> list;

    public EmptyCondition(List<?> list) {
        this.list = list;
    }

    public boolean verify(ProjectObject project) {
        return list.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "The list cannot be empty";
    }
}
