package bermuda;

public class Calculations {

    /**
     * Round number to x amount of precision
     * @param value         Number to get decimal point value of.
     * @param precision     Decimal number
     * @return
     */
    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
