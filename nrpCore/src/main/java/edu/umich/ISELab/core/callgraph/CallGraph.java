package edu.umich.ISELab.core.callgraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallGraph implements Serializable {

    protected Map<String, List<String>> callsIn;

    protected Map<String, List<String>> callsOut;

    protected Map<String, String> accesses;

    public CallGraph() {
        this.callsIn = new HashMap<>();
        this.callsOut = new HashMap<>();
        this.accesses = new HashMap<>();
    }

    public CallGraph(CallGraph callGraph) {
        this();

        for (String key : callGraph.getCallsIn().keySet()) {
            this.callsIn.put(key, new ArrayList<>(callGraph.getCallsIn().get(key)));
        }

        for (String key : callGraph.getCallsOut().keySet()) {
            this.callsOut.put(key, new ArrayList<>(callGraph.getCallsOut().get(key)));
        }

        for (String key : callGraph.getAccesses().keySet()) {
            this.accesses.put(key, callGraph.getAccesses().get(key));
        }
    }

    public Map<String, List<String>> getCallsIn() {
        return callsIn;
    }

    public void setCallsIn(Map<String, List<String>> callsIn) {
        this.callsIn = callsIn;
    }

    public Map<String, List<String>> getCallsOut() {
        return callsOut;
    }

    public void setCallsOut(Map<String, List<String>> callsOut) {
        this.callsOut = callsOut;
    }

    public List<String> getCallsTo(String id) {
        if (callsIn.containsKey(id)) {
            return new ArrayList<>(callsIn.get(id));
        }

        return new ArrayList<>();
    }

    public List<String> getCallsFrom(String id) {
        if (callsOut.containsKey(id)) {
            return new ArrayList<>(callsOut.get(id));
        }

        return new ArrayList<>();
    }

    public void addCall(String sourceMethodId, String targetMethodId) {

        if (!callsIn.containsKey(targetMethodId)) {
            callsIn.put(targetMethodId, new ArrayList<>());
        }

        if (!callsOut.containsKey(sourceMethodId)) {
            callsOut.put(sourceMethodId, new ArrayList<>());
        }

        if (!callsIn.get(targetMethodId).contains(sourceMethodId)) {
            callsIn.get(targetMethodId).add(sourceMethodId);
        }

        if (!callsOut.get(sourceMethodId).contains(targetMethodId)) {
            callsOut.get(sourceMethodId).add(targetMethodId);
        }
    }

    public void removeCall(String sourceMethodId, String targetMethodId) {
        callsIn.get(targetMethodId).remove(sourceMethodId);
        callsOut.get(sourceMethodId).remove(targetMethodId);

        accesses.remove(sourceMethodId + "_" + targetMethodId + "_R");
        accesses.remove(sourceMethodId + "_" + targetMethodId + "_W");
    }

    public boolean isReadAccess(String sourceId, String targetId) {
        return accesses.containsKey(sourceId + "_" + targetId + "_R");
    }

    public boolean isWriteAccess(String sourceId, String targetId) {
        return accesses.containsKey(sourceId + "_" + targetId + "_W");
    }

    public Map<String, String> getAccesses() {
        return accesses;
    }

    public void setAccesses(Map<String, String> accesses) {
        this.accesses = accesses;
    }

    public void addReadCall(String sourceId, String targetId) {
        addCall(sourceId, targetId);
        this.accesses.put(sourceId + "_" + targetId + "_R", "1");
    }

    public void addWriteCall(String sourceId, String targetId) {
        addCall(sourceId, targetId);
        this.accesses.put(sourceId + "_" + targetId + "_W", "1");
    }

    public void addNode(String identifier) {
        if (!callsIn.containsKey(identifier)) {
            callsIn.put(identifier, new ArrayList<>());
        }
        if (!callsOut.containsKey(identifier)) {
            callsOut.put(identifier, new ArrayList<>());
        }
    }

    public void removeNode(String methodId) {
        this.callsIn.remove(methodId);
        this.callsOut.remove(methodId);
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Calls In:").append("\n");

        for (String key : callsIn.keySet()) {
            builder.append(key).append(": ");
            builder.append(callsIn.get(key));
            builder.append("\n");
        }

        for (String key : callsIn.keySet()) {
            builder.append(key).append(": ");
            builder.append(callsIn.get(key));
            builder.append("\n");
        }

        builder.append("Calls Out:").append("\n");

        for (String key : callsOut.keySet()) {
            builder.append(key).append(": ");
            builder.append(callsOut.get(key));
            builder.append("\n");
        }

        return builder.toString();
    }
}
