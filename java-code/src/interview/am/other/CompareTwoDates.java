package interview.am.other;

/**
 *
 * http://www.geeksforgeeks.org/find-number-of-days-between-two-given-dates/
 *
 Find number of days between two given dates

 Given two dates, find total number of days between them. The count of days must be calculated in O(1) time and O(1) auxiliary space.

 Examples:

 Input: dt1 = {10, 2, 2014}
 dt2 = {10, 3, 2015}
 Output: 393
 dt1 represents "10-Feb-2014" and dt2 represents "10-Mar-2015"
 The difference is 365 + 28

 Input: dt1 = {10, 2, 2000}
 dt2 = {10, 3, 2000}
 Output: 29
 Note that 2000 is a leap year

 Input: dt1 = {10, 2, 2000}
 dt2 = {10, 2, 2000}
 Output: 0
 Both dates are same

 Input:   dt1 = {1, 2, 2000};
 dt2 = {1, 2, 2004};
 Output: 1461
 Number of days is 365*4 + 1
 */
public class CompareTwoDates {

    // One Naive Solution is to start from dt1 and keep counting days till dt2 is reached. This solution requires more than O(1) time.
    // A Better and Simple solution is to count total number of days before dt1 from i.e., total days from 00/00/0000
    // to dt1, then count total number of days before dt2. Finally return the difference between two counts.

    // Let the given two dates be "1-Feb-2000" and "1-Feb-2004"
    //  dt1 = {1, 2, 2000};
    //  dt2 = {1, 2, 2004};
    // Count number of days before dt1. Let this count be n1.
    // Every leap year adds one extra day (29 Feb) to total days.
    //  n1 = 2000*365 + 31 + 1 + Number of leap years
    // Count of leap years for a date 'd/m/y' can be calculated using following formula:
    // Number leap years
    //                  = y/4 - y/100 + y/400               if m > 2
    //                  = (y-1)/4 - (y-1)/100 + (y-1)/400   if m <= 2
    // All above divisions must be done using integer arithmetic so that the remainder is ignored.

    // For 01/01/2000, leap year count is 1999/4 - 1999/100 + 1999/400 which is 499 - 19 + 4 = 484
    // Therefore n1 is 2000*365 + 31 + 1 + 484
    // Similarly, count number of days before dt2. Let this count be n2.
    // Finally return n2-n1

    public static final int[] daysInMonth = {31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};

    public int getDifference(Date d1, Date d2) {
        return (int) (getAbsDays(d1) - getAbsDays(d2));
    }

    public long getAbsDays(Date date) {
        long n = date.year * 365 + date.day;
        for (int i=0; i<date.month-1; i++) {
            n += daysInMonth[i];
        }
        n += countLeapYears(date);
        return n;
    }

    private int countLeapYears(Date date) {
        int years = date.year;
        // exclude current year for leap year calculation if the month is <=2
        // for example, 2/29/2016, the 29 includes the leap date in date
        //     whereas, 3/1/2016, only have 28 days added from month 2 defined in the global array.
        if (date.month <= 2) {
            years--;
        }

        // An year is a leap year if it is a multiple of 4, multiple of 400 and not a multiple of 100.
        return years / 4 - years / 100 + years / 400;
    }

    class Date {
        int month;
        int day;
        int year;

        public Date(int month, int day, int year) {
            this.month = month;
            this.day = day;
            this.year = year;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.compare(1,3));
    }
}
