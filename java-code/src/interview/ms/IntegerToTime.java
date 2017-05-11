package interview.ms;

/**
 * 给一个数字代表时间，转化成英文表示的string，例如：input 1310, output: "one ten pm"
 */
public class IntegerToTime {

    private static final String[] tensNames = {
            "",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private static final String[] numNames = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    public static String convertIntegerToTime(int number) {
        int hour = number/100;
        if (hour > 23) {
            // throw new exception
        }
        int minute = number%100;
        if (minute > 59) {
            // throw nex exception
        }

        StringBuilder sb = new StringBuilder();
        sb.append(numberToString(hour%12 == 0 ? 12 : hour%12)); // 0 or 12 both mean 12 o'clock
        if (minute > 0) {
            sb.append(" ").append(numberToString(minute));
        }
        sb.append(" ").append(hour < 12 ? "am" : "pm");

        return sb.toString();
    }

    // num range : [0, 59]
    // if num < 20, find in numNames array;
    // otherwise find num/10 in tensNames array + space + num%10 in numNames array
    // if 0, return empty string
    private static String numberToString (int num) {
        if (num < 20) {
            return numNames[num];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tensNames[num/10]);
        if (num % 10 > 0) {
            sb.append(" ").append(numNames[num%10]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertIntegerToTime(1210));
        System.out.println(convertIntegerToTime(1200));
        System.out.println(convertIntegerToTime(0000));
        System.out.println(convertIntegerToTime(2005));
        System.out.println(convertIntegerToTime(2145));
    }
}
