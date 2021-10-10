package project_2;

import org.junit.Assert;
import org.junit.Test;


public class DateTest {
    /** For test case #1 */
    private static final String DATEBEFORE = "12/31/2020";
    /** For test case #2 */
    private static final String DATEAFTER = "12/31/2021";
    /** For test case #3 */
    private static final String DATEFEB28NONLEAP = "02/28/2021";
    /** For test case #4 */
    private static final String DATEFEBNONLEAP = "02/29/2021";
    /** For test case #5 */
    private static final String[] DATE31 = {"01/31/2021","03/31/2021","05/31/2021","07/31/2021","08/31/2021"};
    /** For test case #6 */
    private static final String[] DATE30 = {"04/31/2021","06/31/2021","09/31/2021"};
    /** For test case #7 */
    private static final String[] DATE1TO12 = {"00/1/2021","13/1/2021"};
    /** For test case #8 */
    private static final String[] BADINPUT = {"13238/1123/2021123", "1/10101010/2021", };

    /**
     * This tests the date before 1980
     * Test Case #1
     */
    @Test
    public void isValidBeforeTest() {
        System.out.println("\nDay before 2021: This should return false");
        boolean actual =  new Date(DATEBEFORE).isValid();
        System.out.println(DATEBEFORE + ", " + actual);
        Assert.assertEquals(false,actual);
    }

    /**
     * This tests the date after today
     * Test Case #2
     */
    @Test
    public void isValidAfterTest() {
        System.out.println("\nDay After Today: This should return false");
        boolean actual =  new Date(DATEAFTER).isValid();
        System.out.println(DATEAFTER + ", " + actual);
        Assert.assertEquals(false,actual);
    }

    /**
     * This tests Feb 29 on Leap Year
     * Test Case #3
     */
    @Test
    public void isValidFeb29Test() {
        System.out.println("\nFeb 29 on non-Leap Year: This should return true");
        boolean actual =  new Date(DATEFEBNONLEAP).isValid();
        System.out.println(DATEFEBNONLEAP + ", " + actual);
        Assert.assertEquals(false,actual);
    }

    /**
     * This tests Feb 29 on non-Leap Year
     * Test Case #4
     */
    @Test
    public void isValidFeb28Test() {
        System.out.println("\nFeb 28 on non-Leap Year: This should return True");
        boolean actual =  new Date(DATEFEB28NONLEAP).isValid();
        System.out.println(DATEFEB28NONLEAP + ", " + actual);
        Assert.assertEquals(true,actual);
    }

    /**
     * This tests Months with 31 days
     * Test Case #5
     */
    @Test
    public void isValid31Test() {
        System.out.println("\nMonths with 31 days: These should all return true");
        for(String data: DATE31) {
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(true,actual);
        }
    }

    /**
     * This tests Months with 30 days
     * Test Case #6
     */
    @Test
    public void isValid30Test() {
        System.out.println("\nMonths with 30 days: These should all return false");
        for(String data: DATE30) {
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(false,actual);
        }
    }

    /**
     * This tests month values less than 1 or greater than 12
     * Test Case #7
     */
    @Test
    public void isValid1to12Test() {
        System.out.println("\nDate 1 - 12: These should all return false");
        for(String data: DATE1TO12) {
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(false,actual);
        }
    }

    /**
     * This tests all bad inputs
     * Test Case #8
     */
    @Test
    public void isValidBadInput() {
        System.out.println("\nBad Input: These should all return false");
        for(String data: BADINPUT) {
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(false,actual);
        }
    }
}