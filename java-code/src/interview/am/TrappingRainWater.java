package interview.am;

/**
 * #42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
    // 2 pass solution - Dynamic Programming
    // 1) loop from left to right, find the max on the left side of each index
    // 2) loop from right to left, find the max on the right side of each index
    // 3) Min(leftMax, rightMax) - height[i] is the water trapped at i. accumulate.
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int res = 0;
        int max = 0;
        int[] container = new int[height.length];
        // max: max height on the left side of bar i
        for (int i=0; i<height.length; i++) {
            container[i] = max;
            max = Math.max(max, height[i]);
        }
        // max: max height on the right side of bar i;
        max = 0;
        for (int i=height.length-1; i>=0; i--) {
            container[i] = Math.min(max, container[i]); // min of left max and right max is the bottle neck at bar i
            max = Math.max(max, height[i]);
            if (container[i] > height[i]) {
                res += container[i] - height[i]; // minus the height of the bar
            }
        }
        return res;
    }

    // 1 pass solution - Two pointers
    // Similar idea of finding the leftMax & rightMax, but we can tackle it with 2 pointers
    // The bottleneck is the smaller of the 2, and the water is the bottleneck - height
    // After each round, we move the smaller of the 2 pointers.
    public static int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;

        int res = 0;
        int l = 0;
        int r = height.length-1;
        int leftMax = 0;
        int rightMax = 0;
        while (l < r) {
            leftMax = Math.max(leftMax, height[l]);
            rightMax = Math.max(rightMax, height[r]);
            if (leftMax < rightMax) {
                res += leftMax - height[l];
                l++;
            } else {
                res += rightMax - height[r];
                r--;
            }
        }

        return res;
    }



    public static int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int res = 0;
        int l = 0;
        int r = height.length-1;
        while (l<r) {
            int min = Math.min(height[l], height[r]); // bottle neck
            if (height[l] == min) {
                l++;
                while (l<r && height[l] < min) {
                    res += min-height[l];
                    l++;
                }
            } else {
                r--;
                while(l<r && height[r] < min) {
                    res += min-height[r];
                    r--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
        System.out.println(trap2(height));
    }
}
