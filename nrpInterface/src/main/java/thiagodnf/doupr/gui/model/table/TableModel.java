package thiagodnf.doupr.gui.model.table;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = -4082269687548756250L;

    protected List<String> columnNames;

    protected Map<String, Class<?>> columnsType;

    public TableModel() {
        this.columnNames = new ArrayList<>();
        this.columnsType = new HashMap<>();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columnNames.get(columnIndex);
    }

    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex >= columnNames.size()) {
            return Object.class;
        }

        return this.columnsType.get(this.columnNames.get(columnIndex));
    }

    public void addColumn(String name, Class<?> type) {
        this.columnNames.add(name);
        this.columnsType.put(name, type);
    }

    public void updateRows() {
        fireTableDataChanged();
    }
}
