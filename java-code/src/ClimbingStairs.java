/**
 * #70. Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
    public static int climbStairs(int n) {
        int f1 = 1;
        int f2 = 2;
        if (n==1) return f1;
        if (n==2) return f2;
        for (int i=3; i<=n; i++) {
            int f3 = f1+f2;
            f1 = f2;
            f2 = f3;
        }

        return f2;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(16));
    }
}
