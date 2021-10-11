package project_2;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The class that represents the release date of an album in the format: "MM/DD/YYYY"
 * @author Ethan Chang, Kevin Cubillos
 */
public class Date implements Comparable<Date> {
    /** Year of the date. */
    private int year;
    /** Month of the date. */
    private int month;
    /** Day of the date. */
    private int day;
    /**
     * A leap year occurs every four years
     */
    private static final int QUADRENNIAL = 4;
    /**
     * Constant to figure out if a year is a leap year
     */
    private static final int CENTENNIAL = 100;
    /**
     * A leap year is divisible by 400
     */
    private static final int QUATERCENTENNIAL = 400;
    /**
     * Lower bound of year range
     */
    private static final int TWENTYONE = 2021;
    /**
     * Smallest calendar day value
     */
    private static final int MIN_DAYS = 1;
    /**
     * Largest calendar day value
     */
    private static final int MAX_DAYS = 31;
    /**
     * Largest calendar month value (offset by 1 due to Calendar class)
     */
    private static final int MAX_MONTH = 11;
    /**
     * Smallest calendar month value (offset by 1 due to Calendar class)
     */
    private static final int MIN_MONTH = 0;
    /** Tells a Date comes before another Date */
    private static final int BEFORE = -1;
    /** Tells a Date is the same as another Date */
    public static final int SAME_DATE = 0;
    /** Tells a Date comes after another Date */
    private static final int AFTER = 1;

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
                || year < TWENTYONE){
            return false;
        }
        if(this.compareTo(new Date()) <= SAME_DATE){
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
        if(year > date.getYear()) return BEFORE;
        if(year == date.getYear()){
            if(month > date.getMonth()) return BEFORE;
            if(month == date.getMonth()){
                if(day > date.getDay()) return BEFORE;
                if(day == date.getDay()) return SAME_DATE;
            }
        }
        return AFTER;
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

}
