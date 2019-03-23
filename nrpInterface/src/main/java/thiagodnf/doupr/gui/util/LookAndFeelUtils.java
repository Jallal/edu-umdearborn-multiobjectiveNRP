package thiagodnf.doupr.gui.util;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.util.ArrayList;
import java.util.List;

public class LookAndFeelUtils {

    public static boolean isAqua() {
        return UIManager.getLookAndFeel().getID().equalsIgnoreCase("Aqua");
    }

    public static boolean isWindows() {
        return UIManager.getLookAndFeel().getID().equalsIgnoreCase("Windows");
    }

    public static List<String> getInstalled() {

        List<String> options = new ArrayList<>();

        for (LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()) {
            options.add(lookAndFeel.getName());
        }

        return options;
    }

    public static String getSystemLookAndFeel() {
        return getNameFromClassName(UIManager.getSystemLookAndFeelClassName());
    }

    public static String getClassNameFromName(String name) {

        for (LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()) {

            if (lookAndFeel.getName().equalsIgnoreCase(name)) {
                return lookAndFeel.getClassName();
            }
        }

        return "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    }

    public static String getNameFromClassName(String className) {

        for (LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()) {

            if (lookAndFeel.getClassName().equalsIgnoreCase(className)) {
                return lookAndFeel.getName();
            }
        }

        return "Nimbus";
    }
}
