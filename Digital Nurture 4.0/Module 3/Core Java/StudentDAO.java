import java.sql.*;

public class StudentDAO {
    private final String url = "jdbc:mysql://localhost:3306/school";
    private final String username = "root";
    private final String password = "root1234"; // change this to your actual password

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void insertStudent(student student) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());

            int rows = pstmt.executeUpdate();
            System.out.println("Inserted " + rows + " row(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(student student) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setInt(3, student.getId());

            int rows = pstmt.executeUpdate();
            System.out.println("Updated " + rows + " row(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAllStudents() {
        String sql = "SELECT * FROM students";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            System.out.println("ID\tName\t\tAge");
            System.out.println("----------------------------");
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
    
                System.out.printf("%d\t%-10s\t%d\n", id, name, age);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Insert a new student
        student newStudent = new student("David", 21);
        dao.insertStudent(newStudent);

        // Update an existing student
        student updatedStudent = new student(1, "Alice Smith", 23); // assuming ID 1 exists
        dao.updateStudent(updatedStudent);

        dao.displayAllStudents();
    }
}
