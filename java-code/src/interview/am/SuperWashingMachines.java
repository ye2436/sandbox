package interview.am;

/**
 * 517. Super Washing Machines

 You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

 For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

 Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

 Example1

 Input: [1,0,5]

 Output: 3

 Explanation:
 1st move:    1     0 <-- 5    =>    1     1     4
 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 3rd move:    2     1 <-- 3    =>    2     2     2
 Example2

 Input: [0,3,0]

 Output: 2

 Explanation:
 1st move:    0 <-- 3     0    =>    1     2     0
 2nd move:    1     2 --> 0    =>    1     1     1
 Example3

 Input: [0,2,0]

 Output: -1

 Explanation:
 It's impossible to make all the three washing machines have the same number of dresses.
 Note:
 The range of n is [1, 10000].
 The range of dresses number in a super washing machine is [0, 1e5].
 */
public class SuperWashingMachines {


    // 这道题是为洗衣机阵列做负载均衡，题目难度为Hard。
    // 如果衣服总数不能被洗衣机总数整除，表明不能均分所有衣服，返回-1；如果能整除，拿avg表示最终每个洗衣机中衣服数。
    // 如果我们能够得到任意位置k上洗衣机最少需要的操作数，则遍历整个数组即可得到最终需要的最小操作数，因为所有位置互不相关，可以同时进行操作。
    // ​对位置k上的洗衣机来说，如果左边k个洗衣机中（下标从0开始）原有衣服总数小于avg*k，表明左边k个洗衣机作为整体最终需要从右边洗衣机（包含位置k）中获取衣服，
    // 而获取衣服必定需要通过位置k的洗衣机，右边同理。
    // 这里拿lCnt表示位置k左边所有洗衣机最终向右边洗衣机（包含位置k）输送的衣服数，如果lCnt小于0，表示左边洗衣机最终需要从右边洗衣机中获取衣服，
    // 同理拿rCnt表示位置k右边所有洗衣机最终向左边洗衣机（包含位置k）中输送的衣服数。lCnt和rCnt在知道了avg之后很容易计算，
    // 这样通过判断lCnt和rCnt的正负即可得出位置k上洗衣机的最小操作数。
    // - 如果lCnt>0 && rCnt>0
    //   表明位置k需要同时从两侧获取衣服，两侧可以同时进行，所以位置k上最小操作数为max(lCnt, rCnt)；
    // - 如果lCnt<0 && rCnt<0
    //   表明位置k同时向两侧输出衣服，两侧不能同时进行，所以位置k上最小操作数为-lCnt-rCnt；
    // - 其他情况
    //   表明位置k需要从一侧获取衣服，然后向另一侧输出衣服，两侧可以同时进行，所以位置k上最小操作数为max(abs(lCnt), abs(rCnt))。
    // 遍历整个数组即可比较得出最终的最小操作次数。
    // Source: http://blog.csdn.net/TstsUgeg/article/details/62427718
    public static int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) return 0;
        int n = machines.length;
        int[] sum = new int[n+1]; // sum[i] is the sum of dresses left of index i, the last one is the total count
        for (int i=0; i < machines.length; i++){
            sum[i+1] = sum[i] + machines[i];
        }
        if (sum[n] % n != 0) {
            return -1;
        }
        int avg = sum[n]/n;
        int minMoves = -1;

        for (int i=0; i < n; i++) {
            int lCnt = sum[i] - avg * i; // lCnt = 0 when i=0
            int rCnt = sum[n] - sum[i+1] - avg * (n - 1 - i); // right side sum : sum[n] - sum[i+1]
                                                              // - balanced avg count * number of machines on the right of i

            if (lCnt > 0 && rCnt> 0) {
                minMoves = Math.max(minMoves, Math.max(lCnt, rCnt));
            } else if (lCnt < 0 && rCnt <0) {
                minMoves = Math.max(minMoves, -lCnt-rCnt);
            } else {
                minMoves = Math.max(minMoves, Math.max(Math.abs(lCnt), Math.abs(rCnt)));
            }
        }

        return minMoves;
    }

    public static void main(String[] args) {
        System.out.println(findMinMoves(new int[]{1,0,5}));
    }
}
