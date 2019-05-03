package thiagodnf.doupr.gui.model.table;

import edu.umich.ISELab.core.grooming.NrpBase;
import edu.umich.ISELab.core.util.StringUtils;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.NrpVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyByRefactoringClassTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected Object[][] data;

    public FrequencyByRefactoringClassTableModel(List<Solution> paretoFront) {

        addColumn("ID", Integer.class);
        addColumn("Refactoring", String.class);
        addColumn("#", Integer.class);

        Map<String, Integer> frequency = new HashMap<>();

        for (Solution solution : paretoFront) {

            List<NrpBase> refactorings = ((NrpVariable) solution.getVariableValue(0)).getRefactorings();

            for (NrpBase refactoring : refactorings) {

                String key = refactoring.getClass().getSimpleName() + " and " + StringUtils.getSimpleName(refactoring.getClass1());

                if (!frequency.containsKey(key)) {
                    frequency.put(key, 0);
                }

                frequency.put(key, frequency.get(key) + 1);
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
