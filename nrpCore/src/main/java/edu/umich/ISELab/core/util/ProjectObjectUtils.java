package edu.umich.ISELab.core.util;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.projectResources.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectObjectUtils {

    public static Map<WorkItem, Person> findPair(Project project) {
        Map<WorkItem, Person>  workPair = new HashMap<>();
        List<Person> personList= project.getPersonList();
        List<WorkItem> workItem= project.getWorkItemList();
       Optional<WorkItem> item = workItem.stream().filter(availableItem-> !availableItem.isAssigned()).findFirst();
       Optional<Person> person = personList.stream().filter(availablePerson-> !availablePerson.isAssigned()).findFirst();
       if(item.isPresent()&&person.isPresent()){
           return (Map<WorkItem, Person>) workPair.put(item.get(),person.get());
       }
        return null;
    }
}
