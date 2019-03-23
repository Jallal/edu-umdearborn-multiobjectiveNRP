package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.gui.subwindow.SubWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public abstract class AbstractPanel extends JPanel {

    private static final long serialVersionUID = -612225461221906504L;

    protected SubWindow parent;

    public AbstractPanel(SubWindow parent, LayoutManager layout) {
        super(layout);

        this.parent = parent;
    }

    public AbstractPanel(LayoutManager layout) {
        this(null, layout);
    }

    public AbstractPanel() {
        super();
    }


    public abstract void load(ProjectObject project, List<Refactoring> refactorings);

    public void update(Object... objects) throws IOException {

    }
}
