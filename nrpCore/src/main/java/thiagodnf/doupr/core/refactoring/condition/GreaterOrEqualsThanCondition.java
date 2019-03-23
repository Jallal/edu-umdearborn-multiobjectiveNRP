package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ProjectObject;

public class GreaterOrEqualsThanCondition extends Condition {

    protected int size;

    protected int max;

    public GreaterOrEqualsThanCondition(int size, int max) {
        this.size = size;
        this.max = max;
    }

    public boolean verify(ProjectObject project) {
        return size >= max;
    }

    @Override
    public String getErrorMessage() {
        return "The size should be greater than " + max;
    }
}
