package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;

import java.util.ArrayList;
import java.util.List;


/**
 * This is an utility class for manage refactoring operations
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class NrpUtils {

    public static ProjectObject apply(ProjectObject project, List<NrpBase> refactorings) throws Exception {

        UUIDUtils.restart();

        ProjectObject copy = ProjectObjectUtils.copy(project);

        for (NrpBase refactoring : refactorings) {
            apply(copy, refactoring);
        }

        return copy;
    }

    public static void apply(ProjectObject project, NrpBase refactoring) throws Exception {

        refactoring.defineActors(project);

        refactoring.loadActors(project);

        refactoring.verifyPreConditions(project);

        refactoring.execute(project);

        refactoring.verifyPostCondition(project);
    }

    public static List<NrpBase> getValids(ProjectObject project, List<NrpBase> refactorings) {

        List<NrpBase> valids = new ArrayList<>();

        for (NrpBase refactoring : refactorings) {

            NrpBase copyRefactoring = refactoring.copy();

            try {
                NrpUtils.apply(project, copyRefactoring);
                valids.add(copyRefactoring);
            } catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
        }

        return valids;
    }
}
