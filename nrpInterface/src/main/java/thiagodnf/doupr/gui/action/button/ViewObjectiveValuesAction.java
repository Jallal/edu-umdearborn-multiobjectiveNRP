package thiagodnf.doupr.gui.action.button;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.subwindow.ViewParetoFrontSubWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ViewObjectiveValuesAction implements ItemListener {

    protected static final Logger LOGGER = Logger.getLogger(ViewObjectiveValuesAction.class);

    private ViewParetoFrontSubWindow window;

    public ViewObjectiveValuesAction(ViewParetoFrontSubWindow window) {
        this.window = window;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getStateChange() == ItemEvent.SELECTED) {

            String selectedOption = (String) event.getItem();

            boolean isNormalized = selectedOption.equalsIgnoreCase("Normalized");

            if (LOGGER.isInfoEnabled()) LOGGER.info("Normalized Values: " + isNormalized);

            window.setNormalized(isNormalized);

            window.updateWindow();
        }
    }
}
