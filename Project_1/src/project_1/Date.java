package project_1;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The class that represents the release date of an album in the format: "MM/DD/YYYY"
 * @author Ethan Damien, Kevin Cubillos
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

    }
}
