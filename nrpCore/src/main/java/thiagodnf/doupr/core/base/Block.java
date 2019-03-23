package thiagodnf.doupr.core.base;

import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Block {

    protected List<String> lines;

    public Block() {
        this.lines = new ArrayList<>();
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<ParameterObject> getParameters(String methodName) {

        methodName = methodName.replace("$", "\\$");

        List<List<String>> lines = match(toString(), "Parameter\\(" + methodName + ",(.*),(.*)\\);");

        List<ParameterObject> parameters = new ArrayList<>();

        // Create the parameters
        for (List<String> line : lines) {

            ParameterObject param = new ParameterObject();

            param.setType(line.get(0));

            if (!line.get(1).isEmpty()) {
                param.setTemplates(Arrays.asList(line.get(1).split(";")));
            }

            parameters.add(param);
        }

        return parameters;
    }

    public Visibility getVisibility(String name) {
        if (name.equalsIgnoreCase("Private")) {
            return Visibility.PRIVATE;
        } else if (name.equalsIgnoreCase("Protected")) {
            return Visibility.PROTECTED;
        } else if (name.equalsIgnoreCase("Public")) {
            return Visibility.PUBLIC;
        }

        return Visibility.PUBLIC;
    }

    public List<MethodObject> getMethods() {

        List<List<String>> lines = match(toString(), "Method\\((.*),(.*),(.*),(.*),(.*)\\);");

        List<MethodObject> methods = new ArrayList<>();

        // Create the methods
        for (List<String> line : lines) {

            if (line.get(0).startsWith("access$")) {
                continue;
            }

            // Ignoring the static initializer
            if (line.get(0).startsWith("<clinit>")) {
                continue;
            }

            // Ignoring the constructors
            if (line.get(0).startsWith("<init>")) {
                continue;
            }

            MethodObject method = new MethodObject();

            method.setName(line.get(0));
            method.setReturnType(line.get(1));
            method.setVisibility(getVisibility(line.get(2)));
            method.setStatic(line.get(3).equalsIgnoreCase("Y") ? true : false);
            method.setAbstract(line.get(4).equalsIgnoreCase("Y") ? true : false);
            method.setParameters(getParameters(method.getName()));

            methods.add(method);
        }

        return methods;
    }

    public List<AttributeObject> getAttributes() {

        List<List<String>> lines = match(toString(), "Attribute\\((.*),(.*),(.*),(.*),(.*),(.*)\\);");

        List<AttributeObject> attributes = new ArrayList<>();

        // Create the methods
        for (List<String> line : lines) {

            AttributeObject attribute = new AttributeObject();

            if (line.get(0).startsWith("this$")) {
                continue;
            }

            attribute.setName(line.get(0));
            attribute.setType(line.get(1));
            attribute.setVisibility(getVisibility(line.get(2)));
            attribute.setStatic(line.get(3).equalsIgnoreCase("Y") ? true : false);
            attribute.setFinal(line.get(4).equalsIgnoreCase("Y") ? true : false);

            if (!line.get(5).isEmpty()) {
                attribute.setTemplates(Arrays.asList(line.get(5).split(";")));
            }

            if (!attribute.getName().contains("$")) {
                attributes.add(attribute);
            }
        }

        return attributes;
    }

    public String getSuperClass() {

        List<List<String>> lines = match(toString(), "Generalization\\((.*)\\);");

        if (lines.isEmpty()) {
            return null;
        }

        if (lines.size() != 1) {
            throw new RuntimeException("A class should have just a generalization");
        }

        if (lines.get(0).get(0).contains("$")) {
            return null;
        }

        return lines.get(0).get(0);
    }

    public List<String> getInterfaces() {

        List<List<String>> lines = match(toString(), "Realization\\((.*)\\);");

        List<String> interfaces = new ArrayList<>();

        if (lines.isEmpty()) {
            return interfaces;
        }

        for (List<String> line : lines) {
            if (!line.get(0).contains("$")) {
                interfaces.add(line.get(0));
            }
        }

        return interfaces;
    }

    public ClassObject getCls() {

        List<List<String>> lines = match(toString(), "Class\\((.*),(.*),(.*),(.*)\\);");

        if (lines.size() != 1) {
            throw new RuntimeException("The block should be have just a class");
        }

        List<String> line = lines.get(0);

        // Create the class

        ClassObject cls = new ClassObject();

        cls.setName(line.get(0));
        cls.setInterface(line.get(1).equalsIgnoreCase("Y") ? true : false);
        cls.setAbstract(line.get(2).equalsIgnoreCase("Y") ? true : false);
        cls.setVisibility(line.get(3));

        cls.setMethods(getMethods());
        cls.setAttributes(getAttributes());

        cls.setSuperClass(getSuperClass());
        cls.setInterfaces(getInterfaces());

        if (cls.getName().contains("$")) {
            return null;
        }

        return cls;
    }

    public List<CallObject> getCalls() {

        List<CallObject> calls = new ArrayList<>();

        List<List<String>> lines = match(toString(), "Call\\((.*),(.*),(.*) --> (.*),(.*),(.*)\\);");

        for (List<String> line : lines) {

            if (line.get(2).startsWith("this$") || line.get(3).startsWith("this$")) {
                continue;
            }

            if (line.get(2).startsWith("access$") || line.get(3).startsWith("access$")) {
                continue;
            }

            if (line.get(2).startsWith("<init>") || line.get(3).startsWith("<init>")) {
                continue;
            }

            if (line.get(2).startsWith("<clinit>") || line.get(3).startsWith("<clinit>")) {
                continue;
            }

            CallObject call = new CallObject();

            call.setType(line.get(0));
            call.setSourceClass(line.get(1));
            call.setSourceMethod(line.get(2));
            call.setTargetName(line.get(3));
            call.setTargetClass(line.get(4));
            call.setOperation(line.get(5));

            calls.add(call);
        }

        return calls;
    }

    /**
     * This method tries to find a pattern into a block content.
     *
     * @param block   the block used
     * @param pattern the pattern to be found
     * @return a list of patterns found
     */
    public List<List<String>> match(String block, String pattern) {

        checkNotNull(block, "The 'block' should not be null");
        checkNotNull(pattern, "The 'pattern' should not be null");
        checkArgument(!block.isEmpty(), "the 'block' should not be empty");
        checkArgument(!pattern.isEmpty(), "the 'pattern' should not be empty");

        List<List<String>> output = new ArrayList<List<String>>();

        // Use regex to find the pattern into the block
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(block);

        while (m.find()) {

            List<String> groups = new ArrayList<String>();

            // As we don't know information about the number of group defined by
            // the user, we get all groups and put it in a list
            for (int i = 1; i <= m.groupCount(); i++) {
                groups.add(m.group(i));
            }

            output.add(groups);
        }

        return output;
    }

    public String toString() {
        return "" + StringUtils.join(lines, "\n");
    }
}
