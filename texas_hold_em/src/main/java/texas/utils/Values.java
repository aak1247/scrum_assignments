package texas.utils;

import static texas.utils.Sorts.sort;

public class Values {
    /**
     *
     * @param values1
     * @param values2
     * @return true when values1 is bigger, null when equal
     */
    public static Boolean compare(int[] values1, int[] values2) {
        var len = values1.length;
        var values1Sorted = sort(values1);
        var values2Sorted = sort(values2);
        int idx1 = len - 1, idx2 = len - 1;
        while (true) {
            if (values1Sorted[idx1] < values2Sorted[idx2]) {
                return false;
            }else if (values1Sorted[idx1] > values2Sorted[idx2]) {
                return true;
            } else {
                --idx1;
                --idx2;
            }
            if (idx1 <=0 || idx2 <= 0) break;
        }
        return null;
    }

    public static int compareAndGetDetValue(int[] values1, int[] values2) {
        var len = values1.length;
        var values1Sorted = sort(values1);
        var values2Sorted = sort(values2);
        int idx1 = len - 1, idx2 = len - 1;
        while (true) {
            if (values1Sorted[idx1] < values2Sorted[idx2]) {
                return values2Sorted[idx2];
            }else if (values1Sorted[idx1] > values2Sorted[idx2]) {
                return values1Sorted[idx1];
            } else {
                --idx1;
                --idx2;
            }
            if (idx1 <=0 || idx2 <= 0) break;
        }
        return 0;
    }
}
