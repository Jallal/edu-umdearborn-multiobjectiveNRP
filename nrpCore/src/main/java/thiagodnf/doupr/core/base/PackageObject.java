package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.ClassObjectUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PackageObject implements Serializable {

    private static final long serialVersionUID = 464284633226348453L;

    protected String name;

    protected List<ClassObject> classes;

    public PackageObject() {
        this(getRandomName());
    }

    public PackageObject(PackageObject pkg) {
        this.name = pkg.getName();
        this.classes = new ArrayList<>();

        for (ClassObject cls : pkg.getClasses()) {
            this.classes.add(new ClassObject(cls));
        }
    }

    public PackageObject(String name) {
        this(name, new ArrayList<>());
    }

    public PackageObject(String name, List<ClassObject> classes) {
        this.name = name;
        this.classes = classes;
    }

    public static String getRandomName() {
        return "Package_" + UUIDUtils.sequencialUUID();
    }

    public List<ClassObject> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassObject> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasClasses() {
        return !this.getClasses().isEmpty();
    }

    public String toSimpleString() {

        StringBuilder builder = new StringBuilder();

        builder.append(getName());
        builder.append("\n").append("\t");
        builder.append(ClassObjectUtils.getSimpleNames(getClasses()));

        return builder.toString();
    }

    @Override
    public String toString() {
        return "PackageObject [name=" + name + ", classes=" + classes + "]";
    }
}
