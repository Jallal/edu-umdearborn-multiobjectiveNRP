package thiagodnf.doupr.gui.panel;

import org.apache.log4j.Logger;
import thiagodnf.doupr.gui.action.button.ClearSavedPreferencesAction;
import thiagodnf.doupr.gui.component.JOpaquePanel;
import thiagodnf.doupr.gui.component.JSortedComboBox;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.gui.util.LookAndFeelUtils;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import thiagodnf.doupr.gui.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PreferencesPanel extends JPanel {

    protected static final Logger LOGGER = Logger.getLogger(PreferencesPanel.class);

    private static final long serialVersionUID = 6195566393770277255L;

    protected JSortedComboBox<String> lookAndFeelsComboBox;

    protected JComboBox<String> loggingLevelComboBox;

    protected JSortedComboBox<String> localeComboBox;

    protected JSortedComboBox<String> languageComboBox;

    protected JButton clearSavedPreferencesButton;

    protected JCheckBox saveOptimizationPreferencesCheckBox;

    protected JCheckBox saveContinuePreferencesCheckBox;

    public PreferencesPanel() {

        if (LOGGER.isInfoEnabled()) LOGGER.info("Loading Preference Panel");

        addComponents();

        init();
    }

    public void init() {
        this.lookAndFeelsComboBox.setSelectedItem(PreferencesUtils.getLookAndFeel());
        this.loggingLevelComboBox.setSelectedItem(PreferencesUtils.getLoggingLevel());
        this.localeComboBox.setSelectedItem(PreferencesUtils.getLocale());
        this.languageComboBox.setSelectedItem(PreferencesUtils.getLanguage());
        this.saveOptimizationPreferencesCheckBox.setSelected(PreferencesUtils.getSaveOptimizationPreferences());
        this.saveContinuePreferencesCheckBox.setSelected(PreferencesUtils.getSaveContinuePreferences());
    }

    protected void addComponents() {

        this.lookAndFeelsComboBox = new JSortedComboBox<String>();
        this.loggingLevelComboBox = new JComboBox<String>();
        this.localeComboBox = new JSortedComboBox<String>();
        this.clearSavedPreferencesButton = new JButton("Clear");
        this.saveOptimizationPreferencesCheckBox = new JCheckBox("Save Optimization Preferences");
        this.saveContinuePreferencesCheckBox = new JCheckBox("Save Continue Preferences");
        this.languageComboBox = new JSortedComboBox<String>();

        // Add the actions

        this.clearSavedPreferencesButton.addActionListener(new ClearSavedPreferencesAction(this));
        this.saveOptimizationPreferencesCheckBox.setOpaque(false);

        // Initialize the values

        for (String lookAndFeel : LookAndFeelUtils.getInstalled()) {
            this.lookAndFeelsComboBox.addItem(lookAndFeel);
        }

        for (String language : Arrays.asList("English")) {
            this.languageComboBox.addItem(language);
        }

        for (String loggingLevel : Arrays.asList("OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE", "ALL")) {
            this.loggingLevelComboBox.addItem(loggingLevel);
        }

        for (String locale : Arrays.asList("pt-BR", "en-US")) {
            this.localeComboBox.addItem(locale);
        }

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("General", null, getGeneralPanel());
        tabbedPane.addTab("Advanced", null, getAdvancedPanel());

        add(tabbedPane);
    }

    protected JPanel getGeneralPanel() {

        JPanel panel = new JOpaquePanel(new GridBagLayout());

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagUtils.setComponent(panel, new JLabel("Look and Feel:"), 0, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panel, lookAndFeelsComboBox, 0, 1, "NONE", "HORIZONTAL");

        GridBagUtils.setSeparator(panel, 1, 2);

        GridBagUtils.setComponent(panel, new JLabel("Language:"), 2, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panel, languageComboBox, 2, 1, "NONE", "HORIZONTAL");

        GridBagUtils.setComponent(panel, new JLabel("Locale:"), 3, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panel, localeComboBox, 3, 1, "NONE", "HORIZONTAL");

        GridBagUtils.setSeparator(panel, 4, 2);

        GridBagUtils.setComponent(panel, saveOptimizationPreferencesCheckBox, 5, 1, "NONE", "HORIZONTAL");
        GridBagUtils.setComponent(panel, saveContinuePreferencesCheckBox, 6, 1, "NONE", "HORIZONTAL");

        return panel;
    }

    protected JPanel getAdvancedPanel() {

        JPanel panel = new JOpaquePanel(new GridBagLayout());

        if (LookAndFeelUtils.isWindows()) {
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        GridBagUtils.setComponent(panel, new JLabel("Logging Level:"), 0, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panel, loggingLevelComboBox, 0, 1, "NONE", "HORIZONTAL");

        GridBagUtils.setComponent(panel, new JLabel("Saved Preferences:"), 1, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panel, clearSavedPreferencesButton, 1, 1, "NONE", "HORIZONTAL");

        JPanel topPanel = new JOpaquePanel(new GridBagLayout());

        GridBagUtils.setComponent(topPanel, panel, 0, 0, "NORTH", "HORIZONTAL");

        return topPanel;
    }

    public void save() {

        if (LOGGER.isInfoEnabled()) LOGGER.debug("Saving the preferences");

        PreferencesUtils.setLookAndFeel((String) lookAndFeelsComboBox.getSelectedItem());
        PreferencesUtils.setLoggingLevel((String) loggingLevelComboBox.getSelectedItem());
        PreferencesUtils.setLocale((String) localeComboBox.getSelectedItem());
        PreferencesUtils.setSaveOptimizationPreferences(saveOptimizationPreferencesCheckBox.isSelected());
        PreferencesUtils.setSaveContinuePreferences(saveContinuePreferencesCheckBox.isSelected());
        PreferencesUtils.setLanguage((String) languageComboBox.getSelectedItem());

        if (LOGGER.isInfoEnabled()) LOGGER.debug("Reloading the user preferences");

        MainWindow.getIntance().loadLookAndFeel();
        MainWindow.getIntance().setLoggingLevel();
        MainWindow.getIntance().setLocale();
    }
}
