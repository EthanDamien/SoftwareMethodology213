package project_1;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private final int JANUARY = 1;
    private final int FEBRUARY = 2;
    private final int MARCH = 3;
    private final int APRIL = 4;
    private final int MAY = 5;
    private final int JUNE = 6;
    private final int JULY = 7;
    private final int AUGUST = 8;
    private final int SEPTEMBER = 9;
    private final int OCTOBER = 10;
    private final int NOVEMBER = 11;
    private final int DECEMBER = 12;
    
    public Date(String date) {} //take “mm/dd/yyyy” and create a Date object
    public Date() {} //create an object with today’s date (see Calendar class)

    public boolean isValid() {
        return false;
    }

    @Override
    public int compareTo(Date date) {
        return 1;
    }
}
