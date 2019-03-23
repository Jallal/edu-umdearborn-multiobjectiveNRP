package thiagodnf.doupr.gui.util;


import java.awt.*;

public class ColorUtils {

	public static Color getColor(double power) {

		double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
		double S = 0.7; // Saturation
		double B = 0.7; // Brightness

		return Color.getHSBColor((float) H, (float) S, (float) B);
	}

	public static Color getColor(int index, double[][] distance) {

		Color[] colors = new Color[distance.length];

		for (int i = 0; i < distance.length; i++) {
			colors[i] = getColor(1.0 - distance[i][index]);
		}

		return blend(colors);
	}

	public static Color blend(Color origin, Color over) {

		int red = (origin.getRed() + over.getRed()) / 2;
		int green = (origin.getGreen() + over.getGreen()) / 2;
		int blue = (origin.getBlue() + over.getBlue()) / 2;

		return new Color(red, green, blue);
	}

	public static Color blend(Color... colors) {

		if (colors.length == 0) {
			return Color.BLACK;
		}

		Color temp = colors[0];

		for (int i = 1; i < colors.length; i++) {
			temp = blend(temp, colors[i]);
		}

		return temp;
	}
}
