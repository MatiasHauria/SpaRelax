package Vista;

import Modelo.Instalacion;
import Persistencia.InstalacionData;
import java.util.ArrayList;
import static Vista.jfSpaRelax.listaInstalaciones;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jifGestionInstalacion extends javax.swing.JInternalFrame {

    InstalacionData instdat;
    private boolean tablaVisible = false;
    private String estadoOperacion = "Ninguno";
    private int idInstalacionSeleccionada = -1;
    private final DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public jifGestionInstalacion() {
        initComponents();
        this.instdat = new InstalacionData();
        columnas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jTextDetalles = new javax.swing.JTextField();
        jTextPrecio = new javax.swing.JTextField();
        jBotonNuevo = new javax.swing.JButton();
        jBotonGuardar = new javax.swing.JButton();
        jBotonActualizar = new javax.swing.JButton();
        jBotonAlta = new javax.swing.JButton();
        jBotonBaja = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jBotonCerrar = new javax.swing.JButton();
        jBotonActualizarTabla = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 30)); // NOI18N
        jLabel1.setText("Gestión de Instalaciones");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso de datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("Detalles de uso:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel4.setText("Precio/H:");

        jTextNombre.setEnabled(false);
        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });

        jTextDetalles.setEnabled(false);

        jTextPrecio.setEnabled(false);

        jBotonNuevo.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonNuevo.setText("Nueva");
        jBotonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonNuevoActionPerformed(evt);
            }
        });

        jBotonGuardar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonGuardar.setText("Guardar");
        jBotonGuardar.setEnabled(false);
        jBotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonGuardarActionPerformed(evt);
            }
        });

        jBotonActualizar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonActualizar.setText("Actualizar");
        jBotonActualizar.setEnabled(false);
        jBotonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonActualizarActionPerformed(evt);
            }
        });

        jBotonAlta.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonAlta.setText("Alta");
        jBotonAlta.setEnabled(false);
        jBotonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonAltaActionPerformed(evt);
            }
        });

        jBotonBaja.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonBaja.setText("Baja");
        jBotonBaja.setEnabled(false);
        jBotonBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonBajaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Borrar Instalacion");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextPrecio)))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBotonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBotonAlta)
                .addGap(18, 18, 18)
                .addComponent(jBotonBaja)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(82, 82, 82))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jBotonNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBotonGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jBotonActualizar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBotonAlta)
                    .addComponent(jBotonBaja)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jBotonCerrar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonCerrar.setText("Cerrar");
        jBotonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonCerrarActionPerformed(evt);
            }
        });

        jBotonActualizarTabla.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jBotonActualizarTabla.setText("Mostrar Tabla");
        jBotonActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonActualizarTablaActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jBotonCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jBotonActualizarTabla, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBotonActualizarTabla)
                        .addGap(144, 144, 144)
                        .addComponent(jBotonCerrar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(110, 110, 110))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBotonActualizarTabla)
                    .addComponent(jBotonCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarTabla () {
        listaInstalaciones = instdat.obtenerInstalaciones();
        modelo.setRowCount(0);
        
        for(Instalacion aux: listaInstalaciones){
            Object[] filas = {
                aux.getIdInstalacion(),
                aux.getNombre(),
                aux.getDetalleUso(),
                aux.getPrecioPorHora(),
                (aux.isEstado() ? "Activa" : "Inactiva")
            };
            modelo.addRow(filas);
        }
    }
    
    private Instalacion buscarInstalacionPorId(int idInstalacion) {
        listaInstalaciones = instdat.obtenerInstalaciones();

        for (Instalacion i : listaInstalaciones) {
            if (i.getIdInstalacion() == idInstalacion) {
                return i;
            }
        }
        return null;
    }
    
    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void jBotonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonNuevoActionPerformed
        estadoOperacion = "Nuevo";
        
        jTextNombre.setEnabled(true);
        jTextDetalles.setEnabled(true);
        jTextPrecio.setEnabled(true);
        jBotonGuardar.setEnabled(true);
        
        jBotonNuevo.setEnabled(false);
        jBotonAlta.setEnabled(false);
        jBotonBaja.setEnabled(false);
        jBotonActualizar.setEnabled(false);
        jButton1.setEnabled(false);
    }//GEN-LAST:event_jBotonNuevoActionPerformed

    private void jBotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonGuardarActionPerformed
        String regexnombre = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s,.!¡?¿#$%&/()='\"*+-]+$";
        String regexdetalles = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s,./!¡?¿#$%&()='\"*+-]+$";
        String regexnum = "^[0-9,.]+$";
        boolean instalacionExiste = false;

        if (!jTextNombre.getText().matches(regexnombre) || jTextNombre.getText().isEmpty() || jTextNombre.getText().length() >= 50) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre valido.", "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!jTextDetalles.getText().matches(regexdetalles) || jTextDetalles.getText().isEmpty() || 
                jTextDetalles.getText().matches(regexnum)) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese detalles validos.", "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (jTextPrecio.getText().matches(regexnombre) || jTextPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un precio valido", "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        listaInstalaciones = instdat.obtenerInstalaciones();
        
        if (estadoOperacion.equalsIgnoreCase("Nuevo")) {
            
            for(Instalacion i : listaInstalaciones ) {
                if (i.getNombre().equalsIgnoreCase(jTextNombre.getText()) &&
                    i.getDetalleUso().equalsIgnoreCase(jTextDetalles.getText())) {
                    instalacionExiste = true;
                    break;
                }
            }
            
            if (instalacionExiste == true) {
                JOptionPane.showMessageDialog(this, "Ya existe una instalacion con el nombre o los detalles ingresados.", 
                        "Error. Masajista Existente", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Instalacion instalacion = new Instalacion(jTextNombre.getText(), jTextDetalles.getText(),
                    Double.parseDouble(jTextPrecio.getText()));
            
            instdat.insertarInstalacion(instalacion);
            JOptionPane.showMessageDialog(null, "Se ha Guardado Correctamente la Instalacion");
            
            estadoOperacion = "Ninguno";
        } else if (estadoOperacion.equalsIgnoreCase("Actualizar")) {
            int idInstalacionActualizar = this.idInstalacionSeleccionada;
            Instalacion InstalacionActualizar = buscarInstalacionPorId(idInstalacionActualizar);

            if (InstalacionActualizar != null) {
                instdat.actualizarInstalacion(
                    idInstalacionActualizar, 
                    jTextNombre.getText(), 
                    jTextDetalles.getText(),
                    Double.parseDouble(jTextPrecio.getText())
                );
                    JOptionPane.showMessageDialog(this, "Instalacion actualizada con Exito!");
                    this.idInstalacionSeleccionada = -1;
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro la Instalacion.", "Error de Busqueda",
                        JOptionPane.ERROR_MESSAGE);
            }
            estadoOperacion = "Ninguno";
        }
            
            cargarTabla();
        
            jTextNombre.setText("");
            jTextDetalles.setText("");
            jTextPrecio.setText("");
            
            jTextNombre.setEnabled(false);
            jTextDetalles.setEnabled(false);
            jTextPrecio.setEnabled(false);
            jBotonGuardar.setEnabled(false);
            
            jBotonNuevo.setEnabled(true);
            jBotonAlta.setEnabled(false);
            jBotonBaja.setEnabled(false);
    }//GEN-LAST:event_jBotonGuardarActionPerformed

    private void columnas() {
        modelo.addColumn("ID instalacion");
        modelo.addColumn("Nombre Instalacion");
        modelo.addColumn("Detalles");
        modelo.addColumn("Precio por Hora");
        modelo.addColumn("Estado");
        jTable.setModel(modelo);
    }


    private void jBotonActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonActualizarTablaActionPerformed
        if (tablaVisible == false) {
            cargarTabla();
            jBotonActualizarTabla.setText("Actualizar Tabla");
        } else {
            cargarTabla();
        }
    }//GEN-LAST:event_jBotonActualizarTablaActionPerformed

    private void jBotonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBotonCerrarActionPerformed

    private void jBotonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonActualizarActionPerformed
        int filaSeleccionada = jTable.getSelectedRow();
        
        if(filaSeleccionada != -1){
            Object valorId = jTable.getValueAt(filaSeleccionada, 0);

            int idInstalacion = Integer.parseInt(valorId.toString());
            this.idInstalacionSeleccionada = idInstalacion;
            Instalacion instalacionSeleccionada = buscarInstalacionPorId(idInstalacion);
            
            if (instalacionSeleccionada != null) {
                estadoOperacion = "Actualizar";
                
                jTextNombre.setText(String.valueOf(instalacionSeleccionada.getNombre()));
                jTextDetalles.setText(instalacionSeleccionada.getDetalleUso());
                jTextPrecio.setText(String.valueOf(instalacionSeleccionada.getPrecioPorHora()));

                jTextNombre.setEnabled(true);
                jTextDetalles.setEnabled(true);
                jTextPrecio.setEnabled(true);

                jBotonGuardar.setEnabled(true);
                jBotonNuevo.setEnabled(false);
                jButton1.setEnabled(false);
                jBotonActualizar.setEnabled(false);
                jBotonAlta.setEnabled(false);
                jBotonBaja.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jBotonActualizarActionPerformed

    private void jBotonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonAltaActionPerformed
        if (jTable.getSelectedRow() != -1) {
            int id = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
            
            for (Instalacion i : instdat.obtenerInstalaciones()) {
                if (id == i.getIdInstalacion() && i.isEstado() == true) {
                    JOptionPane.showMessageDialog(this, "La Instalacion ya se encuentra activa.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (id == i.getIdInstalacion() && i.isEstado() == false) {
                    instdat.altaInstalacion(id);
                    cargarTabla();
                    JOptionPane.showMessageDialog(this, "Instalacion dada de alta exitosamente.");
                }
            }
        } else if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,"Seleccione una fila para continuar.");
        }
    }//GEN-LAST:event_jBotonAltaActionPerformed

    private void jBotonBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonBajaActionPerformed
        if (jTable.getSelectedRow() != -1) {
            int id = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
            
            for (Instalacion i : instdat.obtenerInstalaciones()) {
                if (id == i.getIdInstalacion() && i.isEstado() == false) {
                    JOptionPane.showMessageDialog(this, "La Instalacion ya se encuentra inactiva.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (id == i.getIdInstalacion() && i.isEstado() == true) {
                    instdat.bajaInstalacion(id);
                    cargarTabla();
                    JOptionPane.showMessageDialog(this, "Instalacion dada de baja exitosamente.");
                }
            }
        } else if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,"Seleccione una fila para continuar.");
        }
    }//GEN-LAST:event_jBotonBajaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jBotonNuevo.setEnabled(false);
        jBotonActualizar.setEnabled(false);

        int filaSeleccionada = jTable.getSelectedRow();

        if (filaSeleccionada != -1) {
            int opcion = JOptionPane.showConfirmDialog(this, "Estas seguro de que queres borrar los datos de la Instalacion?",
                    "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                Object valorId = jTable.getValueAt(filaSeleccionada, 0);
                int idInstalacion = Integer.parseInt(valorId.toString());
                Instalacion instalacionSeleccionada = buscarInstalacionPorId(idInstalacion);

                if (instalacionSeleccionada != null) {
                    instdat.borrarInstalacion(instalacionSeleccionada.getIdInstalacion());
                    JOptionPane.showMessageDialog(this, "Instalacion borrada con exito.");

                    cargarTabla();
                    jTextNombre.setText("");
                    jTextDetalles.setText("");
                    jTextPrecio.setText("");
                    jButton1.setEnabled(false);
                    jBotonNuevo.setEnabled(true);
                    jBotonAlta.setEnabled(false);
                    jBotonBaja.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontro la Instalacion.", "Error de Busqueda",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se Encontro la Fila Seleccionada.", "Error de Busqueda",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        int filaSeleccionada = jTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            jBotonActualizar.setEnabled(true);
            jButton1.setEnabled(true);
            jBotonAlta.setEnabled(true);
            jBotonBaja.setEnabled(true);
        }
    }//GEN-LAST:event_jTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBotonActualizar;
    private javax.swing.JButton jBotonActualizarTabla;
    private javax.swing.JButton jBotonAlta;
    private javax.swing.JButton jBotonBaja;
    private javax.swing.JButton jBotonCerrar;
    private javax.swing.JButton jBotonGuardar;
    private javax.swing.JButton jBotonNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextDetalles;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextPrecio;
    // End of variables declaration//GEN-END:variables
}
