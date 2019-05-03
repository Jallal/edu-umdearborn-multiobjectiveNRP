package thiagodnf.doupr.core.refactoring.util;

import thiagodnf.doupr.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Candidate {

    protected ClassObject sourceCls;

    protected ClassObject targetCls;

    protected List<MethodObject> methods;

    protected List<AttributeObject> attributes;

    public Candidate(ClassObject sourceCls) {
        this.sourceCls = sourceCls;
        this.methods = new ArrayList<>();
        this.attributes = new ArrayList<>();
    }

    public Candidate(ClassObject sourceCls, ClassObject targetCls) {
        this(sourceCls);

        this.targetCls = targetCls;
    }

    public Candidate(ClassObject sourceCls, ClassObject targetCls, MethodObject attr) {
        this(sourceCls, targetCls);

        this.methods.add(attr);
    }

    public Candidate(ClassObject sourceCls, ClassObject targetCls, AttributeObject attr) {
        this(sourceCls, targetCls);

        this.attributes.add(attr);
    }

    public Candidate(ClassObject sourceCls, AttributeObject attr) {
        this(sourceCls);

        this.attributes.add(attr);
    }

    public Candidate(ClassObject sourceCls, MethodObject m) {
        this(sourceCls);

        this.methods.add(m);
    }

    public ClassObject getSourceCls() {
        return sourceCls;
    }

    public void setSourceCls(ClassObject cls1) {
        this.sourceCls = cls1;
    }

    public ClassObject getTargetCls() {
        return targetCls;
    }

    public void setTargetCls(ClassObject cls2) {
        this.targetCls = cls2;
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

    @Override
    public String toString() {

        List<String> list = new ArrayList<>();

        if (sourceCls != null)
            list.add(sourceCls.getSimpleName());
        if (targetCls != null)
            list.add(targetCls.getSimpleName());

        if (methods != null && !methods.isEmpty())
            list.add(methods.stream().map(e -> e.getName()).collect(Collectors.toList()).toString());

        if (attributes != null && !attributes.isEmpty())
            list.add(attributes.stream().map(e -> e.getName()).collect(Collectors.toList()).toString());

        return "[" + StringUtils.join(list, ",") + "]";
    }
}
