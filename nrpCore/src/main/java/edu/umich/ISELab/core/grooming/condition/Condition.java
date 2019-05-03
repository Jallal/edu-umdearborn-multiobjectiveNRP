package edu.umich.ISELab.core.grooming.condition;

import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

public abstract class Condition {

    protected boolean isNegate = false;

    public boolean validate(WorkItem item, Person person ) {

        if (isNegate()) {
            return !verify(item, person);
        }

        return verify(item,person);
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

    public abstract boolean verify(WorkItem item, Person person );

    protected abstract String getErrorMessage();
}
