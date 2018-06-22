package search;

public class BinarySearchMissing {
    private static int[] array;
    private static int x;
    // l = left, r = right, n = array.length, m = mid


    // PRE: a[i] >= a[i + 1] for i = 0..n-2,
    // POST: R = r, a[R] = x || ((a[i] != x for i = 0..n-1) && R = - insertion point - 1,
    // insertion point : (x > a[0] && insertion point = 0 || x < a[n-1] && insertion point = n ||
    // || a[insertion point + 1] < x < a[insertion point - 1]),
    // a[i] = a'[i] for i = 0 .. n-1
    private static int iterativeBinarySearch() {
        int left = -1;
        int right = array.length;
        int mid;
        // INV: -1 <= l < r <= n && r - l > r' - l' && (l <= l' < r' <= r),
        // a[i]' = a[i] for i = 0.. n-1 && x' = x
        while (right - left > 1) {
            mid = (left + right) / 2;
            // m' = (r + l) / 2 && (l <= m' < r)
            if (array[mid] <= x) {
                right = mid;
                // l' = l, r' = m
                // m < r -> r' < r
                // l' = l && r' < r -> r - l > r' - l' && l <= l' < r' <= r && x' = x
            } else {
                left = mid;
                // l' = m, r' = r
                // m > l -> l' > l
                // l' > l && r' = r -> r - l > r' - l' && l <= l' < r' <= r && x' = x
            }
        }

        if (right == array.length || array[right] != x) {
            return -right - 1;
        }
        // ((a[i] != x for i = 0..n-1) && R = - insertion point - 1
        return right;
        // a[r] = x, R = r
    }

    // INV -1 <= l < r <= n,
    // a[i]' = a[i] for i = 0 .. n-1
    // PRE: a[i] >= a[i + 1], for i = 0.. n - 2,
    // l = -1 && r = n
    // POST: R = r, a[R] = x || ((a[i] != x for i = 0..n-1) && R = - insertion point - 1,
    // insertion point: (x > a[0] && insertion point = 0 || x < a[n-1] && insertion point = n ||
    // || (a[insertion point + 1] < x < a[insertion point - 1]),
    // && a[i]' = a[i], for i = 0 .. n - 1
    private static int recursiveBinarySearch(int left, int right) {
        if (right - left == 1) {
            if (right == array.length || array[right] != x) {
                return -right - 1;
            }
            // ((a[i] != x for i = 0..n-1) && R = - insertion point - 1
            return right;
            // a[r] = x, R = r
        } else {
            int mid = (left + right) / 2;
            //m = (r + l) / 2 && r - l > 1 && (l <= m < r)
            if (array[mid] <= x) {
                // a[m] <= x, r' = m, l' = l ->
                // -> a[r'] <= x,
                // m < r -> r' < r && l <= l' < r' <= r
                // a[i]' = a[i], for i = 0 .. n && x' = x
                return recursiveBinarySearch(left, mid);
            } else {
                // a[m] > x, l' = m, r' = r ->
                // -> a[l'] > x,
                // m > l -> l' > l && l <= l' < r' <= r
                // a[i]' = a[i], for i = 0 .. n && x' = x
                return recursiveBinarySearch(mid, right);
            }
        }
    }

    public static void main(String[] args) {
        x = Integer.parseInt(args[0]);
        array = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
        int iterativeAnswer = iterativeBinarySearch();
        int recursiveAnswer = recursiveBinarySearch(-1, array.length);
        if (iterativeAnswer != recursiveAnswer) {
            System.out.println("Error: iterativeAnswer: " + iterativeAnswer + " recursiveAnswer: " + recursiveAnswer);
            return;
        }
        System.out.println(iterativeAnswer);
    }
}
