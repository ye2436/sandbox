package interview.li.other;

import java.util.Stack;

/**
 * 给multidimensional array，给一个function， 输入这个array以及各个dimension上的index，可以output这个位置上的数字。
 写一个function，input是multidimensional array，以及array的dimensions，只能调用上面给的那个function，输出这个array里面所有的数字的和。
 题不难，是我当时脑子懵了，一直在想怎么找这个array的各个dimension上的boundary，其实input就给了。和面试官一直在交流，但我没说好，十几分钟一直在纠结这个问题。
 后来面试官举了个例子，立刻反应过来了。但也没有什么时间，就草草的说了下pseudo code，用dfs做所有dimension上的不同index的combination，然后调用那个function求和。
 */
public class MultiDimensionalArray {
    public static int sum(MultidimensionalArray mArray, int[] dim) {
        return loopSum(dim, 0, mArray, new Stack<Integer> ());
    }

    private static int loopSum(int[] dim, int dimIndex, MultidimensionalArray mArray, Stack<Integer> stackIndices) {
        if (dimIndex >= dim.length) {
            int[] indices = new int[stackIndices.size()];
            int i = 0;
            for (int index : stackIndices)
                indices[i++] = index;
            return mArray.get(indices); // get data from mArray
        }
        int sum = 0;
        for (int index = 0; index < dim[dimIndex]; ++index)
            stackIndices.push(index);
        sum += loopSum(dim, dimIndex + 1, mArray, stackIndices);
        stackIndices.pop();
        return sum;
    }

    public interface MultidimensionalArray {
        int get(int[] indices);
    }
}
