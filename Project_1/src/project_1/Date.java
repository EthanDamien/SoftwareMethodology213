package project_1;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The class that represents the release date of an album in the format: "MM/DD/YYYY"
 * @author Ethan Chang, Kevin Cubillos
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    /**
     * A leap year occurs every four years
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Constant to figure out if a year is a leap year
     */
    public static final int CENTENNIAL = 100;
    /**
     * A leap year is divisible by 400
     */
    public static final int QUATERCENTENNIAL = 400;
    /**
     * Lower bound of year range
     */
    public static final int THE_EIGHTYS = 1980;
    /**
     * Smallest calendar day value
     */
    public static final int MIN_DAYS = 1;
    /**
     * Largest calendar day value
     */
    public static final int MAX_DAYS = 31;
    /**
     * Largest calendar month value (offset by 1 due to Calendar class)
     */
    public static final int MAX_MONTH = 11;
    /**
     * Smallest calendar month value (offset by 1 due to Calendar class)
     */
    public static final int MIN_MONTH = 0;

    /**
     * Constructor of Date that initializes the year, month, and day by the given date.
     * @param date String of date in the format: "MM/DD/YYYY".
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * Constructor of Date that initializes the year, month, and day by today's date.
     */
    public Date() {
        Calendar currentDate = Calendar.getInstance();
        month = currentDate.get(Calendar.MONTH) + 1; //Months are offset by 1
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        year = currentDate.get(Calendar.YEAR);

    }

    /**
     * Checks if date is a valid Calendar date.
     * Excludes any dates from the eighties and before.
     * @return true if date is valid, false otherwise.
     */
    public boolean isValid() {
        int monthOffset = month - 1;
        if(monthOffset < MIN_MONTH || monthOffset > MAX_MONTH || day < MIN_DAYS || day > MAX_DAYS
                || year < THE_EIGHTYS){
            return false;
        }
        if(this.compareTo(new Date()) == -1){
            return false;
        }
        if(day <= MAX_DAYS - 3){
            return true;
        }
        if(day == MAX_DAYS - 2){
            if(monthOffset == Calendar.FEBRUARY){
                if(year % QUADRENNIAL == 0){
                    if(year % CENTENNIAL == 0){
                        if(year % QUATERCENTENNIAL == 0){
                            return true;
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return true;
        }
        if(day == MAX_DAYS - 1 && monthOffset != Calendar.FEBRUARY + 1){
            return true;
        }
        if(day == MAX_DAYS && (monthOffset == Calendar.JANUARY || monthOffset == Calendar.DECEMBER || monthOffset
                == Calendar.MARCH || monthOffset == Calendar.MAY || monthOffset == Calendar.JULY
                || monthOffset == Calendar.AUGUST || monthOffset == Calendar.OCTOBER)){
            return true;
        }

        return false;
    }


    /**
     * Compares date to another date.
     * @param date the date to be compared to
     * @return 1 if older, 0 if equal, or -1 if younger
     */
    @Override
    public int compareTo(Date date) {
        if(year > date.getYear()) return -1;
        if(year == date.getYear()){
            if(month > date.getMonth()) return -1;
            if(month == date.getMonth()){
                if(day > date.getDay()) return -1;
                if(day == date.getDay()) return 0;
            }
        }
        return 1;
    }

    /**
     * Return string representation of date.
     * @return A string in the format: "MM/DD/YYYY".
     */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Return the value of day.
     * @return An int of day.
     */
    public int getDay(){
        return day;
    }

    /**
     * Return the value of month.
     * @return An int of month.
     */
    public int getMonth(){
        return month;
    }

    /**
     * Return the value of year.
     * @return An int of year.
     */
    public int getYear(){
        return year;
    }

    /**
     * Testbed main for the Date class
     */
    public static void main(String[] args) {
        boolean expectedResult = false;
        //test case #1, The method shouldn’t take any date before 1980
        Date date = new Date("07/25/1969");
        System.out.println("Test Case #1");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #2, The method shouldn’t take any date after today’s date
        date = new Date("07/15/2040");
        System.out.println("Test Case #2");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #3, The number of days in February for a leap year shall be 29
        date = new Date("02/29/2020");
        System.out.println("Test Case #3");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #4, The number of days in February for a non-leap year shall be 28
        date = new Date("02/29/2021");
        System.out.println("Test Case #4");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.1, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("01/31/2021");
        System.out.println("Test Case #5.1");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.2, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("03/31/2021");
        System.out.println("Test Case #5.2");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.3, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("05/31/2021");
        System.out.println("Test Case #5.3");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.4, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("07/31/2021");
        System.out.println("Test Case #5.4");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.5, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("08/31/2020");
        System.out.println("Test Case #5.5");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.6, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("10/31/2020");
        System.out.println("Test Case #5.6");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5.7, The number of days in January, March, May, July, August, October,
        // December must have 31 days
        date = new Date("12/31/2020");
        System.out.println("Test Case #5.7");
        expectedResult = true;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #6.1, The number of days in April, June, September, November must have 30 days
        date = new Date("04/31/2021");
        System.out.println("Test Case #6.1");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #6.2, The number of days in April, June, September, November must have 30 days
        date = new Date("06/31/2021");
        System.out.println("Test Case #6.2");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #6.3, The number of days in April, June, September, November must have 30 days
        date = new Date("09/31/2020");
        System.out.println("Test Case #6.3");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #6.4, The number of days in April, June, September, November must have 30 days
        date = new Date("11/31/2020");
        System.out.println("Test Case #6.4");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #7.1, The valid range for the month is 1-12
        date = new Date("00/1/2021");
        System.out.println("Test Case #7.1");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #7.2, The valid range for the month is 1-12
        date = new Date("13/1/2021");
        System.out.println("Test Case #7.2");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #8.1, Days should be between 1-31, January Test
        date = new Date("1/0/2021");
        System.out.println("Test Case #8.1");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #8.2, Days should be between 1-31, January Test
        date = new Date("1/32/2021");
        System.out.println("Test Case #8.2");
        expectedResult = false;
        if(expectedResult == date.isValid())
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
    }
}
