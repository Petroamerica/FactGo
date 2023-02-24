/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafe;

import Datos.Datos;
import Logica.CellRendererEnvio;
import Logica.RestToken;
import LogicaSql.LogEnviar;
import LogicaSql.LogReporte;
import LogicaSql.Sql;
import LogicaSql.cLogControlEnvio;

import static LogicaSql.Sql.cadena_conexion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import LogicaSql.cLogControlEnvio;

/**
 *
 * @author kbarreda
 */
public class FrameEnviar extends javax.swing.JFrame {

    /*--------------------------------------------Correo-------------------------------------------------*/
    String Mensage = "";
    String Subject = "";
    String CorreoCompa�ia = "";
    String CorreoPlanta = "";

    /*---------------------------------------------------------------------------------------------*/
    public static String msj = "";
    public int contadorDocumentos;
    public int contadorDocumentosmal;
    public int contadorDocumentosGlobal;
    Date myDate = new Date();
    public static String FechaEnvioSis = "";
    public static String PublicSerieNro = "";
    public static String PublicTIPOs = "";

    /*----------------------------------VALIDACION DE ERRORES----------------------------------------*/
    public static Integer ErrorToken_StatusCode = null;
    public static String ErrorToken_ReasonPhrase = "";

    public static String DocumentBienCode = "";
    public static String DocumentBienDescription = "";

    public static String DocumentError = "";
    public static String DocumentErrorError_Description = "";

    public static Integer DocumentStatusCode = null;
    public static String DocumentoReasonPhrase = "";

    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/
    public static ArrayList<Doc> list = new ArrayList<Doc>();
    public static List<String> ListDoc = new ArrayList<>();
    public static List<Integer> ListCod = new ArrayList<>();
    public static List<String> ListDes = new ArrayList<>();
    public static List<String> ListDocERROR = new ArrayList<>();
    public static List<Integer> ListCodERROR = new ArrayList<>();
    public static List<String> ListDesERROR = new ArrayList<>();
    public static List<String> ListTipoERROR = new ArrayList<>();

    /*---------------------------------------------------------------------------------------------*/

    // variables para ControlEnviar
    public static String consultaRes, sDiferenciaDias;

    public FrameEnviar() {
        // setIconImage(new
        // ImageIcon(getClass().getResource("/imagenes/icons8_Email_Send_100px.png")).getImage());
        initComponents();
    }

    public class Doc {

        public int id;
        public String FEdoc;
        public int FEcod;
        public String FEdes;

        public Doc(int Id, String FEdoc, int FEcod, String FEdes) {
            this.id = Id;
            this.FEdoc = FEdoc;
            this.FEcod = FEcod;
            this.FEdes = FEdes;
        }
    }

    public ArrayList MetListErrores() {
        Integer IntFor = ListDoc.size();

        for (int factor = 1; factor <= IntFor; factor++) {
            Integer ds = factor - 1;
            Doc u1 = new Doc(factor, ListDoc.get(ds), ListCod.get(ds), ListDes.get(ds));
            list.add(u1);
        }
        return list;
    }

    public void NewJFrameinsertablasa() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        ArrayList<Doc> list = MetListErrores();
        Object rowData[] = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).id;
            rowData[1] = list.get(i).FEdoc;
            rowData[2] = list.get(i).FEcod;
            rowData[3] = list.get(i).FEdes;
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

    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRendererEnvio());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        Cantidadtotalbien2 = new java.awt.Label();
        Cantidadtotalbien1 = new java.awt.Label();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Cantidadtotalbien = new java.awt.Label();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(300, 130));
        setMinimumSize(new java.awt.Dimension(830, 470));
        setUndecorated(true);
        setSize(new java.awt.Dimension(830, 470));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(28, 59, 89));
        jLabel8.setText("M�dulo: Envio de documentos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, 30));

        jButton6.setBackground(new java.awt.Color(13, 56, 86));
        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Delete_Bin_48px_4.png"))); // NOI18N
        jButton6.setText(" Limpiar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setMaximumSize(new java.awt.Dimension(95, 32));
        jButton6.setMinimumSize(new java.awt.Dimension(95, 32));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 120, 30));

        jButton4.setBackground(new java.awt.Color(13, 56, 86));
        jButton4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Email_Send_50px_1.png"))); // NOI18N
        jButton4.setText("Enviar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 130, 30));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "N�", "Documento", "Cod", "Estado Envio"
                }));
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable2.getColumnModel().getColumn(1).setMinWidth(100);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(100);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(40);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 590, 370));

        jLabel9.setFont(new java.awt.Font("BrowalliaUPC", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(7, 45, 65));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Bulleted_List_48px_1.png"))); // NOI18N
        jLabel9.setText("Total :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        Cantidadtotalbien2.setBackground(new java.awt.Color(204, 204, 204));
        Cantidadtotalbien2.setFont(new java.awt.Font("Gill Sans MT", 1, 13)); // NOI18N
        Cantidadtotalbien2.setForeground(new java.awt.Color(41, 59, 103));
        jPanel1.add(Cantidadtotalbien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 60, -1));

        Cantidadtotalbien1.setBackground(new java.awt.Color(204, 204, 204));
        Cantidadtotalbien1.setFont(new java.awt.Font("Gill Sans MT", 1, 13)); // NOI18N
        Cantidadtotalbien1.setForeground(new java.awt.Color(41, 59, 103));
        jPanel1.add(Cantidadtotalbien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 60, -1));

        jLabel7.setFont(new java.awt.Font("BrowalliaUPC", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(7, 45, 65));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Ok_50px.png"))); // NOI18N
        jLabel7.setText("Total Exitosos :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, -1, -1));

        jLabel6.setFont(new java.awt.Font("BrowalliaUPC", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 45, 65));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Cancel_50px.png"))); // NOI18N
        jLabel6.setText("Total Errores :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, -1, -1));

        Cantidadtotalbien.setBackground(new java.awt.Color(204, 204, 204));
        Cantidadtotalbien.setFont(new java.awt.Font("Gill Sans MT", 1, 13)); // NOI18N
        Cantidadtotalbien.setForeground(new java.awt.Color(41, 59, 103));
        jPanel1.add(Cantidadtotalbien, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 590, 470));

        jPanel3.setBackground(new java.awt.Color(33, 63, 86));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Email_Send_100px_1.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 240, 10));

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
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 200, -1));

        jLabel3.setBackground(new java.awt.Color(16, 54, 80));
        jLabel3.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Combo_Chart_25.png"))); // NOI18N
        jLabel3.setText(" Reporte Documentos ");
        jLabel3.setToolTipText("");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, -1));

        jLabel5.setBackground(new java.awt.Color(16, 54, 80));
        jLabel5.setFont(new java.awt.Font("BrowalliaUPC", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Envelope_64px.png"))); // NOI18N
        jLabel5.setText(" Reportar Errores");
        jLabel5.setToolTipText("");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 200, -1));

        jProgressBar1.setMaximum(50);
        jPanel3.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 200, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 240, 10));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 240, 10));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MouseClicked
        FrameMenu abrir = new FrameMenu();
        abrir.setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel3MouseClicked
        FrameReporte abrir = new FrameReporte();
        abrir.setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel5MouseClicked
        generarContenidoCorreo();
    }// GEN-LAST:event_jLabel5MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6ActionPerformed
        borrar();
    }// GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        try {
            borrar();
            this.jButton4.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            EnviarDocumentos();
            this.jButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (IOException ex) {
            Logger.getLogger(FrameEnviar.class.getName()).log(Level.SEVERE, null, ex);
            this.jButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (SQLException ex) {
            Logger.getLogger(FrameEnviar.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellRender(jTable2);
        NewJFrameinsertablasa();
        this.jButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_jButton4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowOpened
        timer = new Timer(5, new progreso());
    }// GEN-LAST:event_formWindowOpened

    public static int traeCantidadArchivos() {
        int nNumArchivos = 0;

        try {
            FileReader fr = new FileReader(Datos.RutaListaDocumentos + Datos.FeUsuario + ".txt");
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                nNumArchivos++;
            }
            br.close();
        } catch (Exception ex) {
        }

        return nNumArchivos;
    }

    public static String[] traeListaArchivos() {
        int indiceArreglo = 0;
        String[] archivos_xml = new String[traeCantidadArchivos() + 1];

        try {
            FileReader fr = new FileReader(Datos.RutaListaDocumentos + Datos.FeUsuario + ".txt");
            BufferedReader br = new BufferedReader(fr);
            while ((archivos_xml[indiceArreglo] = br.readLine()) != null) {
                indiceArreglo++;
            }
            br.close();
        } catch (Exception ex) {
        }

        return archivos_xml;
    }

    // ---------------------------------------------------------------------------------------
    public void EnviarDocumentos() throws IOException, SQLException {

        int indiceArreglo = 0;
        int nNumArchivos2 = 1;
        int nNumArchivos = traeCantidadArchivos();
        String[] archivos_xml = new String[nNumArchivos + 1];
        archivos_xml = traeListaArchivos();
        String verificarDocLista = archivos_xml[indiceArreglo];
        if (verificarDocLista == null) {
            JOptionPane.showMessageDialog(null, "No existe lista de documentos", "Atenci�n!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            /*
             * Process proc = Runtime.getRuntime().
             * exec("java -jar C:\\LoadingFE\\loadingfe\\dist\\loadingfe.jar");
             */
            String VerificarRUC = verificarDocLista.substring(0, 11);
            String VerPlanta = "";

            if (verificarDocLista.length() == 33) {
                VerPlanta = verificarDocLista.substring(28, 30);
            } else if (verificarDocLista.length() == 34) {
                VerPlanta = verificarDocLista.substring(28, 31);
            } else if (verificarDocLista.length() == 38) {
                VerPlanta = verificarDocLista.substring(34, 36);
            } else if (verificarDocLista.length() == 39) {
                VerPlanta = verificarDocLista.substring(34, 37);
            } else {
                System.out.println("-------------ERROR NOMBRE ARCHIVO--------------");
            }
            contadorDocumentos = 0;
            contadorDocumentosmal = 0;
            CorreoCompa�ia = VerificarRUC;
            CorreoPlanta = VerPlanta;
            if (null == VerificarRUC) {
                RestToken.ObtenerToken("");
            } else {
                switch (VerificarRUC) {
                    case "20332711157":
                        RestToken.ObtenerToken("01");
                        break;
                    case "20100005485":
                        RestToken.ObtenerToken("02");
                        break;
                    case "20524417490":
                        RestToken.ObtenerToken("05");
                        break;
                    case "20600427734":
                        RestToken.ObtenerToken("06");
                        break;
                    case "20602359981":
                        RestToken.ObtenerToken("07");
                        break;
                    default:
                        RestToken.ObtenerToken("");
                        break;
                }
            }
            if ((Datos.Dato_Token == null) || (Datos.Dato_Token.equals(""))) {

                JOptionPane.showMessageDialog(null, "Error obtener token", "Atenci�n!", JOptionPane.ERROR_MESSAGE);

            } else {
                while (nNumArchivos2 <= nNumArchivos) {
                    String linea = archivos_xml[indiceArreglo];
                    String ruc = linea.substring(0, 11);
                    String cia = null;
                    String tipo = null;
                    if ("20600427734".equals(ruc)) {
                        cia = "06";
                        tipo = linea.substring(30, 33);
                    } else if ("20602359981".equals(ruc)) {
                        cia = "07";
                        tipo = linea.substring(31, 34);
                    } else if ("20524417490".equals(ruc)) {// TODO SEGUN ENTENDI
                        cia = "05";
                        tipo = linea.substring(30, 33);
                    }

                    if (tipo.equals("N-C")) {
                        tipo = "n/c";
                    } else if (tipo.equals("N-D")) {
                        tipo = "n/d";
                    }
                    String serie = "0" + linea.substring(16, 19);
                    String doc = linea.substring(20, 28);
                    // String ArmarConsulta = "SELECT ISNULL(FECHA_ENVIO_SUNAT, 'X') FROM
                    // DOCUMENTO\n"
                    try {
                        cLogControlEnvio.mControlEnvio(cia, VerPlanta, tipo, serie, doc);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    if (cLogControlEnvio.consultaRes.equals("ok") && cLogControlEnvio.sDiferenciaDias.equals("ok")) {
                        contadorDocumentosGlobal++;
                        // System.out.println("DOC: " + archivos_xml[indiceArreglo]);
                        // System.out.println("--------------------------------------------");
                        VerificarRUC = "";
                        String NameDoc = "";
                        String SCia = "";

                        String DatosDocLista = archivos_xml[indiceArreglo];
                        VerificarRUC = verificarDocLista.substring(0, 11);
                        switch (VerificarRUC) {
                            case "20332711157":
                                SCia = "01";
                                break;
                            case "20100005485":
                                SCia = "02";
                                break;
                            case "20524417490":
                                SCia = "05";
                                break;
                            case "20600427734":
                                SCia = "06";
                                break;
                            case "20602359981":
                                SCia = "07";
                                break;
                            default:
                                SCia = "";
                                break;
                        }

                        RestToken.ObtenerToken(SCia);

                        Integer canttexto = DatosDocLista.length();

                        switch (canttexto) {
                            case 33:
                                NameDoc = DatosDocLista.substring(0, 28);
                                PublicSerieNro = DatosDocLista.substring(15, 28);
                                PublicTIPOs = DatosDocLista.substring(30, 33);
                                break;
                            case 34:
                                NameDoc = DatosDocLista.substring(0, 28);
                                PublicSerieNro = DatosDocLista.substring(15, 28);
                                PublicTIPOs = DatosDocLista.substring(30, 33);
                                break;
                            case 38:
                                NameDoc = DatosDocLista.substring(0, 34);
                                PublicSerieNro = DatosDocLista.substring(12, 23);
                                PublicTIPOs = DatosDocLista.substring(12, 15);
                                break;
                            case 39:
                                NameDoc = DatosDocLista.substring(0, 34);
                                PublicSerieNro = DatosDocLista.substring(12, 23);
                                PublicTIPOs = DatosDocLista.substring(12, 15);
                                break;
                            default:
                                NameDoc = "vacio";
                                break;
                        }

                        EnvioDocumentos(NameDoc);
                        if (DocumentStatusCode == 200) {
                            msj = "✔ " + contadorDocumentosGlobal + " : " + "Documento enviado correctamente" + "\n"
                                    + "   " + "      Nombre Archivo: " + archivos_xml[indiceArreglo];
                            FechaEnvioSis = new SimpleDateFormat("yyyy-MM-dd h:mm:ss").format(myDate);
                            InsertTocken(DatosDocLista, DocumentBienDescription, FechaEnvioSis, SCia);
                            /* datoloading(PublicSerieNro); */
                            ListDoc.add(PublicSerieNro);
                            ListCod.add(DocumentStatusCode);
                            ListDes.add("Documento enviado correctamente");
                            contadorDocumentos++;
                        } else if (DocumentStatusCode == 412) {
                            msj = "▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃¡¡ ERROR !!▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃" + "\n" + "\n" + "✘ "
                                    + contadorDocumentosGlobal + " " + "Error al enviar el documento" + "\n" + "    "
                                    + "➥ Nombre Archivo: " + archivos_xml[indiceArreglo] + "\n" + "    "
                                    + "➥ Codigo Estado: " + DocumentBienCode + "\n" + "    " + "➥ Descripción : " + "\n"
                                    + DocumentBienDescription + "\n" + "▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃";
                            ListDoc.add(PublicSerieNro);
                            ListCod.add(DocumentStatusCode);
                            ListDes.add(DocumentBienDescription);
                            ListDocERROR.add(PublicSerieNro);
                            ListCodERROR.add(DocumentStatusCode);
                            ListDesERROR.add(DocumentBienDescription);
                            ListTipoERROR.add(PublicTIPOs);
                            contadorDocumentosmal++;
                            /* datoloading(PublicSerieNro); */
                        } else {
                            msj = "▃▃▃▃▃▃▃▃▃▃▃▃▃¡¡ ERROR INESPERADO !!▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃" + "\n" + "\n" + "✘ "
                                    + contadorDocumentosGlobal + " " + "Error al enviar el documento" + "\n" + "    "
                                    + "➥ Nombre Archivo: " + archivos_xml[indiceArreglo] + "\n" + "    "
                                    + "➥ Codigo Estado: " + DocumentError + "\n" + "    " + "➥ Descripción estado: "
                                    + "\n" + "    " + DocumentErrorError_Description + "➥ Server Codigo : "
                                    + DocumentStatusCode + "\n" + "    " + "➥ Server Descripción: " + "    "
                                    + DocumentoReasonPhrase + "\n" + "▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃";
                            ListDoc.add(PublicSerieNro);
                            ListCod.add(DocumentStatusCode);
                            ListDes.add(DocumentErrorError_Description);
                            ListDocERROR.add(PublicSerieNro);
                            ListCodERROR.add(DocumentStatusCode);
                            ListDesERROR.add(DocumentBienDescription);
                            ListTipoERROR.add(PublicTIPOs);
                            contadorDocumentosmal++;
                            /* datoloading(PublicSerieNro); */

                        }
                        // indiceArreglo++;
                        // nNumArchivos2++;
                        // jTextArea1.append(msj + "\n" + " " + "\n");
                    }

                    else if (consultaRes != "ok") {
                        ListDoc.add(linea);
                        ListCod.add(999);
                        ListDes.add("El documento ya fue enviado");
                        contadorDocumentosmal++;
                    } else if (sDiferenciaDias.equals("no")) {
                        ListDoc.add(linea);
                        ListCod.add(998);
                        ListDes.add("�ste documento excede el limite de envio");
                        contadorDocumentosmal++;
                    }
                    indiceArreglo++;
                    nNumArchivos2++;

                }
            }

            timer.start();
        }

    }

    public class progreso implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            int n = jProgressBar1.getValue();
            if (n < 50) {
                n++;
                jProgressBar1.setValue(n);
            } else {
                timer.stop();

                String contadorDocumentos1 = String.valueOf(contadorDocumentos);
                String contadorDocumentosmal1 = String.valueOf(contadorDocumentosmal);
                String contadorDocumentosglobal1 = String.valueOf(contadorDocumentosGlobal);
                if ("0".equals(contadorDocumentos1)) {
                    Cantidadtotalbien1.setText("0");
                } else {
                    Cantidadtotalbien1.setText(contadorDocumentos1);
                }
                if ("0".equals(contadorDocumentosmal1)) {
                    Cantidadtotalbien.setText("0");
                } else {
                    Cantidadtotalbien.setText(contadorDocumentosmal1);
                }
                Cantidadtotalbien2.setText(contadorDocumentosglobal1);
                JOptionPane.showMessageDialog(null, "Proceso terminado", "Aviso !", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }

    public static void EnvioDocumentos(String NombreArchivo) throws IOException {

        CloseableHttpClient httpclientDocument = HttpClients.createDefault();
        HttpPost httpPostDocument = new HttpPost(Datos.LinkDocument);
        httpPostDocument.setHeader("Authorization", "Bearer " + Datos.Dato_Token);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        byte[] xml = Files.readAllBytes(Paths.get(Datos.RutaDocumentos + NombreArchivo + ".xml"));
        builder.addBinaryBody("file", xml, ContentType.create("text/xml"), NombreArchivo + ".xml");
        // builder.addBinaryBody("file", xml);
        HttpEntity entityDocument = builder.build();
        httpPostDocument.setEntity(entityDocument);
        CloseableHttpResponse responseDocument = httpclientDocument.execute(httpPostDocument);
        ObjectMapper mapperDocument = new ObjectMapper();
        JsonNode rootNodeTokerDocument = mapperDocument.readTree(EntityUtils.toString(responseDocument.getEntity()));
        DocumentBienCode = rootNodeTokerDocument.path("code").asText();
        DocumentBienDescription = rootNodeTokerDocument.path("description").asText();
        DocumentError = rootNodeTokerDocument.path("error").asText();
        DocumentErrorError_Description = rootNodeTokerDocument.path("error_description").asText();
        DocumentStatusCode = responseDocument.getStatusLine().getStatusCode();
        DocumentoReasonPhrase = responseDocument.getStatusLine().getReasonPhrase();
        System.out.println("code :" + DocumentBienCode);
        System.out.println("description: " + DocumentBienDescription);
        System.out.println("error :" + DocumentError);
        System.out.println("error_description: " + DocumentErrorError_Description);
        System.out.println("STATUS CODE: " + DocumentStatusCode);
        System.out.println("getReasonPhrase: " + DocumentoReasonPhrase);
        System.out.println("----------------------------------------------------");

    }

    public int InsertTocken(String sListaVar, String sTicket, String fechaEnvio, String Cia) {
        String sSerie = "";
        String sNroDoc = "";
        String sPlanta = "";
        String sTipoDoc = "";
        int conexion = 0;
        try {
            switch (sListaVar.length()) {
                case 33:
                    sSerie = sListaVar.substring(16, 19);
                    sNroDoc = sListaVar.substring(20, 28);
                    sPlanta = sListaVar.substring(28, 30);
                    sTipoDoc = sListaVar.substring(30, 33);
                    break;
                case 34:
                    sSerie = sListaVar.substring(16, 19);
                    sNroDoc = sListaVar.substring(20, 28);
                    sPlanta = sListaVar.substring(28, 31);
                    sTipoDoc = sListaVar.substring(31, 34);
                    break;
                case 38:
                    break;
                case 39:
                    break;
                default:
                    System.out.println("-------------ERROR NOMBRE ARCHIVO--------------");
                    break;
            }
            if (sListaVar.length() < 36) {
                LogEnviar.InsertarTokentSQL(sTicket, Cia, sPlanta, sSerie, sNroDoc, sTipoDoc);
            } else {
                /*
                 * sListaVar=sListaVar.substring(0, 34);
                 * File archivo = new File("D:/facturacion/sunat/" + sListaVar + ".xml");
                 * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                 * DocumentBuilder documentBuilder = null;
                 * try {
                 * documentBuilder = dbf.newDocumentBuilder();
                 * } catch (ParserConfigurationException ex) {
                 * Logger.getLogger(FrameEnviar.class.getName()).log(Level.SEVERE, null, ex);
                 * }
                 * Document document = null;
                 * try {
                 * document = documentBuilder.parse(archivo);
                 * } catch (SAXException ex) {
                 * Logger.getLogger(FrameEnviar.class.getName()).log(Level.SEVERE, null, ex);
                 * } catch (IOException ex) {
                 * Logger.getLogger(FrameEnviar.class.getName()).log(Level.SEVERE, null, ex);
                 * }
                 * document.getDocumentElement().normalize();
                 * 
                 * NodeList listaEmpleados =
                 * document.getElementsByTagName("sac:VoidedDocumentsLine");
                 * Node nodo = listaEmpleados.item(0);
                 * Element element = (Element) nodo;
                 * String sTipobaja =
                 * element.getElementsByTagName("cbc:DocumentTypeCode").item(0).getTextContent()
                 * ;
                 * String sSeriebaja = "0" +
                 * element.getElementsByTagName("sac:DocumentSerialID").item(0).getTextContent()
                 * .substring(1, 4);
                 * String snrobaja =
                 * element.getElementsByTagName("sac:DocumentNumberID").item(0).getTextContent()
                 * ;
                 * System.out.println("DocumentTypeCode: " + sTipobaja);
                 * System.out.println("DocumentSerialID: " + sSeriebaja);
                 * System.out.println("DocumentNumberID: " + snrobaja);
                 * String sIdTipoDoc = null;
                 * if (null == sTipobaja) {
                 * System.out.println("---------ERROR NULL BAJA---------");
                 * } else {
                 * switch (sTipobaja) {
                 * case "01":
                 * sIdTipoDoc = "FAC";
                 * break;
                 * case "03":
                 * sIdTipoDoc = "BOL";
                 * break;
                 * case "07":
                 * sIdTipoDoc = "N/C";
                 * break;
                 * case "08":
                 * sIdTipoDoc = "N/D";
                 * break;
                 * default:
                 * break;
                 * }
                 * }
                 * LogEnviar.InsertarBajaSQL(Datos.FeCiaCod,sSeriebaja,snrobaja,sIdTipoDoc);
                 */
            }
        } catch (Exception e) {

        }
        return conexion;
    }

    /*
     * public Connection cadena_conexion() {
     * Connection cnu = null;
     * try {
     * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     * cnu = DriverManager.getConnection(
     * "jdbc:sqlserver://192.168.2.12;database=PETROAMER", "sa", "sa");
     * } catch (Exception e) {
     * }
     * return cnu;
     * }
     */

    public void SendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Datos.UsernameMail, Datos.PassWordMail);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Datos.UsernameMail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Datos.ToMail));
            message.setSubject(Subject);
            message.setText(Mensage);
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Reporte enviado Correctamente");
        } catch (MessagingException e) {
            // throw new RuntimeException(e); DESACTIVAR ESTE CODIGO PARA SABER EL ERROR
            JOptionPane.showMessageDialog(null, "Falla en enviar reporte", " Error!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void generarContenidoCorreo() {
        Mensage = "";
        Integer IntFor = ListDocERROR.size();
        if (IntFor == 0) {
            JOptionPane.showMessageDialog(null, "Sin error que reportar!", "Atenci�n!",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            FechaEnvioSis = new SimpleDateFormat("yyyy-MM-dd h:mm:ss").format(myDate);
            Subject = "Reporte Error " + CorreoCompa�ia + " " + FechaEnvioSis;
            Mensage = "Estimado, Adjunto informaci�n de errores:\n\n";

            Mensage = Mensage + "Compa�ia : " + CorreoCompa�ia + "\n" + "Planta : " + CorreoPlanta + "\n\n";
            // Mensage = Mensage + "SERIE - NRO" + " | " + "TIP" + " | " + "COD" + " | " +
            // "DESCRIPCIÓN DE ERROR" + "\n\n";

            for (int factor = 0; factor <= ListDocERROR.size() - 1; factor++) {
                Mensage = Mensage + ListDocERROR.get(factor) + " | " + ListTipoERROR.get(factor) + " | "
                        + ListCodERROR.get(factor) + " | " + ListDesERROR.get(factor) + "\n";
            }
            Mensage = Mensage + "\n\nSaludos.";
            SendMail();
        }

    }

    public void borrar() {
        Cantidadtotalbien2.setText("");
        Cantidadtotalbien1.setText("");
        Cantidadtotalbien.setText("");
        jProgressBar1.setValue(0);
        contadorDocumentosmal = 0;
        contadorDocumentos = 0;
        contadorDocumentosGlobal = 0;
        list.clear();
        ListDes.clear();
        ListCod.clear();
        ListDoc.clear();
        ListDocERROR.clear();
        ListCodERROR.clear();
        ListDesERROR.clear();
        ListTipoERROR.clear();
        Removerinsertablasa();
    }

    private Timer timer;

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameEnviar.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEnviar.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEnviar.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEnviar.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrameEnviar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label Cantidadtotalbien;
    private java.awt.Label Cantidadtotalbien1;
    private java.awt.Label Cantidadtotalbien2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
