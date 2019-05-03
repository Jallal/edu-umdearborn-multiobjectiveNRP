package thiagodnf.doupr.gui.panel;

import edu.umich.ISELab.core.grooming.NrpBase;
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


    public abstract void load(ProjectObject project, List<NrpBase> refactorings);

    public void update(Object... objects) throws IOException {

    }
}
