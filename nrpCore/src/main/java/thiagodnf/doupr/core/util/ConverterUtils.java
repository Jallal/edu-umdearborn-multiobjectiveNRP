package thiagodnf.doupr.core.util;

public class ConverterUtils {

    public static double[] toDoubleArray(String[] array) {

        double[] result = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            result[i] = Double.valueOf(array[i]);
        }

        return result;
    }

}
