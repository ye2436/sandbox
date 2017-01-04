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

        int n = 0;

        boolean minus;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (i==0) {
                if (c == '+') {
                    continue;
                } else if (c == '-') {
                    minus = true;
                }
            }
            if (c < '0'|| c > '9') {
                return 0;
            }

        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi(""));
    }
}
