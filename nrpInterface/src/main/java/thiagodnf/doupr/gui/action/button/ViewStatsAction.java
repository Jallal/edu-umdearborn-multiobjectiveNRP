package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.subwindow.ViewStatsWindow;
import thiagodnf.doupr.gui.window.MainWindow;
import thiagodnf.doupr.optimization.solution.Solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewStatsAction implements ActionListener {

    protected ViewParetoFrontSubWindow window;

    public ViewStatsAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Solution> paretoFront = window.getParetoFront();

        SubWindow frame = new ViewStatsWindow(paretoFront);

        MainWindow.getIntance().openSubWindow(frame);
    }
}
