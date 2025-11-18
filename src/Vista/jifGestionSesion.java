package Vista;

import java.sql.*;
import Modelo.Sesion;
import Modelo.Consultorio;
import Modelo.Tratamiento;
import Modelo.Masajista;
import Persistencia.SesionData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

public class jifGestionSesion extends javax.swing.JInternalFrame {
    private SesionData sesionData;
    private boolean aux = false;
    private String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ,\\s]+$"; // Expresion regular para letras.
    private String regex2 = "^[\\d.]+$"; // Expresion regular para digitos numericos.
    private List<Sesion> sesion;
    public jifGestionSesion() {
        initComponents();
        this.sesionData = new SesionData();
        this.sesion = new ArrayList<>();
        cargarSesionIds();
        cargarConsultoriosIds();
        cargarTratamientosIds();
        camposEditables();
    }
    

    
//    private void cargarCantidadInstalaciones() { No sé como hacer este JComboBox porque la logica del arraylist de instalaciones, básicamente no tiene lógica.
//        String[] arr = {
//            "1",
//            "2",
//            "3",
//            "4",
//            "5"
//        }; // En proceso.
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonActualizar = new javax.swing.JButton();
        jButtonCargar = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonDeshabilitar = new javax.swing.JButton();
        jButtonHabilitar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxTratamientosId = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxConsultoriosId = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxMatriculas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDateChooserFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaSesiones = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 30)); // NOI18N
        jLabel1.setText("Gestión de Sesiones");

        jButtonActualizar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonActualizar.setText("Actualizar sesión");

        jButtonCargar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonCargar.setText("Cargar sesión");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jButtonBorrar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonBorrar.setText("Borrar sesión");

        jButtonCancelar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonDeshabilitar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonDeshabilitar.setText("Deshabilitar sesión");

        jButtonHabilitar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonHabilitar.setText("Habilitar sesión");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel4.setText("ID de tratamiento:");

        jComboBoxTratamientosId.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jComboBoxTratamientosId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el ID" }));
        jComboBoxTratamientosId.setBorder(null);
        jComboBoxTratamientosId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTratamientosId, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxTratamientosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("ID de consultorio:");

        jComboBoxConsultoriosId.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jComboBoxConsultoriosId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el ID" }));
        jComboBoxConsultoriosId.setBorder(null);
        jComboBoxConsultoriosId.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jComboBoxConsultoriosId, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxConsultoriosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        jComboBoxMatriculas.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jComboBoxMatriculas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione la matricula" }));
        jComboBoxMatriculas.setBorder(null);
        jComboBoxMatriculas.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel6.setText("Fecha de inicio:");

        jDateChooserFechaFin.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel5.setText("Matricula de especialista:");

        jDateChooserFechaInicio.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel7.setText("Fecha de fin:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMatriculas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxMatriculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jTablaSesiones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTablaSesiones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizar)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jButtonHabilitar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDeshabilitar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel1)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCargar)
                    .addComponent(jButtonActualizar)
                    .addComponent(jButtonCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHabilitar)
                    .addComponent(jButtonDeshabilitar)
                    .addComponent(jButtonBorrar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        if (aux == false) {
            habilitarCampos();
            deshabilitarBotones();
            jButtonCargar.setEnabled(true);
            jButtonCancelar.setEnabled(true);
            aux = true; 
            jButtonCargar.setText("Guardar");
            return;
        }

        if (aux == true) {
            if (jComboBoxConsultoriosId.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un ID del consultorio antes de continuar.");
                return;
            } else if (jComboBoxMatriculas.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione la matricula de un especialista antes de continuar.");
                return;
            } else if (jComboBoxTratamientosId.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un ID del tratamiento antes de continuar.");
                return;
            }
            
            Calendar fIni = Calendar.getInstance();
            fIni.setTime(jDateChooserFechaInicio.getDate());
            Timestamp iniTimeStamp = new Timestamp(fIni.getTimeInMillis());
            Calendar fFin = Calendar.getInstance();
            fFin.setTime(jDateChooserFechaFin.getDate());
            Timestamp finTimestamp = new Timestamp(fFin.getTimeInMillis());
            Consultorio consultorio = (Consultorio) jComboBoxConsultoriosId.getSelectedItem();
            Tratamiento tratamiento = (Tratamiento) jComboBoxTratamientosId.getSelectedItem();
            Masajista matricula = (Masajista) jComboBoxMatriculas.getSelectedItem();
//            sesionData.insertarSesion(new Sesion(consultorio, tratamiento, instalaciones, matricula, LocalDateTime.MIN, LocalDateTime.MIN, isIcon));
            
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limpiezaCampos();
        deshabilitarCampos();
        habilitarBotones();
        jButtonCancelar.setEnabled(false);
        if (aux == true) {
            aux = false;
        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonDeshabilitar;
    private javax.swing.JButton jButtonHabilitar;
    private javax.swing.JComboBox<String> jComboBoxConsultoriosId;
    private javax.swing.JComboBox<String> jComboBoxMatriculas;
    private javax.swing.JComboBox<String> jComboBoxTratamientosId;
    private com.toedter.calendar.JDateChooser jDateChooserFechaFin;
    private com.toedter.calendar.JDateChooser jDateChooserFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaSesiones;
    // End of variables declaration//GEN-END:variables

        private void cargarSesionIds() {
        for (Sesion sesion : sesionData.obtenerSesiones()) {
        }
    }
    
    private void cargarConsultoriosIds() {
        for (Sesion sesion : sesionData.obtenerSesiones()) {
            jComboBoxConsultoriosId.addItem(String.valueOf(sesion.getCodConsultorio()));
        }
    }
    
    private void cargarTratamientosIds() {
        for (Sesion sesion : sesionData.obtenerSesiones()) {
            jComboBoxTratamientosId.addItem(String.valueOf(sesion.getCodTratamiento()));
        }
    }
    
    private void deshabilitarCampos() {
        jComboBoxConsultoriosId.setEnabled(false);
        jComboBoxMatriculas.setEnabled(false);
        jComboBoxTratamientosId.setEnabled(false);
        jDateChooserFechaInicio.setEnabled(false);
        jDateChooserFechaFin.setEnabled(false);
    }
    
    private void limpiezaCampos() {
        jComboBoxTratamientosId.setSelectedIndex(0);
        jComboBoxMatriculas.setSelectedIndex(0);
        jComboBoxConsultoriosId.setSelectedIndex(0);
    }
    
    private void habilitarCampos() {
        jComboBoxConsultoriosId.setEnabled(true);
        jComboBoxMatriculas.setEnabled(true);
        jComboBoxTratamientosId.setEnabled(true);
        jDateChooserFechaInicio.setEnabled(true);
        jDateChooserFechaFin.setEnabled(true);
    }
    
    private void habilitarBotones() {
        jButtonCargar.setEnabled(true);
        jButtonActualizar.setEnabled(true);
        jButtonBorrar.setEnabled(true);
        jButtonHabilitar.setEnabled(true);
        jButtonDeshabilitar.setEnabled(true);
    }
    
    private void deshabilitarBotones() {
        jButtonCargar.setEnabled(false);
        jButtonActualizar.setEnabled(false);
        jButtonBorrar.setEnabled(false);
        jButtonHabilitar.setEnabled(false);
        jButtonDeshabilitar.setEnabled(false);
    }
    
    private void camposEditables() {
    }
    
    private void columns() {
        modeloTabla.addColumn("ID_Sesion");
        modeloTabla.addColumn("ID_Consu");
        modeloTabla.addColumn("ID_Tratamiento");
        modeloTabla.addColumn("ID_Pack");
        modeloTabla.addColumn("Instalaciones");
        modeloTabla.addColumn("Matricula");
        modeloTabla.addColumn("FechaInicio");
        modeloTabla.addColumn("FechaFin");
        modeloTabla.addColumn("Estado");
        jTablaTratamientos.setModel(modeloTabla);
    }
    
    private void rows() {
        modeloTabla.setRowCount(0);
        List<Sesion> listaSesiones = new ArrayList<>(sesionData.obtenerSesiones());
        for (Sesion sesion : listaSesiones) {
                Object[] filas = {
                    sesion.getCodSesion(),
                    sesion.getCodConsultorio(),
                    sesion.getCodTratamiento(),
                    sesion.getCodPack(),
                    sesion.getInstalacion(),
                    sesion.getMatricula(),
                    sesion.getFechaHoraInicio(),
                    sesion.getFechaHoraFin(),
                    (sesion.isEstado() ? "Activo" : "Inactivo")
                };
                modeloTabla.addRow(filas);
            }
        }
    }

}
