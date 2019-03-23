package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class IsMethodCondition extends Condition {

    protected Object object;

    public IsMethodCondition(Object object) {
        this.object = object;
    }

    public boolean verify(ProjectObject projects) {

        if (object instanceof MethodObject) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("The actors is not a method");
    }
}
