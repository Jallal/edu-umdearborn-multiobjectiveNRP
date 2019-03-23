package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class HasTheTypeCondition extends Condition {

    protected AttributeObject attr;

    protected ClassObject typeCls;

    public HasTheTypeCondition(AttributeObject attr, ClassObject typeCls) {
        this.attr = attr;
        this.typeCls = typeCls;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (attr.getType().equalsIgnoreCase(typeCls.getName())) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The type of the attribute is not ";
    }
}
