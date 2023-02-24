package SisfactPoo;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;

import SisfactPoo.dao.XMLPluspetrol;
import SisfactPoo.dao.XmlEnergigas;
import SisfactPoo.dao.XmlEnergigas_NC;
import SisfactPoo.dao.XmlNumay;
import SisfactPoo.dao.XmlPhoeninca;
import SisfactPoo.dao.XmlRepsol;
import SisfactPoo.dao.XmlRepsolNC;
import SisfactPoo.dao.XmlValero;

import java.io.File;
import java.sql.SQLException;
import Datos.Datos;
/**
 *
 * @author kbarreda
 */
public class LogMigraXml {

    public static void MigraXml(File RutaArchivo) {
        String sArchivo = RutaArchivo + "";
        if (RutaArchivo.getName().length() >= 11) {
            switch (RutaArchivo.getName().substring(0, 11)) {
                case "20503840121":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01") || (RutaArchivo.getName().substring(12, 13)).equals("1") ) {
                    	XmlRepsol.ValidadXmlRepsol(sArchivo);
                    } else if ((RutaArchivo.getName().substring(12, 14)).equals("07")) {
                        XmlRepsolNC.ValidadXmlRepsol(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no válido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20601147450":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01")) {
                        XmlPhoeninca.ValidadXmlPhoeninca(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20553167672":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01")) {
                        XmlNumay.ValidarXmlNumay(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20565643496":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01")) {
                        XmlNumay.ValidarXmlNumay(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20304177552":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("03")) {
                        XMLPluspetrol.ValidadXmlPluspetrol(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20506151547":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01")) {
                    	XmlEnergigas.ValidadXmlEnergigas(sArchivo);
                    } else if ((RutaArchivo.getName().substring(12, 14)).equals("07")) {
                    	XmlEnergigas_NC.ValidadXmlEnergigas_NC(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                case "20513251506":
                    if ((RutaArchivo.getName().substring(12, 14)).equals("01")) {
                    	XmlValero.ValidadXmlPluspetrol(sArchivo);
                    } else {
                        FrameMigraXml.ListDoc.add("Xml no valido");
                        FrameMigraXml.ListFecha.add("-");
                        FrameMigraXml.ListCod.add("Error");
                        FrameMigraXml.ListDes.add("No se tiene información del archivo: " + sArchivo);
                    }
                    break;
                default:
                    FrameMigraXml.ListDoc.add("Xml no válido");
                    FrameMigraXml.ListFecha.add("-");
                    FrameMigraXml.ListCod.add("Error");
                    FrameMigraXml.ListDes.add(sArchivo);
                //System.out.println("file default(" + RutaArchivo + ")" + "(" + RutaArchivo.getName().substring(0, 11) + ")");

            }
        } else {
            FrameMigraXml.ListDoc.add("Xml no válido");
            FrameMigraXml.ListFecha.add("-");
            FrameMigraXml.ListCod.add("Error");
            FrameMigraXml.ListDes.add(sArchivo);
        }

    }

    public static boolean InsertBdXml(String Consulta) {
        boolean insert = false;
        try {
            Sql.SQLpss = cadena_conexion().prepareStatement(Consulta);
            Sql.SQLpss.executeUpdate();
            //Sql.SQLrss.close();
            Sql.SQLcnu.close();
            Sql.SQLpss.close();
            insert = true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 2627) {
                FrameMigraXml.ListCod.add("Duplicado");
            } else {
                FrameMigraXml.ListCod.add("Error");
                System.out.println(Consulta);
                System.out.println("");
                System.out.println("ERROR-CODE-:" + ex.getErrorCode());
                System.out.println("");
                System.out.println("SQL-STATE-:" + ex.getSQLState());
                System.out.println("");
                System.out.println("MENSAJE-:" + ex.getMessage());
                System.out.println("");
                System.out.println("TOSTRING-:" + ex.toString());
            }
            //Logger.getLogger(LogMigraXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insert;
    }

    public static String consultarCompra(String cia,String idProvredor,String serie_doc,String Nro_doc) {
        System.out.println(cia+"---"+idProvredor+"---"+serie_doc+"---"+Nro_doc);
        String respuesta="vacio";
        try {
            String Consulta="select ID_PLANTA from DOCUMENTO_COMPRA where cia='"+cia+"' and ID_PROVEEDOR='"+idProvredor+"'\n" +
" and ID_TIPO_DOC='fac' and SERIE_DOC='"+serie_doc+"' and NRO_DOC='"+Nro_doc+"'";
            Sql.SQLpss = cadena_conexion().prepareStatement(Consulta);
            Sql.SQLrss = Sql.SQLpss.executeQuery();
            Sql.SQLrsm = Sql.SQLrss.getMetaData();
         
            while (Sql.SQLrss.next()) {
                respuesta=Sql.SQLrss.getString(1);
            }
            Sql.SQLcnu.close();
            Sql.SQLpss.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return respuesta;
    }

    public static String consultarTipoCambio(String fecha) {
    	String respuesta="";
    	try {
            String Consulta="SELECT top 1 FACTOR_DESTINO_VENTA  FROM TIPO_CAMBIO  WHERE   CIA = '06' AND ID_ESTADO = '01' and FECHA = '"+fecha+"' ORDER BY FECHA DESC";
            Sql.SQLpss = cadena_conexion().prepareStatement(Consulta);
            Sql.SQLrss = Sql.SQLpss.executeQuery();
            Sql.SQLrsm = Sql.SQLrss.getMetaData();
         
            while (Sql.SQLrss.next()) {
                respuesta=Sql.SQLrss.getString(1);
            }
            Sql.SQLcnu.close();
            Sql.SQLpss.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    	
    	return respuesta;
    }
    
}
