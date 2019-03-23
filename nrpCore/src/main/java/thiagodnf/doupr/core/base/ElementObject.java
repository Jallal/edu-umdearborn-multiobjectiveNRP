package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.UUIDUtils;

import java.io.Serializable;

public abstract class ElementObject implements Serializable {

    protected String id;

    ;
    protected String name;
    protected boolean isStatic;
    protected Visibility visibility;

    /**
     * Copy Constructor
     *
     * @param element the one that will be copied
     */
    public ElementObject(ElementObject element) {
        this.id = element.getId();
        this.name = element.getName();
        this.visibility = element.getVisibility();
        this.isStatic = element.isStatic();
    }

    /**
     * Default Constructor
     */
    public ElementObject() {
        this.id = UUIDUtils.randomUUID();
        this.visibility = Visibility.PUBLIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isPublic() {
        return visibility == Visibility.PUBLIC;
    }

    public boolean isPrivate() {
        return visibility == Visibility.PRIVATE;
    }

    public boolean isProtected() {
        return visibility == Visibility.PROTECTED;
    }

    public String getIdentifier() {
        return getName() + "-" + getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String getSignature();

    public enum Visibility {
        PUBLIC, PROTECTED, PRIVATE
    }
}
