package edu.umich.ISELab.core.grooming.util;

import edu.umich.ISELab.core.backlog.WorkItem;

public class Candidate {

    protected WorkItem sourceCls;


    protected WorkItem targetCls;

    //protected List<MethodObject> methods;

    //protected List<AttributeObject> attributes;

    public Candidate(WorkItem sourceCls) {
        this.sourceCls = sourceCls;
        //this.methods = new ArrayList<>();
       // this.attributes = new ArrayList<>();
    }

    public Candidate(WorkItem sourceCls, WorkItem targetCls) {
        this(sourceCls);

        this.targetCls = targetCls;
    }

    /*public Candidate(WorkItem sourceCls, WorkItem targetCls, MethodObject attr) {
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
    }*/

    public WorkItem getSourceCls() {
        return sourceCls;
    }

    public void setSourceCls(WorkItem cls1) {
        this.sourceCls = cls1;
    }

    public WorkItem getTargetCls() {
        return targetCls;
    }

    public void setTargetCls(WorkItem cls2) {
        this.targetCls = cls2;
    }

    /*public List<MethodObject> getMethods() {
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
    }*/

    @Override
    public String toString() {
        return "Candidate{" +
                "sourceCls=" + sourceCls +
                ", targetCls=" + targetCls +
                '}';
    }

}
