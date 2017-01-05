/**
 * Implement atoi to convert a string to an integer.
 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 In C:
 The atoi function skips all white-space characters at the beginning of the string, converts the subsequent characters as part of the number,
 and then stops when it encounters the first character that isn't a number.

 */
public class StringToInteger {

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();

        long n = 0;
        boolean minus = false;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (i==0) {
                if (c == '+') {
                    continue;
                } else if (c == '-') {
                    minus = true;
                    continue;
                }
            }
            if (c < '0'|| c > '9') {
                break;
            }

            n = n * 10 + (c-'0');
            if (n > Integer.MAX_VALUE) {
                break;
            }

        }
        if (minus) {
            if (-n <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int)-n;
        }
        if (n >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)n;
    }

    public static int my2(String str) {
        if(str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        int sign = 1;
        int index = 0;

        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        long num = 0;
        for (; index < str.length(); index++) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9')
                break;
            num = num * 10 + (str.charAt(index) - '0');
            if (num > Integer.MAX_VALUE ) {
                break;
            }
        }
        if (num * sign >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (num * sign <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)num * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));

        //System.out.println(my2("2147483648"));
    }
}
