import java.util.List;
import java.util.stream.Collectors;
record Person(String name, int age) {}
public class PersonRecordDemo {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("ashiya", 30),
            new Person("rahul", 17),
            new Person("vinay", 25),
            new Person("nisha", 15),
            new Person("priya", 40)
        );
        System.out.println("All People:");
        people.forEach(System.out::println);
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());
        System.out.println("\nAdults (age >= 18):");
        adults.forEach(System.out::println);
    }
}
