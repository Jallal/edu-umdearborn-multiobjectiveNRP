package thiagodnf.doupr.gui.panel;

import edu.umich.ISELab.core.grooming.NrpBase;
import thiagodnf.doupr.gui.action.table.OpenSolutionAction;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.model.table.ParetoFrontTableModel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.solution.Solution;
import vahid.ML.Clustering;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParetoFrontPanel extends JPanelForTabbedPane {

    private static final long serialVersionUID = 3568509262970040686L;

    protected JTable table;

    protected ViewParetoFrontSubWindow window;

    protected Problem problem;

    protected Clustering clustering;

    public ParetoFrontPanel(ViewParetoFrontSubWindow window, Problem problem, List<Solution> paretoFront, Clustering clustering) {

        setLayout(new BorderLayout());

        this.window = window;
        this.problem = problem;
        this.clustering = clustering;

        addComponents();
    }

    private void addComponents() {
        this.table = new JTable(new ParetoFrontTableModel(problem, clustering));
        this.table.addMouseListener(new OpenSolutionAction(window, problem));

        // add the table to the frame
        this.add(new JScrollPane(table));
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void load(ProjectObject project, List<NrpBase> refactorings) {

    }

    @SuppressWarnings("unchecked")
    public void update(Object... objects) {
        ((ParetoFrontTableModel) this.table.getModel()).setParetoFront((List<Solution>) objects[0]);
    }
}
