package servidorNotificaciones.sop_corba.NotificacionesIntPackage;

/**
* servidorNotificaciones/sop_corba/NotificacionesIntPackage/ClsAlertaDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from snotificaciones.idl
* viernes 19 de junio de 2020 05:04:47 PM COT
*/

public final class ClsAlertaDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO value = null;

  public ClsAlertaDTOHolder ()
  {
  }

  public ClsAlertaDTOHolder (servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.type ();
  }

}
