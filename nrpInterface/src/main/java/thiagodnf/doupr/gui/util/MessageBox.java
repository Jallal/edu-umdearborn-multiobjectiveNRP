package thiagodnf.doupr.gui.util;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class MessageBox {

    protected static final Logger LOGGER = Logger.getLogger(MessageBox.class);

    public static void info(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Success", JOptionPane.INFORMATION_MESSAGE);

        if (LOGGER.isInfoEnabled()) LOGGER.info("Showing success message: " + msg);
    }

    public static void info(String msg) {
        info(MainWindow.getIntance(), msg);
    }

    public static void warning(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Warning", JOptionPane.WARNING_MESSAGE);

        if (LOGGER.isInfoEnabled()) LOGGER.info("Showing warning message: " + msg);
    }

    public static void warning(String msg) {
        warning(MainWindow.getIntance(), msg);
    }

    public static void error(Component parent, String msg, Exception ex) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);

//		if(ex != null) {
        LOGGER.error(msg, ex);
//		}else {
//			LOGGER.error();
//		}
    }

    public static void error(String msg, Exception ex) {
        error(MainWindow.getIntance(), msg, ex);
    }

    public static void error(String msg) {
        error(msg, null);
    }

    public static void error(Exception ex) {
        error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    public static boolean confirm(Component parent, String title, String msg) {

        if (LOGGER.isInfoEnabled()) LOGGER.info(msg + " Confirm that? ");

        int result = JOptionPane.showConfirmDialog(parent, msg, title, JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user pressed OK");

            return true;
        }

        if (LOGGER.isInfoEnabled()) LOGGER.info("The user canceled");

        return false;
    }

    public static boolean confirm(String title, String msg) {
        return confirm(MainWindow.getIntance(), title, msg);
    }

    public static void confirm(JPanel panel, String title, MessageBoxListener listener) {
        confirm(MainWindow.getIntance(), panel, title, JOptionPane.OK_CANCEL_OPTION, listener);
    }

    public static void confirm(JPanel panel, String title, int optionType, MessageBoxListener listener) {
        confirm(MainWindow.getIntance(), panel, title, optionType, listener);
    }

    public static void confirm(Component parent, JPanel panel, String title, int optionType, MessageBoxListener listener) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Confirm dialog with panel: " + title);

        int option = JOptionPane.showConfirmDialog(
                parent,
                panel,
                title,
                optionType,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.CANCEL_OPTION) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user cancelled the dialog");

            return;
        }

        if (option == JOptionPane.CLOSED_OPTION) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user close the dialog");

            return;
        }

        if (listener != null) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user pushed the OK button");

            listener.onAccepted(option);
        }
    }

    public static void select(String title, List<String> options, MessageBoxListener listener) {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Showing the selection dialog called '" + title + "' to user");

        if (options.isEmpty()) {
            return;
        }

        // Before show the list to the user it is necessary to sort it
        Collections.sort(options);

        Object[] possibilities = options.toArray(new Object[options.size()]);

        String selectedOption = (String) JOptionPane.showInputDialog(
                MainWindow.getIntance(),
                "Choose an option:",
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                possibilities[0]
        );

        // The user pushed the cancel button
        if (selectedOption == null || selectedOption.isEmpty()) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user close or cancel the dialog");

            return;
        }

        if (listener != null) {

            if (LOGGER.isInfoEnabled()) LOGGER.info("The user pushed the OK button");

            listener.onAccepted(selectedOption);
        }
    }

    public interface MessageBoxListener {

        public void onAccepted(Object selectedOption);
    }
}
