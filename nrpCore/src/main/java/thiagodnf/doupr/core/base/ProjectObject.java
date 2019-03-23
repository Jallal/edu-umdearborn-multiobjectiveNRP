package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.callgraph.CallGraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectObject implements Serializable {

    private static final long serialVersionUID = 2583239415853852201L;

    protected List<PackageObject> packages;

    protected Map<String, Object> attributes;

    protected Map<String, Double> designMetrics;

    protected CallGraph callGraph;

    public ProjectObject() {
        this.packages = new ArrayList<>();
        this.attributes = new HashMap<>();
        this.designMetrics = new HashMap<>();
        this.callGraph = new CallGraph();
    }

    public ProjectObject(ProjectObject project) {
        this.packages = new ArrayList<>();
        this.attributes = new HashMap<>(project.getAttributes());
        this.designMetrics = new HashMap<>(project.getDesignMetrics());
        this.callGraph = new CallGraph(project.getCallGraph());

        for (PackageObject pkg : project.getPackages()) {
            this.packages.add(new PackageObject(pkg));
        }
    }

    public Map<String, Double> getDesignMetrics() {
        return designMetrics;
    }

    public void setDesignMetrics(Map<String, Double> designMetrics) {
        this.designMetrics = designMetrics;
    }

    public List<PackageObject> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageObject> packages) {
        this.packages = packages;
    }

    public List<ClassObject> getClasses() {

        List<ClassObject> classes = new ArrayList<>();

        for (PackageObject pkg : getPackages()) {
            classes.addAll(pkg.getClasses());
        }

        return classes;
    }

    public PackageObject getPackage(ClassObject cls) {

        for (PackageObject pkg : getPackages()) {

            for (ClassObject c : pkg.getClasses()) {

                if (c == cls) {
                    return pkg;
                }
            }
        }

        return null;
    }

    public CallGraph getCallGraph() {
        return callGraph;
    }

    public void setCallGraph(CallGraph callGraph) {
        this.callGraph = callGraph;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ProjectObject [packages=" + packages + "]";
    }
}
