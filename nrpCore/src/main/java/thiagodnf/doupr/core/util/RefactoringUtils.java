package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;

import java.util.ArrayList;
import java.util.List;


/**
 * This is an utility class for manage refactoring operations
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class RefactoringUtils {

    public static ProjectObject apply(ProjectObject project, List<Refactoring> refactorings) throws Exception {

        UUIDUtils.restart();

        ProjectObject copy = ProjectObjectUtils.copy(project);

        for (Refactoring refactoring : refactorings) {
            apply(copy, refactoring);
        }

        return copy;
    }

    public static void apply(ProjectObject project, Refactoring refactoring) throws Exception {

        refactoring.defineActors(project);

        refactoring.loadActors(project);

        refactoring.verifyPreConditions(project);

        refactoring.execute(project);

        refactoring.verifyPostCondition(project);
    }

    public static List<Refactoring> getValids(ProjectObject project, List<Refactoring> refactorings) {

        List<Refactoring> valids = new ArrayList<>();

        for (Refactoring refactoring : refactorings) {

            Refactoring copyRefactoring = refactoring.copy();

            try {
                RefactoringUtils.apply(project, copyRefactoring);
                valids.add(copyRefactoring);
            } catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
        }

        return valids;
    }
}
