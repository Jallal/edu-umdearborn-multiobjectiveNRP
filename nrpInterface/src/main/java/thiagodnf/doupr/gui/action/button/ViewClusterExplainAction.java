package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.subwindow.ViewClusterExplainWindow;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.window.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewClusterExplainAction implements ActionListener {

    protected ViewParetoFrontSubWindow window;

    public ViewClusterExplainAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String chartsDir = window.getOutputFolder().toAbsolutePath().toString();

        SubWindow frame = new ViewClusterExplainWindow(chartsDir);

        MainWindow.getIntance().openSubWindow(frame);
    }
}
