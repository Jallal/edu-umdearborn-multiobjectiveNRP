package thiagodnf.doupr.gui.action.jmenuitem;

import thiagodnf.doupr.gui.panel.PreferencesPanel;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.MessageBox.MessageBoxListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenPreferencesAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        PreferencesPanel panel = new PreferencesPanel();

        MessageBox.confirm(panel, "Preferences", new MessageBoxListener() {

            @Override
            public void onAccepted(Object selectedOption) {
                ((PreferencesPanel) panel).save();
            }
        });
    }
}

	
