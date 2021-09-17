package project_1;

import java.util.Calendar;

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
    
    public Date(String date) {
        month = Integer.parseInt(date.substring(0,2));
        day = Integer.parseInt(date.substring(3,5));
        year = Integer.parseInt(date.substring(6,10));
    } //take “mm/dd/yyyy” and create a Date object

    public Date() {
        Calendar currentDate = Calendar.getInstance();
        month = currentDate.get(Calendar.MONTH);
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        year = currentDate.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    public boolean isValid() {
        if(month < MIN_MONTH || month > MAX_MONTH || day < MIN_DAYS || day > MAX_DAYS || year < THE_EIGHTYS
                || year > Calendar.getInstance().get(Calendar.YEAR)){
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

    // 1 -> older
    // -1 -> younger
    // 0 -> equal
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

    @Override
    public String toString(){
        return (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day) + "/" + year;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
}
