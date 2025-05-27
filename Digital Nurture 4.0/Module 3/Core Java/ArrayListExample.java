import java.util.ArrayList;
import java.util.Scanner;
public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String name;
        System.out.println("Enter student names (type 'exit' to stop):");
        while (true) {
            System.out.print("Enter name: ");
            name = input.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            studentNames.add(name);
        }
        System.out.println("\nList of student names entered:");
        for (String student : studentNames) {
            System.out.println(student);
        }
        input.close();
    }
}
