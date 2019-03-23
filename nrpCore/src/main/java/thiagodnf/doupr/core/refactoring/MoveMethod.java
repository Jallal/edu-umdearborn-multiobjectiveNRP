package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ParameterObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasPolymorphismCondition;
import thiagodnf.doupr.core.refactoring.condition.HasSignatureCondition;
import thiagodnf.doupr.core.refactoring.condition.InheritanceHierarchyCondition;
import thiagodnf.doupr.core.refactoring.condition.IsMethodCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForMoveMethod;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveMethod extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(MoveMethod.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected MethodObject m;

    public MoveMethod() {
        super();
    }

    public MoveMethod(MoveMethod moveMethod) {
        super(moveMethod);
    }

    public MoveMethod(String sourceClass, String targetClass, String methodName) {
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
        conditions.add(NOT(new InheritanceHierarchyCondition(sourceCls, targetCls)));
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
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        // 2. Declare the new method in the recipient class. You may want to
        // give a new name for the method that is more appropriate for it in the
        // new class.

        MethodObject newMethod = new MethodObject(m);
        newMethod.setId(UUIDUtils.randomUUID());
        newMethod.getParameters().add(new ParameterObject(sourceCls.getName()));

        targetCls.getMethods().add(newMethod);


        // 3. Decide how you will refer to the recipient class. You may already
        // have a field or method that returns an appropriate object, but if
        // not, you will need to write a new method or field to store the object
        // of the recipient class.

        AttributeObject refAttr = new AttributeObject();

        refAttr.setType(targetCls.getName());
        refAttr.setId(UUIDUtils.randomUUID());
        refAttr.setVisibility(Visibility.PROTECTED);
        refAttr.setName(targetCls.getSimpleName().toLowerCase());

        if (!sourceCls.hasSignature(refAttr)) {
            sourceCls.getAttributes().add(refAttr);
        } else {
            refAttr = AttributeObjectUtils.findByName(sourceCls, targetCls.getSimpleName().toLowerCase());
        }

        // ========================================================================
        // Update the calls
        // ========================================================================

        CallGraph cg = project.getCallGraph();

        // Process Call Outs

        List<String> targetIds = cg.getCallsFrom(m.getIdentifier());

        for (String targetId : targetIds) {

            if (cg.isReadAccess(m.getIdentifier(), targetId)) {
                cg.addReadCall(newMethod.getIdentifier(), targetId);
            } else if (cg.isWriteAccess(m.getIdentifier(), targetId)) {
                cg.addWriteCall(newMethod.getIdentifier(), targetId);
            } else {
                cg.addCall(newMethod.getIdentifier(), targetId);
            }

            cg.removeCall(m.getIdentifier(), targetId);
        }

        cg.addReadCall(this.m.getIdentifier(), refAttr.getIdentifier());
        cg.addCall(this.m.getIdentifier(), newMethod.getIdentifier());

        this.m = newMethod;
    }

    @Override
    public Refactoring copy() {
        return new MoveMethod(this);
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
        return new DefineActorsForMoveMethod();
    }

    @Override
    public String getName() {
        return "Move Method";
    }
}
