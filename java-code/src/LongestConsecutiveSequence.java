import java.util.*;

/**
 * #128. Longest Consecutive Sequence
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    // 先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，并且把用过的数字从集合中移除（因为连续的关系，一个数字不会出现在两个串中）。
    // 最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。如果我们用HashSet来存储数字，则可以认为访问时间是常量的，
    // 那么算法需要一次扫描来建立集合，第二次扫描来找出最长串，所以复杂度是O(2*n)=O(n)，空间复杂度是集合的大小，即O(n)。
    public static int longestConsecutive(int[] nums) {
        int maxLength = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (!set.isEmpty()) {
            Iterator iterator = set.iterator();
            // 从集合中取出一个数字作为代表，成为一个新的集合，
            int num = (Integer) iterator.next();
            set.remove(num);
            int length = 1; // size of the sub set, represented by current number
            // find neighbors of current number, remove from set if found, and update length
            int neighbor = num-1;
            while (set.contains(neighbor)) {
                set.remove(neighbor--);
                length++;
            }
            neighbor = num+1;
            while (set.contains(neighbor)) {
                set.remove(neighbor++);
                length++;
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }


    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
