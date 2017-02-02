/**
 * #72. Edit Distance
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {
    public static int minDistance(String word1, String word2) {
        if (word1.length()==0) return word2.length();
        if (word2.length()==0) return word1.length();
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i=0; i<=word1.length(); i++) {
            dp[i][0] = i; // 0列相当于word1转换成一个空的字符，等同于每次删除i个字符
        }
        for (int j=0; j<=word2.length(); j++) {
            dp[0][j] = j; // 0行相当于一个空的字符串转换成word2，等同于每次插入j个字符
        }

        for (int i=0; i<word1.length(); i++) {
            for (int j=0; j<word2.length(); j++) {
                System.out.println("processing "+word1.substring(0,i+1)+ ", "+word2.substring(0,j+1));
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j];
                } else {
                    System.out.println(dp[i][j+1]+", "+ dp[i+1][j]+", "+dp[i][j]);
                    dp[i+1][j+1] = Math.min(dp[i][j+1], Math.min(dp[i+1][j], dp[i][j])) + 1;
                    System.out.println(dp[i+1][j+1]);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("intention","execution"));
    }
}
