package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasPolymorphismCondition;
import thiagodnf.doupr.core.refactoring.condition.HasSignatureCondition;
import thiagodnf.doupr.core.refactoring.condition.IsMethodCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSuperClassOfCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForPullUpMethod;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PullUpMethod extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(PullUpMethod.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected MethodObject m;

    public PullUpMethod() {
        super();
    }

    public PullUpMethod(PullUpMethod pullUpMethod) {
        super(pullUpMethod);
    }

    public PullUpMethod(String sourceClass, String targetClass, String methodName) {
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
        conditions.add(new IsSuperClassOfCondition(targetCls, sourceCls));
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

        // ========================================================================
        // Perform the operation
        // ========================================================================

        this.m = new MethodObject(m);
        this.m.setId(UUIDUtils.randomUUID());

        targetCls.getMethods().add(m);

        for (String className : targetCls.getSubClasses()) {

            ClassObject subCls = ProjectObjectUtils.findByName(project, className);

            doPullUpMethod(project, subCls, this.methods.get(0));
        }
    }

    protected void doPullUpMethod(ProjectObject project, ClassObject subCls, String methodName) throws Exception {

        MethodObject m = MethodObjectUtils.findByName(subCls, methodName);

        if (m == null) {
            return;
        }

        subCls.getMethods().remove(m);

        // ========================================================================
        // Get the methods and attributes that we have to move
        // ========================================================================

        List<String> methodsToMove = new ArrayList<>();
        List<String> attributesToMove = new ArrayList<>();

        List<String> targetIds = project.getCallGraph().getCallsFrom(m.getIdentifier());

        for (MethodObject method : subCls.getMethods()) {

            if (targetIds.contains(method.getIdentifier())) {
                methodsToMove.add(method.getName());
            }
        }

        for (AttributeObject attr : subCls.getAttributes()) {

            if (targetIds.contains(attr.getIdentifier())) {
                attributesToMove.add(attr.getName());
            }
        }

        for (String attribute : attributesToMove) {
            RefactoringUtils.apply(project, new PullUpField(class1, class2, attribute));
        }

        for (String method : methodsToMove) {
            RefactoringUtils.apply(project, new PullUpMethod(class1, class2, method));
        }

        // ========================================================================
        // Update the calls
        // ========================================================================

        CallGraph cg = project.getCallGraph();

        // Process Call Outs

        targetIds = cg.getCallsFrom(m.getIdentifier());

        for (String targetId : targetIds) {

            if (cg.isReadAccess(m.getIdentifier(), targetId)) {
                cg.addReadCall(this.m.getIdentifier(), targetId);
            } else if (cg.isWriteAccess(m.getIdentifier(), targetId)) {
                cg.addWriteCall(this.m.getIdentifier(), targetId);
            } else {
                cg.addCall(this.m.getIdentifier(), targetId);
            }

            cg.removeCall(m.getIdentifier(), targetId);
        }

        // Process Call Ins

        List<String> sourceIds = cg.getCallsTo(m.getIdentifier());

        for (String sourceId : sourceIds) {
            cg.addReadCall(sourceId, this.m.getIdentifier());
            cg.removeCall(sourceId, m.getIdentifier());
        }

        cg.removeNode(m.getIdentifier());
    }

    @Override
    public Refactoring copy() {
        return new PullUpMethod(this);
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
        return new DefineActorsForPullUpMethod();
    }

    @Override
    public String getName() {
        return "Pull Up Method";
    }
}
