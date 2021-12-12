/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_movie_rental;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ads
 */
class conn_db
{
    
 static public Connection getCon() throws Exception
    {
     String driver="sun.jdbc.odbc.JdbcOdbcDriver";      
     String URL= "jdbc:odbc:mrdb";
          
     Class.forName(driver);  
        
     return  DriverManager.getConnection(URL);  
    }
 
}


public class GUI_Movie_Rental {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    
}
