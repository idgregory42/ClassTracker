/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class UserInfo
{
    private Connection con;
    public UserInfo() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/UserInfo", "idgregory42", "I@n1223631968");
    }
    
    public void addUser(int id, String name, String user, String pass) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("insert into USER_INFO values(?,?,?,?)");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, user);
        stmt.setString(4, pass);
        stmt.execute(); 
    }
    
    public List<String> getUsernames() throws SQLException{
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select USERNAME from IDGREGORY42.USER_INFO");
        List<String> names = new ArrayList<>();
        while(rs.next()){
            names.add(rs.getString("USERNAME"));
        }
        return names;
    }
    
    public List<String> getPasswords() throws SQLException{
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select PASSWORD from IDGREGORY42.USER_INFO");
        List<String> pass = new ArrayList<>();
        while(rs.next()){
            pass.add(rs.getString("PASSWORD"));
        }
        return pass;        
    }
   
    public void closeCon() throws SQLException{
        con.close();
    }
    
    
}