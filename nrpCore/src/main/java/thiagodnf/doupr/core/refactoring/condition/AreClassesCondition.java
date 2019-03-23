package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ProjectObject;

import java.util.ArrayList;
import java.util.List;

public class AreClassesCondition extends Condition {

    protected String sourceClass;

    protected String targetClass;

    protected Object o1;

    protected Object o2;

    public AreClassesCondition(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public AreClassesCondition(String sourceClass, String targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public boolean verify(ProjectObject project) {

        List<Boolean> results = new ArrayList<>();

        results.add(new IsClassCondition(o1).verify(project));
        results.add(new IsClassCondition(o2).verify(project));

        return !results.contains(false);
    }

    @Override
    public String getErrorMessage() {
        return String.format("The actors '%' and '%' are not a class", sourceClass, targetClass);
    }
}
