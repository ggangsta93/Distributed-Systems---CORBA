package servidorAlertas;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import servidorAlertas.sop_corba.GesAsintomaticosInt;
import servidorAlertas.sop_corba.GesAsintomaticosIntHelper;
import servidorAlertas.vistas.servidorAlertasVista;
public class ServidorDeObjetos {
    
    private static servidorAlertasVista vistaAlertas=null;
    private static GesAsintomaticosImpl objRemoto =null;
    private hiloORB hilo=null;
    public static void main(String[] args) throws RemoteException {
        ServidorDeObjetos servidorDeObjetos=new ServidorDeObjetos();
        vistaAlertas=new servidorAlertasVista(servidorDeObjetos);
        vistaAlertas.setVisible(true);
        objRemoto = new GesAsintomaticosImpl(vistaAlertas);
    }
    
    public boolean encenderServicio( int numPuertoRMIRegistry, String direccionIpRMIRegistry ){
        hilo = new hiloORB(objRemoto, numPuertoRMIRegistry, direccionIpRMIRegistry);
        hilo.start(); 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hilo.esExitoso;
    }

}

class hiloORB extends Thread
{  
    private int numPuertoRMIRegistry;
    private String direccionIpRMIRegistry;
    private GesAsintomaticosImpl objRemoto;
    public boolean esExitoso=false;
    public hiloORB(GesAsintomaticosImpl objremoto, int numPuertoRMIRegistry, String direccionIpRMIRegistry){
            this.objRemoto = objremoto;
            this.numPuertoRMIRegistry = numPuertoRMIRegistry;
            this.direccionIpRMIRegistry = direccionIpRMIRegistry;
    }
    
   @Override
   public void run()
   {
         try{
                String[] vec = new String[4];
                vec[0] = "-ORBInitialHost";
                vec[1] = direccionIpRMIRegistry;
                vec[2] = "-ORBInitialPort";
                vec[3] = String.valueOf(numPuertoRMIRegistry);
        
                // crea e inicia el ORB
                ORB orb = ORB.init(vec, null);      
                POA rootpoa =  POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootpoa.the_POAManager().activate();

                //*** crea una instancia del servant
                objRemoto.consultarReferenciaRemotaDeNotificacion(vec[1], Integer.parseInt(vec[3]));

                //*** se genera la referencia del servant
                org.omg.CORBA.Object obj = rootpoa.servant_to_reference(objRemoto);
                GesAsintomaticosInt href = GesAsintomaticosIntHelper.narrow(obj);

                // se obtiene una referencia al name service
                org.omg.CORBA.Object objref =orb.resolve_initial_references("NameService");
               NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

                // *** se realiza el binding de la referencia del servant en el N_S ***
                String name = "objGesAsintomaticos";
                NameComponent path[] = ncref.to_name( name );
                ncref.rebind(path, href);

                System.out.println("El Servidor esta listo y esperando ...");
                esExitoso=true;
                // esperan por las invocaciones desde los clientes
                orb.run();
      } 
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	  
      System.out.println("Servidor: Saliendo ...");
       
   }
}
