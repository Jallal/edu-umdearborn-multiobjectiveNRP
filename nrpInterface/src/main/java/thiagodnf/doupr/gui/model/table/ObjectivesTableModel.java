package thiagodnf.doupr.gui.model.table;

import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.util.constants.ObjectiveConstants;

import java.util.List;

public class ObjectivesTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<Objective> objectives;

    public ObjectivesTableModel(List<Objective> objectives) {

        this.objectives = objectives;

        addColumn("ID", Integer.class);
        addColumn("Objective", String.class);
        addColumn("Ranking", Double.class);
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
                return objectives.get(rowIndex).getAttributes().get(ObjectiveConstants.RANKING);
            default:
                return "";
        }
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
        updateRows();
    }
}
