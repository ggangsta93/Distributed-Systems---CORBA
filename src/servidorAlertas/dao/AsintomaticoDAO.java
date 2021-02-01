/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorAlertas.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO;


/**
 *
 * @author javier
 */
public class AsintomaticoDAO {
    
    private String rutaArchivo="";
    private ConexionBD conexionABaseDeDatos=null;
    
    public AsintomaticoDAO(String rutaArchivo, ConexionBD conexionABaseDeDatos){
        this.rutaArchivo = rutaArchivo;
        this.conexionABaseDeDatos = conexionABaseDeDatos;
    }    
       
    public ClsAsintomaticoDTO existeAsintomatico(String tipo_id, int id){
        ClsAsintomaticoDTO objAsintomatico=null;
      
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select asintomatico.tipo_id, asintomatico.id, asintomatico.nombres, asintomatico.apellidos, asintomatico.direccion from asintomatico where asintomatico.tipo_id=? AND asintomatico.id=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setString(1, tipo_id);
            sentencia.setInt(2, id);
            ResultSet res = sentencia.executeQuery();
            while(res.next()){
                objAsintomatico= new ClsAsintomaticoDTO();
                objAsintomatico.tipo_id=res.getString("tipo_id");
                objAsintomatico.id=res.getInt("id");
                objAsintomatico.nombres =res.getString("nombres");
                objAsintomatico.apellidos = res.getString("apellidos");
                objAsintomatico.direccion = res.getString("direccion");
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la consulta de un asintomatico: "+e.getMessage());         
        }
        
        return objAsintomatico;
    }
    public boolean registrarAsintomatico(ClsAsintomaticoDTO objAsin){        
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "insert into asintomatico(asintomatico.tipo_id, asintomatico.id, asintomatico.nombres, asintomatico.apellidos, asintomatico.direccion) values(?,?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, objAsin.tipo_id);
            sentencia.setInt(2, objAsin.id);
            sentencia.setString(3, objAsin.nombres);
            sentencia.setString(4, objAsin.apellidos);
            sentencia.setString(5, objAsin.direccion);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        return resultado == 1;
    }
    public int obtenerCantidadRegistros(){
        int cantidad = 0;   
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select count(*) AS cant from asintomatico";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            while(res.next()){     
                cantidad=res.getInt("cant");
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la consulta de un asintomatico: "+e.getMessage());         
        }
        return cantidad;
    }
    public boolean modificarAsintomatico(ClsAsintomaticoDTO objAsin,String old_tipo_id, int old_id){        
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "update asintomatico set asintomatico.tipo_id=?, asintomatico.id=?, asintomatico.nombres=?, asintomatico.apellidos=?, asintomatico.direccion=? where asintomatico.tipo_id=? AND asintomatico.id=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, objAsin.tipo_id);
            sentencia.setInt(2, objAsin.id);
            sentencia.setString(3, objAsin.nombres);
            sentencia.setString(4, objAsin.apellidos);
            sentencia.setString(5, objAsin.direccion);
            sentencia.setString(6, old_tipo_id);
            sentencia.setInt(7, old_id);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        return resultado == 1;
    }
      
}
