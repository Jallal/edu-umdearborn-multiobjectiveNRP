package edu.umich.ISELab.optimization.variables;

import edu.umich.ISELab.core.grooming.grooming;

import java.util.ArrayList;
import java.util.List;

public class NrpVariable extends Variable {

    protected List<grooming> groomings;

    public NrpVariable(NrpVariable variable) {

        this.groomings = new ArrayList<>();

        for (grooming grooming : variable.getRefactorings()) {
            this.groomings.add(grooming.copy());
        }
    }

    public NrpVariable() {
        this.groomings = new ArrayList<>();
    }

    public List<grooming> getRefactorings() {
        return groomings;
    }

    public void setRefatorings(List<grooming> groomings) {

        this.groomings = groomings;
    }

    public NrpVariable copy() {
        return new NrpVariable(this);
    }
}
