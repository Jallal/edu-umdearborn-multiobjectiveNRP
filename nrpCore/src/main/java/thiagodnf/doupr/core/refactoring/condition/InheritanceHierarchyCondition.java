package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;

import java.util.ArrayList;
import java.util.List;

public class InheritanceHierarchyCondition extends Condition {

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    public InheritanceHierarchyCondition(ClassObject sourceCls, ClassObject targetCls) {
        this.sourceCls = sourceCls;
        this.targetCls = targetCls;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (sourceCls == null || targetCls == null) {
            return false;
        }

        List<String> superClsFromSource = new ArrayList<>(sourceCls.getSuperClasses());
        List<String> superClsFromTarget = new ArrayList<>(targetCls.getSuperClasses());

        superClsFromSource.add(sourceCls.getName());
        superClsFromTarget.add(targetCls.getName());

        for (String superCls : superClsFromSource) {

            if (superClsFromTarget.contains(superCls)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The classes %DOHAVE%the same root class";
    }
}
