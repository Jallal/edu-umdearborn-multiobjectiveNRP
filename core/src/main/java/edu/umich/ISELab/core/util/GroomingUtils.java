package edu.umich.ISELab.core.util;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.Grooming;

import java.util.ArrayList;
import java.util.List;

public class GroomingUtils {
    public static Project apply(Project project, List<Grooming> groomingList) throws Exception {

        UUIDUtils.restart();

        Project copy = ProjectObjectUtils.copy(project);

        for (Grooming grooming : groomingList) {
            apply(copy, grooming);
        }

        return copy;
    }

    public static void apply(Project project, Grooming grooming) throws Exception {

        grooming.defineActors(project);

        grooming.loadActors(project);

        grooming.verifyPreConditions(project);

        grooming.execute(project);

        grooming.verifyPostCondition(project);
    }

    public static List<Grooming> getValids(Project project, List<Grooming> groomingList) {

        List<Grooming> valids = new ArrayList<>();

        for (Grooming grooming : groomingList) {

            Grooming copyGrooming = grooming.copy();

            try {
                GroomingUtils.apply(project, copyGrooming);
                valids.add(copyGrooming);
            } catch (Exception ex) { /*If the exception was thrown, we have to ignore it*/ }
        }

        return valids;
    }
}
