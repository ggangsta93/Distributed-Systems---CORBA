package servidorNotificaciones.sop_corba.NotificacionesIntPackage;


/**
* servidorNotificaciones/sop_corba/NotificacionesIntPackage/ClsAlertaDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from snotificaciones.idl
* viernes 19 de junio de 2020 05:04:47 PM COT
*/

public final class ClsAlertaDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String fecha = null;
  public String hora = null;
  public int puntuacion = (int)0;

  public ClsAlertaDTO ()
  {
  } // ctor

  public ClsAlertaDTO (String _fecha, String _hora, int _puntuacion)
  {
    fecha = _fecha;
    hora = _hora;
    puntuacion = _puntuacion;
  } // ctor

} // class ClsAlertaDTO
