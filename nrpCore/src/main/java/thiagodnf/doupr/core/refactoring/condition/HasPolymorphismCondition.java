package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.util.ProjectObjectUtils;

public class HasPolymorphismCondition extends Condition {

    protected ClassObject sourceClass;

    protected MethodObject m;

    public HasPolymorphismCondition(ClassObject sourceClass, MethodObject m) {
        this.sourceClass = sourceClass;
        this.m = m;
    }

    @Override
    public boolean verify(ProjectObject project) {

        for (String className : sourceClass.getSuperClasses()) {

            ClassObject superCls = ProjectObjectUtils.findByName(project, className);

            if (superCls.hasSignature(m)) {
                return true;
            }
        }

        for (String className : sourceClass.getSubClasses()) {

            ClassObject subCls = ProjectObjectUtils.findByName(project, className);

            if (subCls.hasSignature(m)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The method %DOESHAVE%polymorphism";
    }
}
