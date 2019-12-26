import java.sql.*;
import java.util.Vector;

public class Database {
    Connection conn;
    Statement stmt;
    PreparedStatement prstmt;
    String querySTMT;
    ResultSet rs;
    Employe e;
    Vector<Employe> employees;

    public Database() {
        // auto close connection

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/java?serverTimezone=UTC", "root", "");
            if (conn != null) {
                System.out.println("Connected to the database!");
                stmt = conn.createStatement();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector getEmps() {
        employees = new Vector<>();
        querySTMT = new String("SELECT * FROM employee");
        try {
            rs = stmt.executeQuery(querySTMT);
            while (rs.next()) {
                e = new Employe();
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("firstName"));
                e.setLastName(rs.getString("lastName"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                employees.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public void insertEmp(Employe e) {
        querySTMT = "INSERT INTO employee (firstName, lastName, phone, email) VALUES (?,?,?,?)";
        try {
            prstmt = conn.prepareStatement(querySTMT);
            prstmt.setString(1, e.getFirstName());
            prstmt.setString(2, e.getLastName());
            prstmt.setString(3, e.getPhone());
            prstmt.setString(4, e.getEmail());
            int res = prstmt.executeUpdate();
            if (res > 0) {
                System.out.println("executed successfully : " + res);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void updateEmp(int id, Employe e) {
        querySTMT = "UPDATE employee SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE `id` = ? ";
        try {
            prstmt = conn.prepareCall(querySTMT);
            prstmt.setString(1, e.getFirstName());
            prstmt.setString(2, e.getLastName());
            prstmt.setString(3, e.getEmail());
            prstmt.setString(4, e.getPhone());
            prstmt.setInt(5, id);

            int res = prstmt.executeUpdate();
            if (res > 0) {
                System.out.println("executed successfully : " + res);
            } else {
                System.out.println("failed");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void deleteEmp(Employe e) {
        querySTMT = "DELETE FROM employee WHERE id = ? ";
        try {
            prstmt = conn.prepareStatement(querySTMT);
            prstmt.setInt(1, e.getId());
            int res = prstmt.executeUpdate();
            if (res > 0) {
                System.out.println("executed successfully : " + res);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
