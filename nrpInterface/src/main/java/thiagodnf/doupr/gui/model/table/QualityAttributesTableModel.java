package thiagodnf.doupr.gui.model.table;

import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.util.EvaluationUtils;

import java.util.ArrayList;
import java.util.List;

public class QualityAttributesTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<Objective> objectives = new ArrayList<>();

    protected ProjectObject project;

    protected ProjectObject refactored;

    public QualityAttributesTableModel(ProjectObject project, ProjectObject refactored) {

        this.project = project;
        this.refactored = refactored;
        this.objectives = new ArrayList<>();

        addColumn("ID", Integer.class);
        addColumn("Components", String.class);
        addColumn("Raw Value", Double.class);
        addColumn("Ratio", Double.class);

        for (Objective objective : EvaluationUtils.OBJECTIVES) {
            this.objectives.add(objective);
        }
    }

    @Override
    public int getRowCount() {
        return objectives.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return objectives.get(rowIndex).toString();
            case 2:
                return objectives.get(rowIndex).getValue(refactored);
            case 3:
                return objectives.get(rowIndex).getDiff(project, refactored);
            default:
                return "";
        }
    }

    public void setRefactored(ProjectObject refactored) {
        this.refactored = refactored;
    }
}
