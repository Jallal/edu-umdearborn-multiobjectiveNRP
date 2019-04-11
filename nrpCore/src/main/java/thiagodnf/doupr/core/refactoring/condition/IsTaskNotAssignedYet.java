package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class IsTaskNotAssignedYet extends Condition{
    protected ClassObject targetCls;

    protected ClassObject sourceCls;

    public IsTaskNotAssignedYet(ClassObject targetCls, ClassObject sourceCls) {
        this.targetCls = targetCls;
        this.sourceCls = sourceCls;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (sourceCls.getSuperClass() == null || sourceCls.getSuperClass().isEmpty()) {
            return false;
        }

        if (sourceCls.getSuperClass().equalsIgnoreCase(targetCls.getName())) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("The '%s' class is not super class of '%s' class", targetCls.getSimpleName(), sourceCls.getSimpleName());
    }
}
