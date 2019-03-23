package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSuperClassOfCondition;
import thiagodnf.doupr.core.refactoring.condition.NumberOfMethodsCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForExtractSuperClass;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <b>Refactoring:</b> Extract Superclass
 * </p>
 *
 * <p>
 * <b>Why Refactor:</b> One type of code duplication occurs when two classes
 * perform similar tasks in the same way, or perform similar tasks in different
 * ways. Objects offer a built-in mechanism for simplifying such situations via
 * inheritance. But oftentimes this similarity remains unnoticed until classes
 * are created, necessitating that an inheritance structure be created later.
 * </p>
 *
 * <p><b>Benefits:</b></p>
 * <ul>
 * <li>Code deduplication. Common fields and methods now "live" in one place only.</li>
 * </ul>
 *
 *
 * <p><b>When Not to Use:</b></p>
 * <ul>
 * <li>You can not apply this technique to classes that already have a superclass.</li>
 * </ul>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-21
 */
public class ExtractSuperClass extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(ExtractSuperClass.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    public ExtractSuperClass() {
        super();
    }

    public ExtractSuperClass(ExtractSuperClass extractClass) {
        super(extractClass);
    }

    public ExtractSuperClass(String class1, List<String> attributes, List<String> methods) {
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

        conditions.add(new ExistCondition(sourceCls));
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
        conditions.add(new IsSuperClassOfCondition(targetCls, sourceCls));

        // Custom

//		conditions.add(new GreaterOrEqualsThanCondition(attributes.size() + methods.size(), 1));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        this.targetCls = new ClassObject();
        this.class2 = targetCls.getName();

        sourceCls.setSuperClass(targetCls.getName());
        sourceCls.getSuperClasses().add(targetCls.getName());

        targetCls.getSubClasses().add(sourceCls.getName());

        project.getPackage(sourceCls).getClasses().add(targetCls);

        doPullUpField(project);
        doPullUpMethod(project);
    }

    protected void doPullUpField(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String attributeName : this.attributes) {

            try {
                RefactoringUtils.apply(project, new PullUpField(class1, class2, attributeName));
            } catch (Exception ex) {
                itemsToRemove.add(attributeName);
            }
        }

        for (String item : itemsToRemove) {
            this.attributes.remove(item);
        }
    }

    protected void doPullUpMethod(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String methodName : this.methods) {

            try {
                RefactoringUtils.apply(project, new PullUpMethod(class1, class2, methodName));
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
        return new ExtractSuperClass(this);
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
        return new DefineActorsForExtractSuperClass();
    }

    @Override
    public String getName() {
        return "Extract Super Class";
    }
}
