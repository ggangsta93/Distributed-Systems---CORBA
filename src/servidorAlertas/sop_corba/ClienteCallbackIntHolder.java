package servidorAlertas.sop_corba;

/**
* servidorAlertas/sop_corba/ClienteCallbackIntHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from salertas.idl
* lunes 22 de junio de 2020 01:26:50 PM COT
*/

public final class ClienteCallbackIntHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorAlertas.sop_corba.ClienteCallbackInt value = null;

  public ClienteCallbackIntHolder ()
  {
  }

  public ClienteCallbackIntHolder (servidorAlertas.sop_corba.ClienteCallbackInt initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorAlertas.sop_corba.ClienteCallbackIntHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorAlertas.sop_corba.ClienteCallbackIntHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorAlertas.sop_corba.ClienteCallbackIntHelper.type ();
  }

}
