package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.IsFieldCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineTasksForAssigingToDeveloper;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class InputTasks extends NrpBase {


    protected static final Logger LOGGER = Logger.getLogger(DecreaseFieldSecurity.class);

    protected ClassObject sourceCls;

    protected AttributeObject attr;

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));

    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {
        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new IsClassCondition(sourceCls));
        conditions.add(new IsFieldCondition(attr));
        conditions.add(new DefineCondition(sourceCls, attr));
        conditions.add(NOT(new HasVisibilityCondition(attr, Visibility.PUBLIC)));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(ProjectObject project) {
        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new IsClassCondition(sourceCls));
        conditions.add(new IsFieldCondition(attr));
        conditions.add(new DefineCondition(sourceCls, attr));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) throws Exception {
        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        if (attr.isPrivate()) {
            attr.setVisibility(Visibility.PROTECTED);
        } else if (attr.isProtected()) {
            attr.setVisibility(Visibility.PUBLIC);
        }

    }

    @Override
    public DefineActors getDefineActors() {
        return new DefineTasksForAssigingToDeveloper();
    }

    @Override
    public String getName() {
        return "Decrease Field Security";
    }

    @Override
    public NrpBase copy() {
        return new InputTasks(this);
    }

    @Override
    public ClassObject getSourceCls() {
        return null;
    }

    @Override
    public ClassObject getTargetCls() {
        return null;
    }
}
