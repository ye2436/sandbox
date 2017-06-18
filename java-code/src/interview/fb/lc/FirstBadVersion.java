package interview.fb.lc;

/**
 * 278. First Bad Version Add to List

 You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {

    // Binary Search
    // In this problem, we take the advantage of that the integer division is always leaning towards the smaller whole number.
    // --- in binary search, mid point is biased to the left
    // Therefore, the middle element is either the center point itself (odd count) or on the left of the center point (even count)
    // So even if we keep the bad version ones when found, our result will always land on the left most ones.
    // And the while loop will end when l=r, meaning that we are left with the last element.

    public int firstBadVersion2(int n) {
        int l = 0;
        int r = n;
        while (l < r) {
            //int m = (l+r)/2;
            int m = l + (r-l)/2; // use this version to avoid integer overflow
            if (isBadVersion(m)) {
                r = m; // include m point; while condition l < r
            } else {
                l = m+1;
            }
        }
        return l;
    }

    public int firstBadVersion(int n) {
        int l = 0;
        int r = n;
        while (l <= r) {
            //int m = (l+r)/2;
            int m = l + (r-l)/2; // use this version to avoid integer overflow
            if (isBadVersion(m)) {
                r = m-1; // not include m point; while condition l <= r
            } else {
                l = m+1;
            }
        }
        return l;
    }

    public int firstBadVersion_p(int n) {
        if (n<=0) return 0;
        int l = 1;
        int r = n;
        while (l<=r) {
            int m = (l+r)/2;
            if (isBadVersion(m)) {
                r = m-1;
            } else {
                l = m+1;
            }
        }
        return l;
    }


    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    public boolean isBadVersion(int version) {
        return false;
    }
}
