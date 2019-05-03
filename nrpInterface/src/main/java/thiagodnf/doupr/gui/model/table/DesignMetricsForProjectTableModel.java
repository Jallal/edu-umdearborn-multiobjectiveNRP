package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.evaluation.designmetrics.AbstractDesignMetric;
import thiagodnf.doupr.evaluation.util.DesignMetricsUtil;
import thiagodnf.doupr.evaluation.util.EvaluationUtils;

import java.util.List;

public class DesignMetricsForProjectTableModel extends TableModel {

    private static final long serialVersionUID = -6045536120549526699L;

    protected ProjectObject project;

    protected ProjectObject refactored;

    protected List<AbstractDesignMetric> metrics;

    public DesignMetricsForProjectTableModel(ProjectObject project, ProjectObject refactored) {

        this.project = project;
        this.metrics = EvaluationUtils.DESIGN_METRICS;

        addColumn("ID", Integer.class);
        addColumn("Abbrev.", String.class);
        addColumn("Description", String.class);
        addColumn("Design Property", String.class);
        addColumn("Avg. Raw Value", Double.class);
        addColumn("Ratio", Double.class);
    }


    @Override
    public int getRowCount() {
        return metrics.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        AbstractDesignMetric metric = metrics.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return metric.getAbbreviation();
            case 2:
                return metric.toString();
            case 3:
                return metric.getDesignProperty();
            case 4:
                return refactored.getDesignMetrics().get(metric.getDesignProperty());
            case 5:
                return DesignMetricsUtil.rate(project.getDesignMetrics().get(metric.getDesignProperty()), refactored.getDesignMetrics().get(metric.getDesignProperty()));
            default:
                return "";
        }
    }

    public void setRefactored(ProjectObject refactored) {
        this.refactored = refactored;
    }
}
