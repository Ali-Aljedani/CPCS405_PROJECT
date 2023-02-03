
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class CPCS405Project {


    public static void main(String[] args) {
    
        String url = "jdbc:derby://localhost:1527/GroupProject405";
        String dbusername = "sa";
        String dbpassword = "sa";
        getUsersTable(url, dbusername, dbpassword);
        System.out.println("");
        getUserByID(url, dbusername, dbpassword, 2);
        System.out.println("");
        SignUp(url, dbusername, dbpassword, 3, "Mine", "12221qs", "Ahmed", "Ali",new Date(System.currentTimeMillis()));
        System.out.println("");
    }
    
    public static void getUsersTable(String url, String dbusername, String dbpassword){
        
        String query = "SELECT * From USERS";

        try {
            Connection con = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            //Add date
            while (rs.next()) {
                int UID = rs.getInt("UserID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println("User ID: " + UID
                        + "\nUsername: " + username
                        + "\nPassword: " + password
                        + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName);
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption "+e);
        }
    }
    public static void getUserByID(String url, String dbusername, String dbpassword, int ID){
    
        String query = "SELECT * From USERS WHERE USERID = "+ID;
            try {
            Connection con = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            //Add date
            while (rs.next()) {
                int UID = rs.getInt("UserID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println("User ID: " + UID
                        + "\nUsername: " + username
                        + "\nPassword: " + password
                        + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName);
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption "+e);
        }
    }
    public static void SignUp(String url, String dbusername, String dbpassword, int ID,
            String Username,String Password,String Firstname,String Lastname, Date date){
    
        String query = "INSERT INTO USERS VALUES ("+Username+","+Password+","+Firstname+","+Lastname+""+date+")";
        try {
            Connection con = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement stet = con.createStatement();
            stet.executeUpdate(query);
            getUsersTable(url, dbusername, dbpassword);
            
        } catch (SQLException e) {
            System.out.println("SQL Excption "+e);
        }
    }

}
