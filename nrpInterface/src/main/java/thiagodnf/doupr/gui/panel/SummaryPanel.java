package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.action.table.OpenClassAction;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.model.table.SummaryTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SummaryPanel extends JPanelForTabbedPane {

    private static final long serialVersionUID = 2429978364028158965L;

    protected JTable table;

    protected ProjectObject project;

    protected SummaryTableModel summaryTableModel;

    public SummaryPanel() {

        setLayout(new BorderLayout());

        this.summaryTableModel = new SummaryTableModel();

        this.table = new JTable(summaryTableModel);
        this.table.addMouseListener(new OpenClassAction(this));
       // this.table.getRowSorter().toggleSortOrder(1);
        this.table.getColumnModel().getColumn(2);
        this.table.getColumnModel().getColumn(3);
        this.table.getColumnModel().getColumn(4);

        // add the table to the frame
        this.add(new JScrollPane(table));
    }

    @Override
    public void load(ProjectObject project, List<NrpBase> refactorings) {
        this.project = project;
        this.summaryTableModel.setProject(project);
        this.summaryTableModel.updateRows();
        this.table.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    public ProjectObject getProject() {
        return project;
    }
}
