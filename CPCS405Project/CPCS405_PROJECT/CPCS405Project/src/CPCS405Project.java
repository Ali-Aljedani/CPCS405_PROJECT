
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class CPCS405Project {


    public static void main(String[] args) throws SQLException {
    
        String url = "jdbc:derby://localhost:1527/GroupProject405";
        String dbusername = "sa";
        String dbpassword = "sa";
        
        Connection con = DriverManager.getConnection(url, dbusername, dbpassword);

        DBClass db = new DBClass();
//        db.getUsersTable(con);
//        System.out.println("");
//        db.getUserByID(con, 2);
//        System.out.println("");
//        System.out.println(db.getID());
//        System.out.println("");
//        System.out.println(db.getID());
//          db.SignUp(con, "Whathappend", "kcksx2", "Majed", "Khalid");
//        System.out.println("");
//        db.getUsersTable(con);
//        System.out.println("");
//        System.out.println(db.signin(con, "Yosef97sh", "qwerPo1"));
          //db.createGamesTable(con);
          System.out.println("");
//          db.getGamessTable(con);
//          System.out.println(db.getRecordID());
          //db.InsertGameRecord(con, 2, 8, "+", db.getUserIDByUsername(con, "Yosef97sh"), 1);
          System.out.println(db.countScoreOfGameByUsername(con, "Yosef97sh", 1));
    } 
}
