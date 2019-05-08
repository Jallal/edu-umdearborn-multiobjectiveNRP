package edu.umich.ISELab.optimization.variables;

import edu.umich.ISELab.core.grooming.NrpBase;

import java.util.ArrayList;
import java.util.List;

public class NrpVariable extends Variable {

    protected List<NrpBase> nrpBases;

    public NrpVariable(NrpVariable variable) {

        this.nrpBases = new ArrayList<>();

        for (NrpBase nrpBase : variable.getRefactorings()) {
            this.nrpBases.add(nrpBase.copy());
        }
    }

    public NrpVariable() {
        this.nrpBases = new ArrayList<>();
    }

    public List<NrpBase> getRefactorings() {
        return nrpBases;
    }

    public void setRefatorings(List<NrpBase> nrpBases) {

        this.nrpBases = nrpBases;
    }

    public NrpVariable copy() {
        return new NrpVariable(this);
    }
}
