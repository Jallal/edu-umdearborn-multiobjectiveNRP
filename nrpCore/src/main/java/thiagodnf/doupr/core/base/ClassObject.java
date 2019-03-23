package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.util.StringUtils;
import thiagodnf.doupr.core.util.UUIDUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassObject implements Serializable {

    private static final long serialVersionUID = -1262698267552395347L;

    protected String name;

    protected boolean isInterface;

    protected boolean isAbstract;

    protected String visibility;

    protected String superClass;

    protected List<MethodObject> methods;

    protected List<AttributeObject> attributes;


    protected List<String> superClasses;

    protected List<String> subClasses;

    protected List<String> interfaces;

    protected Map<String, Double> designMetrics = new HashMap<>();

    /**
     * Copy Constructor
     *
     * @param cls ClassObject to be copied
     */
    public ClassObject(ClassObject cls) {
        this.name = cls.getName();
        this.isInterface = cls.isInterface();
        this.isAbstract = cls.isAbstract();
        this.visibility = cls.getVisibility();
        this.superClass = cls.getSuperClass();

        this.methods = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.superClasses = new ArrayList<>(cls.getSuperClasses());
        this.subClasses = new ArrayList<>(cls.getSubClasses());
        this.interfaces = new ArrayList<>(cls.getInterfaces());
        this.designMetrics = new HashMap<>(cls.getDesignMetrics());

        for (MethodObject method : cls.getMethods()) {
            this.methods.add(new MethodObject(method));
        }

        for (AttributeObject attribute : cls.getAttributes()) {
            this.attributes.add(new AttributeObject(attribute));
        }
    }

    public ClassObject() {
        this(getRandomName());
    }

    public ClassObject(String name) {
        this.name = name;
        this.visibility = "Public";
        this.methods = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.superClasses = new ArrayList<>();
        this.subClasses = new ArrayList<>();
    }

    public static String getRandomName() {
        return "Class_" + UUIDUtils.sequencialUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return StringUtils.getSimpleName(getName());
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    public boolean isConcrete() {
        return !isInterface() && !isAbstract();
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<MethodObject> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodObject> methods) {
        this.methods = methods;
    }

    public List<AttributeObject> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeObject> attributes) {
        this.attributes = attributes;
    }

    public List<AttributeObject> getAttributes(Visibility visibility) {

        List<AttributeObject> list = new ArrayList<>();

        for (AttributeObject attr : getAttributes()) {
            if (attr.getVisibility() == visibility) {
                list.add(attr);
            }
        }

        return list;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public boolean hasSuperClass() {
        return this.superClass != null;
    }

    public boolean hasAttributes() {
        return !this.attributes.isEmpty();
    }

    public boolean hasAttribute(String attributeName) {
        for (AttributeObject attribute : getAttributes()) {
            if (attribute.getName().equalsIgnoreCase(attributeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAttributeWithType(String type) {
        for (AttributeObject attribute : getAttributes()) {
            if (attribute.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAttribute(AttributeObject attribute) {
        for (AttributeObject attr : getAttributes()) {
            if (attr == attribute) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfMethods() {
        int numberOfMethods = 0;

        for (MethodObject method : getMethods()) {
            if (!method.isContructor()) {
                numberOfMethods++;
            }
        }

        return numberOfMethods;
    }

    public boolean hasMethods() {
        return getNumberOfMethods() != 0;
    }

    public boolean hasMethod(String methodName) {
        for (MethodObject method : this.methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasMethod(MethodObject method) {
        for (MethodObject m : getMethods()) {
            if (m == method) {
                return true;
            }
        }

        return false;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public boolean hasInterface() {
        return this.interfaces != null && !this.interfaces.isEmpty();
    }

    public boolean hasSignature(AttributeObject attr) {

        for (AttributeObject attribute : getAttributes()) {
            if (attribute.hasTheSameSignature(attr)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSignature(MethodObject m) {

        for (MethodObject method : getMethods()) {
            if (method.hasTheSameSignature(m)) {
                return true;
            }
        }

        return false;
    }

    public List<String> getSubClasses() {
        return subClasses;
    }

    public void setSubClasses(List<String> subClasses) {
        this.subClasses = subClasses;
    }

    public List<String> getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(List<String> superClasses) {
        this.superClasses = superClasses;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append(visibility);
        builder.append(" ");

        if (isInterface) {
            builder.append("interface");
        } else {
            builder.append("class");
        }

        builder.append(" ");

        if (isAbstract) {
            builder.append("abstract").append(" ");
        }

        builder.append(name).append(" ");

        if (hasSuperClass()) {
            builder.append("extends ").append(superClass);
        }

        builder.append("{\n");

        for (AttributeObject attribute : attributes) {
            builder.append("\t").append(attribute).append("\n");
        }

        builder.append("\n");

        for (MethodObject method : methods) {
            builder.append("\t").append(method).append("\n");
        }

        builder.append("}");

        return builder.toString();
    }

    public Map<String, Double> getDesignMetrics() {
        return designMetrics;
    }

    public void setDesignMetrics(Map<String, Double> designMetrics) {
        this.designMetrics = designMetrics;
    }
}
