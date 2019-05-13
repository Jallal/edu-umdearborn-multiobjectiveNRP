package edu.umich.ISELab.core.grooming.defineActor;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;
import edu.umich.ISELab.core.util.ProjectObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Actor extends DefineActors {


    @Override
    public Candidate execute(Project project) {
        int trials = 0;
       List<WorkItem> activeItems = new ArrayList<>();
         List<Person> activeResources = new ArrayList<>();
        Map<WorkItem, Person> pair = ProjectObjectUtils.findAll(project);
        if (pair!=null) {
            for (Map.Entry<WorkItem, Person> me : pair.entrySet()) {
                if (!me.getKey().isAssigned() && !me.getValue().isAssigned()) {
                    activeItems.add(me.getKey());
                    activeResources.add(me.getValue());
                    trials++;
                }if (trials > MAX_TRIES) {
                    return null;
                }

            }
            return new Candidate(activeItems, activeResources);
        }
        return null;
    }

}
