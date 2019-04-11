package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.model.table.QualityAttributesTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QualityAttributesPanel extends JPanelForTabbedPane {

    private static final long serialVersionUID = -8546039623530779123L;

    protected JTable table;

    protected ProjectObject project;

    protected QualityAttributesTableModel model;

    public QualityAttributesPanel(ProjectObject project) {

        setLayout(new BorderLayout());

        this.project = project;

        this.model = new QualityAttributesTableModel(project, project);
        this.table = new JTable(model);
        this.table.getColumnModel().getColumn(2).setMaxWidth(80);
        this.table.getColumnModel().getColumn(3).setMaxWidth(80);

        // add the table to the frame
        this.add(new JScrollPane(table));
    }

    @Override
    public void load(ProjectObject refactored, List<NrpBase> refactorings) {
        model.setRefactored(refactored);
        model.updateRows();
    }
}
