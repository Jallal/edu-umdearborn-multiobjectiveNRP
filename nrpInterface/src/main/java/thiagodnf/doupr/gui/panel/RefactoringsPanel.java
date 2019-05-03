package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.action.table.EvaluateAction;
import thiagodnf.doupr.gui.model.table.RefactoringsTableModel;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RefactoringsPanel extends AbstractPanel {

    private static final long serialVersionUID = 3568509262970040686L;

    protected JTable table;

    protected RefactoringsTableModel tableModel;

    public RefactoringsPanel(ViewSolutionSubWindow window) {

        setLayout(new BorderLayout());

        this.tableModel = new RefactoringsTableModel();
        this.table = new JTable(tableModel);
        this.table.addMouseListener(new EvaluateAction(window));
        this.table.getColumnModel().getColumn(1).setPreferredWidth(400);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(60);
        this.table.setRowSorter(null); // The user cannot sort these columns

        // add the table to the frame
        this.add(new JScrollPane(table));
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void load(ProjectObject project, List<NrpBase> refactorings) {
        this.tableModel.setRefactorings(refactorings);
        updateRows();
    }

    public void updateRows() {
        this.tableModel.updateRows();
    }
}
