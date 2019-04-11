package thiagodnf.doupr.gui.component;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.gui.panel.AbstractPanel;

import java.awt.*;
import java.util.List;

public class JOpaquePanel extends AbstractPanel {

    private static final long serialVersionUID = -3456862541222530509L;

    public JOpaquePanel() {
        super();
        setOpaque(false);
    }

    public JOpaquePanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }

    @Override
    public void load(ProjectObject project, List<NrpBase> refactorings) {
        // TODO Auto-generated method stub

    }
}
