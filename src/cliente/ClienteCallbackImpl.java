/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.vistas.ClienteInicio;
import servidorAlertas.sop_corba.ClienteCallbackIntOperations;
import servidorAlertas.sop_corba.ClienteCallbackIntPOA;

/**
 *
 * @author Personal
 */
public class ClienteCallbackImpl extends ClienteCallbackIntPOA {
    
    private  ClienteInicio guiCliente=null;
    public ClienteCallbackImpl(ClienteInicio guiCliente){
        super();
        this.guiCliente=guiCliente;
    }
    
    @Override
    public void notificar(String mensaje) {
        guiCliente.escribirEnNotificaciones(mensaje);
    }
    
}
