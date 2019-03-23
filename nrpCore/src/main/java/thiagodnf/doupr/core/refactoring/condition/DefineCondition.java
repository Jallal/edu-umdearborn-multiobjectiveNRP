package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;

public class DefineCondition extends Condition {

    protected ClassObject cls;

    protected ElementObject el;

    public DefineCondition(ClassObject cls, ElementObject el) {
        this.cls = cls;
        this.el = el;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (el instanceof AttributeObject) {
            return verifyAttribute(project, (AttributeObject) el);
        }

        if (el instanceof MethodObject) {
            return verifyMethod(project, (MethodObject) el);
        }

        return false;
    }

    protected boolean verifyAttribute(ProjectObject project, AttributeObject attr) {

        for (AttributeObject a : cls.getAttributes()) {
            if (a == attr) {
                return true;
            }
        }

        return false;
    }

    protected boolean verifyMethod(ProjectObject project, MethodObject m) {

        for (MethodObject method : cls.getMethods()) {
            if (method == m) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The class does not define the attribute or method";
    }
}
