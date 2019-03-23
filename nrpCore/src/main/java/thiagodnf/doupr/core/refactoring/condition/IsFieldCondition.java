package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class IsFieldCondition extends Condition {

    protected Object object;

    public IsFieldCondition(Object object) {
        this.object = object;
    }

    public boolean verify(ProjectObject project) {

        if (object instanceof AttributeObject) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("The actors is not a field");
    }
}
