package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ProjectObject;

public class ExistCondition extends Condition {

    protected Object object;

    protected String element;

    public ExistCondition(Object object) {
        this(object, "undefined");
    }

    public ExistCondition(Object object, String element) {
        this.object = object;
        this.element = element;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (object == null) {
            return false;
        }

        return true;
    }

    @Override
    protected String getErrorMessage() {
        return "The " + element + "%IS%required";
    }
}
