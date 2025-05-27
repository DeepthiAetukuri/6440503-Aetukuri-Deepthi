import java.sql.*;

public class AccountDAO {
    private final String url = "jdbc:mysql://localhost:3306/bank";
    private final String username = "root";
    private final String password = "root1234"; // change to your actual password

    // Method to transfer money
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false); // Start transaction

            // 1. Deduct from sender
            PreparedStatement debitStmt = conn.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE id = ?"
            );
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            int debitRows = debitStmt.executeUpdate();

            // 2. Add to receiver
            PreparedStatement creditStmt = conn.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE id = ?"
            );
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);
            int creditRows = creditStmt.executeUpdate();

            // 3. Commit or rollback
            if (debitRows == 1 && creditRows == 1) {
                conn.commit();
                System.out.println("Transfer successful. Amount: $" + amount);
            } else {
                conn.rollback();
                System.out.println("Transfer failed. Transaction rolled back.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("Error during transfer: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true); // Reset autocommit
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to display all accounts
    public void displayAccounts() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {

            System.out.println("ID\tName\tBalance");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%.2f\n", rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();

        System.out.println("Before transfer:");
        dao.displayAccounts();

        // Transfer $200 from Alice (ID 1) to Bob (ID 2)
        dao.transferMoney(1, 2, 200.00);

        System.out.println("\nAfter transfer:");
        dao.displayAccounts();
    }
}
