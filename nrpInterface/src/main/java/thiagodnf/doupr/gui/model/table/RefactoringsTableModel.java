package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.core.refactoring.NrpBase;

import java.util.ArrayList;
import java.util.List;

public class RefactoringsTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<NrpBase> refactorings;

    public RefactoringsTableModel() {

        this.refactorings = new ArrayList<>();

        addColumn("ID", Integer.class);
        addColumn("Refactoring", String.class);
        addColumn("User Feedback", Double.class);
    }

    @Override
    public int getRowCount() {
        return refactorings.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        NrpBase refactoring = refactorings.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return new RefactoringFormatter(refactoring).toSimpleString();
            case 2:
                return refactoring.getUserFeedback();
            default:
                return "";
        }
    }

    public void setRefactorings(List<NrpBase> refactorings) {
        this.refactorings = refactorings;
    }
}
