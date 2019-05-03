package thiagodnf.doupr.gui.model.table;


import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.solution.Solution;
import vahid.ML.Clustering;

import java.util.ArrayList;
import java.util.List;

public class ClusterCenterTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected List<Solution> paretoFront;

    protected Problem problem;

    protected Clustering clustering;

    public ClusterCenterTableModel(Problem problem, Clustering clustering) {
        this(problem, new ArrayList<>());
        this.clustering = clustering;
    }

    public ClusterCenterTableModel(Problem problem, List<Solution> paretoFront) {

        this.problem = problem;
        this.paretoFront = paretoFront;

        addColumn("SolutionID", Integer.class);
        addColumn("Cluster", String.class);

        for (Objective objective : problem.getObjectives()) {
            addColumn(objective.toString(), Double.class);
        }
    }

    @Override
    public int getRowCount() {
        return clustering.clusterCenters.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Solution solution = paretoFront.get(Integer.parseInt(clustering.clusterCenters.get(rowIndex)[1]));

        switch (columnIndex) {
            case 0:
                return Integer.parseInt(clustering.clusterCenters.get(rowIndex)[1]);
            case 1:
                return "Cluster " + clustering.clusterCenters.get(rowIndex)[0];
        }

        int index = columnIndex - 2;

        return solution.getObjective(index);
    }

    public void setParetoFront(List<Solution> paretoFront) {
        this.paretoFront = paretoFront;
        updateRows();
    }
}