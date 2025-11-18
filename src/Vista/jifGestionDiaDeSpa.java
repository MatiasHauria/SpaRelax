package Vista;

import Modelo.Cliente;
import Modelo.DiaDeSpa;
import Modelo.Sesion;
import Persistencia.ClienteData;
import Persistencia.DiadespaData;
import Persistencia.SesionData;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jifGestionDiaDeSpa extends javax.swing.JInternalFrame {

      private DefaultTableModel modeloTabla;
    private DiadespaData diaData;
    private ClienteData clienteData;
    private SesionData sesionData;
    private List<Cliente> listaClientes;
    private List<DiaDeSpa> listaDias;
    private List<Sesion> listaSesiones;
    private String operacion = "";
    private int idSeleccionado = -1;
    private List<String> sesionesSeleccionadas;

    public jifGestionDiaDeSpa() {
        initComponents();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        this.diaData = new DiadespaData();
        this.clienteData = new ClienteData();
        this.sesionData = new SesionData();
        this.sesionesSeleccionadas = new ArrayList<>();
        modeloTabla = new DefaultTableModel();
        configurarTabla();
        cargarClientes();
        cargarSesiones();
        cargarTabla();
        deshabilitarCampos();
    }
    
    private void configurarTabla() {
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Sesiones");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Monto");
        modeloTabla.addColumn("Estado");
        tablaSpa.setModel(modeloTabla);
    }
      
    private void cargarClientes() {
        listaClientes = clienteData.obtenerClientes();
        comboCliente.removeAllItems();
        for (Cliente c : listaClientes) {
            comboCliente.addItem(c.getNombreCompleto());
        }
    }
      
    private void cargarSesiones(){
        listaSesiones = sesionData.obtenerSesiones();
        comboSesiones.removeAllItems();
        for (Sesion s : listaSesiones){
            comboSesiones.addItem("Sesion " + s.getCodSesion() + " - " + s.getTratamiento().getNombre());
        }
    }
    
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        listaDias = diaData.obtenerTodosLosDiasDeSpa();
        
        for (DiaDeSpa dia : listaDias) {
            modeloTabla.addRow(new Object[]{
                dia.getCodPack(),
                dia.getCliente().getNombreCompleto(),
                String.join(", ", dia.getSesionesCodigos()),
                dia.getFechayHora(),
                dia.getMonto(),
                dia.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }

    private void habilitarCampos() {
        comboCliente.setEnabled(true);
        jDateChooser1.setEnabled(true);
        jtfPreferencias.setEnabled(true);
        jtfMonto.setEnabled(true);
        comboSesiones.setEnabled(true);
        btnSesion.setEnabled(true);
    }

    private void deshabilitarCampos() {
        comboCliente.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jtfPreferencias.setEnabled(false);
        jtfMonto.setEnabled(false);
        comboSesiones.setEnabled(false);
        btnSesion.setEnabled(false);
        jtfSesionesVisual.setEnabled(false);
    }

    private void limpiarCampos() {
        jtfId.setText("");
        jtfPreferencias.setText("");
        jtfMonto.setText("");
        jDateChooser1.setDate(null);
        comboCliente.setSelectedIndex(0);
        comboSesiones.setSelectedIndex(0);
        jtfSesionesVisual.setText("");
        sesionesSeleccionadas.clear();
    }

    private boolean validarCampos() {
        if (comboCliente.getSelectedIndex() < 0) {
            mostrarError("Seleccione un cliente");
            return false;
        }
        if (jDateChooser1.getDate() == null) {
            mostrarError("Seleccione una fecha");
            return false;
        }
        if (jtfPreferencias.getText().trim().isEmpty()) {
            mostrarError("Ingrese las preferencias");
            return false;
        }
        if (jtfMonto.getText().trim().isEmpty()) {
            mostrarError("Ingrese el monto");
            return false;
        }
        if (sesionesSeleccionadas.isEmpty()) {
            mostrarError("Seleccione al menos una sesión");
            return false;
        }
        
        try {
            Double.parseDouble(jtfMonto.getText());
        } catch (NumberFormatException e) {
            mostrarError("El monto debe ser un número válido");
            return false;
        }
        
        return true;
    }

    private void agregarSesion(){
        int selectedIndex = comboSesiones.getSelectedIndex();
        if (selectedIndex < 0){
            mostrarError("Seleccione una sesión de la lista");
            return;
        }
        
        Sesion sesionSeleccionada = listaSesiones.get(selectedIndex);
        String codigoSesion = String.valueOf(sesionSeleccionada.getCodSesion());
        
        if (!sesionesSeleccionadas.contains(codigoSesion)) {
            sesionesSeleccionadas.add(codigoSesion);
            actualizarVisualSesiones();
            mostrarExito("Sesión agregada: " + sesionSeleccionada.getTratamiento().getNombre());
        } else {
            mostrarError("Esta sesión ya fue agregada");
        }
    }

    private void actualizarVisualSesiones() {
        jtfSesionesVisual.setText(String.join(", ", sesionesSeleccionadas));
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSesion = new javax.swing.JComboBox<>();
        jtfSesiones = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jtfPreferencias = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfMonto = new javax.swing.JTextField();
        comboSesiones = new javax.swing.JComboBox<>();
        btnSesion = new javax.swing.JButton();
        jtfSesionesVisual = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSpa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        comboSesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dia de Spa");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ingreso de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("Codigo Pack:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel4.setText("Cliente:");

        comboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel6.setText("Preferencias:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel7.setText("Sesiones:");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel8.setText("Precio:");

        comboSesiones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSesion.setText("+");
        btnSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboCliente, 0, 134, Short.MAX_VALUE)
                                    .addComponent(comboSesiones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfPreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSesion)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtfSesionesVisual)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboSesiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSesion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfSesionesVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfPreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(14, 14, 14))
        );

        btnNuevo.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnAlta.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnBaja.setText("Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tablaSpa.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tablaSpa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaSpa);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel2.setText("Su belleza es nuestra firma, y su garantía");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 111, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        operacion = "NUEVO";
        habilitarCampos();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
          if (!validarCampos()) return;

        try {
            Cliente cliente = listaClientes.get(comboCliente.getSelectedIndex());
            LocalDateTime fecha = jDateChooser1.getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            String preferencias = jtfPreferencias.getText();
            double monto = Double.parseDouble(jtfMonto.getText());
            
            List<Sesion> sesiones = new ArrayList<>();
            for (String codigo : sesionesSeleccionadas) {
                Sesion sesion = sesionData.buscarSesion(Integer.parseInt(codigo));
                if (sesion != null) {
                    sesiones.add(sesion);
                }
            }

            DiaDeSpa dia = new DiaDeSpa(cliente, sesiones, fecha, preferencias, monto);

            if ("NUEVO".equals(operacion)) {
                diaData.generarDiaDeSpa(dia);
                mostrarExito("Día de Spa creado exitosamente");
            } else if ("ACTUALIZAR".equals(operacion)) {
                dia.setCodPack(idSeleccionado);
                diaData.actualizarDiaDeSpa(idSeleccionado, dia);
                mostrarExito("Día de Spa actualizado exitosamente");
            }

            cargarTabla();
            limpiarCampos();
            deshabilitarCampos();
            operacion = "";

        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        int fila = tablaSpa.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione un día de spa de la tabla");
            return;
        }

        int id = (int) tablaSpa.getValueAt(fila, 0);
        diaData.altaLogicaDiaDeSpa(id);
        mostrarExito("Día de spa activado exitosamente");
        cargarTabla();
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
          int fila = tablaSpa.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione un día de spa de la tabla");
            return;
        }

        int id = (int) tablaSpa.getValueAt(fila, 0);
        diaData.bajaLogicaDiaDeSpa(id);
        mostrarExito("Día de spa desactivado exitosamente");
        cargarTabla();
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
     int fila = tablaSpa.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione un día de spa de la tabla");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este día de spa?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            int id = (int) tablaSpa.getValueAt(fila, 0);
            diaData.borrarDiaDeSpa(id);
            mostrarExito("Día de spa eliminado exitosamente");
            cargarTabla();
            limpiarCampos();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
         int fila = tablaSpa.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione un día de spa de la tabla");
            return;
        }

        operacion = "ACTUALIZAR";
        idSeleccionado = (int) tablaSpa.getValueAt(fila, 0);
        habilitarCampos();
        DiaDeSpa dia = diaData.buscarDiaDeSpa(idSeleccionado);
        if (dia != null) {
            jtfId.setText(String.valueOf(dia.getCodPack()));
           
            for (int i = 0; i < comboCliente.getItemCount(); i++) {
                if (comboCliente.getItemAt(i).equals(dia.getCliente().getNombreCompleto())) {
                    comboCliente.setSelectedIndex(i);
                    break;
                }
            }
            jtfSesiones.setText(String.join(", ", dia.getSesionesCodigos()));
            jDateChooser1.setDate(java.util.Date.from(dia.getFechayHora()
                    .atZone(ZoneId.systemDefault()).toInstant()));
            jtfPreferencias.setText(dia.getPreferencias());
            jtfMonto.setText(String.valueOf(dia.getMonto()));
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionActionPerformed
        agregarSesion();
    }//GEN-LAST:event_btnSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSesion;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JComboBox<String> comboSesion;
    private javax.swing.JComboBox<Object> comboSesiones;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfMonto;
    private javax.swing.JTextField jtfPreferencias;
    private javax.swing.JTextField jtfSesiones;
    private javax.swing.JTextField jtfSesionesVisual;
    private javax.swing.JTable tablaSpa;
    // End of variables declaration//GEN-END:variables
}
