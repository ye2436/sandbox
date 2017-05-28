package interview.li;

import java.util.HashMap;
import java.util.Map;

/**
 * 464. Can I Win
 */
public class CanIWIn {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        return canWin(desiredTotal, new boolean[maxChoosableInteger+1], new HashMap<>());
    }

    public boolean canWin(int desiredTotal, boolean[] used, Map<Integer, Boolean> map) {
        if (desiredTotal <= 0) return false;
        int key = convert(used);
        if (!map.containsKey(key)) {
            for (int i=1; i<used.length; i++) {
                if (used[i]) continue;
                used[i] = true;
                if (!canWin(desiredTotal-i, used, map)) { // if opponent can't win
                    map.put(key, true);
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
            map.put(key, false);
        }
        return map.get(key);
    }

    public int convert(boolean[] array) {
        int num = 0;
        for (boolean b : array) {
            num <<= 1;
            if (b) {
                num |= 1;
            }
        }
        return num;
    }
}
