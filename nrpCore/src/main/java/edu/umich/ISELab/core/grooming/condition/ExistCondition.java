package edu.umich.ISELab.core.grooming.condition;

import edu.umich.ISELab.core.backlog.WorkItem;

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
    public boolean verify(WorkItem project) {

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
