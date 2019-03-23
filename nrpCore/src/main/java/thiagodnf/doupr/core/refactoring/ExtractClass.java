package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.NumberOfMethodsCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForExtractClass;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Split a class into two classes by moving some methods and fields to a new
 * class. This refactoring can be applied when one class doing work that should
 * be done by two.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-21
 */
public class ExtractClass extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(ExtractClass.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    public ExtractClass() {
        super();
    }

    public ExtractClass(ExtractClass extractClass) {
        super(extractClass);
    }

    public ExtractClass(String class1, List<String> attributes, List<String> methods) {
        super(class1, null, attributes, methods);
    }

    @Override
    public void loadActors(ProjectObject project) {
        this.sourceCls = ProjectObjectUtils.findByName(project, this.class1);
        this.targetCls = null;
    }

    @Override
    public List<Condition> getPreConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(NOT(new ExistCondition(targetCls)));
        conditions.add(new IsClassCondition(sourceCls));
        conditions.add(new NumberOfMethodsCondition(sourceCls, 2));

        return conditions;
    }

    @Override
    public List<Condition> getPostConditions(ProjectObject project) {

        List<Condition> conditions = new ArrayList<>();

        conditions.add(new ExistCondition(sourceCls, class1));
        conditions.add(new ExistCondition(targetCls, class2));
        conditions.add(new IsClassCondition(targetCls));

        // Custom

//		conditions.add(new GreaterOrEqualsThanCondition(attributes.size() + methods.size(), 1));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        this.targetCls = new ClassObject();
        this.class2 = targetCls.getName();

        project.getPackage(sourceCls).getClasses().add(targetCls);

        // Create the reference attribute
        AttributeObject attr = new AttributeObject();

        attr.setType(targetCls.getName());
        attr.setName(targetCls.getSimpleName().toLowerCase());
        attr.setVisibility(Visibility.PROTECTED);

        this.sourceCls.getAttributes().add(attr);

        doMoveFields(project);
        doMoveMethods(project);
    }

    protected void doMoveFields(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String attributeName : this.attributes) {

            try {
                RefactoringUtils.apply(project, new MoveField(class1, class2, attributeName));
            } catch (Exception ex) {
                itemsToRemove.add(attributeName);
            }
        }

        for (String item : itemsToRemove) {
            this.attributes.remove(item);
        }
    }

    protected void doMoveMethods(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String methodName : this.methods) {

            try {
                RefactoringUtils.apply(project, new MoveMethod(class1, class2, methodName));
            } catch (Exception ex) {
                itemsToRemove.add(methodName);
            }
        }

        for (String item : itemsToRemove) {
            this.methods.remove(item);
        }
    }

    @Override
    public Refactoring copy() {
        return new ExtractClass(this);
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
        return new DefineActorsForExtractClass();
    }

    @Override
    public String getName() {
        return "Extract Class";
    }
}
