package interview.am;

import java.util.Stack;

/**
 * 丢棒球砸砖块，貌似是地里没有出现过的题，输入是一个字符串数组，每一个值可能是一个整数，或者Z，或者X，或者+。
 * 整数代表现在拿的分，X代表当前成绩是前一个分数Double，+代表当前成绩是前两个的和，Z代表移除前一个成绩，然后要求的是最后的总成绩
 * 例子： 输入 ["5", "-2", "4", "Z", "X", 9, "+", "+"].
 * 输出 27
 * 5 : sum = 5
 * -2 : sum = 5 - 2 = 3.
 * 4 : sum = 3 + 4 = 7
 * Z : sum = 7 - 4 = 3
 * X : sum = 3 + -2 * 2 = -1 (因为4被移除了，前一个成绩是-2)
 * 9 : sum = -1 + 9 = 8
 * + : sum = 8 + 9 - 4 = 13 (前两个成绩是9和-4).
 * + : sum = 13 + 9 + 5 = 27 (前两个成绩是5 和 9)
 * 用一个stack就可以秒掉
 */
public class BaseBallScores {

    public static int calculateScore(String[] scores) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (String str: scores) {
            if (str.equals("Z") && !stack.isEmpty()) {
                stack.pop();
            } else if (str.equals("X") && !stack.isEmpty()) {
                // pop or peek?
                stack.push(stack.peek()*2);
            } else if (str.equals("+")) {
                // what if stack becomes empty?
                int score1 = stack.pop();
                int score2 = stack.peek();
                int newScore = score1+score2;
                stack.push(score1);
                stack.push(newScore);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculateScore(new String[]{"5", "-2", "4", "Z", "X", "9", "+", "+"}));
    }
}
