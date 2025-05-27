import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Zara");
        names.add("Alice");
        names.add("John");
        names.add("Bob");
        names.add("Monica");
        System.out.println("Original list:");
        for (String name : names) {
            System.out.println(name);
        }
        Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("\nSorted list:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
