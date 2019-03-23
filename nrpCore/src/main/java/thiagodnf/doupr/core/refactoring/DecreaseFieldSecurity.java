package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForIncreaseOrDecreaseFieldSecurity;
import thiagodnf.doupr.core.util.AttributeObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-13
 */
public class DecreaseFieldSecurity extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(DecreaseFieldSecurity.class);

    protected ClassObject sourceCls;

    protected AttributeObject attr;

    public DecreaseFieldSecurity() {
        super();
    }

    public DecreaseFieldSecurity(DecreaseFieldSecurity encapsulateField) {
        super(encapsulateField);
    }

    public DecreaseFieldSecurity(String className, String attributeName) {
        super(className, null, Arrays.asList(attributeName), new ArrayList<>());
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));
    }

    @Override
    public List<thiagodnf.doupr.core.refactoring.condition.Condition> getPreConditions(ProjectObject project) {

        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsFieldCondition(attr));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, attr));
        conditions.add(NOT(new thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition(attr, Visibility.PUBLIC)));

        return conditions;
    }

    @Override
    public List<thiagodnf.doupr.core.refactoring.condition.Condition> getPostConditions(ProjectObject project) {

        List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(attr, this.attributes.get(0)));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsFieldCondition(attr));
        conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, attr));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

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
    public Refactoring copy() {
        return new DecreaseFieldSecurity(this);
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
        return new DefineActorsForIncreaseOrDecreaseFieldSecurity();
    }

    @Override
    public String getName() {
        return "Decrease Field Security";
    }
}
