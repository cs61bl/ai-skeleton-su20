import java.math.*;

/**
 *  Tests calcNetForceExertedByXY
 */
public class TestCalcNetForceExertedByXY {

    /**
     *  Tests calcNetForceExertedByXY.
     */
    public static void main(String[] args) {
        calcNetForceExertedByXY();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     */
    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected
                    + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected
                    + " and you gave " + actual);
        }
    }

    /**
     *  Rounds a double value to a number of decimal places.
     *
     *  @param  value   Double to be rounded.
     *  @param  places  Integer number of places to round VALUE to.
     */
    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *  Checks the Body class to make sure calcNetForceExertedByXY works.
     */
    private static void calcNetForceExertedByXY() {
        System.out.println("Checking calcNetForceExertedByXY...");

        Body p1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body p2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");

        Body p3 = new Body(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body p4 = new Body(3.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Body[] planets = {p2, p3, p4};

        double xNetForce = p1.calcNetForceExertedByX(planets);
        double yNetForce = p1.calcNetForceExertedByY(planets);

        checkEquals(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");

        System.out.println("Running test again, but with array that contains the target planet.");

        planets = new Body[]{p1, p2, p3, p4};

        xNetForce = p1.calcNetForceExertedByX(planets);
        yNetForce = p1.calcNetForceExertedByY(planets);

        checkEquals(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");
    }
}