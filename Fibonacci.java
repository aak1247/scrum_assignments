import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Fibonacci {
    private static List<Integer> range(int min, int max) {
        return Stream.iterate(min, i -> i + 1).limit(max - min + 1).collect(Collectors.toList());
    }
    private static List<Integer> range(int max) {
        return range(0, max);
    }
    public static BigInteger of(int input){
        if (input <= 2) return BigInteger.valueOf(1);
        var res = BigInteger.valueOf(1);
        var last = BigInteger.valueOf(1);
        for (var i : range(3, input)) {
            var t = res;
            res = res.add(last);
            last = t;
        }
        return res;
    }

    public static BigInteger of_r(int input){
        if (input <= 2) return BigInteger.valueOf(1);
        else return of_r(input - 1).add(of_r(input - 2));
    }

    public static void main(String args[]) {
        for (var i : range(200)){
            System.out.println("Fibonacci.of(" + i +") == " + of(i));
        }
    }
}
