package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.gui.model.table.DesignMetricsForClassTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesignMetricsForClassPanel extends AbstractPanel {

    private static final long serialVersionUID = 1724783358047819259L;

    protected JTable table;

    protected ProjectObject project;

    public DesignMetricsForClassPanel(ClassObject cls) {

        setLayout(new BorderLayout());

        this.table = new JTable(new DesignMetricsForClassTableModel(cls));
        this.table.getColumnModel().getColumn(1).setMaxWidth(100);
        this.table.getColumnModel().getColumn(4).setMaxWidth(100);

        this.add(new JScrollPane(table));
    }

    @Override
    public void load(ProjectObject refactored, List<Refactoring> refactorings) {
        // Not used
    }
}
