package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.IsMethodCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForIncreaseOrDecreaseMethodSecurity;
import thiagodnf.doupr.core.util.MethodObjectUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-08-13
 */
public class IncreaseMethodSecurity extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(IncreaseMethodSecurity.class);

    protected ClassObject sourceCls;

    protected MethodObject m;

    public IncreaseMethodSecurity() {
        super();
    }

    public IncreaseMethodSecurity(IncreaseMethodSecurity encapsulateField) {
        super(encapsulateField);
    }

    public IncreaseMethodSecurity(String className, String methodName) {
        super(className, null, new ArrayList<>(), Arrays.asList(methodName));
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.m = MethodObjectUtils.findByName(sourceCls, this.methods.get(0));
    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(m, this.methods.get(0)));
        conditions.add(new IsClassCondition(sourceCls));
        conditions.add(new IsMethodCondition(m));
        conditions.add(new DefineCondition(sourceCls, m));
        conditions.add(NOT(new HasVisibilityCondition(m, Visibility.PRIVATE)));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(m, this.methods.get(0)));
        conditions.add(new IsClassCondition(sourceCls));
        conditions.add(new IsMethodCondition(m));
        conditions.add(new DefineCondition(sourceCls, m));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        // ========================================================================
        // Perform the operation
        // ========================================================================

        if (m.isPublic()) {
            m.setVisibility(Visibility.PROTECTED);
        } else if (m.isProtected()) {
            m.setVisibility(Visibility.PRIVATE);
        }
    }

    @Override
    public Refactoring copy() {
        return new IncreaseMethodSecurity(this);
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
        return new DefineActorsForIncreaseOrDecreaseMethodSecurity();
    }

    @Override
    public String getName() {
        return "Increase Method Security";
    }
}
