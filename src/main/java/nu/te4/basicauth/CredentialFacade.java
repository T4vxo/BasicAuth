/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.basicauth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

/**
 *
 * @author TE4-Lärardator
 */
public class CredentialFacade {

    public static Credentials createCredentials(String basicAuth) {
       return null; 
    }
    public static void save(Credentials credentials) {
     
    }
    public static Credentials get(String username) {     
        try (Connection connection = SqlLiteConnectorFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("SELECT * FROM user WHERE username='%s'", username);
            ResultSet data = stmt.executeQuery(sql);
            data.next();
            return new Credentials(data.getString("username"),data.getString("password"));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return null; 
        }
    }
    public static boolean verify(String username, String password){
        return false;
    }
}
