package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterObject implements Serializable {

    private static final long serialVersionUID = -6125868598446623002L;

    protected String type;

    protected List<String> templates;

    /**
     * Copy Constructor
     *
     * @param parameter The parameter to be cloned
     */
    public ParameterObject(ParameterObject parameter) {
        this.type = parameter.getType();
        this.templates = new ArrayList<>(parameter.getTemplates());
    }

    public ParameterObject(String type, List<String> templates) {
        this.type = type;
        this.templates = templates;
    }

    public ParameterObject(String type) {
        this(type, new ArrayList<>());
    }

    public ParameterObject() {
        this(null, new ArrayList<>());
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

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(type);

        if (!templates.isEmpty()) {
            builder.append("<");
            builder.append(StringUtils.join(templates, ","));
            builder.append(">");
        }

        return builder.toString();
    }

    public String toSimpleString() {

        StringBuilder builder = new StringBuilder();

        builder.append(StringUtils.getSimpleName(type));

        if (!templates.isEmpty()) {
            builder.append("<");
            builder.append(StringUtils.join(StringUtils.getSimpleNames(templates), ","));
            builder.append(">");
        }

        return builder.toString();
    }

    public String getSignature() {

        StringBuilder builder = new StringBuilder();

        builder.append(type);

        if (!templates.isEmpty()) {
            builder.append("<").append(StringUtils.join(templates, ",")).append(">");
        }

        return builder.toString();
    }
}
