package thiagodnf.doupr.gui.window;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import thiagodnf.doupr.core.util.UUIDUtils;
import thiagodnf.doupr.gui.action.jmenuitem.CloseAllWindowsAction;
import thiagodnf.doupr.gui.action.jmenuitem.CloseApplicationAction;
import thiagodnf.doupr.gui.action.jmenuitem.CloseWindowsAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenAboutAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenAboutDevelopersAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenConsoleAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenFileAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenPreferencesAction;
import thiagodnf.doupr.gui.action.jmenuitem.OpenRecentFilesAction;
import thiagodnf.doupr.gui.action.jmenuitem.WindowAsTileAction;
import thiagodnf.doupr.gui.action.jmenuitem.WindowsAsCascadeAction;
import thiagodnf.doupr.gui.subwindow.RecentFilesSubWindow;
import thiagodnf.doupr.gui.subwindow.SubWindow;
import thiagodnf.doupr.gui.util.LookAndFeelUtils;
import thiagodnf.doupr.gui.util.MessageBox;
import thiagodnf.doupr.gui.util.OSUtils;
import thiagodnf.doupr.gui.util.PreferencesUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Locale;

public class MainWindow extends JFrame {

	protected static final Logger LOGGER = Logger.getLogger(MainWindow.class);
	private static final long serialVersionUID = -6786161270072119947L;
	private static MainWindow instance;
	public String executionId;
	protected JDesktopPane desktop;

	private MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/logo.png")));
		addMenuBar();
		addComponents();
	}

	public static MainWindow getIntance() {
		if (instance == null) {
			instance = new MainWindow();
		}

		return instance;
	}

	protected void addComponents() {

		this.desktop = new JDesktopPane() {

			private static final long serialVersionUID = 365637582706145621L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (LookAndFeelUtils.isWindows()) {
					g.setColor(new Color(171, 171, 171));
					g.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};

		getContentPane().add(desktop);

		// Make dragging a little faster but perhaps uglier.
		//desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		//desktop.setOpaque(false);
	}

	protected void addMenuBar() {

		// Create the menu's item

		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		JMenu windowMenu = new JMenu("Window");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		// Create the menu's menuItem

		JMenuItem openFileMenuItem = new JMenuItem("Open File");
		JMenuItem recentFilesMenuItem = new JMenuItem("Recent Files");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem closeAllMenuItem = new JMenuItem("Close All");
		JMenuItem cascadeMenuItem = new JMenuItem("Cascade");
		JMenuItem tileMenuItem = new JMenuItem("Tile");
		JMenuItem aboutMenuItem = new JMenuItem("About Doupr");
		JMenuItem aboutDevelopersMenuItem = new JMenuItem("About the Developers");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem debugConsoleMenuItem = new JMenuItem("Debug Console");
		JMenuItem preferencesMenuItem = new JMenuItem("Preferences");

		//Define the keystrokes

		openFileMenuItem.setAccelerator(getKeyStroke('O'));
		exitMenuItem.setAccelerator(getKeyStroke('Q'));
		closeMenuItem.setAccelerator(getKeyStroke('W'));

		//Define the Mnemonic
		fileMenu.setMnemonic(KeyEvent.VK_F);
		viewMenu.setMnemonic(KeyEvent.VK_V);
		windowMenu.setMnemonic(KeyEvent.VK_W);
		editMenu.setMnemonic(KeyEvent.VK_T);
		helpMenu.setMnemonic(KeyEvent.VK_H);

		// Define the actions for each item;

		openFileMenuItem.addActionListener(new OpenFileAction());
		exitMenuItem.addActionListener(new CloseApplicationAction());
		closeAllMenuItem.addActionListener(new CloseAllWindowsAction());
		closeMenuItem.addActionListener(new CloseWindowsAction());
		cascadeMenuItem.addActionListener(new WindowsAsCascadeAction());
		tileMenuItem.addActionListener(new WindowAsTileAction());
		debugConsoleMenuItem.addActionListener(new OpenConsoleAction());
		preferencesMenuItem.addActionListener(new OpenPreferencesAction());
		recentFilesMenuItem.addActionListener(new OpenRecentFilesAction());
		aboutDevelopersMenuItem.addActionListener(new OpenAboutDevelopersAction());
		aboutMenuItem.addActionListener(new OpenAboutAction());

		// Build the menus;

		fileMenu.add(openFileMenuItem);

		if (!OSUtils.isMacOS()) {
			fileMenu.addSeparator();
			fileMenu.add(exitMenuItem);
		}

		viewMenu.add(recentFilesMenuItem);
		viewMenu.addSeparator();
		viewMenu.add(debugConsoleMenuItem);

		windowMenu.add(cascadeMenuItem);
		windowMenu.add(tileMenuItem);
		windowMenu.addSeparator();
		windowMenu.add(closeMenuItem);
		windowMenu.add(closeAllMenuItem);

		editMenu.add(preferencesMenuItem);

		helpMenu.add(aboutDevelopersMenuItem);

		if (!OSUtils.isMacOS()) {
			helpMenu.addSeparator();
			helpMenu.add(aboutMenuItem);
		}

		JMenuBar menuBar = new JMenuBar();

		menuBar.add(fileMenu);

		if (!OSUtils.isMacOS()) {
			menuBar.add(editMenu);
		}

		menuBar.add(viewMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);
	}

	public JDesktopPane getDesktop() {
		return this.desktop;
	}

	protected SubWindow find(JInternalFrame subWindow) {

		for (JInternalFrame frame : getDesktop().getAllFrames()) {

			if (subWindow.getTitle().equalsIgnoreCase(frame.getTitle())) {
				return (SubWindow) frame;
			}
		}

		return null;
	}

	public void openSubWindow(SubWindow subWindow) {

		SubWindow frame = find(subWindow);

		if (frame != null) {
			subWindow = frame;
		} else {
			getDesktop().add(subWindow);
		}

		subWindow.createAndShowGUI();

		// put on top the last subwindow added
		try {
			subWindow.setSelected(true);
		} catch (PropertyVetoException ex) {
			MessageBox.error(ex);
		}

		// Remove the focus when a window is created
		subWindow.getContentPane().requestFocusInWindow();
	}

	public void closeSubWindow(SubWindow subWindow) {

		if (LOGGER.isInfoEnabled()) LOGGER.info("Closing the sub-window: " + subWindow.getTitle());

		getDesktop().remove(subWindow);

		revalidate();

		repaint();

		// Active the last frame added
		if (desktop.getAllFrames().length != 0) {

			try {
				desktop.getAllFrames()[0].setSelected(true);
			} catch (PropertyVetoException ex) {
				MessageBox.error(ex);
			}
		}
	}

	protected KeyStroke getKeyStroke(char letter) {
		return KeyStroke.getKeyStroke(letter, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
	}

	public void loadLookAndFeel() {

		String className = LookAndFeelUtils.getClassNameFromName(PreferencesUtils.getLookAndFeel());

		try {
			UIManager.setLookAndFeel(className);
			SwingUtilities.updateComponentTreeUI(MainWindow.getIntance());
		} catch (Exception ex) {
			MessageBox.error(ex);
		}
	}

	public void setLoggingLevel() {
		LogManager.getRootLogger().setLevel(Level.toLevel(PreferencesUtils.getLoggingLevel()));
	}

	public void setLocale() {

		String locale = PreferencesUtils.getLocale();

		String[] array = locale.split("-");

		Locale.setDefault(new Locale(array[0], array[1]));
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	public void createAndShowGUI() {

		if (LOGGER.isInfoEnabled()) LOGGER.info("Showing the main window");

		this.executionId = UUIDUtils.randomUUID();

		if (LOGGER.isInfoEnabled()) LOGGER.info("ExecutionId: " + executionId);

		// Let's define the window title
		setTitle("A Software Refactoring Tool - Version 1.1");

		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// The default size of the window
		setSize(800, 600);

		// Now the window is opened at center
		setLocationRelativeTo(null);

		//Create and set up the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (OSUtils.isMacOS()) {
			getRootPane().putClientProperty("apple.awt.fullscreenable", Boolean.valueOf(true));
		}

		//Display the window.
		setVisible(true);

		// When the app is started, just show the recent files window
		MainWindow.getIntance().openSubWindow(new RecentFilesSubWindow());

		requestFocus();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (LOGGER.isInfoEnabled()) LOGGER.info("Closed");
			}
		});
	}
}
