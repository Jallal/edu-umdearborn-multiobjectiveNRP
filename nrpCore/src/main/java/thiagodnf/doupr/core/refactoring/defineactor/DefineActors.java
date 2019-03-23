package thiagodnf.doupr.core.refactoring.defineactor;

public abstract class DefineActors {

    protected int MAX_TRIES = 300;

    public abstract thiagodnf.doupr.core.refactoring.util.Candidate execute(thiagodnf.doupr.core.base.ProjectObject project);
}
