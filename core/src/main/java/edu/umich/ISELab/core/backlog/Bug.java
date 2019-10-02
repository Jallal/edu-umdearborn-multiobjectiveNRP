package edu.umich.ISELab.core.backlog;

public class Bug extends WorkItem {

    String bug_id;
    String creation_ts;
    String reporter;
    String rep_platform;
    String op_sys;
    String assigned_to;
    String version;
    String component_id;
    String short_desc;

    public Bug() {

    }

    public Bug(String bug_id, String short_desc, String creation_ts, String reporter, String rep_platform, String op_sys, String assigned_to, String version, String component_id) {

        super();
        this.setAssigned(false);
        this.setWorkItemID(bug_id);
        this.setReadyForImplementation(true);
        this.bug_id = bug_id;
        this.short_desc = short_desc;
        this.creation_ts = creation_ts;
        this.reporter = reporter;
        this.rep_platform = rep_platform;
        this.op_sys = op_sys;
        this.assigned_to = assigned_to;
        this.version = version;
        this.component_id = component_id;
    }


    public String getBug_id() {
        return bug_id;
    }

    public void setBug_id(String bug_id) {
        this.bug_id = bug_id;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getCreation_ts() {
        return creation_ts;
    }

    public void setCreation_ts(String creation_ts) {
        this.creation_ts = creation_ts;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getRep_platform() {
        return rep_platform;
    }

    public void setRep_platform(String rep_platform) {
        this.rep_platform = rep_platform;
    }

    public String getOp_sys() {
        return op_sys;
    }

    public void setOp_sys(String op_sys) {
        this.op_sys = op_sys;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComponent_id() {
        return component_id;
    }

    public void setComponent_id(String component_id) {
        this.component_id = component_id;
    }

    /*@Override
    public String toString() {
        return "Bug{" +
                "bug_id='" + bug_id + '\'' +
                ", creation_ts='" + creation_ts + '\'' +
                ", reporter='" + reporter + '\'' +
                ", rep_platform='" + rep_platform + '\'' +
                ", op_sys='" + op_sys + '\'' +
                ", assigned_to='" + assigned_to + '\'' +
                ", version='" + version + '\'' +
                ", component_id='" + component_id + '\'' +
                ", short_desc='" + short_desc + '\'' +
                '}';
    }*/
    @Override
    public String toString() {

        return this.getWorkItemID() + " is assigned to " + super.getPerson().getName() + "\n";
    }


}
