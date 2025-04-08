/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Kthryn
 */
public class  conexion {
    Connection con;
   
    
    public   conexion(){
        try{
             
 Class.forName("com.mysql.cj.jdbc.Driver");
  con=DriverManager.getConnection("jdbc:mysql://localhost/restaurantecatering","root","");      
             System.out.println("Se ha conectado a la base de datos");
             
        
        }catch (Exception e){
            
            System.err.println("No se ha conectado la base de datos");
       
        }
    }

    public Connection getConnection(){
    return con;
    }
    
}

