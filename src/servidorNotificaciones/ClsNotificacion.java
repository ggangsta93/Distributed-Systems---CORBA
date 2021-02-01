/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorNotificaciones;

import servidorNotificaciones.sop_corba.NotificacionesIntPOA;
import servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO;
import servidorNotificaciones.vistas.servidorNotificacionesVista;

/**
 *
 * @author Personal
 */
public class ClsNotificacion extends NotificacionesIntPOA {
    
    private servidorNotificacionesVista gui=null;
    public ClsNotificacion(servidorNotificacionesVista gui){
        super();
        this.gui=gui;
    }
    
    @Override
    public void notificarRegistro(ClsNotificacionDTO objNotificacion) {
        System.out.println("Desde notificarRegistro()");
         gui.escribirEnNotificaciones("                                                                       ALERTA GENERADA                        ");
         gui.escribirEnNotificaciones("                        No de id: "+objNotificacion.asintomatico.tipo_id+" "+objNotificacion.asintomatico.id);
	 gui.escribirEnNotificaciones("                        Nombres y Apellidos: "+objNotificacion.asintomatico.nombres+" "+objNotificacion.asintomatico.apellidos);
	 gui.escribirEnNotificaciones("                        Direccion: "+objNotificacion.asintomatico.direccion);
         int pos = -1;
         for(int i=0; i<objNotificacion.alerta.length;i++){
             if(!objNotificacion.alerta[i].fecha.equalsIgnoreCase("."))
                 pos++;
         }        
	 gui.escribirEnNotificaciones("                        Fecha de alerta: "+objNotificacion.alerta[pos].fecha);
	 gui.escribirEnNotificaciones("                        Hora de alerta: "+objNotificacion.alerta[pos].hora);
	 gui.escribirEnNotificaciones("                                                                                                                 ");
	 gui.escribirEnNotificaciones("                                                             Indicadores que generaron la alerta             ");
	 gui.escribirEnNotificaciones("                         ______________________________________________________________");
	 gui.escribirEnNotificaciones("                        |                                                                              |                                            |");
	 gui.escribirEnNotificaciones("                        |         Nombre del indicador                                   |           Valor                         |");
	 gui.escribirEnNotificaciones("                        |_______________________________________|______________________|");
	 gui.escribirEnNotificaciones("                        |       Frecuencia cardiaca:                                      |           "+String.format("%.2f",objNotificacion.ultimoIndicador.frecuenciaCardiaca)+"                       |");
	 gui.escribirEnNotificaciones("                        |       Frecuencia respiratoria:                                 |           "+String.format("%.2f",objNotificacion.ultimoIndicador.frecuenciaRespiratoria)+"                        |");
	 gui.escribirEnNotificaciones("                        |       Temperatura:                                                 |           "+String.format("%.2f",objNotificacion.ultimoIndicador.temperatura)+"                        |");
	 gui.escribirEnNotificaciones("                        |_______________________________________|______________________|");
	 gui.escribirEnNotificaciones("                                                                                                                         ");
	 gui.escribirEnNotificaciones("                                             La enfermera o el medico deben revisar al paciente:        ");
	 gui.escribirEnNotificaciones("                                                         "+objNotificacion.asintomatico.nombres+" "+objNotificacion.asintomatico.apellidos+" con "+objNotificacion.asintomatico.tipo_id+" "+objNotificacion.asintomatico.id);
	 gui.escribirEnNotificaciones("                                                                                                                         ");
	 gui.escribirEnNotificaciones("                                                                              Ultimas 5 alertas                       ");
	 gui.escribirEnNotificaciones("                         _______________________________________________________________");
	 gui.escribirEnNotificaciones("                        |                                          |                                            |                                    |");
	 gui.escribirEnNotificaciones("                        |     Fecha alerta                 |    Hora de alerta                 |    Puntuacion              |");
	 gui.escribirEnNotificaciones("                        |_____________________|______________________|__________________|");
	for(int i=0 ; i< 5 ;i++){
		if(!objNotificacion.alerta[i].fecha.equalsIgnoreCase(".")    ){
                                                int longitudFecha = objNotificacion.alerta[i].fecha.length();
                                                int longitudHora = objNotificacion.alerta[i].hora.length();
                                                int diferencia = 10-longitudFecha;
                                                String espacioFecha,espacioHora; 
                                                
                                                String aux="", aux2= "  ";
                                                for(int j=0; j<diferencia-1; j++)
                                                        aux2 +="  ";                                                
                                                espacioFecha = (diferencia==0)?aux:aux2;
                                                
                                                diferencia = 8-longitudHora;
                                                aux="";
                                                aux2= "  ";
                                                for(int k=0; k<diferencia-1; k++)
                                                        aux2 +="  ";                                                
                                                espacioHora = (diferencia==0)?aux:aux2;
                                                    
			 gui.escribirEnNotificaciones("                        |     "+espacioFecha+""+objNotificacion.alerta[i].fecha+"                   |       "+espacioHora+""+objNotificacion.alerta[i].hora+"                       |          "+objNotificacion.alerta[i].puntuacion+"                        |");
		}
	}
	gui.escribirEnNotificaciones("                        |_____________________|______________________|__________________|");
        gui.escribirEnNotificaciones("****************************************************************************************************************************************");
        System.out.println("Saliendo de notificarRegistro()");

    }
    
}
