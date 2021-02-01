package cliente;

import static cliente.ClienteDeObjetos.lecturaSensores;
import static cliente.ClienteDeObjetos.obtenerPuntuacion;
import cliente.vistas.ClienteInicio;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import servidorAlertas.sop_corba.ClienteCallbackInt;
import servidorAlertas.sop_corba.ClienteCallbackIntHelper;
import servidorAlertas.sop_corba.GesAsintomaticosInt;
import servidorAlertas.sop_corba.GesAsintomaticosIntHelper;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsAsintomaticoDTO;
import servidorAlertas.sop_corba.GesAsintomaticosIntPackage.ClsIndicadoresDTO;


public class ClienteDeObjetos { 
    private POA rootpoa=null;
    private static GesAsintomaticosInt ref=null;
    private static ClsAsintomaticoDTO objAsintomatico = new ClsAsintomaticoDTO();
    private static ClsIndicadoresDTO objIndicadores = new ClsIndicadoresDTO();
    private static boolean asintomaticoRegistrado = false;
    private static ClienteInicio guiCliente = null;
    private hiloEnviarNotificaciones hilo = null;

    public static void main(String[] args) {
        ClienteDeObjetos clienteDeObjetos=new ClienteDeObjetos();
        guiCliente=new ClienteInicio(clienteDeObjetos);
        guiCliente.setVisible(true);
    }
    
    
    public boolean obtenerObjetoRemoto(int numPuertoRMIRegistry, String direccionIpRMIRegistry){
        boolean seObtuvo = true;
         try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialHost";
            vec[1] = direccionIpRMIRegistry;
            vec[2] = "-ORBInitialPort";
            vec[3] = String.valueOf(numPuertoRMIRegistry);

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);
            
            //obtiene la referencia del rootpoa y activa el POAManager
            rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            // se obtiene la referencia al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objGesAsintomaticos";
            ref = GesAsintomaticosIntHelper.narrow(ncRef.resolve_str(name));            

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
            seObtuvo=false;
        }
        return seObtuvo;
    }

    public boolean registrarAsintomatico(String nombres, String apellidos, String tipo_id, int id, String direccion){
        objAsintomatico.nombres = nombres;
        objAsintomatico.apellidos = apellidos;
        objAsintomatico.tipo_id = tipo_id;
        objAsintomatico.id = id;
        objAsintomatico.direccion = direccion;
        
        ClienteCallbackImpl usuario=new ClienteCallbackImpl(guiCliente);   
        org.omg.CORBA.Object ref2;
        try {
            ref2 = rootpoa.servant_to_reference(usuario);
            ClienteCallbackInt objCallbak = ClienteCallbackIntHelper.narrow(ref2);
            
            if (ref.registrarAsintomatico(objAsintomatico, objCallbak)) {
                System.out.println("Se registrò con èxito. ");
                objIndicadores.tipo_id=objAsintomatico.tipo_id;
                objIndicadores.id=objAsintomatico.id;
                asintomaticoRegistrado = true;
            } else {
                System.out.println("No se pudo registrar. ");
            }
        } catch (ServantNotActive ex) {
            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asintomaticoRegistrado;
    }
    
    public boolean modificarAsintomatico(String nombres, String apellidos, String tipo_id, int id, String direccion){
        boolean seModifico=false;
        ClsAsintomaticoDTO objAsin = new ClsAsintomaticoDTO();
        objAsin.nombres = nombres;
        objAsin.apellidos = apellidos;
        objAsin.tipo_id = tipo_id;
        objAsin.id = id;
        objAsin.direccion = direccion;
        seModifico = ref.modificarAsintomatico(objAsin, objAsintomatico.tipo_id, objAsintomatico.id);
        if(seModifico){
            objAsintomatico = objAsin;
            objIndicadores.tipo_id=objAsintomatico.tipo_id;
            objIndicadores.id=objAsintomatico.id;
        }
        return seModifico;
    }

  
    public int consultarRegPermitidos(){
        int valor=0;
        valor = ref.consultarRegPermitidos();
        return valor;
      }

    public boolean cambiarRegPermitidos(int cantidad){
        boolean cambiado =false;
        cambiado = ref.cambiarRegPermitidos(cantidad);
        return cambiado;
    }
    
    public void desconectar(){
        if(hilo != null){
            hilo.stop();
        }
        ref = null;
    }

    public void enviarIndicadores(boolean bandera){
        
        if (asintomaticoRegistrado && hilo!=null){
            if(bandera)
                hilo.reanudarhilo();
            else
                hilo.suspenderhilo();            
        }  
        
        if (asintomaticoRegistrado && hilo==null){
                hilo = new hiloEnviarNotificaciones(ref, objIndicadores, guiCliente);
                hilo.start();            
        }           
    }

    private static float IndicadoresRandom() {
        return (float) (Math.random() * 100);
    }

    public static void lecturaSensores() {
        float fecuenciaCardiaca = IndicadoresRandom();
        float frecuenciaRespiratoria = IndicadoresRandom();
        float temperatura = IndicadoresRandom();

        objIndicadores.frecuenciaCardiaca=fecuenciaCardiaca;
        objIndicadores.frecuenciaRespiratoria=frecuenciaRespiratoria;
        objIndicadores.temperatura=temperatura;
        
        guiCliente.escribirEnFreCardiaca(String.format("%.2f",fecuenciaCardiaca));
        guiCliente.escribirEnFreRespiratoria(String.format("%.2f",frecuenciaRespiratoria));
        guiCliente.escribirEnTemperatura(String.format("%.2f",temperatura));
    }

    public static int obtenerPuntuacion(){
        int suma = 0;
        if (objIndicadores.frecuenciaCardiaca < 60 || objIndicadores.frecuenciaCardiaca > 80){
            suma++;
        }
        if (objIndicadores.frecuenciaRespiratoria < 70 || objIndicadores.frecuenciaRespiratoria > 90){
            suma++;
        }
        if (objIndicadores.temperatura < 36.2 || objIndicadores.temperatura > 38.2){
            suma++;
        }
        return suma;
    }
    
}


class hiloEnviarNotificaciones extends Thread
{  
    private  GesAsintomaticosInt objRemoto=null;
    private  ClsIndicadoresDTO objIndicadores =null;
    private  ClienteInicio guiCliente=null;
    
    private boolean suspender=false;//Suspende un hilo cuando es true
    private boolean pausar=false;//Detiene un hilo cuando es true
    
    public hiloEnviarNotificaciones(GesAsintomaticosInt objRemoto,ClsIndicadoresDTO objIndicadores,ClienteInicio guiCliente){
        this.objRemoto=objRemoto;
        this.objIndicadores=objIndicadores;
        this.guiCliente=guiCliente;
    }
    
   @Override
   public void run()
   {
        while (true) {
                
                
                try {
                       
                    
                        for(int i=0; i<8 ; i++){
                             guiCliente.sincronizarBarra(i);
                             Thread.sleep(1000);  
                            synchronized (this){
                                while(suspender){
                                    wait();
                                }
                                if(pausar)break;
                            } 
                        }
                        
                        
                        
                        lecturaSensores();
                        int puntuacion = obtenerPuntuacion();

                        if (puntuacion >= 2) {
                             guiCliente.escribirEnConsola("Enviando indicadores...\nFrecuencia cardiaca: " + objIndicadores.frecuenciaCardiaca+"\nFrecuencia respiratoria: " + objIndicadores.frecuenciaRespiratoria+"\nTemperatura: " + objIndicadores.temperatura);
                             objRemoto.enviarIndicadores(objIndicadores);
                        }
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }     
   }
   
   synchronized void pausarhilo(){
       pausar = true;
       suspender =false;
       notify();
   }
   synchronized void suspenderhilo(){
       suspender =true;
   }
   synchronized void reanudarhilo(){
       suspender =false;
       notify();
   }
   
   
}
