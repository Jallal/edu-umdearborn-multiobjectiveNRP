package edu.umich.ISELab.optimization.variables;

import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GroomingVariable extends Variable {

    protected List<Grooming> Groomings;

    public GroomingVariable(GroomingVariable variable) {

        this.Groomings = new ArrayList<>();

        for (Grooming grooming : variable.getRefactorings()) {
            this.Groomings.add(grooming.copy());
        }
    }

    public GroomingVariable() {
        this.Groomings = new ArrayList<>();
    }

    public List<Grooming> getRefactorings() {
        return Groomings;
    }

    public void setRefatorings(List<Grooming> Groomings) {

        this.Groomings = Groomings;
    }

    public GroomingVariable copy() {
        return new GroomingVariable(this);
    }

    @Override
    public String toString() {

        List<String> lines = new ArrayList<String>();

        for (int i = 0; i < Groomings.size(); i++) {
            lines.add("\n ************************************\n");
            for(WorkItem workItem: Groomings.get(i).getWorkItems()) {
               lines.add(workItem.toString());
            }
            lines.add("\n ************************************\n");
        }

        return StringUtils.join(lines, " ");
    }
}
