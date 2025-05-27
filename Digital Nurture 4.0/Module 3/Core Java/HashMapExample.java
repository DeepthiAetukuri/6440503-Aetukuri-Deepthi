import java.util.HashMap;
import java.util.Scanner;
public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner input = new Scanner(System.in);
        int id;
        String name;
        System.out.println("Add student ID and name entries (type ID as -1 to stop):");

        while (true) {
            System.out.print("Enter student ID: ");
            id = input.nextInt();
            input.nextLine();  

            if (id == -1) {
                break;
            }
            System.out.print("Enter student name: ");
            name = input.nextLine();

            studentMap.put(id, name);
        }

        System.out.print("\nEnter student ID to retrieve name: ");
        int searchId = input.nextInt();

        if (studentMap.containsKey(searchId)) {
            System.out.println("Student Name: " + studentMap.get(searchId));
        } else {
            System.out.println("No student found with ID " + searchId);
        }

        input.close();
    }
}
