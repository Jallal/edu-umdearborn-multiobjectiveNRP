package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.model.table.FrequencyByClassTableModel;
import thiagodnf.doupr.optimization.solution.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrequencyByClassPanel extends AbstractPanel {

    private static final long serialVersionUID = -6155232591429239382L;

    protected JTable table;

    protected List<Solution> paretoFront;

    public FrequencyByClassPanel(List<Solution> paretoFront) {

        this.paretoFront = paretoFront;

        addComponents();
    }

    public void addComponents() {

        setLayout(new BorderLayout());

        this.table = new JTable(new FrequencyByClassTableModel(paretoFront));
       // this.table.sort(2, JTable.SORT_DESCENDING);
        this.table.getColumnModel().getColumn(2).setMaxWidth(45);

        // add the table to the frame
        this.add(new JScrollPane(table));
    }

    @Override
    public void load(ProjectObject project, List<NrpBase> refactorings) {
        // Not implemented
    }
}
