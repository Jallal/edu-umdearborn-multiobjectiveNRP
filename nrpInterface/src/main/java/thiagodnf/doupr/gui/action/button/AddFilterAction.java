package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.panel.FormFilterPanel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.MessageBox.MessageBoxListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFilterAction implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(AddFilterAction.class);

    protected ViewParetoFrontSubWindow window;

    public AddFilterAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FormFilterPanel panel = new FormFilterPanel(window.getProblem());

        MessageBox.confirm(panel, "New Filter", new MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                if (LOGGER.isInfoEnabled()) LOGGER.info("Added Filter");
                if (LOGGER.isInfoEnabled()) LOGGER.info(panel.getSelectedFilter());

                window.getFilters().add(panel.getSelectedFilter());
                window.updateWindow();
            }
        });
    }
}
