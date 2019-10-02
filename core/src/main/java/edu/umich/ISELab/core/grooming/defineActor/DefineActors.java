package edu.umich.ISELab.core.grooming.defineActor;


import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.grooming.util.Candidate;

public abstract class DefineActors {

    protected int MAX_TRIES = 3000;

    public abstract Candidate execute(Project project);
}
