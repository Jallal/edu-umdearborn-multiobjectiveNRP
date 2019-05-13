package edu.umich.ISELab.core.util;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ProjectObjectUtils {

    public static Map<WorkItem, Person> findPair(Project project) {
        Map<WorkItem, Person> workPair = new HashMap<>();
        //person List
        List<Person> personList = project.getPersonList();

        //Work item
        List<WorkItem> workItems = project.getWorkItemList();
        Random randPerson = new Random(System.currentTimeMillis());
        Random randItem = new Random(System.currentTimeMillis()+1);

        int count = 0;
        while (count < 3000) {
            WorkItem randomItem = workItems.get(randItem.nextInt(workItems.size()));
            Person randomPerson = personList.get(randPerson.nextInt(personList.size()));
            count++;
            if (!randomItem.isAssigned() && !randomPerson.isAssigned()) {
                randomItem.setAssigned(TRUE);
                randomPerson.setAssigned(TRUE);
                workPair.put(randomItem, randomPerson);

            }
        }
        return workPair;
    }

    public static Map<WorkItem, Person> findAll(Project project) {

        Map<WorkItem, Person> newPair = findPair(project);
        return cleanCandidates(newPair);
    }


    public static Map<WorkItem, Person> cleanCandidates(Map<WorkItem, Person> workPair) {

        for (Map.Entry<WorkItem, Person> me : workPair.entrySet()) {
            me.getKey().setAssigned(FALSE);
            me.getValue().setAssigned(FALSE);

        }
        return workPair;
    }


    public static void cleanTheProject(Project project) {

        List<Person> personList = project.getPersonList();
        List<WorkItem> workItem = project.getWorkItemList();
        for (Person person : personList) {
            for (WorkItem item : workItem) {
                if (item.isAssigned() || person.isAssigned()) {
                    item.setAssigned(FALSE);
                    person.setAssigned(FALSE);
                }
            }
        }

    }


    public static Project copy(Project project) {

        checkNotNull(project, "The 'project' instance cannot be null");
        cleanTheProject(project);
        return new Project(project);
    }
}
