package lc;

/**
 * #12. Integer to Roman
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999
 */
public class IntegerToRoman {

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // thousand
        int th = num / 1000;
        for (int i=0; i<th; i++) {
            sb.append('M');
        }

        // hundred
        int h = (num % 1000) / 100;
        sb.append(convert(h, "C","D","M"));
        // ten
        int t = (num % 100) / 10;
        sb.append(convert(t, "X","L","C"));
        int d = num % 10;
        sb.append(convert(d, "I","V","X"));

        return sb.toString();
    }

    private static String convert(int num, String one, String five, String ten) {
        StringBuilder sb = new StringBuilder();
        if (num == 9) {
            sb.append(one+ten);
        } else if (num>=5) {
            sb.append(five);
            for (int i=0; i<num-5; i++) {
                sb.append(one);
            }
        } else if (num==4) {
            sb.append(one+five);
        } else {
            for (int i=0; i<num; i++) {
                sb.append(one);
            }
        }
        return sb.toString();
    }

    public static String intToRoman_2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    public static void main(String[] args) {

        System.out.println(intToRoman(10));
    }
}
