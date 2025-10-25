import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private Connection con;

    public StudentController(String url, String user, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
    }

    public boolean addStudent(Student s) {
        try {
            String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());

            int rows = ps.executeUpdate();
            con.commit();
            return rows > 0;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getDouble("Marks")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStudent(Student s) {
        try {
            String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());

            int rows = ps.executeUpdate();
            con.commit();
            return rows > 0;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int studentID) {
        try {
            String sql = "DELETE FROM Student WHERE StudentID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentID);

            int rows = ps.executeUpdate();
            con.commit();
            return rows > 0;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
