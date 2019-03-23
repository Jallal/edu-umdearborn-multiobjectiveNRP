package thiagodnf.doupr.gui.sys;

import thiagodnf.doupr.gui.util.MessageBox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesManager {

    private static PreferencesManager instance;

    private PreferencesManager() {
        // You must not create an instance of this class. For that,
        // use PreferencesManager.getInstance()
    }

    public static PreferencesManager getInstance() {

        if (instance == null) {
            instance = new PreferencesManager();
        }

        return instance;
    }

    public static void clearAll() throws BackingStoreException {
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).clear();
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).removeNode();
    }

    public static String[] getKeys() throws BackingStoreException {
        return Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).keys();
    }

    public void saveObject(String key, Object value) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).putByteArray(key, baos.toByteArray());
        } catch (IOException ex) {
            MessageBox.error(ex);
        }
    }

    public void save(String key, double value) {
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).putDouble(key, value);
    }

    public void save(String key, boolean value) {
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).putBoolean(key, value);
    }

    public void save(String key, int value) {
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).putInt(key, value);
    }

    public void save(String key, String value) {
        Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).put(key, value);
    }

    public String restore(String key, String defaultOption) {
        return Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).get(key, defaultOption);
    }

    public double restore(String key, double defaultOption) {
        return Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).getDouble(key, defaultOption);
    }

    public int restore(String key, int defaultOption) {
        return Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).getInt(key, defaultOption);
    }

    public boolean restore(String key, boolean defaultOption) {
        return Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).getBoolean(key, defaultOption);
    }

    public Object restoreObject(String key, Object defaultOption) {

        byte[] array = Preferences.userRoot().node(PreferencesManager.class.getSimpleName()).getByteArray(key, null);

        if (array == null) {
            return defaultOption;
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(array);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception ex) {
            MessageBox.error(ex);
        }

        return defaultOption;
    }
}
