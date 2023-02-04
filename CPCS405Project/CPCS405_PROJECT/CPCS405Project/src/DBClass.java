
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class DBClass {

    private static int ID;
    private static int RecordID = 0;
    private final String url;
    private final String dbusername;
    private final String dbpassword;

    public DBClass() {
        this.url = "jdbc:derby://localhost:1527/GroupProject405";
        this.dbusername = "sa";
        this.dbpassword = "sa";
    }

    public void getUsersTable(Connection con) {

        String query = "SELECT * From USERS";

        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            //Add date
            while (rs.next()) {
                DBClass.ID++;
                int UID = rs.getInt("UserID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date date = rs.getDate("date");
                System.out.println("User ID: " + UID
                        + "\nUsername: " + username
                        + "\nPassword: " + password
                        + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName
                        + "\nDate: " + date);
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }
    
    public int getUserIDByUsername(Connection con, String Username) {

        String query = "SELECT USERID From USERS WHERE USERNAME = '" + Username+"' ";
        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            while (rs.next()) {
                int UID = rs.getInt("UserID");
                return UID;
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
        return -1;
    }

    public void getUserByID(Connection con, int ID) {

        String query = "SELECT * From USERS WHERE USERID = " + ID;
        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            //Add date
            while (rs.next()) {
                int UID = rs.getInt("UserID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date date = rs.getDate("date");
                System.out.println("User ID: " + UID
                        + "\nUsername: " + username
                        + "\nPassword: " + password
                        + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName
                        + "\nDate: " + date);
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }

    public void SignUp(Connection con, String Username, String Password, String Firstname, String Lastname) {
        DBClass.ID++;
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO USERS VALUES (" + ID + ",'" + Username + "', '" + Password + "', '" + Firstname + "', '" + Lastname + "', '" + date + "')";
        try {
            Statement stet = con.createStatement();
            stet.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }

    public boolean signin(Connection con, String username, String password) {
        String query = "SELECT USERNAME, PASSWORD FROM USERS"
                + " WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            while (rs.next()) {
                String dusername = rs.getString("username");
                String dpassword = rs.getString("password");

                System.out.println(""
                        + "\nUsername: " + dusername
                        + "\nPassword: " + dpassword);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
        return false;
    }

    public int getID() {
        return ID;
    }

    public void getGamessTable(Connection con) {

        String query = "SELECT * From GAMES";

        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            while (rs.next()) {
                DBClass.RecordID++;
                int GID = rs.getInt("REcordID");
                int FirstNumber = rs.getInt("FirstNumber");
                String SecondNumber = rs.getString("SecondNumber");
                String Operator = rs.getString("Operator");
                int GameNumber = rs.getInt("GameNumber");
                int UID = rs.getInt("UserID");
                Date date = rs.getDate("date");
                System.out.println("Game ID: " + GID
                        + "\nFirst Number: " + FirstNumber
                        + "\nSecond Number: " + SecondNumber
                        + "\nOperator: " + Operator
                        + "\nGameNumber: " + GameNumber
                        + "\nUser ID: " + UID
                        + "\nDate: " + date);
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }
    
    public void InsertGameRecord(Connection con, int FirstNumber, int SecondNumber, String Operator,
            int GameNumber,int UID) {
        
        DBClass.RecordID++;
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO GAMES VALUES (" + RecordID + "," + FirstNumber + ", " + SecondNumber + ", '" + Operator + "', " + GameNumber + ", " + UID + ", '"+ date + "')";

        try {
            Statement stet = con.createStatement();
            stet.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }
    
    public int countScoreOfGameByUsername(Connection con, String username,int GameNumebr){

        String query = "Select Count(*) from GAMES WHERE USERID = "+getUserIDByUsername(con, username)+" AND GAMENUMBER = "+GameNumebr;
    
        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            rs.next();
                int count = rs.getInt(1);
                return count;
            
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
        return -1;
    }

    public int getRecordID() {
        return RecordID;
    }
    
    
    

}
