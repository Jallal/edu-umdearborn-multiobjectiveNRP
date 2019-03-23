package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class IsSubClassOfCondition extends Condition {

    protected ClassObject targetCls;

    protected ClassObject sourceCls;

    public IsSubClassOfCondition(ClassObject targetCls, ClassObject sourceCls) {
        this.targetCls = targetCls;
        this.sourceCls = sourceCls;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (targetCls.getSuperClass() == null || targetCls.getSuperClass().isEmpty()) {
            return false;
        }

        if (targetCls.getSuperClass().equalsIgnoreCase(sourceCls.getName())) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The class is not the sub class";
    }
}
