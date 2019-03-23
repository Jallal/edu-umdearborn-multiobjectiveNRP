

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.sys.MacOSManager;
import thiagodnf.doupr.gui.util.OSUtils;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Launcher {

    protected static final Logger LOGGER = Logger.getLogger(Launcher.class);

    public static void main(String[] args) throws IOException {
        System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");


        if (LOGGER.isInfoEnabled())
            LOGGER.info("Starting the app");
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Operational System: " + System.getProperty("os.name"));

        if (OSUtils.isMacOS()) {
            System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Doupr");
            System.setProperty("apple.awt.application.name", "Doupr");
            new MacOSManager();
        }

        // Load the preferences
        MainWindow.getIntance().loadLookAndFeel();
        MainWindow.getIntance().setLoggingLevel();
        MainWindow.getIntance().setLocale();

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainWindow.getIntance().createAndShowGUI();
            }
        });
    }
}
