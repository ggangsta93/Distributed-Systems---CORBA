package servidorAlertas.sop_corba.GesAsintomaticosIntPackage;


/**
* servidorAlertas/sop_corba/GesAsintomaticosIntPackage/ClsAsintomaticoDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from salertas.idl
* lunes 22 de junio de 2020 01:26:50 PM COT
*/

abstract public class ClsAsintomaticoDTOHelper
{
  private static String  _id = "IDL:servidorAlertas/sop_corba/GesAsintomaticosInt/ClsAsintomaticoDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [5];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "nombres",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "apellidos",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "tipo_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[3] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "direccion",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.id (), "ClsAsintomaticoDTO", _members0);
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

  public static servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO value = new servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO ();
    value.nombres = istream.read_string ();
    value.apellidos = istream.read_string ();
    value.tipo_id = istream.read_string ();
    value.id = istream.read_long ();
    value.direccion = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO value)
  {
    ostream.write_string (value.nombres);
    ostream.write_string (value.apellidos);
    ostream.write_string (value.tipo_id);
    ostream.write_long (value.id);
    ostream.write_string (value.direccion);
  }

}
