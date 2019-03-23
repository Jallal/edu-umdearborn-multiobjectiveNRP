package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.DefineCondition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.HasVisibilityCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.IsFieldCondition;
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
public class IncreaseFieldSecurity extends Refactoring {

	protected static final Logger LOGGER = Logger.getLogger(IncreaseFieldSecurity.class);

	protected ClassObject sourceCls;

	protected AttributeObject attr;

	public IncreaseFieldSecurity() {
		super();
	}

	public IncreaseFieldSecurity(IncreaseFieldSecurity encapsulateField) {
		super(encapsulateField);
	}

	public IncreaseFieldSecurity(String className, String attributeName) {
		super(className, null, Arrays.asList(attributeName), new ArrayList<>());
	}

	@Override
	public void loadActors(ProjectObject project) {
		this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
		this.attr = AttributeObjectUtils.findByName(sourceCls, this.attributes.get(0));
	}

	@Override
	public List<Condition> getPreConditions(ProjectObject project) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(new ExistCondition(sourceCls, class1));
		conditions.add(new ExistCondition(attr, this.attributes.get(0)));
		conditions.add(new IsClassCondition(sourceCls));
		conditions.add(new IsFieldCondition(attr));
		conditions.add(new DefineCondition(sourceCls, attr));
		conditions.add(NOT(new HasVisibilityCondition(attr, Visibility.PRIVATE)));

		return conditions;
	}

	@Override
	public List<Condition> getPostConditions(ProjectObject project) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(new ExistCondition(sourceCls, class1));
		conditions.add(new ExistCondition(attr, this.attributes.get(0)));
		conditions.add(new IsClassCondition(sourceCls));
		conditions.add(new IsFieldCondition(attr));
		conditions.add(new DefineCondition(sourceCls, attr));

		return conditions;
	}

	@Override
	public void execute(ProjectObject project) {

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

		// ========================================================================
		// Perform the operation
		// ========================================================================

		if (attr.isPublic()) {
			attr.setVisibility(Visibility.PROTECTED);
		} else if (attr.isProtected()) {
			attr.setVisibility(Visibility.PRIVATE);
		}
	}

	@Override
	public Refactoring copy() {
		return new IncreaseFieldSecurity(this);
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
		return "Increase Field Security";
	}
}
