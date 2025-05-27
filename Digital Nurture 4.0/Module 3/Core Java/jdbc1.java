import java.sql.*;
public class jdbc1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school"; // Database URL
        String username = "root"; // Replace with your MySQL username
        String password = "root1234"; // Replace with your MySQL password

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL database!");

            // Execute query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");

            // Print results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}

