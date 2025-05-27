import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class FileWritingDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string to write to the file: ");
        String data = input.nextLine();
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(data);
            System.out.println("Data has been written to output.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}
