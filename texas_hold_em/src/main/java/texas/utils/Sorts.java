package texas.utils;

import java.util.stream.IntStream;

public class Sorts {
    public static int[] sort(int[] values) {
        return IntStream.of(values).sorted().toArray();
    }

    public static int[] sortAndGetIndexes(int[] values) {
        var idxs = IntStream.range(0, values.length).toArray();
        for (int i = 0, valuesLength = values.length; i < valuesLength - 1; i++) {
            int min = values[i];
            int minIdx = i;
            for (int j = i+1; j < valuesLength; j++) {
                var cur = values[j];
                if (cur < min) {
                    minIdx = j;
                    min = cur;
                }
            }
            var t = values[i];
            values[i] = values[minIdx];
            values[minIdx] = t;
            idxs[i] = minIdx;
        }
        return idxs;
    }
}
