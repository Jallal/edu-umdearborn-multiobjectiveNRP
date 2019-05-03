package thiagodnf.doupr.gui.model.table;

import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.solution.Solution;
import vahid.ML.Clustering;

import java.util.ArrayList;
import java.util.List;

public class ParetoFrontTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<Solution> paretoFront;

    protected Problem problem;

    protected Clustering clustering;

    public ParetoFrontTableModel(Problem problem, Clustering clustering) {
        this(problem, new ArrayList<>(), clustering);
    }

    public ParetoFrontTableModel(Problem problem, List<Solution> paretoFront, Clustering clustering) {

        this.problem = problem;
        this.paretoFront = paretoFront;
        this.clustering = clustering;

        addColumn("ID", Integer.class);
        addColumn("Name", String.class);

        for (Objective objective : problem.getObjectives()) {
            addColumn(objective.toString(), Double.class);
        }

        addColumn("User Selection", String.class);
        addColumn("User Feedback", Double.class);
        addColumn("Cluster", Integer.class);
    }

    @Override
    public int getRowCount() {
        return paretoFront.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Solution solution = paretoFront.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return solution.getAttribute("id");
            case 1:
                return "Solution " + solution.getAttribute("id");
        }

        if (columnIndex == getColumnCount() - 3) {
            return solution.getUserSelection() ? "Yes" : "No";
        }

        if (columnIndex == getColumnCount() - 2) {
            return solution.getUserFeedback();
        }

        if (columnIndex == getColumnCount() - 1) {
            return clustering.clusterLabels.get(rowIndex)[1];
        }

        int index = columnIndex - 2;

        return solution.getObjective(index);
    }

    public void setParetoFront(List<Solution> paretoFront) {
        this.paretoFront = paretoFront;
        updateRows();
    }
}
