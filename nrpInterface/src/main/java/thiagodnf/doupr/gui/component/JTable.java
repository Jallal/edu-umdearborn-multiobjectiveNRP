package thiagodnf.doupr.gui.component;

import org.jdesktop.swingx.JXTable;
import thiagodnf.doupr.gui.util.OSUtils;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class JTable extends JXTable {

    public static final int SORT_ASCENDING = 1;
    public static final int SORT_DESCENDING = 2;
    private static final long serialVersionUID = -4250828860722333420L;

    public JTable(TableModel model) {
        super(model);

        setRowSorter(new TableRowSorter<TableModel>(getModel()));
        setRowHeight(22);

        if (getModel().getColumnName(0).equalsIgnoreCase("ID")) {
            getColumnModel().getColumn(0).setMaxWidth(40);
        }

        JTableHeader header = getTableHeader();

        setShowGrid(false, false);

        if (OSUtils.isMacOS()) {
            header.setPreferredSize(new Dimension((int) header.getPreferredSize().getWidth(), 22));
        }
    }

    public void sort(int columnIndex, int type) {

        getRowSorter().toggleSortOrder(columnIndex);

        if (type == SORT_DESCENDING) {
            getRowSorter().toggleSortOrder(columnIndex);
        }
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

        Component c = super.prepareRenderer(renderer, row, column);

        // Color row based on a cell value

        if (!isRowSelected(row)) {

            if (row % 2 == 0) {
                c.setBackground(getBackground());
            } else {
                c.setBackground(new Color(245, 245, 245));
            }
        }

        return c;
    }

    @Override
    public int getSelectedRow() {

        if (super.getSelectedRow() == -1) {
            return -1;
        }

        return super.convertRowIndexToModel(super.getSelectedRow());
    }

    @Override
    public int[] getSelectedRows() {

        int[] selectedRows = super.getSelectedRows();

        for (int i = 0; i < selectedRows.length; i++) {
            selectedRows[i] = super.convertRowIndexToModel(selectedRows[i]);
        }

        return selectedRows;
    }


}
