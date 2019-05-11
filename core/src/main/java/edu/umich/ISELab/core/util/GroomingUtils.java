package edu.umich.ISELab.core.util;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.Grooming;

import java.util.ArrayList;
import java.util.List;

public class GroomingUtils {
    public static Project apply(Project project, List<Grooming> refactorings) throws Exception {

        UUIDUtils.restart();

        Project copy = ProjectObjectUtils.copy(project);

        for (Grooming refactoring : refactorings) {
            apply(copy, refactoring);
        }

        return copy;
    }

    public static void apply(Project project, Grooming refactoring) throws Exception {

        refactoring.defineActors(project);

        refactoring.loadActors(project);

        refactoring.verifyPreConditions(project);

        refactoring.execute(project);

        refactoring.verifyPostCondition(project);
    }

    public static List<Grooming> getValids(Project project, List<Grooming> refactorings) {

        List<Grooming> valids = new ArrayList<>();

        for (Grooming refactoring : refactorings) {

            Grooming copyRefactoring = refactoring.copy();

            try {
                GroomingUtils.apply(project, copyRefactoring);
                valids.add(copyRefactoring);
            } catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
        }

        return valids;
    }
}
