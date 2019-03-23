package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.panel.SettingsForParetoFrontPanel;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class RemoveFilterAction extends Observable implements ActionListener {

    protected static final Logger LOGGER = Logger.getLogger(RemoveFilterAction.class);

    protected SettingsForParetoFrontPanel panel;

    protected ViewParetoFrontSubWindow window;

    public RemoveFilterAction(ViewParetoFrontSubWindow window, SettingsForParetoFrontPanel panel) {
        this.window = window;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedRow = panel.getTable().getSelectedRow();

        if (selectedRow == -1) {
            MessageBox.warning("You must select at least a filter before remove it");
            return;
        }

        if (LOGGER.isInfoEnabled()) LOGGER.info("Removed Filter");
        if (LOGGER.isInfoEnabled()) LOGGER.info(window.getFilters().get(selectedRow));

        window.getFilters().remove(selectedRow);
        window.updateWindow();
    }
}
