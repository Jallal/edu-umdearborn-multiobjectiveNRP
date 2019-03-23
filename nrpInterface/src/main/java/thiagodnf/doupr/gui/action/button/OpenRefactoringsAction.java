package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.core.util.FileReaderUtils;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenRefactoringsAction implements ActionListener {

    protected ViewSolutionSubWindow window;

    public OpenRefactoringsAction(ViewSolutionSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();

        // The possible format that the user can choose
        chooser.setFileFilter(new FileNameExtensionFilter("Refactoring List (.refactorings)", "refactorings"));

        // Show only the files that have the same extension filter
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = chooser.showOpenDialog(MainWindow.getIntance());

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();

        // Before add all refactorings, we have to remove all included ones
        window.removeAllRefactorings();

        try {
            window.getRefactorings().addAll(FileReaderUtils.readRefactorings(selectedFile));
            window.applyRefactorings();
        } catch (IOException ex) {
            MessageBox.error(ex);
        }
    }
}
