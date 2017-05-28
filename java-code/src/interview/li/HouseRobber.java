package interview.li;

/**
 * 198. House Robber
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int prevYes = 0; // accumulated nums if prev was robbed
        int prevNo = 0; // accumulated nums if prev was not robbed
        for (int n:nums) {
            int tmp = prevNo;
            // prevNo on the left side of = is the current No, which will become prev No after this loop
            prevNo = Math.max(prevYes, prevNo); // so if this house not robbed, the num depends on prev num
            prevYes = tmp + n; // if this house is to be robbed, then prev has to be no, add prevNo to current n
        }
        return Math.max(prevYes, prevNo);
    }
}
