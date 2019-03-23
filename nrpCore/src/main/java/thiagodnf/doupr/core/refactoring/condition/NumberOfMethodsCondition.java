package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class NumberOfMethodsCondition extends Condition {

    protected ClassObject targetCls;

    protected int minValue;

    public NumberOfMethodsCondition(ClassObject targetCls, int minValue) {
        this.targetCls = targetCls;
        this.minValue = minValue;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (targetCls == null) {
            return false;
        }

        if (targetCls.getMethods().size() >= minValue) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The elements cannot be empty or null";
    }
}
