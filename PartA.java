import java.sql.*;

public class PartA{
    public static void main(String[] args) {
        // Replace these with your Nimbus database credentials
        //String url = "jdbc:mysql://bytexldb.com:3306/testdb"; // your host/db

        String url = "jdbc:mysql://bytexldb.com:5051/db_442jha2b9"; // correct host, port, and database

        String user = "user_442jha2b9";                       // your username
        String password = "p442jha2b9";                       // your password

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Nimbus DB
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to Nimbus Database Successfully!");

            // Create statement and run query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            System.out.println("EmpID\tName\tSalary");
            System.out.println("-------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" +
                                   rs.getString("Name") + "\t" +
                                   rs.getDouble("Salary"));
            }

            // Close connections
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
