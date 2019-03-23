package thiagodnf.doupr.gui.sys;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.AppEvent.QuitEvent;
import com.apple.eawt.PreferencesHandler;
import com.apple.eawt.QuitHandler;
import com.apple.eawt.QuitResponse;
import thiagodnf.doupr.gui.action.jmenuitem.OpenAboutAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenPreferencesAction;

@SuppressWarnings({"restriction"})
public class MacOSManager implements AboutHandler, QuitHandler, PreferencesHandler {

    public MacOSManager() {
        com.apple.eawt.Application app = com.apple.eawt.Application.getApplication();
        app.setAboutHandler(this);
        app.setPreferencesHandler(this);
        app.setQuitHandler(this);
    }

    @Override
    public void handlePreferences(PreferencesEvent arg0) {
        new OpenPreferencesAction().actionPerformed(null);
    }

    @Override
    public void handleQuitRequestWith(QuitEvent arg0, QuitResponse arg1) {
        System.exit(0);
    }

    @Override
    public void handleAbout(AboutEvent e) {
        new OpenAboutAction().actionPerformed(null);
    }
}
