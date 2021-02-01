package servidorAlertas.sop_corba;


/**
* servidorAlertas/sop_corba/ClienteCallbackIntHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from salertas.idl
* lunes 22 de junio de 2020 01:26:50 PM COT
*/

abstract public class ClienteCallbackIntHelper
{
  private static String  _id = "IDL:servidorAlertas/sop_corba/ClienteCallbackInt:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorAlertas.sop_corba.ClienteCallbackInt that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorAlertas.sop_corba.ClienteCallbackInt extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (servidorAlertas.sop_corba.ClienteCallbackIntHelper.id (), "ClienteCallbackInt");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static servidorAlertas.sop_corba.ClienteCallbackInt read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ClienteCallbackIntStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorAlertas.sop_corba.ClienteCallbackInt value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static servidorAlertas.sop_corba.ClienteCallbackInt narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorAlertas.sop_corba.ClienteCallbackInt)
      return (servidorAlertas.sop_corba.ClienteCallbackInt)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorAlertas.sop_corba._ClienteCallbackIntStub stub = new servidorAlertas.sop_corba._ClienteCallbackIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static servidorAlertas.sop_corba.ClienteCallbackInt unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorAlertas.sop_corba.ClienteCallbackInt)
      return (servidorAlertas.sop_corba.ClienteCallbackInt)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorAlertas.sop_corba._ClienteCallbackIntStub stub = new servidorAlertas.sop_corba._ClienteCallbackIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
