package Vista;

import Modelo.Instalacion;
import Modelo.Sesion;
import Modelo.Consultorio;
import Modelo.Tratamiento;
import Modelo.Masajista;
import Persistencia.SesionData;
import Persistencia.ConsultorioData;
import Persistencia.InstalacionData;
import Persistencia.MasajistaData;
import Persistencia.TratamientoData;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static Vista.jfSpaRelax.listaSesiones;
import java.util.Date;
import javax.swing.JComboBox;

public class jifGestionSesion extends javax.swing.JInternalFrame {

    private SesionData sesionData;
    private ConsultorioData consultorioData;
    private MasajistaData masajistaData;
    private TratamientoData tratamientoData;
    private InstalacionData instalacionData;
    private int idSesionSeleccionada = -1;
    private boolean aux = false;
    private String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ,\\s]+$"; // Expresion regular para letras.
    private String regex2 = "^[\\d.]+$"; // Expresion regular para digitos numericos.
    private boolean tablaVisible = false;
    
    private DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public jifGestionSesion() {
        initComponents();
        this.sesionData = new SesionData();
        this.consultorioData = new ConsultorioData();
        this.masajistaData = new MasajistaData();
        this.tratamientoData = new TratamientoData();
        this.instalacionData = new InstalacionData();
        cargarSesionIds();
        cargarConsultoriosIds();
        cargarTratamientosIds();
        cargarInstalaciones();
        cargarMatriculas();
        columns();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxInstalaciones = new javax.swing.JComboBox<>();
        jbMostrarTabla = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 30)); // NOI18N
        jLabel1.setText("Gestión de Sesiones");

        jButtonActualizar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonActualizar.setText("Actualizar Sesion");
        jButtonActualizar.setEnabled(false);
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonCargar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonCargar.setText("Cargar Sesion");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jButtonBorrar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonBorrar.setText("Borrar Sesion");
        jButtonBorrar.setEnabled(false);
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jButtonCancelar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonDeshabilitar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonDeshabilitar.setText("Deshabilitar Sesion");
        jButtonDeshabilitar.setEnabled(false);
        jButtonDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeshabilitarActionPerformed(evt);
            }
        });

        jButtonHabilitar.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jButtonHabilitar.setText("Habilitar Sesion");
        jButtonHabilitar.setEnabled(false);
        jButtonHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHabilitarActionPerformed(evt);
            }
        });

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
                .addGap(18, 18, 18)
                .addComponent(jComboBoxTratamientosId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxTratamientosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("ID de consultorio:");

        jComboBoxConsultoriosId.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jComboBoxConsultoriosId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el ID" }));
        jComboBoxConsultoriosId.setBorder(null);
        jComboBoxConsultoriosId.setEnabled(false);
        jComboBoxConsultoriosId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsultoriosIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxConsultoriosId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxConsultoriosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxMatriculas, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMatriculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16))
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
        jTablaSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaSesionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaSesiones);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel8.setText("Instalaciones:");

        jComboBoxInstalaciones.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jComboBoxInstalaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una instalacion" }));
        jComboBoxInstalaciones.setBorder(null);
        jComboBoxInstalaciones.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxInstalaciones, 0, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxInstalaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jbMostrarTabla.setText("Mostrar Tabla");
        jbMostrarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMostrarTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonCargar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonHabilitar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDeshabilitar)
                                .addGap(18, 26, Short.MAX_VALUE)
                                .addComponent(jButtonBorrar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbMostrarTabla)
                .addGap(396, 396, 396))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCargar)
                    .addComponent(jButtonActualizar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonHabilitar)
                    .addComponent(jButtonDeshabilitar)
                    .addComponent(jButtonBorrar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbMostrarTabla)
                .addContainerGap(13, Short.MAX_VALUE))
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
            jButtonCargar.setText("Guardar sesión");
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
            } else if (jComboBoxInstalaciones.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione una instalacion antes de continuar.");
                return;
            }
            //parseo de fecha-hora-inicio/final
            LocalDateTime iniDateTime = jDateChooserFechaInicio.getDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay();
            LocalDateTime finDateTime = jDateChooserFechaFin.getDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay();
            //obtener consultorio
            String idConsultorio = (String) jComboBoxConsultoriosId.getSelectedItem();
            Consultorio consultorio = consultorioData.buscarConsultorio(Integer.parseInt(idConsultorio));
            //obtener el tratamiento
            String idTratamiento = (String) jComboBoxTratamientosId.getSelectedItem();
            Tratamiento tratamiento = tratamientoData.buscarTratamiento(Integer.parseInt(idTratamiento));
            //obtener matricula del masajista
            String matricula = (String) jComboBoxMatriculas.getSelectedItem();
            Masajista masajista = masajistaData.buscarMasajista(Integer.parseInt(matricula));
            //obtener la instalacion
            String nombreInstalacion = (String) jComboBoxInstalaciones.getSelectedItem();
            Instalacion instalacion = instalacionData.buscarInstalacionNombre(nombreInstalacion);
            ArrayList<Instalacion> instalaciones = new ArrayList<>();
            instalaciones.add(instalacion);
            //insertamos en la sesion
            sesionData.insertarSesion(new Sesion(consultorio, tratamiento, instalaciones, masajista, iniDateTime, finDateTime, false));
            //refrescar la tabla
            rows();
            //recargar combos
            recargarCombos();
            //limpieza
            limpiezaCampos();
            deshabilitarCampos();
            habilitarBotones();
            jButtonCargar.setText("Cargar sesión");
            aux = false;
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limpiezaCampos();
        deshabilitarCampos();
        jButtonCargar.setEnabled(true);
        jButtonCargar.setText("Cargar Sesion");
        jButtonBorrar.setEnabled(false);
        jButtonHabilitar.setEnabled(false);
        jButtonDeshabilitar.setEnabled(false);
        jButtonActualizar.setEnabled(false);
        jButtonActualizar.setText("Actualizar Sesion");
        jButtonCancelar.setEnabled(false);
        if (aux == true) {
            aux = false;
        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHabilitarActionPerformed
        //obtengo el id de la sesion y lo doy de alta
        if (jTablaSesiones.getSelectedRow() != -1) {
            Integer idSesion = (Integer) modeloTabla.getValueAt(jTablaSesiones.getSelectedRow(), 0);
            for (Sesion s : sesionData.obtenerSesiones()) {
                if (idSesion == s.getCodSesion() && s.isEstado() == true) {
                    JOptionPane.showMessageDialog(this, "La sesion ya se encuentra activa.");
                    return;
                } else if (idSesion == s.getCodSesion() && s.isEstado() == false) {
                    sesionData.altaSesion(idSesion);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila antes de continuar.");
        }
        rows();
    }//GEN-LAST:event_jButtonHabilitarActionPerformed

    private void jButtonDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeshabilitarActionPerformed
        //obtengo el id de la sesion y lo doy de baja
        if (jTablaSesiones.getSelectedRow() != -1) {
            Integer idSesion = (Integer) modeloTabla.getValueAt(jTablaSesiones.getSelectedRow(), 0);
            for (Sesion s : sesionData.obtenerSesiones()) {
                if (idSesion == s.getCodSesion() && s.isEstado() == false) {
                    JOptionPane.showMessageDialog(this, "La sesion ya se encuentra inactiva.");
                    return;
                } else if (idSesion == s.getCodSesion() && s.isEstado() == true) {
                    sesionData.bajaSesion(idSesion);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila antes de continuar.");
        }
        rows();
    }//GEN-LAST:event_jButtonDeshabilitarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        //obtengo el id de la sesion y lo borro con opciones
        if (jTablaSesiones.getSelectedRow() != -1) {
            Integer idSesion = (Integer) modeloTabla.getValueAt(jTablaSesiones.getSelectedRow(), 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea borrar la sesion de la base de datos?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (confirm) {
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.YES_OPTION:
                    sesionData.borrarSesion(idSesion);
                    break;
                default:
                    return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila antes de continuar.");
        }             
        rows();
    }//GEN-LAST:event_jButtonBorrarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        switch(jButtonActualizar.getText()) {
            case "Actualizar Sesion":
                int filaSeleccionada = jTablaSesiones.getSelectedRow();
                
                if (filaSeleccionada != -1) {
                    Object valorSesion = jTablaSesiones.getValueAt(filaSeleccionada, 0);
                
                    int idSesion = Integer.parseInt(valorSesion.toString());
                    this.idSesionSeleccionada = idSesion;
                    Sesion SesionSeleccionada = buscarSesionPorCodigo(idSesionSeleccionada);

                    if (SesionSeleccionada != null) {
                
                
                        seleccionarItemPorValor(jComboBoxConsultoriosId, SesionSeleccionada.getCodConsultorio());
                        seleccionarItemPorValor(jComboBoxMatriculas, SesionSeleccionada.getMatricula());
                        seleccionarItemPorValor(jComboBoxTratamientosId, SesionSeleccionada.getCodTratamiento());
                        Instalacion inst = SesionSeleccionada.getInstalacion().get(0);
                        String nombreInst = inst.getNombre();
                        seleccionarInstalacionPorNombre(jComboBoxInstalaciones, nombreInst);
                
                        LocalDateTime ldt = SesionSeleccionada.getFechaHoraFin();
                        Date dateFin = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                        jDateChooserFechaFin.setDate(dateFin);
                
                        LocalDateTime ldtInicio = SesionSeleccionada.getFechaHoraInicio();
                        Date dateInicio = Date.from(ldtInicio.atZone(ZoneId.systemDefault()).toInstant());
                        jDateChooserFechaInicio.setDate(dateInicio);
                
                        jDateChooserFechaFin.setDate(dateFin);
                        jDateChooserFechaInicio.setDate(dateInicio);
                
                        //habilitar campos
                        jComboBoxMatriculas.setEnabled(true);
                        jComboBoxInstalaciones.setEnabled(true);
                        jComboBoxConsultoriosId.setEnabled(true);
                        jComboBoxTratamientosId.setEnabled(true);
                        jButtonCancelar.setEnabled(true);
                        jDateChooserFechaFin.setEnabled(true);
                        jDateChooserFechaInicio.setEnabled(true);
                
                        jButtonCargar.setEnabled(false);
                        jButtonBorrar.setEnabled(false);
                        jButtonHabilitar.setEnabled(false);
                        jButtonDeshabilitar.setEnabled(false);
                
                        jButtonActualizar.setText("Guardar Sesion");
                    }
                }
                break;
            case "Guardar Sesion":
                int idSesionActualizar = this.idSesionSeleccionada;
                Sesion sesionActualizar = buscarSesionPorCodigo(idSesionActualizar); 

                if (sesionActualizar != null) {
                    String idCons = (String) jComboBoxConsultoriosId.getSelectedItem();
                    sesionActualizar.setCodConsultorio(Integer.parseInt(idCons));
                    String idTrat = (String) jComboBoxTratamientosId.getSelectedItem();
                    sesionActualizar.setCodTratamiento(Integer.parseInt(idTrat));
                    String mat = (String) jComboBoxMatriculas.getSelectedItem();
                    sesionActualizar.setMatricula(Integer.parseInt(mat));
                    
                    if(jDateChooserFechaInicio.getDate() != null && jDateChooserFechaFin.getDate() != null) {
                        LocalDateTime inicio = jDateChooserFechaInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        LocalDateTime fin = jDateChooserFechaFin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        sesionActualizar.setFechaHoraInicio(inicio);
                        sesionActualizar.setFechaHoraFin(fin);
                    }
                    
                    String nombreInstalacion = (String) jComboBoxInstalaciones.getSelectedItem();
                    Instalacion instalacionObj = instalacionData.buscarInstalacionNombre(nombreInstalacion);
                    ArrayList<Instalacion> nuevasInstalaciones = new ArrayList<>();
                    
                    if (instalacionObj != null) {
                        nuevasInstalaciones.add(instalacionObj);
                    }
                    
                    sesionActualizar.setInstalacion(nuevasInstalaciones);
                    ArrayList<String> nuevosNombres = new ArrayList<>();
                    nuevosNombres.add(nombreInstalacion);
                    sesionActualizar.setNombresInstalacion(nuevosNombres);
                    
                    sesionData.ActualizarSesiones(idSesionActualizar, sesionActualizar);
                    JOptionPane.showMessageDialog(this, "Sesion actualizada con Exito!");
                    this.idSesionSeleccionada = -1;
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontro la Sesion.", "Error de Busqueda", JOptionPane.ERROR_MESSAGE);
                }
                
            jButtonActualizar.setText("Actualizar Sesion");
            rows();
            limpiezaCampos();
            jButtonActualizar.setEnabled(false);
            jButtonCargar.setEnabled(true);
            deshabilitarCampos();
            break;
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jComboBoxConsultoriosIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsultoriosIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxConsultoriosIdActionPerformed

    private void jTablaSesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaSesionesMouseClicked
        int filaSeleccionada = jTablaSesiones.getSelectedRow();
        if (filaSeleccionada != -1) {
            jButtonActualizar.setEnabled(true);
            jButtonBorrar.setEnabled(true);
            jButtonDeshabilitar.setEnabled(true);
            jButtonHabilitar.setEnabled(true);
        }
    }//GEN-LAST:event_jTablaSesionesMouseClicked

    private void jbMostrarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMostrarTablaActionPerformed
        if (tablaVisible == false) {
            rows();
            jbMostrarTabla.setText("Actualizar Tabla");
        } else {
            rows();
        }
    }//GEN-LAST:event_jbMostrarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonDeshabilitar;
    private javax.swing.JButton jButtonHabilitar;
    private javax.swing.JComboBox<String> jComboBoxConsultoriosId;
    private javax.swing.JComboBox<String> jComboBoxInstalaciones;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaSesiones;
    private javax.swing.JButton jbMostrarTabla;
    // End of variables declaration//GEN-END:variables
    
    
    
    private void seleccionarInstalacionPorNombre(JComboBox<String> combo, String nombreBuscado) {

    for (int i = 0; i < combo.getItemCount(); i++) {
        String item = combo.getItemAt(i);
        if (item.equals(nombreBuscado)) {
            combo.setSelectedIndex(i);
            return;
        }
    }
}
    private void seleccionarItemPorValor(JComboBox combo, Object valorBuscado) {
    for (int i = 0; i < combo.getItemCount(); i++) {
        Object item = combo.getItemAt(i);
        if (item.toString().equals(valorBuscado.toString())) {
            combo.setSelectedIndex(i);
            return;
            }
        }
   
    }
    private Sesion buscarSesionPorCodigo(int SesionSeleccionada) {
        
        listaSesiones = sesionData.obtenerSesiones();

        for (Sesion m : listaSesiones) {
            if (m.getCodSesion()== SesionSeleccionada) {
                return m;
            }
        }
        return null;
    }
    private void cargarSesionIds() {
        for (Sesion sesion : sesionData.obtenerSesiones()) {
        }
    }

    private void cargarConsultoriosIds() {
    jComboBoxConsultoriosId.removeAllItems();
    jComboBoxConsultoriosId.addItem("Seleccione el ID");

    for (Consultorio c : consultorioData.obtenerConsultorio()) {
        jComboBoxConsultoriosId.addItem(String.valueOf(c.getNroConsultorio()));
    }
}

   private void cargarTratamientosIds() {
    jComboBoxTratamientosId.removeAllItems();
    jComboBoxTratamientosId.addItem("Seleccione el ID");

    for (Tratamiento t : tratamientoData.mostrarTratamientos()) {
        jComboBoxTratamientosId.addItem(String.valueOf(t.getIdTratamiento()));
    }
}

    private void cargarInstalaciones() {
    jComboBoxInstalaciones.removeAllItems();
    jComboBoxInstalaciones.addItem("Seleccione una instalacion");

    for (Instalacion inst : instalacionData.obtenerInstalaciones()) {
        jComboBoxInstalaciones.addItem(inst.getNombre());
    }
}
    
    private void cargarMatriculas() {
    jComboBoxMatriculas.removeAllItems();
    jComboBoxMatriculas.addItem("Seleccione la matricula");

    for (Masajista m : masajistaData.obtenerMasajista()) {
        jComboBoxMatriculas.addItem(String.valueOf(m.getMatricula()));
    }
}

    private void recargarCombos() {
    cargarConsultoriosIds();
    cargarTratamientosIds();
    cargarInstalaciones();
    cargarMatriculas();
}
    private void deshabilitarCampos() {
        jComboBoxConsultoriosId.setEnabled(false);
        jComboBoxMatriculas.setEnabled(false);
        jComboBoxTratamientosId.setEnabled(false);
        jComboBoxInstalaciones.setEnabled(false);
        jDateChooserFechaInicio.setEnabled(false);
        jDateChooserFechaFin.setEnabled(false);
    }

    private void limpiezaCampos() {
        jComboBoxTratamientosId.setSelectedIndex(0);
        jComboBoxMatriculas.setSelectedIndex(0);
        jComboBoxConsultoriosId.setSelectedIndex(0);
        jComboBoxInstalaciones.setSelectedIndex(0);
        jDateChooserFechaFin.setDate(null);
        jDateChooserFechaInicio.setDate(null);
    }

    private void habilitarCampos() {
        jComboBoxConsultoriosId.setEnabled(true);
        jComboBoxMatriculas.setEnabled(true);
        jComboBoxTratamientosId.setEnabled(true);
        jComboBoxInstalaciones.setEnabled(true);
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
        jTablaSesiones.setModel(modeloTabla);
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
                (sesion.isEstado() ? "Activa" : "Inactiva")
            };
            modeloTabla.addRow(filas);
        }
    }
}
