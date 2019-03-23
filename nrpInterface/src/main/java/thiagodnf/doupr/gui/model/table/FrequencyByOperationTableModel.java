package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.optimization.solution.Solution;
import thiagodnf.doupr.optimization.variables.RefactoringVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyByOperationTableModel extends TableModel {

    private static final long serialVersionUID = 7000095650637763498L;

    protected Object[][] data;

    public FrequencyByOperationTableModel(List<Solution> paretoFront) {

        addColumn("ID", Integer.class);
        addColumn("Operation", String.class);
        addColumn("#", Integer.class);

        Map<String, Integer> frequency = new HashMap<>();

        for (Solution solution : paretoFront) {

            List<Refactoring> refactorings = ((RefactoringVariable) solution.getVariableValue(0)).getRefactorings();

            for (Refactoring refactoring : refactorings) {
                if (!frequency.containsKey(refactoring.getName())) {
                    frequency.put(refactoring.getName(), 0);
                }

                frequency.put(refactoring.getName(), frequency.get(refactoring.getName()) + 1);
            }
        }

        this.data = new Object[frequency.size()][3];

        int counter = 0;

        for (String key : frequency.keySet()) {
            data[counter][0] = counter;
            data[counter][1] = key;
            data[counter][2] = frequency.get(key);

            counter++;
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
