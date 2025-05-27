import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class StreamEvenFilter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 22, 33, 40, 55, 60, 71);
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        System.out.println("Original list: " + numbers);
        System.out.println("Even numbers: " + evenNumbers);
    }
}
