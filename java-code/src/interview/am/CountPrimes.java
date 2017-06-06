package interview.am;

/**
 *  204. Count Primes

 Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {

    // Look ahead. First, we know 2 is prime, then any number (<n) that can be divided by 2 is {not a prime}.
    // Mark those numbers. Next time, when we encounter a prime, we mark its multiples as {not prime}
    // * How do we store? Use a boolean array of size n, representing if number i is not a prime.
    // * Why not a prime instead of is prime? because we are marking not prime.

    public static int countPrimes(int n) {
        int count = 0;
        boolean[] notPrime = new boolean[n]; // default everything to prime

        // first prime is 2
        for (int i=2; i<n; i++) {
            if (!notPrime[i]) {
                count++;
                // Time Limit Exceeded (n = 499979)
                /*for (int j=i+1; j<n; j++) {
                    if (!notPrime[j] && j%i == 0) {
                        notPrime[j] = true;
                    }
                }*/

                for (int j=2; i*j<n; j++) { // j is n/i
                    if (!notPrime[i*j]) {
                        notPrime[i*j] = true;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(5));
    }
}
