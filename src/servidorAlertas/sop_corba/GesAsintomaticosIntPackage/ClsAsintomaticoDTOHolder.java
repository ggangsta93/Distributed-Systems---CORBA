package servidorAlertas.sop_corba.GesAsintomaticosIntPackage;

/**
* servidorAlertas/sop_corba/GesAsintomaticosIntPackage/ClsAsintomaticoDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from salertas.idl
* lunes 22 de junio de 2020 01:26:50 PM COT
*/

public final class ClsAsintomaticoDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO value = null;

  public ClsAsintomaticoDTOHolder ()
  {
  }

  public ClsAsintomaticoDTOHolder (servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.type ();
  }

}