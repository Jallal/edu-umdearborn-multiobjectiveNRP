package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.StringUtils;

import java.io.Serializable;

public class CallObject implements Serializable {

    public static final String METHOD = "METHOD";
    public static final String ATTRIBUTE = "ATTRIBUTE";
    private static final long serialVersionUID = -5887208785961998169L;
    protected String type;

    protected String sourceClass;

    protected String sourceMethod;

    protected String targetName;

    protected String targetClass;

    protected String operation;

    /**
     * Copy Constructor
     *
     * @param call CallObject to be copied
     */
    public CallObject(CallObject call) {
        this.type = call.getType();
        this.sourceClass = call.getSourceClass();
        this.sourceMethod = call.getSourceMethod();
        this.targetName = call.getTargetName();
        this.targetClass = call.getTargetClass();
        this.operation = call.getOperation();
    }

    /**
     * Default Constructor
     */
    public CallObject() {

    }

    public CallObject(String type, String sourceClass, String sourceMethod, String targetName, String targetClass, String operation) {
        this.type = type;
        this.sourceClass = sourceClass;
        this.sourceMethod = sourceMethod;
        this.targetName = targetName;
        this.targetClass = targetClass;
        this.operation = operation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public String getSourceMethod() {
        return sourceMethod;
    }

    public void setSourceMethod(String sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean hasTheSource(String sourceClass, String sourceMethod) {
        if (this.sourceClass.equalsIgnoreCase(sourceClass) && this.sourceMethod.equalsIgnoreCase(sourceMethod)) {
            return true;
        }

        return false;
    }

    public boolean hasTheTarget(String targetClass, String targetName) {
        if (this.targetClass.equalsIgnoreCase(targetClass) && this.targetName.equalsIgnoreCase(targetName)) {
            return true;
        }

        return false;
    }

    public boolean isCallIn() {
        return this.sourceClass.equalsIgnoreCase(this.targetClass);
    }

    public boolean isCallOut() {
        return !this.sourceClass.equalsIgnoreCase(this.targetClass);
    }

    public boolean isCallForMethod() {
        return this.type != null && this.type.equalsIgnoreCase(METHOD);
    }

    public boolean isCallForAttribute() {
        return this.type != null && this.type.equalsIgnoreCase(ATTRIBUTE);
    }

    public boolean isRead() {
        return this.operation != null && this.operation.equalsIgnoreCase("READ");
    }

    public boolean isWrite() {
        return this.operation != null && this.operation.equalsIgnoreCase("WRITE");
    }

    public boolean isCallFor(ClassObject cls, ElementObject el) {

        if (!getTargetClass().equalsIgnoreCase(cls.getName())) {
            return false;
        }

        if (!getTargetName().equalsIgnoreCase(el.getName())) {
            return false;
        }

        if (el instanceof AttributeObject && !isCallForAttribute()) {
            return false;
        }

        if (el instanceof MethodObject && !isCallForMethod()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((targetClass == null) ? 0 : targetClass.hashCode());
        result = prime * result + ((targetName == null) ? 0 : targetName.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CallObject other = (CallObject) obj;
        if (operation == null) {
            if (other.operation != null)
                return false;
        } else if (!operation.equals(other.operation))
            return false;
        if (targetClass == null) {
            if (other.targetClass != null)
                return false;
        } else if (!targetClass.equals(other.targetClass))
            return false;
        if (targetName == null) {
            if (other.targetName != null)
                return false;
        } else if (!targetName.equals(other.targetName))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("(");
        builder.append(type);
        builder.append(",");
        builder.append(sourceClass == null ? "" : StringUtils.getSimpleName(sourceClass));
        builder.append(",");
        builder.append(sourceMethod);
        builder.append(",");
        builder.append(targetName);
        builder.append(",");
        builder.append(targetClass == null ? "" : StringUtils.getSimpleName(targetClass));
        builder.append(",");
        builder.append(operation == null ? "" : operation);
        builder.append(")");

        return builder.toString();
    }
}
