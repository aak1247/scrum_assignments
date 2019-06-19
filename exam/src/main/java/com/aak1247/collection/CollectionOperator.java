package com.aak1247.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionOperator {
    public List<Integer> getListByInterval(int left, int right) {
        var min = Integer.min(left, right);
        var max = Integer.max(right, left);
        var s = IntStream.range(min, max + 1).boxed();
        if (left < right) return s.sorted().collect(Collectors.toList());
        else return s.sorted((a, b) -> b - a).collect(Collectors.toList());
    }

    public List<Integer> getEvenListByIntervals(int left, int right) {
        var min = Integer.min(left, right);
        var max = Integer.max(right, left);
        var s1 = IntStream.range(min, max + 1).boxed().filter(n -> n % 2 == 0);
        if (left < right) return s1.sorted().collect(Collectors.toList());
        else return s1.sorted((a, b) -> b - a).collect(Collectors.toList());
    }

    public List<Integer> popEvenElments(int[] array) {
        return IntStream.of(array).boxed().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    public int popLastElment(int[] array) {
        return array[array.length - 1];
    }

    public List<Integer> popCommonElement(int[] firstArray, int[] secondArray) {
        var l1 = IntStream.of(firstArray).boxed().collect(Collectors.toList());
        var l2 = IntStream.of(secondArray).boxed().collect(Collectors.toList());
        return l1.stream().filter(n -> l2.contains(n)).collect(Collectors.toList());
    }

    public List<Integer> addUncommonElement(Integer[] firstArray, Integer[] secondArray) {
        var s1 = Arrays.stream(firstArray);
        var s2 = Arrays.stream(secondArray);
        return Stream.concat(s1, s2).collect(Collectors.toList());
    }
}
