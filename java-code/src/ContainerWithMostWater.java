/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {
    /*
    * O(n)的复杂度。保持两个指针i,j；分别指向长度数组的首尾。如果ai 小于aj，则移动i向后（i++）。反之，移动j向前（j--）。
    * 如果当前的area大于了所记录的area，替换之。这个想法的基础是，如果i的长度小于j，无论如何移动j，短板在i，不可能找到比当前记录的area更大的值了，
    * 只能通过移动i来找到新的可能的更大面积。
    * */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length-1;
        while (i < j) {
            if (height[i]<height[j]) {
                maxArea = Math.max(height[i]*(j-i), maxArea);
                i++;
            } else {
                maxArea = Math.max(height[j]*(j-i), maxArea);
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {

        System.out.println(maxArea(new int[]{5,1,3,2,4}));
    }
}
