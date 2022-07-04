import java.sql.*;

public class DBconn {
    public static final String DB_URL = "jdbc:postgresql://localhost/dvdrental.tar";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "sakarya5454";

    public static void main(String[] args) throws SQLException {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            connect.setAutoCommit(false);

            PreparedStatement pr = connect.prepareStatement("INSERT INTO actor (first_name,last_name) VALUES (?,?)");
            pr.setString(1,"Dogukan");
            pr.setString(2,"Kam");
            pr.executeUpdate();

            if(true){
                throw new SQLException();
            }
            connect.commit();
            pr.close();
            connect.close();

        }catch (SQLException e){
            if(connect != null){
                connect.rollback();
            }
            System.out.println(e.getMessage());
        }

    }
}
