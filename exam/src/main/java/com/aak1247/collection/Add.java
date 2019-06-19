package com.aak1247.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Add {

    //add
    private List<Integer> getEventList(List<Integer> arrayList) {
        return arrayList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    }

    public int getSumOfEvens(int leftBorder, int rightBorder) {
        var min = Integer.min(leftBorder, rightBorder);
        var max = Integer.max(leftBorder, rightBorder);
        return IntStream.range(min, max + 1).filter(i -> i % 2 == 0).sum();
    }

    public int getSumOfOdds(int leftBorder, int rightBorder) {
        var min = Integer.min(leftBorder, rightBorder);
        var max = Integer.max(leftBorder, rightBorder);
        return IntStream.range(min, max + 1).filter(i -> i % 2 != 0).sum();
    }

    public int getSumTripleAndAddTwo(List<Integer> arrayList) {
        return arrayList.stream().map(i -> 3 * i + 2).reduce(0, (a, b) -> a + b);
    }

    public List<Integer> getTripleOfOddAndAddTwo(List<Integer> arrayList) {
        return arrayList.stream().map(i -> {
            if (i % 2 != 0) {
                return 3 * i + 2;
            }
            return i;
        }).collect(Collectors.toList());
    }

    public int getSumOfProcessedOdds(List<Integer> arrayList) {
        return arrayList.stream().map(i -> {
            if (i % 2 != 0) {
                return 3 * i + 5;
            }
            return 0;
        }).reduce(0, (a, b) -> a + b);
    }

    public double getMedianOfEven(List<Integer> arrayList) {
        var list = getEventList(arrayList);
        list.sort((a, b) -> a - b);
        if (list.size() % 2 != 0)
            return list.get(list.size() / 2);
        else
            return (float) (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
    }

    public double getAverageOfEven(List<Integer> arrayList) {
        var list = getEventList(arrayList);
        return (float) (list.stream().mapToInt(Integer::intValue).sum()) / (float) list.size();
    }

    public boolean isIncludedInEvenIndex(List<Integer> arrayList, Integer specialElment) {
        return getEventList(arrayList).contains(specialElment);
    }

    public List<Integer> getUnrepeatedFromEvenIndex(List<Integer> arrayList) {
        return getEventList(arrayList).stream().distinct().collect(Collectors.toList());
    }

    public List<Integer> sortByEvenAndOdd(List<Integer> arrayList) {
        var s1 = getEventList(arrayList).stream().sorted();
        var s2 = arrayList.stream().filter(i -> i % 2 != 0).sorted((a, b) -> b - a);
        return Stream.concat(s1, s2).collect(Collectors.toList());
    }

    public List<Integer> getProcessedList(List<Integer> arrayList) {
        var res = new ArrayList<Integer>(arrayList.size() - 1);
        arrayList.stream().reduce((a, b) -> {
            res.add(3 * (a + b));
            return b;
        });
        return res;
    }
}
