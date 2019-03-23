package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasSignatureCondition;
import thiagodnf.doupr.core.refactoring.condition.InheritanceHierarchyCondition;
import thiagodnf.doupr.core.refactoring.condition.IsFieldCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForMoveField;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveField extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(MoveField.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected AttributeObject attr;

    public MoveField() {
        super();
    }

    public MoveField(MoveField moveField) {
        super(moveField);
    }

    public MoveField(String sourceClass, String targetClass, String attributeName) {
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
        conditions.add(NOT(new InheritanceHierarchyCondition(sourceCls, targetCls)));
        conditions.add(new DefineCondition(sourceCls, attr));
        conditions.add(NOT(new DefineCondition(targetCls, attr)));

        // Custom Conditions
        conditions.add(NOT(new HasSignatureCondition(targetCls, attr)));

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

        // Step 1. If the field is public, refactoring will be much easier if
        // you make the field private and provide public access methods (for
        // this, you can use Encapsulate Field).

        if (attr.isPublic()) {
            RefactoringUtils.apply(project, new EncapsulateField(class1, this.attributes.get(0)));
        }

        // Step 2. Create the same field with access methods in the recipient class.

        AttributeObject newAttr = new AttributeObject(attr);
        newAttr.setId(UUIDUtils.randomUUID());

        targetCls.getAttributes().add(newAttr);

        MethodObject getMethod = MethodObjectUtils.createGetter(targetCls, newAttr);
        MethodObject setMethod = MethodObjectUtils.createSetter(targetCls, newAttr);

        CallGraph cg = project.getCallGraph();

        cg.addReadCall(getMethod.getIdentifier(), newAttr.getIdentifier());
        cg.addWriteCall(setMethod.getIdentifier(), newAttr.getIdentifier());

        targetCls.getMethods().add(getMethod);
        targetCls.getMethods().add(setMethod);

        // Step 3. Decide how you will refer to the recipient class. You may already
        // have a field or method that returns an appropriate object, but if
        // not, you will need to write a new method or field to store the object
        // of the recipient class.

        AttributeObject refAttr = new AttributeObject();

        refAttr.setType(targetCls.getName());
        refAttr.setVisibility(Visibility.PROTECTED);
        refAttr.setName(targetCls.getSimpleName().toLowerCase());

        if (!sourceCls.hasSignature(refAttr)) {
            sourceCls.getAttributes().add(refAttr);
        } else {
            refAttr = AttributeObjectUtils.findByName(sourceCls, targetCls.getSimpleName().toLowerCase());
        }

        // Step 4. Replace all references to the old field with appropriate
        // calls to methods in the recipient class. If the field is not private,
        // take care of this in the superclass and subclasses.

        List<String> sourceIds = cg.getCallsTo(attr.getIdentifier());

        for (String targetId : sourceIds) {

            if (cg.isReadAccess(targetId, attr.getIdentifier())) {
                cg.addReadCall(targetId, getMethod.getIdentifier());
            } else if (cg.isWriteAccess(targetId, attr.getIdentifier())) {
                cg.addWriteCall(targetId, setMethod.getIdentifier());
            }

            cg.removeCall(targetId, attr.getIdentifier());
        }

        // Step 5. Delete the field in the original class.

        sourceCls.getAttributes().remove(attr);
        cg.removeNode(attr.getIdentifier());

        this.attr = newAttr;
    }

    @Override
    public Refactoring copy() {
        return new MoveField(this);
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
        return new DefineActorsForMoveField();
    }

    @Override
    public String getName() {
        return "Move Field";
    }
}
