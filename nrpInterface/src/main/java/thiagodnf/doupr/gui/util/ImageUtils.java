package thiagodnf.doupr.gui.util;

import javax.swing.*;
import java.net.URL;

public class ImageUtils {

    public static ImageIcon getImageIcon(Object o, String filename) {

//		URL url = o.getClass().getClassLoader().getResource( "/" + filename);
        URL url = o.getClass().getClassLoader().getResource(filename);

        return new ImageIcon(url);
    }
}
