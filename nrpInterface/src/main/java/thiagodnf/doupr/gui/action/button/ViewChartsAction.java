package thiagodnf.doupr.gui.action.button;

import org.apache.commons.io.FileUtils;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.PackageObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.sys.LOGGER;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.export.html.generator.HTMLClassDiagramGenerator;
import thiagodnf.doupr.export.html.generator.HTMLHierarchicalEdgeBundlingGenerator;
import thiagodnf.doupr.export.html.generator.HTMLInteractiveForceLayout;
import thiagodnf.doupr.export.html.generator.HTMLRotatingClusterLayout;
import thiagodnf.doupr.gui.subwindow.ViewSolutionSubWindow;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewChartsAction implements ActionListener {

    protected ProjectObject project;

    protected ViewSolutionSubWindow window;

    public ViewChartsAction(ProjectObject project) {
        this.project = project;
    }

    public ViewChartsAction(ViewSolutionSubWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectObject project = this.project;

        if (project == null) {
            project = this.window.getRefactored();
        }

        Object[] possibilities = {
                "Class Diagram",
                "Class Diagram (From a Specified Class)",
                "Dependency Graph",
                "Dependency Graph (From a Specified Class)",
                //	"Rotating Cluster Layout"
        };

        LOGGER.info(this, "Showing the selection dialog called '" + "Choose an option" + "' to user");

        String selectedOption = (String) JOptionPane.showInputDialog(
                MainWindow.getIntance(),
                "Choose an option",
                "Charts",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Class Diagram");

        // The user pushed the cancel button
        if (selectedOption == null || selectedOption.isEmpty()) {

            LOGGER.info(this, "The user close or cancel the dialog");

            return;
        }

        try {
            if (selectedOption.equalsIgnoreCase("Dependency Graph")) {
                openDependencyGraphForAllClasses(project);
            } else if (selectedOption.equalsIgnoreCase("Rotating Cluster Layout")) {
                open(new HTMLRotatingClusterLayout("").getContent(MainWindow.getIntance(), project));
            } else if (selectedOption.equalsIgnoreCase("Interactive Force Layout")) {
                open(new HTMLInteractiveForceLayout("").getContent(MainWindow.getIntance(), project));
            } else if (selectedOption.equalsIgnoreCase("Class Diagram")) {
                openClassDiagramForAllClasses(project);
            } else if (selectedOption.equalsIgnoreCase("Class Diagram (From a Specified Class)")) {
                openClassDiagramFromASpecifiedClass(project);
            } else if (selectedOption.equalsIgnoreCase("Dependency Graph (From a Specified Class)")) {
                openCallsFromASpecifiedClass(project);
            }
        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }

    protected void openClassDiagramForAllClasses(ProjectObject project) {

        LOGGER.info(this, "Opening Class Diagram for all classes");

        try {
            open(new HTMLClassDiagramGenerator("").getContent(MainWindow.getIntance(), project));
        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }

    protected void openDependencyGraphForAllClasses(ProjectObject project) {

        LOGGER.info(this, "Opening Dependency Graph for all classes");

        try {
            open(new HTMLHierarchicalEdgeBundlingGenerator("").getContent(MainWindow.getIntance(), project));
        } catch (Exception ex) {
            MessageBox.error(ex);
        }
    }

    protected void openClassDiagramFromASpecifiedClass(ProjectObject project) {

        LOGGER.info(this, "Opening Class Diagram from a specific class");

        List<String> options = new ArrayList<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {
                options.add(cls.getName());
            }
        }

        MessageBox.select("Choose a Class", options, new MessageBox.MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                ClassObject cls = ProjectObjectUtils.findByName(project, String.valueOf(selectedOption));

                LOGGER.info(this, "The user selected: " + cls.getName());

                try {
                    open(new HTMLClassDiagramGenerator("").getContent(MainWindow.getIntance(), project, cls));
                } catch (Exception ex) {
                    MessageBox.error(ex);
                }
            }
        });
    }

    protected void openCallsFromASpecifiedClass(ProjectObject project) {

        LOGGER.info(this, "Opening dependency graph from a specific class");

        List<String> options = new ArrayList<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {
                options.add(cls.getName());
            }
        }

        MessageBox.select("Choose a Class", options, new MessageBox.MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {

                ClassObject cls = ProjectObjectUtils.findByName(project, String.valueOf(selectedOption));

                LOGGER.info(this, "The user selected: " + cls.getName());

                try {
                    open(new HTMLHierarchicalEdgeBundlingGenerator("").getContent(MainWindow.getIntance(), project, cls));
                } catch (Exception ex) {
                    MessageBox.error(ex);
                }
            }
        });
    }

    protected void open(String content) {
        try {
            File tempFile = File.createTempFile("doupr", ".html");

            FileUtils.writeStringToFile(tempFile, content);

            tempFile.deleteOnExit();

            Desktop.getDesktop().browse(tempFile.toURI());
        } catch (IOException ex) {
            MessageBox.error(ex);
        }
    }
}
