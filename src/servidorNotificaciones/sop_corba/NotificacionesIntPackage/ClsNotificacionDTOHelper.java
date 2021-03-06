package servidorNotificaciones.sop_corba.NotificacionesIntPackage;


/**
* servidorNotificaciones/sop_corba/NotificacionesIntPackage/ClsNotificacionDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from snotificaciones.idl
* viernes 19 de junio de 2020 05:04:47 PM COT
*/

abstract public class ClsNotificacionDTOHelper
{
  private static String  _id = "IDL:servidorNotificaciones/sop_corba/NotificacionesInt/ClsNotificacionDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAsintomaticoDTOHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "asintomatico",
            _tcOf_members0,
            null);
          _tcOf_members0 = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsUltimoIndicadorDTOHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "ultimoIndicador",
            _tcOf_members0,
            null);
          _tcOf_members0 = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_array_tc (5, _tcOf_members0 );
          _members0[2] = new org.omg.CORBA.StructMember (
            "alerta",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTOHelper.id (), "ClsNotificacionDTO", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO value = new servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO ();
    value.asintomatico = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAsintomaticoDTOHelper.read (istream);
    value.ultimoIndicador = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsUltimoIndicadorDTOHelper.read (istream);
    value.alerta = new servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTO[5];
    for (int _o0 = 0;_o0 < (5); ++_o0)
    {
      value.alerta[_o0] = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.read (istream);
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO value)
  {
    servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAsintomaticoDTOHelper.write (ostream, value.asintomatico);
    servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsUltimoIndicadorDTOHelper.write (ostream, value.ultimoIndicador);
    if (value.alerta.length != (5))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    for (int _i0 = 0;_i0 < (5); ++_i0)
    {
      servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsAlertaDTOHelper.write (ostream, value.alerta[_i0]);
    }
  }

}
