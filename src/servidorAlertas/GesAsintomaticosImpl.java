/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorAlertas;

import java.util.HashMap;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import servidorAlertas.dao.AlertasDAO;
import servidorAlertas.dao.AsintomaticoDAO;
import servidorAlertas.dao.ConexionBD;
import servidorAlertas.sop_corba.ClienteCallbackInt;
import servidorAlertas.sop_corba.GesAsintomaticosIntPOA;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsIndicadoresDTO;
import servidorAlertas.vistas.servidorAlertasVista;
import servidorNotificaciones.sop_corba.NotificacionesInt;
import servidorNotificaciones.sop_corba.NotificacionesIntHelper;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsUltimoIndicadorDTO;


/**
 *
 * @author Personal
 */
public class GesAsintomaticosImpl extends GesAsintomaticosIntPOA {
    private NotificacionesInt objRemoto_SN;
    private HashMap <String,ClienteCallbackInt> asintomaticos;
    private AlertasDAO alertasDAO=null;
    private AsintomaticoDAO asintomaticoDAO=null;
    private int cantidadRegPermitida;
    private servidorAlertasVista gui=null;
    public GesAsintomaticosImpl(servidorAlertasVista gui){
        super();
        ConexionBD conexion=new ConexionBD();
        this.asintomaticoDAO =new  AsintomaticoDAO("Asintomaticos/registrados.txt",conexion);
        this.alertasDAO =new AlertasDAO(this, asintomaticoDAO,conexion);
        this.asintomaticos= new HashMap <> ();
        this.cantidadRegPermitida=0;
        this.gui = gui;
    }
    
    @Override
    public boolean registrarAsintomatico(ClsAsintomaticoDTO objAsin, ClienteCallbackInt objCliente) {
        System.out.println("Desde registrarAsintomatico()");
        boolean bandera = false;

        if (asintomaticoDAO.obtenerCantidadRegistros() < cantidadRegPermitida) {
            if (asintomaticoDAO.existeAsintomatico(objAsin.tipo_id, objAsin.id) == null) {
                asintomaticoDAO.registrarAsintomatico(objAsin);
                asintomaticos.put(objAsin.tipo_id+""+objAsin.id, objCliente);
                bandera = true;
                System.out.println("	Registrado.");
            } else {
                System.out.println("	No registrado.");
            }

        }
        System.out.println("Saliendo de registrarAsintomatico()");
        return bandera;
    }

    @Override
    public boolean enviarIndicadores(ClsIndicadoresDTO objIndi) {
        System.out.println("Desde enviarIndicadores()");
        boolean estaRecibido = false;

        ClsAsintomaticoDTO objusuarioConsultado = asintomaticoDAO.existeAsintomatico(objIndi.tipo_id, objIndi.id);

        if (objusuarioConsultado != null) {
            System.out.println("	Indicador procesado.");
            alertasDAO.escribirAlerta(objIndi);

            int puntuacion = obtenerPuntuacion(objIndi);

            if (puntuacion >= 3 && asintomaticoDAO.existeAsintomatico(objIndi.tipo_id, objIndi.id)!=null) {
        
                ClsNotificacionDTO objNoti = new ClsNotificacionDTO(new servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAsintomaticoDTO("","","",0,""), new servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsUltimoIndicadorDTO(0,0,0), new servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO[5]);             
                
                //Asignamos el asintomatico al objeto Notificaciones
                objNoti.asintomatico.nombres = objusuarioConsultado.nombres;
                objNoti.asintomatico.apellidos = objusuarioConsultado.apellidos;
                objNoti.asintomatico.tipo_id = objusuarioConsultado.tipo_id;
                objNoti.asintomatico.id = objusuarioConsultado.id;
                objNoti.asintomatico.direccion = objusuarioConsultado.direccion;  
                
                            
                alertasDAO.leerAlertas(objNoti);//Asignamos las 5 ultimas alertas al objeto Notificaciones
                ClsUltimoIndicadorDTO objUltIndi=new ClsUltimoIndicadorDTO();
                objUltIndi.frecuenciaCardiaca=objIndi.frecuenciaCardiaca;
                objUltIndi.frecuenciaRespiratoria=objIndi.frecuenciaRespiratoria;
                objUltIndi.temperatura=objIndi.temperatura;
                objNoti.ultimoIndicador = objUltIndi;//Asignamos el ultimo indicador al objeto notificaciones
                asintomaticos.get(objIndi.tipo_id+""+objIndi.id).notificar("El paciente "+objusuarioConsultado.nombres+" "+objusuarioConsultado.apellidos+"\ncon identificacion "+objusuarioConsultado.tipo_id+""+objusuarioConsultado.id+"\nesta fuera del rango normal.");
                objRemoto_SN.notificarRegistro(objNoti);
                System.out.println("	Se enviò una notificacion.");
            }
            estaRecibido = true;

        } else {
            estaRecibido = false;
            System.out.println("	Indicador no procesado.");
        }
        System.out.println("Saliendo de enviarIndicadores()");

        return estaRecibido;
    }
    
     public void consultarReferenciaRemotaDeNotificacion(String dir_Ip, int numPuerto) {
        System.out.println("	consultarReferenciaRemotaDeNotificacion()");
         try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialHost";
            vec[1] = dir_Ip;
            vec[2] = "-ORBInitialPort";
            vec[3] = String.valueOf(numPuerto);

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);
                        
            // se obtiene la referencia al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "ObjetoRemotoNotificaciones";
            objRemoto_SN = NotificacionesIntHelper.narrow(ncRef.resolve_str(name));            

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    public int obtenerPuntuacion(ClsIndicadoresDTO objIndi) {
        System.out.println("	obtenerPuntuacion()");
        int suma = 0;
        if (objIndi.frecuenciaCardiaca < 60 || objIndi.frecuenciaCardiaca > 80) {
            suma++;
        }
        if (objIndi.frecuenciaRespiratoria < 70 || objIndi.frecuenciaRespiratoria > 90) {
            suma++;
        }
        if (objIndi.temperatura < 36.2 || objIndi.temperatura > 38.2) {
            suma++;
        }
        return suma;
    }

    @Override
    public int consultarRegPermitidos() {
        System.out.println("Desde consultarRegPermitidos()");
        System.out.println("Saliendo de consultarRegPermitidos()");
        return this.cantidadRegPermitida;
    }

    @Override
    public boolean cambiarRegPermitidos(int cantidad) {
        System.out.println("Desde cambiarRegPermitidos()");
        System.out.println("Saliendo de cambiarRegPermitidos()");
        this.cantidadRegPermitida = cantidad;
        return true;
    }

    @Override
    public boolean modificarAsintomatico(ClsAsintomaticoDTO objAsin, String old_tipo_id, int old_id) {
        System.out.println("Desde modificarAsintomatico()");
        boolean seRealizo;
        asintomaticos.put(objAsin.tipo_id+""+objAsin.id, asintomaticos.get(old_tipo_id+""+old_id));
        asintomaticos.remove(old_tipo_id+""+old_id);
        seRealizo = asintomaticoDAO.modificarAsintomatico(objAsin, old_tipo_id, old_id);
        System.out.println("Saliendo de modificarAsintomatico()");
        return seRealizo;
    }
    
}
