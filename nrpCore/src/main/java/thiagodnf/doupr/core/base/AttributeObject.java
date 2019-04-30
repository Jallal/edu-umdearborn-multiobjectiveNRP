package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AttributeObject extends ElementObject {

    protected String type;

    protected boolean isFinal;

    protected List<String> templates;

    public AttributeObject(AttributeObject attribute) {
        super(attribute);

        this.type = attribute.getType();
        this.isFinal = attribute.isFinal();
        this.templates = new ArrayList<>(attribute.getTemplates());
    }

    public AttributeObject() {
        this.templates = new ArrayList<String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeWithTemplate() {
        StringBuilder builder = new StringBuilder();

        builder.append(StringUtils.getSimpleName(getType()));

        if (!templates.isEmpty()) {

            List<String> temp = templates.stream().map(StringUtils::getSimpleName).collect(Collectors.toList());

            builder.append("\\<").append(StringUtils.join(temp, ",")).append("\\>");
        }

        return builder.toString();
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    public boolean hasTheSameSignature(AttributeObject attr) {
        return getSignature().equalsIgnoreCase(attr.getSignature());
    }

    @Override
    public String getSignature() {

        StringBuilder builder = new StringBuilder();

        builder.append(visibility);
        builder.append(" ");
        builder.append(type);

        if (!templates.isEmpty()) {
            builder.append("<");
            builder.append(thiagodnf.doupr.core.util.StringUtils.join(templates, ","));
            builder.append(">");
        }

        builder.append(" ");
        builder.append(name);

        return builder.toString();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(visibility);

        if (isStatic) {
            builder.append(" ").append("static");
        }
        if (isFinal()) {
            builder.append(" ").append("final");
        }

        builder.append(" ");
        builder.append(type);
        builder.append(" ");
        builder.append(name);

        if (!templates.isEmpty()) {
            builder.append("<").append(thiagodnf.doupr.core.util.StringUtils.join(templates, ",")).append(">");
        }

        return builder.toString();
    }

    public String toSimpleString() {
        StringBuilder builder = new StringBuilder();

        builder.append(visibility);

        if (isStatic) {
            builder.append(" ").append("static");
        }
        if (isFinal()) {
            builder.append(" ").append("final");
        }

        builder.append(" ");
        builder.append(thiagodnf.doupr.core.util.StringUtils.getSimpleName(type));
        builder.append(" ");
        builder.append(name);

        if (!templates.isEmpty()) {
            builder.append("<").append(thiagodnf.doupr.core.util.StringUtils.join(templates, ",")).append(">");
        }

        return builder.toString();
    }
}
