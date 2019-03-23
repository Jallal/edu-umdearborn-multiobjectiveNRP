package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.IsFieldCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSubClassOfCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForPushDownField;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushDownField extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(PushDownField.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected AttributeObject attr;

    public PushDownField() {
        super();
    }

    public PushDownField(PushDownField pullUpField) {
        super(pullUpField);
    }

    public PushDownField(String sourceClass, String targetClass, String attributeName) {
        super(sourceClass, targetClass, Arrays.asList(attributeName), new ArrayList<>());
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.targetCls = ProjectObjectUtils.findByName(project, this.class2);
        this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));
    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(targetCls, class2));
        conditions.add(new ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new AreClassesCondition(sourceCls, targetCls));
        conditions.add(new IsFieldCondition(attr));
        conditions.add(new IsSubClassOfCondition(targetCls, sourceCls));
        conditions.add(new DefineCondition(sourceCls, attr));
        conditions.add(NOT(new DefineCondition(targetCls, attr)));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(targetCls, class2));
        conditions.add(new ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new DefineCondition(targetCls, attr));
        conditions.add(NOT(new DefineCondition(sourceCls, attr)));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        sourceCls.getAttributes().remove(attr);
        targetCls.getAttributes().add(attr);

        // ========================================================================
        // Get the methods we have to move
        // ========================================================================

        List<String> methodsToMove = new ArrayList<>();

        List<String> targetIds = project.getCallGraph().getCallsTo(attr.getIdentifier());

        for (MethodObject method : sourceCls.getMethods()) {

            if (targetIds.contains(method.getIdentifier())) {
                methodsToMove.add(method.getName());
            }
        }

        for (String method : methodsToMove) {
            RefactoringUtils.apply(project, new PushDownMethod(class1, class2, method));
        }
    }

    @Override
    public Refactoring copy() {
        return new PushDownField(this);
    }

    @Override
    public ClassObject getSourceCls() {
        return sourceCls;
    }

    @Override
    public ClassObject getTargetCls() {
        return targetCls;
    }

    @Override
    public DefineActors getDefineActors() {
        return new DefineActorsForPushDownField();
    }

    @Override
    public String getName() {
        return "Push Down Field";
    }
}
