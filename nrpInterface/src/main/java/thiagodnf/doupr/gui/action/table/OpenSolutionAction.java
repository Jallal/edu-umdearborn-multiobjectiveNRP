package thiagodnf.doupr.gui.action.table;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.entity.XYItemEntity;
import thiagodnf.doupr.gui.component.JTable;
import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.window.MainWindow;
import thiagodnf.doupr.optimization.problem.Problem;
import thiagodnf.doupr.optimization.solution.Solution;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenSolutionAction extends MouseAdapter implements ChartMouseListener {

    protected Problem problem;

    protected ViewParetoFrontSubWindow window;

    public OpenSolutionAction(ViewParetoFrontSubWindow window, Problem problem) {
        this.window = window;
        this.problem = problem;
    }

    @Override
    public void mousePressed(MouseEvent me) {

        if (me.getClickCount() == 2) {

            JTable table = (JTable) me.getSource();

            int selectedRow = table.getSelectedRow();

            int id = (int) table.getModel().getValueAt(selectedRow, 0);

            openSolution(window, id);
        }
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {

        if (event.getEntity() instanceof XYItemEntity) {

            XYItemEntity entity = (XYItemEntity) event.getEntity();

            int indexSolution = entity.getSeriesIndex();

            int id = (int) window.getFilteredSolutions().get(indexSolution).getAttribute("id");

            openSolution(window, indexSolution);
        }
    }

    public void openSolution(ViewParetoFrontSubWindow window, int indexSolution) {

        Solution solution = window.getParetoFront().get(indexSolution);

        String title = "Solution " + indexSolution + " | " + window.getTitle();

        solution.setUserSelection();

//		SubWindow frame = new ViewSolutionSubWindow(title, problem, solution);
        SubWindow frame = new ViewSolutionSubWindow(title, window, indexSolution);

        MainWindow.getIntance().openSubWindow(frame);
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        // TODO Auto-generated method stub
    }
}
