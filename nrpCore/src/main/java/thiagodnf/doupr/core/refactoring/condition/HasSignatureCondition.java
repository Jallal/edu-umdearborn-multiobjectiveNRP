package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.AttributeObject;

public class HasSignatureCondition extends Condition {

    protected thiagodnf.doupr.core.base.ClassObject targetCls;

    protected thiagodnf.doupr.core.base.ElementObject el;

    public HasSignatureCondition(thiagodnf.doupr.core.base.ClassObject targetCls, thiagodnf.doupr.core.base.ElementObject el) {
        this.targetCls = targetCls;
        this.el = el;
    }

    @Override
    public boolean verify(thiagodnf.doupr.core.base.ProjectObject project) {

        if (el instanceof AttributeObject && targetCls.hasSignature((AttributeObject) el)) {
            return true;
        } else if (el instanceof thiagodnf.doupr.core.base.MethodObject && targetCls.hasSignature((thiagodnf.doupr.core.base.MethodObject) el)) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The target class %DOESHAVE% the signature";
    }
}
