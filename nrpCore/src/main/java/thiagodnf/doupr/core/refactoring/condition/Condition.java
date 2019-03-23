package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ProjectObject;

public abstract class Condition {

    protected boolean isNegate = false;

    public boolean validate(ProjectObject project) {

        if (isNegate()) {
            return !verify(project);
        }

        return verify(project);
    }

    public boolean isNegate() {
        return isNegate;
    }

    public void setNegate(boolean isNegate) {
        this.isNegate = isNegate;
    }

    public String getError() {

        String msg = getErrorMessage();

        if (isNegate()) {
            msg = msg.replaceAll("%DOHAVE%", " have ");
            msg = msg.replaceAll("%DOESHAVE%", " has ");
            msg = msg.replaceAll("%IS%", " is not ");
            msg = msg.replaceAll("%ARE%", " are not ");
        } else {
            msg = msg.replaceAll("%DOHAVE%", " don't have");
            msg = msg.replaceAll("%DOES%", " doesn't have");
            msg = msg.replaceAll("%IS%", " is ");
            msg = msg.replaceAll("%ARE%", " are ");
        }

        return msg;
    }

    public abstract boolean verify(ProjectObject project);

    protected abstract String getErrorMessage();
}
