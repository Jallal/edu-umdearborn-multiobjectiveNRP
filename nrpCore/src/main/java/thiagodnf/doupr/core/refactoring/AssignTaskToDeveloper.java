package thiagodnf.doupr.core.refactoring;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineTasksForAssigingToDeveloper;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignTaskToDeveloper extends NrpBase{

    protected static final Logger LOGGER = Logger.getLogger(AssignTaskToDeveloper.class);

    protected ClassObject sourceCls;

    protected AttributeObject attr;

    public AssignTaskToDeveloper() {
        super();
    }

    public AssignTaskToDeveloper(AssignTaskToDeveloper assignTaskToDeveloper) {
        super(assignTaskToDeveloper);
    }

    public AssignTaskToDeveloper(String className, String attributeName) {
        super(className, null, Arrays.asList(attributeName), new ArrayList<>());
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));
    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {

        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsFieldCondition(attr));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, attr));
        //conditions.add(new thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition(attr, Visibility.PUBLIC));

        return conditions;
    }

    @Override
    public List<thiagodnf.doupr.core.refactoring.condition.Condition> getPostConditions(ProjectObject project) {

        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsFieldCondition(attr));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, attr));
        //conditions.add(new thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition(attr, Visibility.PRIVATE));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Creating a getter and setter for this attribute");

        // Step 1: Create a getter and setter for the field.

        MethodObject getMethod = MethodObjectUtils.createGetter(sourceCls, attr);
        MethodObject setMethod = MethodObjectUtils.createSetter(sourceCls, attr);

        boolean hasGetter = sourceCls.hasSignature(getMethod);
        boolean hasSetter = sourceCls.hasSignature(setMethod);

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Does it already have a getter? " + hasGetter);
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Does it already have a setter? " + hasSetter);

        if (hasGetter) {
            getMethod = MethodObjectUtils.findByName(sourceCls, "get" + StringUtils.capitalize(attr.getName()));
        } else {
            sourceCls.getMethods().add(getMethod);
        }

        if (hasSetter) {
            setMethod = MethodObjectUtils.findByName(sourceCls, "set" + StringUtils.capitalize(attr.getName()));
        } else {
            sourceCls.getMethods().add(setMethod);
        }

        // Step 3: After all field invocations have been replaced, make the
        // field private.
       // attr.setVisibility(Visibility.PRIVATE);

        // ========================================================================
        // Update the calls
        // ========================================================================

        CallGraph cg = project.getCallGraph();

        // Now, all methods that read this attribute will call the getter method

        List<String> targetIds = cg.getCallsTo(attr.getIdentifier());

        for (String targetId : targetIds) {

            if (cg.isReadAccess(targetId, attr.getIdentifier())) {
                cg.addCall(targetId, getMethod.getIdentifier());
            }

            if (cg.isWriteAccess(targetId, attr.getIdentifier())) {
                cg.addCall(targetId, setMethod.getIdentifier());
            }

            cg.removeCall(targetId, attr.getIdentifier());
        }

        cg.addNode(attr.getIdentifier());
        cg.addReadCall(getMethod.getIdentifier(), attr.getIdentifier());
        cg.addWriteCall(setMethod.getIdentifier(), attr.getIdentifier());
    }

    @Override
    public NrpBase copy() {
        return new AssignTaskToDeveloper(this);
    }

    @Override
    public ClassObject getSourceCls() {
        return sourceCls;
    }

    @Override
    public ClassObject getTargetCls() {
        return null;
    }

    @Override
    public DefineActors getDefineActors() {
        return new DefineTasksForAssigingToDeveloper();
    }

    @Override
    public String getName() {
        return "Encapsulate Field";
    }
}
