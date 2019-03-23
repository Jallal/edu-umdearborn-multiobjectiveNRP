package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;

public class JProgressBox {

    private static JDialog dialog = null;

    private static JProgressBar progressBar = null;

    public static void start(String title, int maxValue) {

        JFrame parent = MainWindow.getIntance();

        progressBar = new JProgressBar(0, maxValue);
        progressBar.setStringPainted(true);

        dialog = new JDialog(parent, title, true);
        dialog.add(progressBar);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(300, 65);
        dialog.setLocationRelativeTo(parent);

        new Thread(new Runnable() {

            public void run() {
                dialog.setVisible(true);
            }
        }).start();
    }

    public static void close() {

        if (dialog != null) {
            dialog.setVisible(false);
        }

        dialog = null;
        progressBar = null;
    }

    public static void setValue(int number) {
        if (progressBar != null) {
            progressBar.setValue(number);
        }
    }
}
