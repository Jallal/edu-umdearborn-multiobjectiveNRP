package edu.umich.ISELab.core.grooming;

import edu.umich.ISELab.core.backlog.Project;
import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.condition.Condition;
import edu.umich.ISELab.core.grooming.defineactor.DefineActors;
import edu.umich.ISELab.core.grooming.util.Candidate;
import edu.umich.ISELab.core.util.StringUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NrpBase implements Serializable {

    protected static final Logger LOGGER = Logger.getLogger(NrpBase.class);

    protected String class1;

    protected String class2;

    protected List<String> methods;

    protected List<String> attributes;

    protected boolean mustDefineActors;

    protected Map<Object, Object> properties;

    public NrpBase() {
        this.mustDefineActors = true;
        this.methods = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    public NrpBase(String class1, String class2, String attributeName, String methodName) {
        this(class1, class2, Arrays.asList(attributeName), Arrays.asList(methodName));
    }

    public NrpBase(String class1, String class2, List<String> attributes, List<String> methods) {
        this.class1 = class1;
        this.class2 = class2;
        this.attributes = attributes;
        this.methods = methods;
        this.mustDefineActors = false;
        this.properties = new HashMap<>();
    }

    public NrpBase(NrpBase nrp) {
        this.class1 = nrp.getClass1();
        this.class2 = nrp.getClass2();
        this.attributes = new ArrayList<>(nrp.getAttributes());
        this.methods = new ArrayList<>(nrp.getMethods());
        this.mustDefineActors = nrp.isMustDefineActors();

        // Copy the properties
        this.properties = new HashMap<>(nrp.getProperties());
    }

    public static Condition NOT(Condition condition) {

        condition.setNegate(true);
        return condition;
    }

    public boolean verifyPreConditions(WorkItem project) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Verifying pre-conditions for " + toString());

        List<Condition> conditions = getPreConditions(project);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(project);

            if (LOGGER.isDebugEnabled()) LOGGER.debug("Analising " + condition + ": " + isValid);

            if (!isValid) {
                throw new Exception(getName() + ": " + condition.getError());
            }
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("All pre-conditions are valids");

        return true;
    }

    public boolean verifyPostCondition(WorkItem item) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Verifying pos-conditions for " + toString());

        List<Condition> conditions = getPostConditions(item);

        for (Condition condition : conditions) {

            boolean isValid = condition.validate(item);

            if (LOGGER.isDebugEnabled()) LOGGER.debug("Analising " + condition + ": " + isValid);

            if (!isValid) {
                throw new Exception(getName() + ": " + condition.getError());
            }
        }

        if (LOGGER.isDebugEnabled()) LOGGER.debug("All pos-conditions are valids");

        return true;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2;
    }

    public void reset() {
        this.class1 = "";
        this.class2 = "";
        this.methods = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {

        this.methods = methods;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {

        this.attributes = attributes;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(this.getClass().getSimpleName());
        builder.append("(");

        builder.append(class1 == null ? "" : class1);
        builder.append(";");

        builder.append(class2 == null ? "" : class2);
        builder.append(";");

        Collections.sort(this.attributes);
        Collections.sort(this.methods);

        builder.append("[");
        builder.append(StringUtils.join(this.attributes, "|"));
        builder.append("]");
        builder.append(";");

        builder.append("[");
        builder.append(StringUtils.join(this.methods, "|"));
        builder.append("]");

        builder.append(")");

        return builder.toString();
    }

    public void defineActors(Project project) throws Exception {

        if (!isMustDefineActors()) {
            return;
        }

        DefineActors defineActors = getDefineActors();

        if (LOGGER.isDebugEnabled()) LOGGER.debug("Define the actors for " + defineActors.getClass().getSimpleName());

        reset();

        Candidate candidate = defineActors.execute(project);

        if (candidate == null) {
            throw new Exception("Cannot define the actors for " + getName());
        }

        this.class1 = candidate.getSourceCls() != null ? candidate.getSourceCls().toString() : "";
        this.class2 = candidate.getTargetCls() != null ? candidate.getTargetCls().toString() : "";
        //this.attributes = candidate.getAttributes().stream().map(e -> e.getName()).collect(Collectors.toList());
        //this.methods = candidate.getMethods().stream().map(e -> e.getName()).collect(Collectors.toList());
        this.mustDefineActors = false;

        return;
    }

    public boolean isMustDefineActors() {
        return mustDefineActors;
    }

    public void setMustDefineActors(boolean mustDefineActors) {
        this.mustDefineActors = mustDefineActors;
    }

    public Map<Object, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<Object, Object> properties) {
        this.properties = properties;
    }

    public double getUserFeedback() {

        if (getProperties().containsKey("USER_FEEDBACK")) {
            return (double) getProperties().get("USER_FEEDBACK");
        }

        return 0.0;
    }

    public void setUserFeedback(double value) {
        getProperties().put("USER_FEEDBACK", value);
    }
    public abstract void loadActors(WorkItem project);
    public abstract List<Condition> getPreConditions(WorkItem project);
    public abstract List<Condition> getPostConditions(WorkItem project);
    public abstract void execute(Project project) throws Exception;
    public abstract DefineActors getDefineActors();
    public abstract String getName();
    public abstract NrpBase copy();
    public abstract WorkItem getSourceCls();
    public abstract WorkItem getTargetCls();

}
