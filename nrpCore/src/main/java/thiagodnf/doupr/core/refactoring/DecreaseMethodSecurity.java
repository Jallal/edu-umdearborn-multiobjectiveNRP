package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ProjectObject;
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
public class DecreaseMethodSecurity extends Refactoring {

	protected static final Logger LOGGER = Logger.getLogger(DecreaseMethodSecurity.class);

	protected ClassObject sourceCls;

	protected MethodObject m;

	public DecreaseMethodSecurity() {
		super();
	}

	public DecreaseMethodSecurity(DecreaseMethodSecurity encapsulateField) {
		super(encapsulateField);
	}

	public DecreaseMethodSecurity(String className, String methodName) {
		super(className, null, new ArrayList<>(), Arrays.asList(methodName));
	}

	@Override
	public void loadActors(ProjectObject project) {
		this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
		this.m = MethodObjectUtils.findByName(sourceCls, this.methods.get(0));
	}

	@Override
	public List<thiagodnf.doupr.core.refactoring.condition.Condition> getPreConditions(ProjectObject project) {

		List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

		conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(m, this.methods.get(0)));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsMethodCondition(m));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, m));
		conditions.add(NOT(new thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition(m, Visibility.PUBLIC)));

		return conditions;
	}

	@Override
	public List<thiagodnf.doupr.core.refactoring.condition.Condition> getPostConditions(ProjectObject project) {

		List<thiagodnf.doupr.core.refactoring.condition.Condition> conditions = new ArrayList<>();

		conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(sourceCls, class1));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.ExistCondition(m, this.methods.get(0)));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsClassCondition(sourceCls));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.IsMethodCondition(m));
		conditions.add(new thiagodnf.doupr.core.refactoring.condition.DefineCondition(sourceCls, m));

		return conditions;
	}

	@Override
	public void execute(ProjectObject project) {

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

		// ========================================================================
		// Perform the operation
		// ========================================================================

		if (m.isPrivate()) {
			m.setVisibility(Visibility.PROTECTED);
		} else if (m.isProtected()) {
			m.setVisibility(Visibility.PUBLIC);
		}
	}

	@Override
	public Refactoring copy() {
		return new DecreaseMethodSecurity(this);
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
		return "Decrease Method Security";
	}
}
