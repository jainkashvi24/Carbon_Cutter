
package Db_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection
{   
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
     public static Connection getConnection() throws SQLException
     {
         Connection con=null;
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carbon_cutter", "root", "admin");
        return con;
     }
    
}
