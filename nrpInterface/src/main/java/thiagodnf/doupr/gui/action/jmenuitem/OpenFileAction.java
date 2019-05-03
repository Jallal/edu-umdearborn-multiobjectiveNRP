package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.asynctask.AsyncTask;
import thiagodnf.doupr.gui.asynctask.OpenFileAsyncTask;
import thiagodnf.doupr.gui.listener.AsyncTaskListener;
import thiagodnf.doupr.gui.subwindow.RecentFilesSubWindow;
import thiagodnf.doupr.gui.subwindow.ViewFileSubWindow;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenFileAction extends MouseAdapter implements ActionListener, KeyListener {

    protected RecentFilesSubWindow window;

    public OpenFileAction(RecentFilesSubWindow window) {
        this.window = window;
    }

    public OpenFileAction() {
        this(null);
    }

    @Override
    public void mousePressed(MouseEvent me) {

        if (me.getClickCount() == 2) {

            JList<?> list = (JList<?>) me.getSource();

            String file = (String) list.getModel().getElementAt(list.getSelectedIndex());

            load(new File(file.split("_")[0]));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileNameExtensionFilter("Blocks File (.blocks)", "blocks"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(MainWindow.getIntance());

        if (result == JFileChooser.APPROVE_OPTION) {
            load(fileChooser.getSelectedFile());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            JList<?> list = (JList<?>) e.getSource();

            String file = (String) list.getModel().getElementAt(list.getSelectedIndex());

            load(new File(file.split("_")[0]));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    protected void load(File selectedFile) {

        AsyncTask<?> asyncTask = new OpenFileAsyncTask(selectedFile);

        asyncTask.addListener(new AsyncTaskListener() {

            @Override
            public void done(Object obj) {

                ProjectObject project = (ProjectObject) obj;

                List<String> recentFiles = new ArrayList<>();

                recentFiles.add(selectedFile.getAbsolutePath() + "_" + System.currentTimeMillis());

                for (String entry : PreferencesUtils.getRecentFiles()) {

                    if (!entry.startsWith(selectedFile.getAbsolutePath())) {
                        recentFiles.add(entry);
                    }
                }

                PreferencesUtils.setRecentFiles(recentFiles);

                MainWindow.getIntance().openSubWindow(new ViewFileSubWindow(selectedFile, project));

                if (window != null) window.updateWindow();
            }
        });

        asyncTask.execute();
    }
}
