package texas;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static texas.utils.Sorts.sort;
import static texas.utils.Sorts.sortAndGetIndexes;
import static texas.utils.Values.compare;
import static texas.utils.Values.compareAndGetDetValue;

public class TexasImpl implements Texas {
    /**
     * convert between value type, char presentation and full name;
     */
    private static Map<String, Integer> valueDecodeMap = new HashMap<>();
    private static Map<Integer, String> valueEncodeMap = new HashMap<>();
    private static Map<String, String> valueTranslateMap = new HashMap<>();

    static {
        valueDecodeMap.put("T", 10);
        valueDecodeMap.put("J", 11);
        valueDecodeMap.put("Q", 12);
        valueDecodeMap.put("K", 13);
        valueDecodeMap.put("A", 14);
        for (var pair : valueDecodeMap.entrySet()) {
            valueEncodeMap.put(pair.getValue(), pair.getKey());
        }
        valueTranslateMap.put("A", "Ace");
        valueTranslateMap.put("T", "10");
        valueTranslateMap.put("J", "Joker");
        valueTranslateMap.put("Q", "Queen");
        valueTranslateMap.put("K", "King");
    }

    @Override
    public String battle(String black, String white) {
        var blackCards = split(black);
        var whiteCards = split(white);
        var kindBlack = kindOf(blackCards);
        var kindWhite = kindOf(whiteCards);
        if (kindBlack > kindWhite) {
            return formatBlack(kindBlack);
        } else if (kindBlack < kindWhite) {
            return formatWhite(kindWhite);
        } else {
            return compareSameKind(blackCards, whiteCards, kindBlack, kindWhite);
        }
    }

    private String compareSameKind(String[] blackCards, String[] whiteCards, int kindBlack, int kindWhite) {
        var valuesBlack = values(blackCards);
        var valuesWhite = values(whiteCards);
        switch (kindBlack) {
            case 1: {
                // high card
                return compareLikeHighCard(kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 2: {
                // pair
                return comparePair(blackCards, whiteCards, kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 3: {
                // two pairs
                return compareTwoPairs(blackCards, whiteCards, kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 4: {
                // three of a kind
                return compareThreeOfAKind(blackCards, whiteCards, kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 5: {
                // straight
                return compareLikeHighCard(kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 6: {
                // flush
                return compareLikeHighCard(kindBlack, kindBlack, valuesBlack, valuesWhite);
            }
            case 7: {
                // full house
                return compareFullHouse(blackCards, whiteCards, kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            case 8: {
                // four of a kind
                return compareFourOfAKind(blackCards, whiteCards, kindBlack, valuesBlack, valuesWhite);
            }
            case 9: {
                // flush straight
                return compareLikeHighCard(kindBlack, kindWhite, valuesBlack, valuesWhite);
            }
            default:
        }
        return formatTie();
    }

    private String compareFullHouse(String[] blackCards, String[] whiteCards, int kindBlack, int kindWhite, int[] valuesBlack, int[] valuesWhite) {
        var f1 = getFullHouse(blackCards);
        var f2 = getFullHouse(whiteCards);
        var valuesF1 = indexToValues(valuesBlack, f1);
        var value2F2 = indexToValues(valuesWhite, f2);
        return compareLikeHighCard(kindBlack, kindWhite, valuesF1, value2F2);
    }

    private String compareFourOfAKind(String[] blackCards, String[] whiteCards, int kindBlack, int[] valuesBlack, int[] valuesWhite) {
        var f1 = getFourOfAKind(blackCards);
        var f2 = getFourOfAKind(whiteCards);
        var valuesF1 = indexToValues(valuesBlack, f1);
        var valuesF2 = indexToValues(valuesWhite, f2);
        return compareLikeHighCard(kindBlack, kindBlack, valuesF1, valuesF2);
    }

    private String compareThreeOfAKind(String[] blackCards, String[] whiteCards, int kindBlack, int kindWhite, int[] valuesBlack, int[] valuesWhite) {
        var t1 = getThreeOfAKind(blackCards);
        var t2 = getThreeOfAKind(whiteCards);
        var v1 = valuesBlack[t1[0]];
        var v2 = valuesWhite[t2[0]];
        if (v1 == v2) {
            var restIdx1 = getRestIdxes(t1);
            var restIdx2 = getRestIdxes(t2);
            var restValues1 = indexToValues(valuesBlack, restIdx1);
            var restValues2 = indexToValues(valuesWhite, restIdx2);
            return compareLikeHighCard(kindBlack, kindWhite, restValues1, restValues2);
        } else if (v1 >= v2) {
            return formatBlack(kindBlack, v1);
        } else {
            return formatWhite(kindWhite, v2);
        }
    }

    private String compareTwoPairs(String[] blackCards, String[] whiteCards, int kindBlack, int kindWhite, int[] valuesBlack, int[] valuesWhite) {
        var ps1 = getTwoPairs(blackCards);
        var ps2 = getTwoPairs(whiteCards);
        var pairValues1 = Arrays.stream(ps1)
                .map(i -> valuesBlack[i])
                .distinct()
                .toArray();
        var pairValues2 = Arrays.stream(ps2)
                .map(i -> valuesWhite[i])
                .distinct()
                .toArray();
        var singleValue1 = IntStream.range(0, 5)
                .filter(i -> !Arrays.asList(ps1).contains(i))
                .map(i -> valuesBlack[i])
                .findFirst().getAsInt();
        var singleValue2 = IntStream.range(0, 5)
                .filter(i -> !Arrays.asList(ps2).contains(i))
                .map(i -> valuesWhite[i])
                .findFirst().getAsInt();
        var compareRes1 = compare(pairValues1, pairValues2);
        var compareDetValue1 = compareAndGetDetValue(pairValues1, pairValues2);
        if (compareRes1 == null) {
            if (singleValue1 == singleValue2) {
                return formatTie();
            } else if (singleValue1 > singleValue2) {
                return formatBlack(kindBlack, singleValue1);
            } else return formatWhite(kindWhite, singleValue2);
        } else if (compareRes1) {
            return formatBlack(kindBlack, compareDetValue1);
        } else {
            return formatWhite(kindWhite, compareDetValue1);
        }
    }

    private String comparePair(String[] blackCards, String[] whiteCards, int kindBlack, int kindWhite, int[] valuesBlack, int[] valuesWhite) {
        var p1 = getPair(blackCards);
        var p2 = getPair(whiteCards);
        assert p1 != null;
        var v1 = valuesBlack[p1[0]];
        assert p2 != null;
        var v2 = valuesWhite[p2[0]];
        if (v1 > v2) {
            return formatBlack(kindBlack, v1);
        } else if (v1 < v2) {
            return formatWhite(kindWhite, v2);
        } else {
            var highCardBlack = Arrays.stream(valuesBlack)
                    .filter(i -> i != v1)
                    .sorted()
                    .toArray();
            var highCardWhite = Arrays.stream(valuesWhite)
                    .filter(i -> i != v2)
                    .sorted()
                    .toArray();
            return compareLikeHighCard(kindBlack, kindWhite, highCardBlack, highCardWhite);
        }
    }

    private String compareLikeHighCard(int kindBlack, int kindWhite, int[] values1, int[] values2) {
        var compareRes = compare(values1, values2);
        var compareDetValue = compareAndGetDetValue(values1, values2);
        if (compareRes == null) {
            return formatTie();
        } else if (compareRes) {
            return formatBlack(kindBlack, compareDetValue);
        } else {
            return formatWhite(kindWhite, compareDetValue);
        }
    }

    private String formatTie() {
        return format("Tie", 0, 0);
    }

    private String formatBlack(int kind) {
        return formatBlack(kind, 0);
    }

    private String formatBlack(int kind, int numb) {
        return format("Black", kind, numb);
    }

    private String formatWhite(int kind) {
        return formatWhite(kind, 0);
    }

    private String formatWhite(int kind, int numb) {
        return format("White", kind, numb);
    }

    private String format(String winner, int kind, int numb) {
        if (winner.equals("Tie")) return "Tie";
        var sb = new StringBuilder();
        sb.append(winner);
        sb.append(" wins - ");
        sb.append(nameOfKind(kind));
        if (numb == 0) {
            return sb.toString();
        } else {
            sb.append(": ");
            sb.append(translateValue(encodeValue(numb)));
            return sb.toString();
        }
    }

    private int kindOf(String[] cards) {
        if (isStraightFlush(cards)) return 9;
        if (isFourOfAKind(cards)) return 8;
        if (isFullHouse(cards)) return 7;
        if (isFlush(cards)) return 6;
        if (isStraight(cards)) return 5;
        if (isThreeOfAKind(cards)) return 4;
        if (isTwoPairs(cards)) return 3;
        if (isPair(cards)) return 2;
        return 1;
    }

    private String nameOfKind(int kind) {
        switch (kind) {
            case 2:
                return "pair";
            case 3:
                return "two pairs";
            case 4:
                return "three of a kind";
            case 5:
                return "straight";
            case 6:
                return "flush";
            case 7:
                return "full house";
            case 8:
                return "four of a kind";
            case 9:
                return "straight flush";
            default:
                return "high card";
        }
    }

    private String[] split(String cards) {
        return cards.split(" ");
    }

    private String getValue(String card) {
        return card.substring(0, 1);
    }

    private String getSuit(String card) {
        return card.substring(1);
    }

    private String[] suits(String[] cards) {
        return Arrays.stream(cards).map(this::getSuit).toArray(String[]::new);
    }

    private int[] values(String[] cards) {
        return Arrays.stream(cards).map(this::getValue).mapToInt(this::decodeValue).toArray();
    }

    private int[] valuesSorted(String[] cards) {
        return Arrays.stream(cards)
                .map(this::getValue)
                .mapToInt(this::decodeValue)
                .sorted()
                .toArray();
    }

    private int[] indexToValues(int[] values, int[] indexes) {
        return Arrays.stream(indexes)
                .map(i -> values[i])
                .toArray();
    }

    private int decodeValue(String vStr) {
        try {
            return Integer.parseInt(vStr);
        } catch (NumberFormatException ignored) {
        }
        return valueDecodeMap.getOrDefault(vStr, 1);
    }

    private String encodeValue(int value) {
        if (value < 10) return Integer.toString(value);
        else {
            return valueEncodeMap.getOrDefault(value, "1");
        }
    }

    private String translateValue(String value) {
        return valueTranslateMap.getOrDefault(value, value);
    }

    /**
     * 对子
     *
     * @param cards
     * @return
     */
    boolean isPair(String[] cards) {
        return getPair(cards) != null;
    }

    private int[] getPair(String[] cards) {
        for (int i = 0; i < cards.length - 1; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                var v1 = getValue(cards[i]);
                var v2 = getValue(cards[j]);
                if (v1.equals(v2)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    boolean isTwoPairs(String[] cards) {
        return getTwoPairs(cards) != null;
    }

    private int[] getTwoPairs(String[] cards) {
        var p1 = getPair(cards);
        if (p1 == null) return null;
        var restIdxes = getRestIdxes(p1);
        var rest = indexesToCards(cards, restIdxes);
        var temp_p1 = getPair(rest);
        var p2 = Arrays.stream(temp_p1)
                .map(i -> restIdxes[i])
                .toArray();
        var res = new int[4];
        // java太难用了。。。。。。
        res[0] = p1[0];
        res[1] = p1[1];
        res[2] = p2[0];
        res[3] = p2[1];
        return res;
    }

    private String[] indexesToCards(String[] cards, int[] restIdxes) {
        return Arrays.stream(restIdxes)
                .boxed()
                .map(i -> cards[i])
                .toArray(String[]::new);
    }

    private int[] getRestIdxes(int[] idxes) {
        return IntStream.range(0, 5)
                .filter(i -> !Arrays.asList(idxes).contains(i))
                .toArray();
    }

    /**
     * 三条
     *
     * @param cards
     * @return
     */
    boolean isThreeOfAKind(String[] cards) {
        return getThreeOfAKind(cards) != null;
    }

    private int[] getThreeOfAKind(String[] cards) {
        var suits = suits(cards);
        for (int i = 0, suitsLength = suits.length, mid = suitsLength - 1, outer = suitsLength - 2; i < outer; i++) {
            var c1 = suits[i];
            for (int j = i + 1; j < mid; j++) {
                var c2 = suits[j];
                for (int k = j + 1; k < suitsLength; k++) {
                    var c3 = suits[k];
                    if (c1.equals(c2) && c1.equals(c3)) return new int[]{i, j, k};
                }
            }
        }
        return null;
    }

    /**
     * 顺子
     *
     * @param cards
     * @return
     */
    boolean isStraight(String[] cards) {
        return getStraight(cards) != null;
    }

    /**
     * @param cards
     * @return array of index, element 0 is the minimal value's index
     */
    private int[] getStraight(String[] cards) {
        var values = values(cards);
        var valuesSorted = valuesSorted(cards);
        if (isConstitent(valuesSorted)) {
            return sortAndGetIndexes(values);
        }
        return null;
    }

    private boolean isConstitent(int[] nums) {
        for (int i = 0, maxIdx = nums.length - 1; i < maxIdx; i++) {
            if (nums[i + 1] - nums[i] != 1) return false;
        }
        return true;
    }

    /**
     * 同花
     *
     * @param cards
     * @return
     */
    boolean isFlush(String[] cards) {
        return (Arrays.stream(suits(cards)).distinct().toArray().length == 1);
    }

    /**
     * 葫芦
     *
     * @param cards
     * @return
     */
    boolean isFullHouse(String[] cards) {
        return getFullHouse(cards) != null;
    }

    private int[] getFullHouse(String[] cards) {
        var values = values(cards);
        var indexes = sortAndGetIndexes(values);
        var sortedValues = sort(values);
        var distinctValues = Arrays.stream(values).distinct().toArray();
        if (distinctValues.length == 2) {
            var v1 = distinctValues[0];
            var v1Count = (int) Arrays.stream(values).filter(i -> i == v1).count();
            switch (v1Count) {
                case 2:
                    if (v1 == sortedValues[0]) return Arrays.copyOfRange(indexes, 2, 5);
                    else return Arrays.copyOfRange(indexes, 0, 3);
                case 3:
                    if (v1 == sortedValues[0]) return Arrays.copyOfRange(indexes, 0, 3);
                    else return Arrays.copyOfRange(indexes, 2, 5);
                default:
                    break;
            }
        }
        return null;
    }

    /**
     * 铁支
     *
     * @param cards
     * @return
     */
    boolean isFourOfAKind(String[] cards) {
        return getFourOfAKind(cards) != null;
    }

    private int[] getFourOfAKind(String[] cards) {
        var values = values(cards);
        var indexes = sortAndGetIndexes(values);
        var valuesSorted = sort(values);
        if (valuesSorted[0] == valuesSorted[3]) {
            return Arrays.stream(indexes, 0, 4).toArray();
        } else if (valuesSorted[1] == valuesSorted[4]) {
            return Arrays.stream(indexes, 1, 5).toArray();
        }
        return null;
    }

    /**
     * 同花顺
     *
     * @param cards
     * @return
     */
    boolean isStraightFlush(String[] cards) {
        return isFlush(cards) && isStraight(cards);
    }
}
