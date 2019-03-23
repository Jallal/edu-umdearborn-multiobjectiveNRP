package thiagodnf.doupr.gui.model.list;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RecentFilesListModel extends AbstractListModel<String> {

    private static final long serialVersionUID = 3791791894259562123L;

    protected List<String> recentFiles;

    public RecentFilesListModel() {
        this(new ArrayList<>());
    }

    public RecentFilesListModel(List<String> recentFiles) {
        this.recentFiles = recentFiles;
    }

    @Override
    public int getSize() {
        return recentFiles.size();
    }

    @Override
    public String getElementAt(int index) {
        return recentFiles.get(index);
    }

    public void setRecentFiles(List<String> recentFiles) {
        this.recentFiles = recentFiles;
    }
}
