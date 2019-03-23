package thiagodnf.doupr.core.refactoring;

import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.condition.Condition;
import thiagodnf.doupr.core.refactoring.condition.ExistCondition;
import thiagodnf.doupr.core.refactoring.condition.IsClassCondition;
import thiagodnf.doupr.core.refactoring.condition.IsSubClassOfCondition;
import thiagodnf.doupr.core.refactoring.condition.NumberOfMethodsCondition;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActors;
import thiagodnf.doupr.core.refactoring.defineactor.DefineActorsForExtractSubClass;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <b>Refactoring:</b> Extract Subclass
 * </p>
 *
 * <p>
 * <b>Why Refactor:</b> Your main class has methods and fields for implementing
 * a certain rare use case for the class. While the case is rare, the class is
 * responsible for it and it would be wrong to move all the associated fields
 * and methods to an entirely separate class. But they could be moved to a
 * subclass, which is just what we will do with the help of this refactoring
 * technique.
 * </p>
 *
 * <p>
 * <b>Benefits:</b></p>
 * <ul>
 * <li>Creates a subclass quickly and easily.</li>
 * <li>You can create several separate subclasses if your main class is
 * currently implementing more than one such special case.</li>
 * </ul>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-21
 */
public class ExtractSubClass extends Refactoring {

    protected static final Logger LOGGER = Logger.getLogger(ExtractSubClass.class);

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    public ExtractSubClass() {
        super();
    }

    public ExtractSubClass(ExtractSubClass extractClass) {
        super(extractClass);
    }

    public ExtractSubClass(String class1, List<String> attributes, List<String> methods) {
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
        conditions.add(new IsSubClassOfCondition(targetCls, sourceCls));

        // Custom

//		conditions.add(new GreaterOrEqualsThanCondition(attributes.size() + methods.size(), 1));

        return conditions;
    }

    @Override
    public void execute(ProjectObject project) {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Executing " + toString());

        this.targetCls = new ClassObject();
        targetCls.setSuperClass(sourceCls.getName());
        targetCls.getSuperClasses().add(sourceCls.getName());
        this.class2 = targetCls.getName();

        // Change the subclass

        sourceCls.getSubClasses().add(targetCls.getName());

        project.getPackage(sourceCls).getClasses().add(targetCls);

        doPushDownField(project);
        doPushDownMethod(project);
    }

    protected void doPushDownField(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String attributeName : this.attributes) {

            try {
                RefactoringUtils.apply(project, new PushDownField(class1, class2, attributeName));
            } catch (Exception ex) {
                itemsToRemove.add(attributeName);
            }
        }

        for (String item : itemsToRemove) {
            this.attributes.remove(item);
        }
    }

    protected void doPushDownMethod(ProjectObject project) {

        List<String> itemsToRemove = new ArrayList<>();

        for (String methodName : this.methods) {

            try {
                RefactoringUtils.apply(project, new PushDownMethod(class1, class2, methodName));
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
        return new ExtractSubClass(this);
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
        return new DefineActorsForExtractSubClass();
    }

    @Override
    public String getName() {
        return "Extract Sub Class";
    }
}
