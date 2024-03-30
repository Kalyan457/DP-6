import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class UglyNumber {
    // TC: O(N)
    // SC: O(N)
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0, n2 = 2, n3 = 3, n5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(n2, Math.min(n3, n5));
            uglyNums[i] = min;
            if (min == n2) {
                p2++;
                n2 = 2 * uglyNums[p2];
            }
            if (min == n3) {
                p3++;
                n3 = 3 * uglyNums[p3];
            }
            if (min == n5) {
                p5++;
                n5 = 5 * uglyNums[p5];
            }
        }
        return uglyNums[n - 1];
    }
    // TC: O(N log N)
    // SC: O(N)
    private int nthUglyNumberUsingPriorityQueue(int n) {
        if (n == 1) return 1;
        Queue<Long> minHeap = new PriorityQueue<>();
        minHeap.add(2l);
        minHeap.add(3l);
        minHeap.add(5l);
        int[] primes = {2, 3, 5};
        Set<Long> visited = new HashSet<>();
        int count = 1;
        while (true) {
            long curr = minHeap.poll();
            for (int i : primes) {
                long next = curr * i;
                if (!visited.contains(next)) {
                    minHeap.add(next);
                    visited.add(next);
                }
            }
            count++;
            if (count == n) {
                return (int)curr;
            }
        }
    }
}
