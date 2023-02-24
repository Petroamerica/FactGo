package sistemafe;

import LogicaSql.LogAdminReporte;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FrameAdminReporte extends javax.swing.JFrame {

    public static List<String> ListIdPlantaRADMIN = new ArrayList();
    public static List<String> ListPlantaDescripRADMIN = new ArrayList();
    public static List<String> ListFechaRADMIN = new ArrayList();
    public static List<String> ListDocRADMIN = new ArrayList();
    public static List<String> ListCodRADMIN = new ArrayList();
    public static List<String> ListDesRADMIN = new ArrayList();
    public static List<String> ListTipoDoc = new ArrayList();
    public static ArrayList<Doc> listss = new ArrayList();

    public static Integer ContarAdminErrores = 0;
    public static Integer ContarAdminSinEnviar = 0;
    public static Integer ContarAdminEspera = 0;
    public static Integer ContarAdminBajas = 0;

    public static String CodCia = "";
    public String ARCia = "";
    public static String Met = "";

    public FrameAdminReporte() throws Exception {
        initComponents();
        // ACTIVARLO PARA QUE LO PUEDAN VER EN PANTALLAS PEQUEï¿½AS
        // setLocationRelativeTo(null);

        if ("recuperar".equals(Met)) {
            jLabel5.setText(Integer.toString(ContarAdminErrores));
            jLabel20.setText(Integer.toString(ContarAdminBajas));
            jLabel23.setText(Integer.toString(ContarAdminSinEnviar));
            jLabel2.setText(Integer.toString(ContarAdminEspera));

            NewJFrameinsertablasa();
            FrameAdminReporte.Met = "";

        } else if ("noenviados".equals(Met)) {
            ListIdPlantaRADMIN.clear();
            ListPlantaDescripRADMIN.clear();
            ListFechaRADMIN.clear();
            ListDocRADMIN.clear();
            ListCodRADMIN.clear();
            ListDesRADMIN.clear();
            listss.clear();
            ContarAdminSinEnviar = 0;
            LogAdminReporte.ReporteAdminReportSQL(CodCia);
            NewJFrameinsertablasa();
            jLabel5.setText(Integer.toString(ContarAdminErrores));
            jLabel20.setText(Integer.toString(ContarAdminBajas));
            jLabel23.setText(Integer.toString(ContarAdminSinEnviar));
            jLabel2.setText(Integer.toString(ContarAdminEspera));
            FrameAdminReporte.Met = "";
        }
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(300, 130));
        setMinimumSize(new java.awt.Dimension(830, 470));
        setUndecorated(true);
        setSize(new java.awt.Dimension(830, 470));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(13, 56, 86));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setBorder(null);
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(16, 54, 80));
        jLabel8.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Time_Machine_50px_1.png"))); // NOI18N
        jLabel8.setToolTipText("ESTE ES EL CODIGO QUE LO GENERA ESTE BOTON");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 60));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, 20));

        jLabel13.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("EN ESPERA");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 10, 80, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 190, 60));

        jPanel8.setBackground(new java.awt.Color(255, 0, 0));
        jPanel8.setBorder(null);
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(16, 54, 80));
        jLabel11.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_CancelREPORT.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, 20));

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ERROR");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 60, 20));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 60));

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));
        jPanel10.setBorder(null);
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(16, 54, 80));
        jLabel19.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Box_Important_50px.png"))); // NOI18N
        jLabel19.setToolTipText("");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        jLabel20.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, 20));

        jLabel21.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("BAJAS PENDIENTES");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 20));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 190, 60));

        jPanel11.setBackground(new java.awt.Color(0, 153, 51));
        jPanel11.setBorder(null);
        jPanel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(16, 54, 80));
        jLabel22.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Email_Send_50pxREPORTE.png"))); // NOI18N
        jLabel22.setToolTipText("");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        jLabel23.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, 20));

        jLabel24.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("SIN ENVIAR");
        jPanel11.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 80, 20));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 190, 60));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "-", "PetroAmerica", "DeltaGas", "AmericaGas", "Hidromundo", "PuntoGas" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 140, 30));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Compaï¿½ia  :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 80, 30));

        jButton1.setText("Generar Reporte");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 140, 30));

        jLabel4.setBackground(new java.awt.Color(16, 54, 80));
        jLabel4.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Dog_House_52px_1.png"))); // NOI18N
        jLabel4.setText(" Volver al menu ");
        jLabel4.setToolTipText("");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, -1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 130));

        jPanel2.setBackground(new java.awt.Color(13, 56, 86));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "IdPl.", "Planta Descripciï¿½n", "Tipo   Serie-Nro", "Fecha", "Cod", "Estado Documento",
                        "Tipo documento"
                }));
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(35);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(35);
            jTable2.getColumnModel().getColumn(1).setMinWidth(170);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(170);
            jTable2.getColumnModel().getColumn(2).setMinWidth(100);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable2.getColumnModel().getColumn(3).setMinWidth(75);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(75);
            jTable2.getColumnModel().getColumn(4).setMinWidth(35);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(35);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 340));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 830, 340));

        pack();
    }

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel8MouseClicked

    }

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel5MouseClicked
        if (ContarAdminEspera == 0) {
            //
        } else {
            try {
                Removerinsertablasa();
                LogAdminReporte.ReportePorEstadoSQL(CodCia, "", "Esperando");
                NewJFrameinsertablasa();
            } catch (Exception ex) {
                Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel11MouseClicked

    }

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel8MouseClicked
        if (ContarAdminErrores == 0) {

        } else {
            try {
                Removerinsertablasa();
                LogAdminReporte.ReportePorEstadoSQL(CodCia, "", "error");
                NewJFrameinsertablasa();
            } catch (Exception ex) {
                Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel19MouseClicked
        //
    }

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel10MouseClicked
        if (ContarAdminBajas == 0) {

        } else {
            try {
                Removerinsertablasa();
                LogAdminReporte.ReportePorEstadoSQL(CodCia, "", "Bajas");
                NewJFrameinsertablasa();
            } catch (Exception ex) {
                Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel22MouseClicked
        //
    }

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel11MouseClicked
        if (ContarAdminSinEnviar == 0) {

        } else {
            try {
                Removerinsertablasa();
                LogAdminReporte.ReportePorEstadoSQL(CodCia, "", "SinEnviar");
                NewJFrameinsertablasa();
            } catch (Exception ex) {
                Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MouseClicked
        FrameMenu abrir = new FrameMenu();
        abrir.setVisible(true);
        this.setVisible(false);
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        ARCia = (String) jComboBox1.getSelectedItem();
        if ("-".equals(ARCia)) {
            JOptionPane.showMessageDialog(null, "Seleccione Compaï¿½ia", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
        }
        {
            Removerinsertablasa();
            ValidarCompañia(ARCia);
            ListIdPlantaRADMIN.clear();
            ListPlantaDescripRADMIN.clear();
            ListFechaRADMIN.clear();
            ListDocRADMIN.clear();
            ListCodRADMIN.clear();
            ListDesRADMIN.clear();
            ListTipoDoc.clear();
            listss.clear();
            ContarAdminErrores = 0;
            ContarAdminSinEnviar = 0;
            ContarAdminEspera = 0;
            ContarAdminBajas = 0;
            try {
                LogAdminReporte.ReporteAdminReportSQL(CodCia);
                LogAdminReporte.ReporteCantidadBajasPendeientesSQL(CodCia);
                LogAdminReporte.ReporteCantidadErroresSQL(CodCia);
                LogAdminReporte.ReporteCantidadEsperaSQL(CodCia);
                jLabel5.setText(Integer.toString(ContarAdminErrores));
                jLabel20.setText(Integer.toString(ContarAdminBajas));
                jLabel23.setText(Integer.toString(ContarAdminSinEnviar));
                jLabel2.setText(Integer.toString(ContarAdminEspera));
            } catch (Exception ex) {
                Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
            }

            NewJFrameinsertablasa();
        }

    }

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable2MouseClicked
        /*
         * int result = JOptionPane.showConfirmDialog(null,
         * "Deseas modificar esta compaÃ±ia?", null, JOptionPane.YES_NO_OPTION);
         * if (result == JOptionPane.YES_OPTION) {
         * int index = jTable2.getSelectedRow();
         * TableModel model = jTable2.getModel();
         * DialogInsertTicket.DlCia = CodCia;
         * DialogInsertTicket.DlPlanta = model.getValueAt(index, 0).toString();
         * DialogInsertTicket.DlComprobante = model.getValueAt(index, 2).toString();
         * DialogInsertTicket.DlFecha = model.getValueAt(index, 3).toString();
         * DialogInsertTicket abrir = new DialogInsertTicket();
         * abrir.setVisible(true);
         * this.setVisible(false);
         * } else {
         * }
         */
        int index = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        DialogInsertTicket.DlCia = CodCia;
        DialogInsertTicket.DlPlanta = model.getValueAt(index, 0).toString();
        DialogInsertTicket.DlPlantaDescrip = model.getValueAt(index, 1).toString();
        DialogInsertTicket.DlComprobante = model.getValueAt(index, 2).toString();
        DialogInsertTicket.DlFecha = model.getValueAt(index, 3).toString();
        DialogInsertTicket.DlTipoDocument = model.getValueAt(index, 6).toString();
        DialogInsertTicket abrir = new DialogInsertTicket();
        abrir.setVisible(true);
        this.setVisible(false);

    }

    public class Doc {

        public String FEidPlanta;
        public String FEplantaDes;
        public String FEdoc;
        public String FEfecha;
        public String FEcod;
        public String FEcodDes;
        public String FEdocTipo;

        public Doc(String FEidPlanta, String FEplantaDes, String FEdoc, String FEfecha, String FEcod, String FEcodDes,
                String FEdocTipo) {
            this.FEidPlanta = FEidPlanta;
            this.FEplantaDes = FEplantaDes;
            this.FEdoc = FEdoc;
            this.FEfecha = FEfecha;
            this.FEcod = FEcod;
            this.FEcodDes = FEcodDes;
            this.FEdocTipo = FEdocTipo;

        }
    }

    public ArrayList MetListErrores() {
        listss.clear();// Se limpia para que la lista no aparezca dos veces.
        Integer IntFor = ListPlantaDescripRADMIN.size();
        for (int factor = 1; factor <= IntFor; factor++) {
            Integer ds = factor - 1;
            Doc u1 = new Doc(ListIdPlantaRADMIN.get(ds), ListPlantaDescripRADMIN.get(ds), ListDocRADMIN.get(ds),
                    ListFechaRADMIN.get(ds), ListCodRADMIN.get(ds), ListDesRADMIN.get(ds), ListTipoDoc.get(ds));

            listss.add(u1);
        }
        return listss;
    }

    public void NewJFrameinsertablasa() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        ArrayList<Doc> listss = MetListErrores();
        Object rowData[] = new Object[7];
        for (int i = 0; i < listss.size(); i++) {
            rowData[0] = listss.get(i).FEidPlanta;
            rowData[1] = listss.get(i).FEplantaDes;
            rowData[2] = listss.get(i).FEdoc;
            rowData[3] = listss.get(i).FEfecha;
            rowData[4] = listss.get(i).FEcod;
            rowData[5] = listss.get(i).FEcodDes;
            rowData[6] = listss.get(i).FEdocTipo;
            model.addRow(rowData);
        }

    }

    public void Removerinsertablasa() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }

    }

    public void ValidarCompañia(String cia) {
        switch (cia) {
            case "PetroAmï¿½rica":
                CodCia = "01";
                break;
            case "DeltaGas":
                CodCia = "02";
                break;
            case "Amï¿½ricaGas":
                CodCia = "05";
                break;
            case "Hidromundo":
                CodCia = "06";
                break;
            case "PuntoGas":
                CodCia = "07";
                break;
            default:

                break;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameAdminReporte.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAdminReporte.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAdminReporte.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAdminReporte.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameAdminReporte().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FrameAdminReporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable2;

}
