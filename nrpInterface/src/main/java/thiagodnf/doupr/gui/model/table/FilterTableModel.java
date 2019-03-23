package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.optimization.filter.Filter;
import thiagodnf.doupr.optimization.util.constants.FilterConstants;

import java.util.ArrayList;
import java.util.List;

public class FilterTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<Filter> filters;

    public FilterTableModel() {
        this(new ArrayList<>());
    }

    public FilterTableModel(List<Filter> filters) {

        this.filters = filters;

        addColumn("Target", String.class);
        addColumn("Objective", String.class);
        addColumn("Rule", String.class);
        addColumn("Value", String.class);
    }

    @Override
    public int getRowCount() {
        return filters.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Filter filter = filters.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return filter.getTarget();
            case 1:
                return filter.getTarget().equalsIgnoreCase(FilterConstants.OBJECTIVE) ? filter.getObjectiveName() : "";
            case 2:
                return filter.getRule();
            case 3:
                return filter.getValue();
            default:
                return "";
        }
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
        updateRows();
    }
}
