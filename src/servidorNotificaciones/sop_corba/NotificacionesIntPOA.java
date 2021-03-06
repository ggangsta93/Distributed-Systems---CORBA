package servidorNotificaciones.sop_corba;


/**
* servidorNotificaciones/sop_corba/NotificacionesIntPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from snotificaciones.idl
* viernes 19 de junio de 2020 05:04:47 PM COT
*/

public abstract class NotificacionesIntPOA extends org.omg.PortableServer.Servant
 implements servidorNotificaciones.sop_corba.NotificacionesIntOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("notificarRegistro", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // servidorNotificaciones/sop_corba/NotificacionesInt/notificarRegistro
       {
         servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTO objNotificacion = servidorNotificaciones.sop_corba.NotificacionesIntPackage.ClsNotificacionDTOHelper.read (in);
         this.notificarRegistro (objNotificacion);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:servidorNotificaciones/sop_corba/NotificacionesInt:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public NotificacionesInt _this() 
  {
    return NotificacionesIntHelper.narrow(
    super._this_object());
  }

  public NotificacionesInt _this(org.omg.CORBA.ORB orb) 
  {
    return NotificacionesIntHelper.narrow(
    super._this_object(orb));
  }


} // class NotificacionesIntPOA
