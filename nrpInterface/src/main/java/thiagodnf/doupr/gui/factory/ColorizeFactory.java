package thiagodnf.doupr.gui.factory;

import thiagodnf.doupr.gui.colorize.Colorize;
import thiagodnf.doupr.gui.colorize.ColorizeByEuclideanDistance;
import thiagodnf.doupr.gui.colorize.NoColorize;

public class ColorizeFactory {

    public static Colorize getColorize(String name) {

        if (name.equalsIgnoreCase(new ColorizeByEuclideanDistance().toString())) {
            return new ColorizeByEuclideanDistance();
        } else if (name.equalsIgnoreCase(new NoColorize().toString())) {
            return new NoColorize();
        }

        return new NoColorize();
    }
}
