package servidorAlertas.sop_corba;


/**
* servidorAlertas/sop_corba/_GesAsintomaticosIntStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from salertas.idl
* lunes 22 de junio de 2020 01:26:50 PM COT
*/

public class _GesAsintomaticosIntStub extends org.omg.CORBA.portable.ObjectImpl implements servidorAlertas.sop_corba.GesAsintomaticosInt
{

  public boolean registrarAsintomatico (servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO objAsin, servidorAlertas.sop_corba.ClienteCallbackInt objCliente)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registrarAsintomatico", true);
                servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.write ($out, objAsin);
                servidorAlertas.sop_corba.ClienteCallbackIntHelper.write ($out, objCliente);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registrarAsintomatico (objAsin, objCliente        );
            } finally {
                _releaseReply ($in);
            }
  } // registrarAsintomatico

  public boolean modificarAsintomatico (servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO objAsin, String old_tipo_id, int old_id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("modificarAsintomatico", true);
                servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTOHelper.write ($out, objAsin);
                $out.write_string (old_tipo_id);
                $out.write_long (old_id);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return modificarAsintomatico (objAsin, old_tipo_id, old_id        );
            } finally {
                _releaseReply ($in);
            }
  } // modificarAsintomatico

  public boolean enviarIndicadores (servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsIndicadoresDTO objIndi)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("enviarIndicadores", true);
                servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsIndicadoresDTOHelper.write ($out, objIndi);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return enviarIndicadores (objIndi        );
            } finally {
                _releaseReply ($in);
            }
  } // enviarIndicadores

  public int consultarRegPermitidos ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("consultarRegPermitidos", true);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return consultarRegPermitidos (        );
            } finally {
                _releaseReply ($in);
            }
  } // consultarRegPermitidos

  public boolean cambiarRegPermitidos (int cantidad)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cambiarRegPermitidos", true);
                $out.write_long (cantidad);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cambiarRegPermitidos (cantidad        );
            } finally {
                _releaseReply ($in);
            }
  } // cambiarRegPermitidos

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:servidorAlertas/sop_corba/GesAsintomaticosInt:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _GesAsintomaticosIntStub
