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
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THE_EIGHTYS = 1980;
    public static final int MIN_DAYS = 1;
    public static final int MAX_DAYS = 31;
    public static final int MAX_MONTH = 12;
    public static final int MIN_MONTH = 1;

    //We need to add a testbed main

    /**
     * Constructor of Date that initializes the year, month, and day by the given date.
     * @param date String of date in the format: "MM/DD/YYYY".
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    } //take “mm/dd/yyyy” and create a Date object

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
        if(month < MIN_MONTH || month > MAX_MONTH || day < MIN_DAYS || day > MAX_DAYS || year < THE_EIGHTYS){
            return false;
        }
        if(this.compareTo(new Date()) == -1){
            return false;
        }
        if(day <= MAX_DAYS - 3){
            return true;
        }
        if(day == MAX_DAYS - 2){
            if(month == Calendar.FEBRUARY){
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
        if(day == MAX_DAYS - 1 && month != Calendar.FEBRUARY){
            return true;
        }
        if(day == MAX_DAYS && (month != Calendar.APRIL && month != Calendar.NOVEMBER && month != Calendar.SEPTEMBER
                && month != Calendar.JUNE && month != Calendar.FEBRUARY)){
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
        return (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day) + "/" + year;
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
