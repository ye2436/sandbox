package interview.li;

import java.util.*;

/**
 * 364. Nested List Weight Sum II
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up.
 * i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class NestedListWeightSumII {

    // still use dfs as in I, but do not sum up the result. Instead, group and store numbers by their depth
    // calculate the sum in the end
    public int depthSumInverse1(List<NestedInteger> nestedList) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> maxDepth = new ArrayList<>(Arrays.asList(0));
        helper(nestedList, 0, map, maxDepth);


        int sum = 0;
        for (int depth : map.keySet()) {
            // map.size is not always the height of the structure. (if a level doesn't have any number)
            int weight = maxDepth.get(0) - depth + 1;
            for (int num: map.get(depth)) {
                sum += num * weight;
            }
        }
        return sum;
    }

    private void helper(List<NestedInteger> nestedIntegers, int depth, Map<Integer, List<Integer>> map, List<Integer> maxDepth) {
        if (nestedIntegers == null || nestedIntegers.size() == 0) {
            return;
        }

        if (depth > maxDepth.get(0)) {
            maxDepth.set(0, depth);
        }
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                if (!map.containsKey(depth)) {
                    map.put(depth, new ArrayList<>(Arrays.asList(nestedInteger.getInteger())));
                } else {
                    map.get(depth).add(nestedInteger.getInteger());
                }
            } else {
                helper(nestedInteger.getList(), depth+1, map, maxDepth);
            }
        }
    }

    // 下面这个方法就比较巧妙了，由史蒂芬大神提出来的，这个方法用了两个变量unweighted和weighted，非权重和跟权重和，初始化均为0，
    // 然后如果nestedList不为空开始循环，先声明一个空数组nextLevel，遍历nestedList中的元素，如果是数字，则非权重和加上这个数字，
    // 如果是数组，就加入nextLevel，这样遍历完成后，第一层的数字和保存在非权重和unweighted中了，其余元素都存入了nextLevel中，
    // 此时我们将unweighted加到weighted中，将nextLevel赋给nestedList，这样再进入下一层计算，由于上一层的值还在unweighted中，
    // 所以第二层计算完将unweighted加入weighted中时，相当于第一层的数字和被加了两次，这样就完美的符合要求了，这个思路又巧妙又牛B，大神就是大神啊，
    // Instead of multiplying by depth, add integers multiple times
    // (by going level by level and adding the unweighted sum to the weighted sum after each level).
    public int depthSumInverse2(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
