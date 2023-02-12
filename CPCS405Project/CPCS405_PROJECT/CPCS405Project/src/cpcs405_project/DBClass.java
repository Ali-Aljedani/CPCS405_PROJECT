package cpcs405_project;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class DBClass {

    private static int ID;
    private static int RecordID;
    private final String url;
    private final String dbusername;
    private final String dbpassword;
    private Connection con ;

    public DBClass() throws SQLException {
        this.url = "jdbc:derby://localhost:1527/GroupProject405";
        this.dbusername = "sa";
        this.dbpassword = "sa";
        this.con = DriverManager.getConnection(url, dbusername, dbpassword);
    }

    public void getUsersTable() {

        String query = "SELECT * From USERS";

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
    
    public int getUserIDByUsername(String Username) {

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

    public void getUserByID(int ID) {

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

    public boolean SignUp(String Username, String Password, String Firstname, String Lastname) {
        int randomId = (int) (Math.random() * 100000000);
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO USERS VALUES (" + randomId + ",'" + Username + "', '" + Password + "', '" + Firstname + "', '" + Lastname + "', '" + date + "')";
        try {
            Statement stet = con.createStatement();
            stet.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
            return false;
        }
    }

    public int signin(String username, String password) {
        String query = "SELECT USERID,USERNAME, PASSWORD FROM USERS"
                + " WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            while (rs.next()) {
                String dusername = rs.getString("username");
                String dpassword = rs.getString("password");
                int id = rs.getInt("UserID");
                return id;
            }
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
        return -1;
    }

    public int getID() {
        return ID;
    }

    public void getGamessTable() {

        String query = "SELECT * From GAMES";

        try {
            Statement stet = con.createStatement();
            ResultSet rs = stet.executeQuery(query);
            while (rs.next()) {
                int GID = rs.getInt("RecordID");
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
    
    public void InsertGameRecord(int FirstNumber, int SecondNumber, String Operator,
            int GameNumber,int UID) {
        
        int randomrecordID = (int) (Math.random() * 100000000);
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO GAMES VALUES (" + randomrecordID + "," + FirstNumber + ", " + SecondNumber + ", '" + Operator + "', " + GameNumber + ", " + UID + ", '"+ date + "')";

        try {
            Statement stet = con.createStatement();
            stet.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("SQL Excption " + e);
        }
    }
    
    public int countScoreOfGameByID(String id,int GameNumebr){

        String query = "Select Count(*) from GAMES WHERE USERID = "+ id +" AND GAMENUMBER = "+GameNumebr;
    
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
