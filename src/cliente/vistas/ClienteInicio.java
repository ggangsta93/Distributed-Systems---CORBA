/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vistas;

import cliente.ClienteDeObjetos;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Julieth
 */
public class ClienteInicio extends javax.swing.JFrame {

    /**
     * Creates new form ClienteInicio
     */
   private ClienteDeObjetos clienteDeObjetos=null;
   private String reg_nombres="", obj_nombres="";
   private String reg_apellidos="", obj_apellidos="";
   private String reg_direccion="", obj_direccion="";
   private String reg_tipo_id="", obj_tipo_id="";
   private int reg_id=0, obj_id=0;
   
   public ClienteInicio(ClienteDeObjetos clienteDeObjetos) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.clienteDeObjetos=clienteDeObjetos;
        habilitarComponentesRegistrar(false);
        habilitarComponentesMonitorear(false);
        habilitarComponentesNotificaciones(false);
        jButton_editar.setEnabled(false); 
        jButton_iniciarMonitoreo.setEnabled(false);
        jButton_registrar.setEnabled(false); 
    }
 public void habilitarComponentesRegistrar(boolean opcion){
         jTextField_nombres.setEnabled(opcion);
         jTextField_apellidos.setEnabled(opcion);
         jComboBox_tipoID.setEnabled(opcion);
         jTextField_identificacion.setEnabled(opcion);
         jTextField_direccion.setEnabled(opcion);                 
    }
    public void habilitarComponentesMonitorear(boolean opcion){
         jTextArea_consola.setEnabled(opcion);
         jProgressBar_tiempo.setEnabled(opcion);
         jTextField_freCardiaca.setEnabled(opcion);
         jTextField_freRespiratoria.setEnabled(opcion);
         jTextField_temperatura.setEnabled(opcion);
                 
    }
    public void habilitarComponentesNotificaciones(boolean opcion){
         jTextArea_notificaciones.setEnabled(opcion);     
    }
    
    public void escribirEnConsola(String cadena){
        jTextArea_consola.append(cadena+"\n");
    }
    
    public void escribirEnFreCardiaca(String cadena){
        jTextField_freCardiaca.setText(cadena);
    }
    public void escribirEnFreRespiratoria(String cadena){
        jTextField_freRespiratoria.setText(cadena);     
    }
    public void escribirEnTemperatura(String cadena){
       jTextField_temperatura.setText(cadena);
    }
    
    public void sincronizarBarra(int valor){
       jProgressBar_tiempo.setValue(valor);
    }
    
    public void escribirEnNotificaciones(String mensaje){
       jTextArea_notificaciones.append(mensaje+"\n\n");
    }
    
    private boolean validarNombre(String cadena, String campo){
        boolean estaBien =true;
       
        if(cadena.equalsIgnoreCase("")){
            if(campo.equalsIgnoreCase("nombres"))             
                    JOptionPane.showMessageDialog(null, "El nombre esta vacio.");    
            else
                    JOptionPane.showMessageDialog(null, "El apellido esta vacio.");   
            estaBien=false;
        }else{
            String[] palabras = cadena.split(" "); 
            boolean contieneNumeros = false;
            double campo1;
            int i=0;
            do{
                try{
                     campo1 =   Double.parseDouble(palabras[i]);
                     contieneNumeros=true;
                }catch(Exception e){}
                i++;
            }while(i < palabras.length);

              
            if(contieneNumeros){
                if(campo.equalsIgnoreCase("nombres"))           
                    JOptionPane.showMessageDialog(null, "El nombre no debe contener numeros.");    
                else
                    JOptionPane.showMessageDialog(null, "El apellido no dede contener numeros.");    
                estaBien=false;
            }  
            
            if(cadena.length() > 30){
                if(campo.equalsIgnoreCase("nombres"))           
                    JOptionPane.showMessageDialog(null, "El nombre sobrepasa los 30 caracteres.");    
                else
                    JOptionPane.showMessageDialog(null, "El apellido sobrepasa los 30 caracteres.");    
                estaBien=false;
            } 
        }
        
        return estaBien;
    }
    private boolean validarCamposRegistro(){
        boolean valoresCorrectos =true;
        
        reg_tipo_id=jComboBox_tipoID.getItemAt(jComboBox_tipoID.getSelectedIndex());
        
        if(validarNombre(jTextField_nombres.getText().replaceAll(" +"," ").trim(),"nombres"))
                reg_nombres=jTextField_nombres.getText().replaceAll(" +"," ").trim();
        else
                valoresCorrectos=false;
        
        if(validarNombre(jTextField_apellidos.getText().replaceAll(" +"," ").trim(),"apellidos"))
                reg_apellidos=jTextField_apellidos.getText().replaceAll(" +"," ").trim();
        else
                valoresCorrectos=false;
        
        try{
                reg_id= Integer.parseInt(jTextField_identificacion.getText());
                if(reg_id<0 ||  reg_id>99999){
                       JOptionPane.showMessageDialog(null, "La  identificacion esta fuera de rango."); 
                       valoresCorrectos=false;
                }
        }catch(Exception e){
                valoresCorrectos=false;
                if(jTextField_identificacion.getText().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "La  identificacion esta vacia."); 
                else
                    JOptionPane.showMessageDialog(null, "La identificacion no debe contener letras.");    
        }   
        
        reg_direccion=jTextField_direccion.getText().replaceAll(" +"," ").trim();
        if(reg_direccion.length() > 30){
                JOptionPane.showMessageDialog(null, "La direccion sobrepasa los 30 caracteres.");    
        }
        
        if(reg_direccion.equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "La direccion esta vacia.");    
               valoresCorrectos=false;
        }
        return valoresCorrectos;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField_puerto = new javax.swing.JTextField();
        jTextField_ip = new javax.swing.JTextField();
        jButton_salir = new javax.swing.JButton();
        jButton_conectar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField_nombres = new javax.swing.JTextField();
        jTextField_apellidos = new javax.swing.JTextField();
        jTextField_identificacion = new javax.swing.JTextField();
        jTextField_direccion = new javax.swing.JTextField();
        jComboBox_tipoID = new javax.swing.JComboBox<>();
        jButton_registrar = new javax.swing.JButton();
        jButton_editar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_consola = new javax.swing.JTextArea();
        jTextField_freCardiaca = new javax.swing.JTextField();
        jTextField_freRespiratoria = new javax.swing.JTextField();
        jTextField_temperatura = new javax.swing.JTextField();
        jProgressBar_tiempo = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton_iniciarMonitoreo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_notificaciones = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();

        jLabel14.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Frecuencia respiratoria ");

        jLabel12.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Temperatura");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asintomatico");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(99, 146, 220));

        jTextField_puerto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_puerto.setText("2020");

        jTextField_ip.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_ip.setText("localhost");

        jButton_salir.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jButton_salir.setForeground(new java.awt.Color(102, 102, 102));
        jButton_salir.setText("SALIR");
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });

        jButton_conectar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jButton_conectar.setForeground(new java.awt.Color(102, 102, 102));
        jButton_conectar.setText("CONECTAR");
        jButton_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_conectarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CONEXION");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Direccion IP:");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Numero de puerto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_ip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_puerto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jButton_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_conectar, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_ip))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jButton_conectar)
                .addGap(18, 18, 18)
                .addComponent(jButton_salir)
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("Conectar", jPanel1);

        jPanel2.setBackground(new java.awt.Color(99, 146, 220));

        jTextField_nombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_apellidos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_identificacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox_tipoID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TI", "CC", "PP" }));

        jButton_registrar.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton_registrar.setForeground(new java.awt.Color(102, 102, 102));
        jButton_registrar.setText("Registrar");
        jButton_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registrarActionPerformed(evt);
            }
        });

        jButton_editar.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton_editar.setForeground(new java.awt.Color(102, 102, 102));
        jButton_editar.setText("Editar");
        jButton_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombres:");

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos:");

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tipo identificacion:");

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Identificacion:");

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Direccion:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REGISTRO PACIENTE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_identificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(jTextField_nombres)
                            .addComponent(jTextField_apellidos)
                            .addComponent(jComboBox_tipoID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_direccion))
                        .addContainerGap(74, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox_tipoID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registrar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(99, 146, 220));

        jTextArea_consola.setColumns(20);
        jTextArea_consola.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea_consola.setRows(5);
        jScrollPane1.setViewportView(jTextArea_consola);

        jTextField_freCardiaca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_freRespiratoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_temperatura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jProgressBar_tiempo.setMaximum(8);

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("MONITOREO....");

        jLabel15.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Frecuencia respiratoria ");

        jLabel13.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Temperatura °C");

        jLabel16.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Frecuencia cardiaca");

        jButton_iniciarMonitoreo.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton_iniciarMonitoreo.setForeground(new java.awt.Color(102, 102, 102));
        jButton_iniciarMonitoreo.setText("Iniciar");
        jButton_iniciarMonitoreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_iniciarMonitoreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jTextField_temperatura, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(jTextField_freRespiratoria)
                    .addComponent(jTextField_freCardiaca))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton_iniciarMonitoreo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jProgressBar_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_freCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_freRespiratoria, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton_iniciarMonitoreo))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Monitorear", jPanel3);

        jPanel4.setBackground(new java.awt.Color(99, 146, 220));

        jTextArea_notificaciones.setColumns(20);
        jTextArea_notificaciones.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea_notificaciones.setRows(5);
        jScrollPane2.setViewportView(jTextArea_notificaciones);

        jLabel11.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NOTIFICACIONES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel11)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Notificaciones", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_conectarActionPerformed
        int numPuertoRMIRegistry = Integer.parseInt(jTextField_puerto.getText());
        String direccionIpRMIRegistry = jTextField_ip.getText();
        if(jButton_conectar.getText().equalsIgnoreCase("Conectar")){
            if(this.clienteDeObjetos.obtenerObjetoRemoto(numPuertoRMIRegistry, direccionIpRMIRegistry)){
                JOptionPane.showMessageDialog(null, "Conexion exitosa con el servidor de alertas.");  
                
                int  cantidad=0;
                boolean estaBien=false;
                if(this.clienteDeObjetos.consultarRegPermitidos()==0){
                    do{
                          try{
                                cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de pacientes a registrar (1-5): "));
                          }catch(Exception e){
                              
                          }
                          
                          if(cantidad<1 || cantidad>5){
                              JOptionPane.showMessageDialog(null, "Numero fuera de rango, intentelo de nuevo.");  
                          }else{
                              estaBien=true;
                          }
                    }
                    while(!estaBien);
                    
                    if(this.clienteDeObjetos.consultarRegPermitidos()==0){//Esto se puso por si ejecutan varias ventanas simultaneas
                        if(this.clienteDeObjetos.cambiarRegPermitidos(cantidad))
                             JOptionPane.showMessageDialog(null, "Cantidad establecida con exito.");  
                    }else{
                             JOptionPane.showMessageDialog(null, "No se registro porque ya existe un valor definido.");  
                    }
                }  
                jButton_conectar.setText("Desconectar");
                jButton_conectar.setBackground(Color.red);
                jTextField_puerto.setEnabled(false);
                jTextField_ip.setEnabled(false);
                jButton_salir.setEnabled(false);    
                jButton_registrar.setEnabled(true);
                habilitarComponentesRegistrar(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Conexion fallida con el servidor de alertas.");    
            }
        }else{
            clienteDeObjetos.desconectar();
            jButton_conectar.setText("Desconectado");
            habilitarComponentesRegistrar(false);
            habilitarComponentesMonitorear(false);
            habilitarComponentesNotificaciones(false);
            jButton_conectar.setEnabled(false);
            jButton_conectar.setBackground(Color.gray);
            jButton_salir.setEnabled(true);
        }       
    }//GEN-LAST:event_jButton_conectarActionPerformed

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
            System.exit(0);
    }//GEN-LAST:event_jButton_salirActionPerformed

    private void jButton_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registrarActionPerformed
        boolean seRegistro=false;
        boolean valoresCorrectos=validarCamposRegistro();         
        if(valoresCorrectos){
            seRegistro = this.clienteDeObjetos.registrarAsintomatico(reg_nombres, reg_apellidos, reg_tipo_id, reg_id, reg_direccion);
            if(seRegistro){
                obj_nombres = reg_nombres;
                obj_apellidos = reg_apellidos;
                obj_tipo_id = reg_tipo_id;
                obj_id = reg_id;
                obj_direccion = reg_direccion;                
                habilitarComponentesRegistrar(false);
                jButton_editar.setEnabled(true);
                jButton_iniciarMonitoreo.setEnabled(true);
                jButton_registrar.setEnabled(false);
                JOptionPane.showMessageDialog(null, "El paciente se registro con exito.");  
                
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudo realizar el registro.");     
            }
        }  
    }//GEN-LAST:event_jButton_registrarActionPerformed

    private void jButton_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editarActionPerformed
        boolean aux =true;
        
        if(jButton_iniciarMonitoreo.getText().equalsIgnoreCase("Pausar")){
            JOptionPane.showMessageDialog(null, "El monitoreo no esta Pausado.");
        } 
        
        if(jButton_editar.getText().equalsIgnoreCase("Guardar")){
            
            if(validarCamposRegistro()){
                if(this.clienteDeObjetos.modificarAsintomatico(reg_nombres, reg_apellidos, reg_tipo_id, reg_id, reg_direccion)){
                    JOptionPane.showMessageDialog(null, "Cambio realizado con exito.");
                    obj_nombres = reg_nombres;
                    obj_apellidos = reg_apellidos;
                    obj_tipo_id = reg_tipo_id;
                    obj_id = reg_id;
                    obj_direccion = reg_direccion;
                    habilitarComponentesRegistrar(false);
                    jButton_editar.setText("Editar");
                    jButton_iniciarMonitoreo.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el cambio.");
                }
            }
             aux=false;             
        }
        
        if(jButton_iniciarMonitoreo.getText().equalsIgnoreCase("Iniciar") && aux){
             habilitarComponentesRegistrar(true);
             jButton_editar.setText("Guardar");
             jButton_iniciarMonitoreo.setEnabled(false);             
        } 
    }//GEN-LAST:event_jButton_editarActionPerformed

    private void jButton_iniciarMonitoreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_iniciarMonitoreoActionPerformed
        if(jButton_iniciarMonitoreo.getText().equalsIgnoreCase("Iniciar")){
            clienteDeObjetos.enviarIndicadores(true);
            habilitarComponentesMonitorear(true);
            habilitarComponentesNotificaciones(true);
            jButton_iniciarMonitoreo.setText("Pausar");
        }else{
            clienteDeObjetos.enviarIndicadores(false);
            habilitarComponentesMonitorear(false);
            habilitarComponentesNotificaciones(false);
            jButton_iniciarMonitoreo.setText("Iniciar");
        }

    }//GEN-LAST:event_jButton_iniciarMonitoreoActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_conectar;
    private javax.swing.JButton jButton_editar;
    private javax.swing.JButton jButton_iniciarMonitoreo;
    private javax.swing.JButton jButton_registrar;
    private javax.swing.JButton jButton_salir;
    private javax.swing.JComboBox<String> jComboBox_tipoID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar_tiempo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea_consola;
    private javax.swing.JTextArea jTextArea_notificaciones;
    private javax.swing.JTextField jTextField_apellidos;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_freCardiaca;
    private javax.swing.JTextField jTextField_freRespiratoria;
    private javax.swing.JTextField jTextField_identificacion;
    private javax.swing.JTextField jTextField_ip;
    private javax.swing.JTextField jTextField_nombres;
    private javax.swing.JTextField jTextField_puerto;
    private javax.swing.JTextField jTextField_temperatura;
    // End of variables declaration//GEN-END:variables
}
