package edu.umich.ISELab.core.grooming;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.condition.Condition;
import edu.umich.ISELab.core.grooming.condition.DefineCondition;
import edu.umich.ISELab.core.grooming.condition.ExistCondition;
import edu.umich.ISELab.core.grooming.defineactor.DefineActors;
import edu.umich.ISELab.core.projectResources.Person;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignTask extends NrpBase {

    protected static final Logger LOGGER = Logger.getLogger(AssignTask.class);

    protected WorkItem activeItem;
    protected Person person;

    //protected AttributeObject attr;

    public AssignTask() {
        super();
    }

    public AssignTask(AssignTask assignTask) {
        super(assignTask);
    }

    public AssignTask(WorkItem item , Person person) {
        super(item,person);
    }

    @Override
    public void loadActors(Project project) {
        //this.sourceCls = ProjectObjectUtils.findByType(project, this.class1);
        //this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));
    }

    @Override
    public List<Condition> getPreConditions(WorkItem item, Person person) {

        List<Condition> conditions = new ArrayList<>();
        conditions.add(new ExistCondition(activeItem, person));
        conditions.add(new DefineCondition(activeItem, person));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(WorkItem project) {
        return null;
    }

    /*@Override
    public List<Condition> getPostConditions(Project project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        // conditions.add(new DefineCondition(sourceCls, attr));
        //conditions.add(new thiagodnf.doupr.core.grooming.condition.HasVisibilityCondition(attr, Visibility.PRIVATE));

        return conditions;
    }*/

    @Override
    public void execute(Project project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Creating a getter and setter for this attribute");

        // Step 1: Create a getter and setter for the field.

        // MethodObject getMethod = MethodObjectUtils.createGetter(sourceCls, attr);
        //MethodObject setMethod = MethodObjectUtils.createSetter(sourceCls, attr);

        //boolean hasGetter = sourceCls.hasSignature(getMethod);
        //boolean hasSetter = sourceCls.hasSignature(setMethod);

        /*if (LOGGER.isDebugEnabled()) LOGGER.debug("Does it already have a getter? " + hasGetter);
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
        cg.addWriteCall(setMethod.getIdentifier(), attr.getIdentifier());*/
    }

    @Override
    public DefineActors getDefineActors() {
        return null;
    }

    @Override
    public NrpBase copy() {
        return new AssignTask(this);
    }

    @Override
    public WorkItem getSourceCls() {
        return sourceCls;
    }

    @Override
    public WorkItem getTargetCls() {
        return null;
    }

    /*@Override
    public DefineActors getDefineActors() {
        return new DefineTasksForAssigingToDeveloper();
    }*/

    @Override
    public String getName() {
        return "Encapsulate Field";
    }
}
