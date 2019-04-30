package thiagodnf.doupr.core.refactoring.defineactor;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.util.Candidate;

public abstract class DefineActors {

    protected int MAX_TRIES = 300;

    public abstract Candidate execute(ProjectObject project);
}
