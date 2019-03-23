package thiagodnf.doupr.core.formatter;

public abstract class Formatter {

    protected Object object;

    public Formatter(Object object) {
        this.object = object;
    }

    public abstract String toString();

    public abstract String toSimpleString();
}
