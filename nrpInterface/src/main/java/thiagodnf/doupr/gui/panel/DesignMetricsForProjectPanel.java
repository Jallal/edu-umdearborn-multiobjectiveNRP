package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;

import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.model.table.DesignMetricsForProjectTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesignMetricsForProjectPanel extends JPanelForTabbedPane {

    private static final long serialVersionUID = 2066639243321019204L;

    protected JTable table;

    protected ProjectObject project;

    protected DesignMetricsForProjectTableModel dataModel;

    public DesignMetricsForProjectPanel(ProjectObject project) {

        this.project = project;

        this.dataModel = new DesignMetricsForProjectTableModel(project, project);
        this.table = new JTable(dataModel);
        this.table.getColumnModel().getColumn(1).setMaxWidth(100);
        this.table.getColumnModel().getColumn(4).setMaxWidth(200);
        this.table.getColumnModel().getColumn(5).setMaxWidth(200);

        setLayout(new BorderLayout());

        this.add(new JScrollPane(table));
    }

    @Override
    public void load(ProjectObject refactored, List<NrpBase> refactorings) {
        dataModel.setRefactored(refactored);
        dataModel.updateRows();
    }
}
