package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.refactoring.condition.AreClassesCondition;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.IsFieldCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSuperClassOfCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForPullUpField;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PullUpField extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(PullUpField.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected AttributeObject attr;

    public PullUpField() {
        super();
    }

    public PullUpField(PullUpField pullUpField) {
        super(pullUpField);
    }

    public PullUpField(String sourceClass, String targetClass, String attributeName) {
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
        conditions.add(new IsSuperClassOfCondition(targetCls, sourceCls));
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

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        this.attr = new AttributeObject(attr);
        this.attr.setId(UUIDUtils.randomUUID());

        targetCls.getAttributes().add(attr);

        if (attr.isPrivate()) {
            attr.setVisibility(Visibility.PROTECTED);
        }

        for (String className : targetCls.getSubClasses()) {

            ClassObject subCls = ProjectObjectUtils.findByName(project, className);

            doPullUpdField(project, subCls, this.attributes.get(0));
        }
    }

    protected void doPullUpdField(ProjectObject project, ClassObject subCls, String attibuteName) {

        AttributeObject attr = AttributeObjectUtils.findByName(subCls, attibuteName);

        if (attr == null) {
            return;
        }

        subCls.getAttributes().remove(attr);

        // ========================================================================
        // Update the calls
        // ========================================================================

        CallGraph cg = project.getCallGraph();

        // Process Call Ins

        List<String> sourceIds = cg.getCallsTo(attr.getIdentifier());

        for (String sourceId : sourceIds) {
            cg.addReadCall(sourceId, this.attr.getIdentifier());
            cg.removeCall(sourceId, attr.getIdentifier());
        }

        cg.removeNode(attr.getIdentifier());
    }

    @Override
    public Refactoring copy() {
        return new PullUpField(this);
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
        return new DefineActorsForPullUpField();
    }

    @Override
    public String getName() {
        return "Pull Up Field";
    }
}
