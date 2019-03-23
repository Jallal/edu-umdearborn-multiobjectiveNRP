package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasPolymorphismCondition;
import thiagodnf.doupr.core.refactoring.condition.HasSignatureCondition;
import thiagodnf.doupr.core.refactoring.condition.IsMethodCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSubClassOfCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForPushDownMethod;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushDownMethod extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(PushDownMethod.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected MethodObject m;

    public PushDownMethod() {
        super();
    }

    public PushDownMethod(PushDownMethod pullUpField) {
        super(pullUpField);
    }

    public PushDownMethod(String sourceClass, String targetClass, String methodName) {
        super(sourceClass, targetClass, new ArrayList<>(), Arrays.asList(methodName));
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.targetCls = ProjectObjectUtils.findByName(project, this.class2);
        this.m = MethodObjectUtils.findByName(sourceCls, this.methods.get(0));
    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(targetCls, class2));
        conditions.add(new ExistCondition(m, this.methods.get(0)));
        conditions.add(new AreClassesCondition(sourceCls, targetCls));
        conditions.add(new IsMethodCondition(m));
        conditions.add(new IsSubClassOfCondition(targetCls, sourceCls));
        conditions.add(new DefineCondition(sourceCls, m));
        conditions.add(NOT(new HasSignatureCondition(targetCls, m)));

        // Custom Conditions
        conditions.add(NOT(new HasPolymorphismCondition(sourceCls, m)));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(targetCls, class2));
        conditions.add(new ExistCondition(m, this.methods.get(0)));
        conditions.add(new DefineCondition(targetCls, m));
        conditions.add(NOT(new DefineCondition(sourceCls, m)));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        sourceCls.getMethods().remove(m);
        targetCls.getMethods().add(m);

        List<String> methodsToMove = new ArrayList<>();

        List<String> targetIds = project.getCallGraph().getCallsTo(m.getIdentifier());

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
        return new PushDownMethod(this);
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
        return new DefineActorsForPushDownMethod();
    }

    @Override
    public String getName() {
        return "Push Down Method";
    }
}
