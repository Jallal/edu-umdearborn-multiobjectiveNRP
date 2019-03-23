package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MethodObject extends ElementObject {

    protected String returnType;

    protected boolean isAbstract;

    protected List<ParameterObject> parameters;

    /**
     * Copy Constructor
     *
     * @param method The method to be cloned
     */
    public MethodObject(MethodObject method) {
        super(method);

        this.returnType = method.getReturnType();
        this.isAbstract = method.isAbstract();
        this.parameters = new ArrayList<>();

        for (ParameterObject parameter : method.getParameters()) {
            this.parameters.add(new ParameterObject(parameter));
        }
    }

    public MethodObject() {
        this.isAbstract = false;
        this.parameters = new ArrayList<>();
    }

    public String getSimpleName() {
        return name.split("\\$")[0];
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public List<ParameterObject> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterObject> parameters) {
        this.parameters = parameters;
    }

    public boolean hasParameters() {
        return !getParameters().isEmpty();
    }

    public boolean hasTheSameSignature(MethodObject m) {
        return getSignature().equalsIgnoreCase(m.getSignature());
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(visibility);

        if (isStatic()) {
            builder.append(" ").append("static");
        }
        if (isAbstract()) {
            builder.append(" ").append("abstract");
        }

        builder.append(" ");
        builder.append(StringUtils.getSimpleName(returnType));
        builder.append(" ");
        builder.append(name);

        builder.append("(");
        builder.append(StringUtils.join(parameters, ","));
        builder.append("){}");

        return builder.toString();
    }

    @Override
    public String getSignature() {

        StringBuilder builder = new StringBuilder();

        builder.append(name.split("\\$")[0]);

        builder.append("(");
        builder.append(StringUtils.join(parameters, ","));
        builder.append(")");

        return builder.toString();
    }

    public boolean isContructor() {
        return getName().startsWith("<init>");
    }
}
