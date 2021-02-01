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
import java.util.Calendar;
import java.util.GregorianCalendar;
import servidorAlertas.GesAsintomaticosImpl;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsIndicadoresDTO;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO;


/**
 *
 * @author javier
 */
public class AlertasDAO {
      private int[] copado=new int[1];
      private int[] copado2=new int[1];
      private GesAsintomaticosImpl gesAsintomaticosImpl=null;
      private AsintomaticoDAO asintomaticoDAO = null;
      private ConexionBD conexionABaseDeDatos=null;
      
      public AlertasDAO(GesAsintomaticosImpl gesAsintomaticosImpl, AsintomaticoDAO asintomaticoDAO, ConexionBD conexionABaseDeDatos){
                this.gesAsintomaticosImpl = gesAsintomaticosImpl;
                this.asintomaticoDAO = asintomaticoDAO;
                this.conexionABaseDeDatos = conexionABaseDeDatos;
                copado[0]=0;
                copado2[0]=0;
      }
        
      public boolean escribirAlerta(ClsIndicadoresDTO objIndi){
        Calendar c = new GregorianCalendar();
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "insert into alerta(alerta.tipo_id, alerta.id, alerta.fecha, alerta.hora, alerta.frecardiaca, alerta.frerespiratoria, alerta.temperatura, alerta.puntuacion) values(?,?,?,?,?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setString(1, objIndi.tipo_id);
            sentencia.setInt(2, objIndi.id);
            sentencia.setString(3, c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)+"");
            sentencia.setString(4, c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND)+"");
            sentencia.setFloat(5,objIndi.frecuenciaCardiaca);
            sentencia.setFloat(6,objIndi.frecuenciaRespiratoria);
            sentencia.setFloat(7,objIndi.temperatura);            
            sentencia.setInt(8, gesAsintomaticosImpl.obtenerPuntuacion(objIndi));
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        return resultado == 1;
     }
      public void leerAlertas(ClsNotificacionDTO objNoti){  
        String[][] arreglo={{".",".","."},{".",".","."},{".",".","."},{".",".","."},{".",".","."}};
        int pos = 0;
        int ciclos=0;
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "SELECT fecha, hora, puntuacion from alerta where tipo_id=? AND id=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setString(1, objNoti.asintomatico.tipo_id);
            sentencia.setInt(2, objNoti.asintomatico.id);
            ResultSet res = sentencia.executeQuery();
                 
            while(res.next()){	
                ciclos++;
		boolean permanecer = true;
		int i=-1;
		while(permanecer){	
                        i++;
                        if(arreglo[i][0].equalsIgnoreCase(".")){
                            arreglo[i][0]=res.getString("fecha");
                            arreglo[i][1]=res.getString("hora");
                            arreglo[i][2]=res.getString("puntuacion");
                            permanecer = false;
                        }
                        if(i == 4){
                            permanecer =false;
                            if(copado2[0]==0){
                                copado[0]=1;
                            }else{
			        copado[0]=0;	
                            }
		        }	
		}
				
		if(copado[0]!=1 &&  !arreglo[4][0].equalsIgnoreCase(".") ){/*Toca desplazar los elementos*/
			arreglo[0][0] = arreglo[1][0];
                        arreglo[0][1] = arreglo[1][1];
			arreglo[0][2] = arreglo[1][2];

			arreglo[1][0]  = arreglo[2][0] ;
                        arreglo[1][1]  = arreglo[2][1] ;
			arreglo[1][2]  = arreglo[2][2] ;

                        arreglo[2][0]  = arreglo[3][0] ;
                        arreglo[2][1]  = arreglo[3][1] ;
			arreglo[2][2]  = arreglo[3][2] ;
                                                
                        arreglo[3][0]  = arreglo[4][0] ;
                        arreglo[3][1]  = arreglo[4][1] ;
			arreglo[3][2]  = arreglo[4][2] ;
                                                                        
                        arreglo[4][0]=res.getString("fecha");
                        arreglo[4][1]=res.getString("hora");
                        arreglo[4][2]=res.getString("puntuacion");
		}
				
		if(copado[0]==1){
		    copado2[0]=1;
                }		
            }
            
            ClsAlertaDTO[] vector=new  ClsAlertaDTO[5];
                        
            for(int i=0; i<5 ;i++){/*Pasando las 5 alertas al objeto notificacion*/
                ClsAlertaDTO objAlerta=new ClsAlertaDTO();
                objAlerta.fecha = arreglo[i][0];
                objAlerta.hora = arreglo[i][1];
                if(!arreglo[i][2].equalsIgnoreCase("."))
                    objAlerta.puntuacion =(int) Float.parseFloat(arreglo[i][2]);  
                else
                    objAlerta.puntuacion=0;  
                vector[i] = objAlerta;
            }                 
            objNoti.alerta=vector;           
            
            sentencia.close();
            conexionABaseDeDatos.desconectar();
        } catch (SQLException e) {
                  System.out.println("error en la consulta de una alerta: "+e.getMessage());         
        }
        
        copado[0]=0;
        copado2[0]=0;
    }//Cierre del metodo
         
}
