package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class IsClassCondition extends Condition {

    protected Object object;

    public IsClassCondition(Object object) {
        this.object = object;
    }

    public boolean verify(ProjectObject project) {

        if (object instanceof ClassObject) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The actor is not a class";
    }
}
