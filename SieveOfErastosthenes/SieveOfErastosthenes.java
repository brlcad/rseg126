/* Christopher Sean Morrison
 * RSEG126 Assignment 4, Due 9 Nov 2021
 * Sieve Of Erastosthenes in Java
 */

package rseg126;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


class SieveOfErastosthenes {

  public static List<Integer> getIntList(int min, int max) {
    int i;
    List<Integer> list = new ArrayList<>(max - min +1);
    for (i=min; i<=max; i++) {
      list.add(i);
    }
    return list;
  }


  public static List<Integer> eratosthenize(int start, int end) {
    /**
     * NOTE: just doing a literal inelegant implementation
     *
     * Algorithm (just this comment) taken directly from Wikipedia:
     * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     *
     * To find all the prime numbers less than or equal to a given
     * integer n by Eratosthenes' method:
     *
     * 1. Create a list of consecutive integers from 2 through n: (2,
     *    3, 4, ..., n).
     *
     * 2. Initially, let p equal 2, the smallest prime number.
     *
     * 3. Enumerate the multiples of p by counting in increments of p
     *    from 2p to n, and mark them in the list (these will be 2p,
     *    3p, 4p, ...; the p itself should not be marked).
     *
     * 4. Find the smallest number in the list greater than p that is
     *    not marked. If there was no such number, stop. Otherwise,
     *    let p now equal this new number (which is the next prime),
     *    and repeat from step 3.
     *
     * 5. When the algorithm terminates, the numbers remaining not
     *    marked in the list are all the primes below n.
     */

    /* 1 */
    List<Integer> nums = getIntList(start, end);

    /* 2 */
    int pidx = 0;
    while (pidx < nums.size()) {

      /* 4 */
      if (nums.get(pidx) == 0) {
        pidx++;
        continue;
      }

      /* 3 */
      for (int i = pidx; i < nums.size(); i += nums.get(pidx)) {
        if (nums.get(i) != nums.get(pidx)) {
          nums.set(i, 0); /* "mark" it */
        }
      }

      /* 4 */
      pidx++;
    }

    /* 5 */
    List<Integer> primes = new ArrayList<Integer>();
    for (int i = 0; i < nums.size(); i++) {
      if (nums.get(i) != 0)
        primes.add(nums.get(i));
    }

    return primes;
  }


  private static int promptInt(String prompt) {
    System.out.println(prompt);

    Scanner scan = new Scanner(System.in);
    int maxnum = 0;
    try {
      maxnum = scan.nextInt();
    } catch (Exception e) {
      /* sink it */
      System.out.println("ERROR: unexpected input");
      System.exit(2);
    }
    scan.close();
    return maxnum;
  }


  public static void main(String[] args) {
    if (args.length != 0) {
      System.out.println("ERROR: Unexpected argument");
      System.exit(1);
    }

    System.out.println("The Sieve of Erastosthenes");
    System.out.println("==========================");

    int max = promptInt(" Enter maximum number: ");

    List<Integer> primes = eratosthenize(2, max);

    /* print 'em */
    System.out.println("Number of primes found: " + primes.size());
    System.out.println("Primes: ");
    int nextToLast = primes.size() - 1;
    for (int i = 0; i < primes.size(); i++) {
      if (i % 10 == 0)
        System.out.println("");

      if (i < nextToLast) {
        System.out.printf("%4d, ", primes.get(i));
      } else {
        System.out.printf("%4d\n", primes.get(i));
      }
    }
  }
}

