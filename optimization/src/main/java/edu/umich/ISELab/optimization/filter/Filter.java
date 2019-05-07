package edu.umich.ISELab.optimization.filter;

import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.ProblemUtils;
import edu.umich.ISELab.optimization.util.constants.FilterConstants;
import edu.umich.ISELab.optimization.problem.Problem;

public class Filter {

    protected String target;

    protected String objectiveName;

    protected String rule;

    protected String value;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public void setObjectiveName(String objectiveName) {
        this.objectiveName = objectiveName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean filter(Problem problem, Solution solution) {

        if (rule.equalsIgnoreCase(FilterConstants.CONTAINS) && solution.toString().toLowerCase().contains(value.toLowerCase())) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.NOT_CONTAINS) && !(solution.toString().toLowerCase().contains(value.toLowerCase()))) {
            return true;
        }

        int index = ProblemUtils.getObjectiveIndex(problem, objectiveName);

        if (index == -1) {
            return false;
        }

        if (rule.equalsIgnoreCase(FilterConstants.EQUAL_TO) && solution.getObjective(index) == Double.valueOf(value)) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.NOT_EQUAL_TO) && solution.getObjective(index) != Double.valueOf(value)) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.GREATER_THAN) && solution.getObjective(index) > Double.valueOf(value)) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.LESS_THAN) && solution.getObjective(index) < Double.valueOf(value)) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.GREATER_THAN_OR_EQUAL_TO) && solution.getObjective(index) >= Double.valueOf(value)) {
            return true;
        }

        if (rule.equalsIgnoreCase(FilterConstants.LESS_THAN_OR_EQUAL_TO) && solution.getObjective(index) <= Double.valueOf(value)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Filter [target=" + target + ", objectiveName=" + objectiveName + ", rule=" + rule + ", value=" + value
                + "]";
    }


}
