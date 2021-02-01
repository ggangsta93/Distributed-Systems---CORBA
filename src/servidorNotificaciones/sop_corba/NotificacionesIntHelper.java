package servidorNotificaciones.sop_corba;


/**
* servidorNotificaciones/sop_corba/NotificacionesIntHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from snotificaciones.idl
* viernes 19 de junio de 2020 05:04:47 PM COT
*/

abstract public class NotificacionesIntHelper
{
  private static String  _id = "IDL:servidorNotificaciones/sop_corba/NotificacionesInt:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorNotificaciones.sop_corba.NotificacionesInt that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorNotificaciones.sop_corba.NotificacionesInt extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (servidorNotificaciones.sop_corba.NotificacionesIntHelper.id (), "NotificacionesInt");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static servidorNotificaciones.sop_corba.NotificacionesInt read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_NotificacionesIntStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorNotificaciones.sop_corba.NotificacionesInt value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static servidorNotificaciones.sop_corba.NotificacionesInt narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorNotificaciones.sop_corba.NotificacionesInt)
      return (servidorNotificaciones.sop_corba.NotificacionesInt)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorNotificaciones.sop_corba._NotificacionesIntStub stub = new servidorNotificaciones.sop_corba._NotificacionesIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static servidorNotificaciones.sop_corba.NotificacionesInt unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorNotificaciones.sop_corba.NotificacionesInt)
      return (servidorNotificaciones.sop_corba.NotificacionesInt)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorNotificaciones.sop_corba._NotificacionesIntStub stub = new servidorNotificaciones.sop_corba._NotificacionesIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}