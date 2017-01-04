/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZagConversion {

    public static String convert(String s, int n) {
        //String[][] matrix = new String[n][s.length()/(2*n-2)]; // and some remaining

        if (n==1) return s;

        int m = s.length()/(2*n-2);
        if (s.length() % n != 0) {
            m++;
        }
        //System.out.println(m);

        StringBuilder sb = new StringBuilder();


        for (int j = 0; j<n; j++) {
            for (int i = 0; i<=m; i++) {
                if (2*(n-1)*(i)+j < s.length()) {
                    sb.append(s.charAt(2*(n-1)*(i)+j));
                }
                if (j!=0 && j!=n-1 && 2*(n-1)*(i+1)-j < s.length()) {
                    sb.append(s.charAt(2*(n-1)*(i+1)-j));
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(convert("abc",3));
    }
}
