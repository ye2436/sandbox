/**
 * Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321
 */
public class ReverseInteger {
    public static int reverse(int x) {
        //int remain = x/10;
        //int last = x % 10;
        if(x==Integer.MIN_VALUE) //注意Integer.MIN_VALUE的绝对值是比Integer.MAX_VALUE大1的，所以经常要单独处理
            return 0;

        int reversed = 0;
        int temp;
        while (x != 0) { // instead of using >0 use !=0, so it applies to negative numbers as well
            temp = reversed * 10 + x % 10;
            x = x/10;
            if (temp/10 != reversed) { // integer overflows
                return 0;
            }
            reversed = temp;
        }

        return reversed;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-321));
    }
}
