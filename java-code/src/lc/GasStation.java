package lc;

/**
 * #134. Gas Station
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */
public class GasStation {

    // - If car starts at A and can not reach B. Any station between A and B can not reach B.(B is the first station that A can not reach.)
    // - If the total number of gas is bigger than the total number of cost. There must be a solution.
    // 维护两个量，一个是总的累积油量total，另一个是当前序列的累计油量sum，如果出现负的，则切换起点，并且将sum置0。
    // 总共是需要扫描所有站一次，时间复杂度是O(n)。而只需要两个额外变量，空间复杂度是O(1)。
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
            return -1;

        int total = 0; // total gas - total cost
        int sum = 0; // so far (gas - cost) from start point
        int start = 0;
        for (int i=0; i<gas.length; i++) {
            int diff = gas[i] - cost[i];
            sum += diff;
            total += diff;
            if (sum < 0) {
                sum = 0;
                start = i+1;
            }
        }
        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        int[] gas = {};
        int[] cost = {};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
