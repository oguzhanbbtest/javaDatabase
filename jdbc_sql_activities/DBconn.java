import java.sql.*;

public class DBconn {
    public static final String DB_URL = "jdbc:postgresql://localhost/dvdrental.tar";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "sakarya5454";

    public static void main(String[] args) throws SQLException {
        Connection connect = null;
        // Statement st = null;
        String  sql = "SELECT * FROM actor";
        String insertsql ="INSERT INTO actor (first_name,last_name) VALUES ('Oguzhan','Kasparov') ";
        String presql ="INSERT INTO actor (first_name,last_name) VALUES (?,?) ";
        String stSql = "UPDATE actor SET first_name='Mahmut' WHERE actor_id=201";
        String prstSql = "UPDATE actor SET first_name=? WHERE actor_id=?";
        String dltsql = "DELETE FROM actor WHERE actor_id=201";
        String prdltsql = "DELETE FROM actor WHERE actor_id=?";

        try {
            connect = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement st = connect.createStatement();
            // System.out.println(st.executeUpdate(insertsql));
            ResultSet data = st.executeQuery(sql);
             while (data.next()){
                System.out.println("ID : "+data.getInt("actor_id"));
                System.out.println("NAME : "+data.getString("first_name")); // verimi görüyorum.
                System.out.println("SURNAME : "+data.getString("last_name"));
            }

            /*PreparedStatement prst = connect.prepareStatement(presql);
            prst.setString(1,"Tarkan");                                     // yeni veri yüklüyorum
            prst.setString(2,"Tarkanov");
            prst.executeUpdate();
            prst.close(); */

            /* PreparedStatement pr = connect.prepareStatement(prstSql);
            pr.setInt(2,202);
            pr.setString(1,"Mahmut");                                       //mevcut veriyi update ediyorum
            pr.executeUpdate(); */

            PreparedStatement pr = connect.prepareStatement(prdltsql);
            pr.setInt(1,202);
            pr.executeUpdate();

            st.close();
            pr.close();
            connect.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
