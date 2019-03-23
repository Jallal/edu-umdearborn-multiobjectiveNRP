package thiagodnf.doupr.gui.model.table;

import thiagodnf.doupr.core.base.ProjectObject;

public class SummaryTableModel extends TableModel {

    private static final long serialVersionUID = 4238689909866943950L;

    protected ProjectObject project;

    public SummaryTableModel() {
        this(new ProjectObject());
    }

    public SummaryTableModel(ProjectObject project) {

        this.project = project;

        addColumn("ID", Integer.class);
        addColumn("Name", String.class);
        addColumn("Type", String.class);
        addColumn("# Attributes", Integer.class);
        addColumn("# Methods", Integer.class);
    }

    @Override
    public int getRowCount() {
        return project.getClasses().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return project.getClasses().get(rowIndex).getSimpleName();
            case 2:
                return project.getClasses().get(rowIndex).isInterface() ? "Interface" : "Class";
            case 3:
                return project.getClasses().get(rowIndex).getAttributes().size();
            case 4:
                return project.getClasses().get(rowIndex).getNumberOfMethods();
            default:
                return "";
        }
    }

    public ProjectObject getProject() {
        return project;
    }

    public void setProject(ProjectObject project) {
        this.project = project;
    }
}
