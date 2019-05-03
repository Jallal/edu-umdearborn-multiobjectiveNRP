package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import edu.umich.ISELab.optimization.util.SolutionListUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearFeedbackAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(ClearFeedbackAction.class);

    protected ViewParetoFrontSubWindow window;

    public ClearFeedbackAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user pressed the button to clear the saved preferences");

        if (!MessageBox.confirm("Warning", "Do you want to clear the user interactions?")) {
            return;
        }

        SolutionListUtils.clearAttributes(window.getParetoFront());

        window.updateUI();
    }
}
