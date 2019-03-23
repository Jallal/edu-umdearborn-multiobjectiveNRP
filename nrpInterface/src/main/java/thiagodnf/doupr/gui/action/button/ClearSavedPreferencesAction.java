package thiagodnf.doupr.gui.action.button;

import thiagodnf.doupr.gui.panel.PreferencesPanel;
import thiagodnf.doupr.gui.sys.PreferencesManager;
import thiagodnf.doupr.gui.util.MessageBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

public class ClearSavedPreferencesAction implements ActionListener {

    protected PreferencesPanel preferencesPanel;

    public ClearSavedPreferencesAction(PreferencesPanel preferencesPanel) {
        this.preferencesPanel = preferencesPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!MessageBox.confirm(preferencesPanel, "Warning", "Do you want to clear the saved preferences?")) {
            return;
        }

        try {
            PreferencesManager.clearAll();
        } catch (BackingStoreException ex) {
            MessageBox.error(ex);
        }

        MessageBox.info(preferencesPanel, "The saved preferences were cleaned");

        preferencesPanel.init();
    }
}
