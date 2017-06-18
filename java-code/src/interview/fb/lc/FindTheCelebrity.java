package interview.fb.lc;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do
 * is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 * Note: There will be exactly one celebrity if he/she is in the party.
 * Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindTheCelebrity {
    public class Solution extends Relation {
        // 2 pass
        public int findCelebrity(int n) {
            // if a person knows someone, he/she is not the celebrity
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i))
                    candidate = i;
            }

            // verify that candidate doesn't know anyone in the party & every one knows the candidate
            for(int i = 0; i < n; i++){
                if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
            }
            return candidate;
        }

        public int findCelebrity_p(int n) {
            if (n<=0) return 0;
            int candidate = 0;
            for (int i=1; i<n; i++) {
                if (knows(candidate, i)) {
                    candidate = i;
                }
            }
            for (int i=0; i<n; i++) {
                if (candidate != i && (knows(candidate, i) || !knows(i, candidate))) return -1;
            }

            return candidate;
        }

        // 3 pass
        public int findCelebrity2(int n) {
            int x = 0;
            for (int i = 0; i < n; ++i) if (knows(x, i)) x = i;
            for (int i = 0; i < x; ++i) if (knows(x, i)) return -1;
            for (int i = 0; i < n; ++i) if (!knows(i, x)) return -1;
            return x;
        }
    }

    public class Relation {
        boolean knows (int a, int b) {
            return false;
        }
    }
}
