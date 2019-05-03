package edu.umich.ISELab.optimization.util;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This utility class supports operations related to arrays
 *
 * @author Thiago Ferreira
 * @version 1.0.0
 * @since 2017-10-09
 */
public class ArraysUtils {

    /**
     * This method receives as input an array and fill it with the same
     * value for each position
     *
     * @param array a given array we want to fill it
     * @param value the value used for filling it
     * @return an array where all positions have the same value
     */
    public static double[] fill(double[] array, double value) {

        checkNotNull(array, "The array cannot be null");

        Arrays.fill(array, value);

        return array;
    }

    /**
     * This method receives as input the size of the array, create one, and
     * fill it with the same number
     *
     * @param arraySize the size of the array
     * @param value     the value used for filling it
     * @return an array where all positions have the same value
     */
    public static double[] fill(int arraySize, double value) {

        checkArgument(arraySize >= 0, "The arraySize should be a positive number");

        return fill(new double[arraySize], value);
    }

    public static double[][] fill(double[][] array, double value) {

        for (int i = 0; i < array.length; i++) {
            array[i] = fill(array[i], value);
        }

        return array;
    }

    public static String toString(double[][] array) {

        checkNotNull(array, "The array cannot be null");

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < array.length; i++) {
            buffer.append(Arrays.toString(array[i])).append("\n");
        }

        return buffer.toString();
    }
}
