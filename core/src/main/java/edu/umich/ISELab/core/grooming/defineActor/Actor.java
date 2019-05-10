package edu.umich.ISELab.core.grooming.defineActor;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.projectResources.Person;
import edu.umich.ISELab.core.util.ProjectObjectUtils;

import java.util.Map;
import java.util.Set;

public class Actor extends DefineActors {

    private WorkItem activeItem;
    private Person activePerson;

    @Override
    public Candidate execute(Project project) {
        int trials = 0;
        Map<WorkItem, Person> pair = ProjectObjectUtils.findPair(project);
        Set<Map.Entry<WorkItem, Person>> st = pair.entrySet();
        for (Map.Entry<WorkItem, Person> me : st) {
            if (!me.getKey().isAssigned() && !me.getValue().isAssigned()) {
                this.activeItem = me.getKey();
                this.activePerson = me.getValue();
            }
            if (trials > MAX_TRIES) {
                return null;
            }

        }
        return new Candidate(this.activeItem, this.activePerson);
    }

}
