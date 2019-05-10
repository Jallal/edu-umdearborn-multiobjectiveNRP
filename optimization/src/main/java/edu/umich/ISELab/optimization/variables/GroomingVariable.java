package edu.umich.ISELab.optimization.variables;

import edu.umich.ISELab.core.grooming.Grooming;

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
}
