package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.colorize.Colorize;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChooseColorizeAction implements ItemListener {

    protected static final Logger LOGGER = Logger.getLogger(ChooseColorizeAction.class);

    private ViewParetoFrontSubWindow window;

    public ChooseColorizeAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("Colorize: " + (Colorize) event.getItem());

            window.setColorize((Colorize) event.getItem());
            window.updateWindow();
        }
    }
}
