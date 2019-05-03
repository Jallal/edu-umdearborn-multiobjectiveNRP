package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.evaluation.designmetrics.AbstractDesignMetric;
import thiagodnf.doupr.evaluation.util.EvaluationUtils;

import java.util.List;

public class DesignMetricsForClassTableModel extends TableModel {

    protected List<AbstractDesignMetric> metrics;

    protected ClassObject cls;

    public DesignMetricsForClassTableModel(ClassObject cls) {

        this.cls = cls;
        this.metrics = EvaluationUtils.DESIGN_METRICS;

        addColumn("ID", Integer.class);
        addColumn("Abbrev.", String.class);
        addColumn("Description", String.class);
        addColumn("Design Property", String.class);
        addColumn("Value", Double.class);
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
                return cls.getDesignMetrics().get(metric.getDesignProperty());
            default:
                return "";
        }
    }
}
