package SisfactPoo.dao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Datos.Datos;
import SisfactPoo.FrameMigraXml;
import SisfactPoo.LogMigraXml;
import SisfactPoo.beans.MigraDocumentoCompra;
import SisfactPoo.beans.MigraDocumentoCompraDet;

public class XmlEnergigas_NC {
	private static Document document;
	public static void ValidadXmlEnergigas_NC(String FileXml) {
		try {
			File archivoDatos = new File(FileXml);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(archivoDatos);
            document.getDocumentElement().normalize();
            MigraDocumentoCompra doc = new MigraDocumentoCompra();
            MigraDocumentoCompraDet docDet = new MigraDocumentoCompraDet();
            Date myDate = new Date();
            
            String sFechavence = "";
            doc.setID_TIPO_DOC("'N/C'");
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = parseador.parse(BuscarDatoXml("CreditNote", "cbc:IssueDate", 0, 0));
            } catch (ParseException ex) {
                Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
            }
            sFechavence = formateador.format(date);
            Date fechaInicial = null;
            Date fechaFinal = null;
            try {
                fechaInicial = formateador.parse(BuscarDatoXml("CreditNote", "cbc:IssueDate", 0, 0));
                fechaFinal = formateador.parse(sFechavence);
            } catch (ParseException ex) {
                Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

            if (("PEN").equals(BuscarDatoXml("CreditNote", "cbc:DocumentCurrencyCode", 0, 0))) {
                doc.setID_MONEDA_DOC("'" + "01" + "'");
                doc.setID_MONEDA_LOCAL("'" + "01" + "'");
                doc.setID_MONEDA_EXT("'" + "02" + "'");
            } else {
                doc.setID_MONEDA_DOC("'" + "02" + "'");
                doc.setID_MONEDA_LOCAL("'" + "01" + "'");
                doc.setID_MONEDA_EXT("'" + "02" + "'");
            }
            
            doc.setCIA("'" + Datos.FeCiaCod + "'");
            
            doc.setID_PROVEEDOR("'" + BuscarDatoXml("cac:PartyIdentification", "cbc:ID", 0, 0) + "'");
            
            doc.setSERIE_DOC("'00"+BuscarDatoXml("CreditNote", "cbc:ID", 0, 0).substring(2, 4)+"'");
            
            String serieReferencia = "";
            int ncod = Integer.valueOf(BuscarDatoXml("cac:BillingReference", "cbc:ID", 0, 0).substring(1, 4));
            //System.out.println(aqweqwe);
            if (String.valueOf(ncod).length() == 2) {
                serieReferencia = "'00" + ncod + "'";
            } else {
                serieReferencia = "'0" + ncod + "'";
            }
            
            String SerieDOC = "";
            String NuevaSerie = BuscarDatoXml("CreditNote", "cbc:ID", 0, 0).substring(0, 4);
            SerieDOC = "'"+NuevaSerie+"'";
            
            String tipo_cambio = LogMigraXml.consultarTipoCambio(BuscarDatoXml("CreditNote", "cbc:IssueDate", 0, 0));
            
            doc.setNRO_DOC("'" + BuscarDatoXml("CreditNote", "cbc:ID", 0, 0).substring(5, 13) + "'");
            doc.setID_CONDICION_PAGO("'" + "00" + "'");
            doc.setFECHA("'" + BuscarDatoXml("CreditNote", "cbc:IssueDate", 0, 0) + "'");
            doc.setFECHA_VENCIMIENTO("'" + BuscarDatoXml("CreditNote", "cbc:IssueDate", 0, 0) + "'");
            doc.setTIPO_CAMBIO("'" + tipo_cambio + "'");
            doc.setFACTOR_CONV_DOC_LOCAL("NULL");
            doc.setFACTOR_CONV_DOC_EXT("NULL");
            doc.setFACTOR_CONV_LOCAL_DOC("NULL");
            doc.setFACTOR_CONV_LOCAL_EXT("NULL");
            doc.setTOTAL_PRODUCTO_ANTES_IMP("'" + BuscarDatoXml("cac:LegalMonetaryTotal", "cbc:LineExtensionAmount", 0, 0) + "'");
            doc.setSUBTOTAL("'" + BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 0, 0) + "'");
            doc.setIMPUESTO("'" + BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxAmount", 0, 0) + "'");
            doc.setTOTAL("'" + BuscarDatoXml("cac:LegalMonetaryTotal", "cbc:PayableAmount", 0, 0) + "'");
            doc.setID_MOTIVO("'" + "01" + "'");
            doc.setOBSERVACION("'" + BuscarDatoXml("cac:DiscrepancyResponse", "cbc:Description", 0, 0) + "'");
            doc.setFLAG_AFECTO_IGV("'" + "1" + "'");
            doc.setID_ESTADO("'" + "01" + "'");
            doc.setFECHA_ENVIO("NULL");
            doc.setUSUARIO_ENVIO("NULL");
            doc.setFECHA_SISTEMA("'" + new SimpleDateFormat("yyyy-MM-dd h:mm:ss").format(myDate) + "'");
            doc.setUSUARIO_SISTEMA("'" + Datos.FeUsuario.toUpperCase() + "'");
            doc.setFECHA_MOD("NULL");
            doc.setUSUARIO_MOD("NULL");
            doc.setFECHA_ANULA("NULL");
            doc.setUSUARIO_ANULA("NULL");
            doc.setVALOR_NO_AFECTO("NULL");
            doc.setOTROS_IMPUESTOS("'" + "0.00" + "'");
            doc.setVALOR_VENTA("'" + BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 0, 0) + "'");
            doc.setFISE("'" + "0.00" + "'");
            doc.setISC("'" + "0.00" + "'");
            doc.setRODAJE("'" + "0.00" + "'");
            doc.setSUBTOTAL_CON_FISE("'" + BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount",0, 0) + "'");
            doc.setMONTO_PERCEPCION("'" + "0.00" + "'");
            doc.setTOTAL_A_PAGAR("'" + BuscarDatoXml("cac:LegalMonetaryTotal", "cbc:PayableAmount", 0, 0) + "'");
            doc.setPLACA_TRACTOR("NULL");
            doc.setPLACA_CISTERNA("NULL");
            doc.setSERIE_PER("NULL");
            doc.setNRO_DOC_PER("NULL");
            FrameMigraXml.sFechaSistema = doc.getFECHA_SISTEMA();
            doc.setNRO_SCOP("NULL");
            String ID_MOTIVO_NOTA = "'" + "03" + "'";
            doc.setID_PLANTA("'01'");
            String respuestacompraN = LogMigraXml.consultarCompra(Datos.FeCiaCod, BuscarDatoXml("cac:PartyIdentification", "cbc:ID", 0, 0),BuscarDatoXml("cac:BillingReference", "cbc:ID", 0, 0).substring(0, 4),ObtenerNro(BuscarDatoXml("cac:BillingReference", "cbc:ID", 0, 0)));
            System.out.print(respuestacompraN);
            if ("vacio".equals(respuestacompraN)) {
                doc.setID_PLANTA("NULL");
            } else {
                doc.setID_PLANTA("'" + respuestacompraN + "'");
            }
            if ("NULL".equals(doc.getID_PLANTA())) {
                FrameMigraXml.ListDoc.add("F" + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                FrameMigraXml.ListCod.add("Error");
                FrameMigraXml.ListDes.add("No se encontró la factura de ref. (Ruta " + FileXml + ")");
            } else {
            	 String querydoc = "INSERT INTO DOCUMENTO_COMPRA(CIA, ID_PLANTA, ID_PROVEEDOR, ID_TIPO_DOC, SERIE_DOC, NRO_DOC, ID_CONDICION_PAGO, FECHA, FECHA_VENCIMIENTO, ID_MONEDA_DOC" 
         		 +",ID_MONEDA_LOCAL, ID_MONEDA_EXT, TIPO_CAMBIO, FACTOR_CONV_DOC_LOCAL, FACTOR_CONV_DOC_EXT, FACTOR_CONV_LOCAL_DOC, FACTOR_CONV_LOCAL_EXT, TOTAL_PRODUCTO_ANTES_IMP" 
         		 +",SUBTOTAL, IMPUESTO, TOTAL, ID_MOTIVO, OBSERVACION, FLAG_AFECTO_IGV, ID_ESTADO, FECHA_ENVIO, USUARIO_ENVIO, FECHA_SISTEMA, USUARIO_SISTEMA, FECHA_MOD, USUARIO_MOD, FECHA_ANULA" 
         		 +",USUARIO_ANULA, VALOR_NO_AFECTO, OTROS_IMPUESTOS, VALOR_VENTA, FISE, ISC, RODAJE, SUBTOTAL_CON_FISE, MONTO_PERCEPCION, TOTAL_A_PAGAR, PLACA_TRACTOR, PLACA_CISTERNA, SERIE_PER" 
         		 +",NRO_DOC_PER, NRO_SCOP, SALDO_DOC, ID_MOTIVO_NOTA) VALUES (" + doc.getCIA() + "," + doc.getID_PLANTA() + "," + doc.getID_PROVEEDOR() + "," + doc.getID_TIPO_DOC() + "," + SerieDOC + "," + doc.getNRO_DOC() + "," + doc.getID_CONDICION_PAGO() + ","
                 + doc.getFECHA() + "," + doc.getFECHA_VENCIMIENTO() + "," + doc.getID_MONEDA_DOC() + "," + doc.getID_MONEDA_LOCAL() + "," + doc.getID_MONEDA_EXT() + "," + doc.getTIPO_CAMBIO() + ","
                 + doc.getFACTOR_CONV_DOC_LOCAL() + "," + doc.getFACTOR_CONV_DOC_EXT() + "," + doc.getFACTOR_CONV_LOCAL_DOC() + "," + doc.getFACTOR_CONV_LOCAL_EXT() + "," + doc.getTOTAL_PRODUCTO_ANTES_IMP() + ","
                 + doc.getSUBTOTAL() + "," + doc.getIMPUESTO() + "," + doc.getTOTAL() + "," + doc.getID_MOTIVO() + "," + doc.getOBSERVACION() + "," + doc.getFLAG_AFECTO_IGV() + "," + doc.getID_ESTADO() + ","
                 + doc.getFECHA_ENVIO() + "," + doc.getUSUARIO_ENVIO() + "," + doc.getFECHA_SISTEMA() + "," + doc.getUSUARIO_SISTEMA() + "," + doc.getFECHA_MOD() + "," + doc.getUSUARIO_MOD() + ","
                 + doc.getFECHA_ANULA() + "," + doc.getUSUARIO_ANULA() + "," + doc.getVALOR_NO_AFECTO() + "," + doc.getOTROS_IMPUESTOS() + "," + doc.getVALOR_VENTA() + "," + doc.getFISE() + ","
                 + doc.getISC() + "," + doc.getRODAJE() + "," + doc.getSUBTOTAL_CON_FISE() + "," + doc.getMONTO_PERCEPCION() + "," + doc.getTOTAL_A_PAGAR() + "," + doc.getPLACA_TRACTOR() + ","
                 + doc.getPLACA_CISTERNA() + "," + doc.getSERIE_PER() + "," + doc.getNRO_DOC_PER() + "," + doc.getNRO_SCOP() + ",NULL, '03'" + ");";
            	 if (LogMigraXml.InsertBdXml(querydoc) == true) {
                     FrameMigraXml.ListDoc.add("F" + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                     FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                     FrameMigraXml.ListCod.add("Migrado");
                     FrameMigraXml.ListDes.add(FileXml);
                     Integer contador = 1;
                     NodeList listaEmpleados = document.getElementsByTagName("cac:CreditNoteLine");
                     int contar = listaEmpleados.getLength();
                     for (Integer NroItem = 0; NroItem < contar; NroItem++) {
                         docDet.setCIA(doc.getCIA());
                         docDet.setID_PLANTA(doc.getID_PLANTA());
                         //docDet.setID_PROVEEDOR("'" + BuscarDatoXml("cac:PartyIdentification", "cbc:ID", NroItem, 0) + "'");
                         docDet.setID_PROVEEDOR(doc.getID_PROVEEDOR());
                         docDet.setID_TIPO_DOC(doc.getID_TIPO_DOC());
                         docDet.setSERIE_DOC(doc.getSERIE_DOC());
                         docDet.setNRO_DOC(doc.getNRO_DOC());
                         docDet.setITEM("'" + String.valueOf(contador++) + "'");
                         docDet.setFLAG_ARTI_OTROS("NULL");
                         docDet.setARTICULO_OTROS("NULL");
                         String codigo = BuscarDatoXml("cac:Item", "cbc:ID", NroItem, 0);
                         switch (codigo) {
                         case "11620305":
                             docDet.setID_ARTICULO("'015'");/*GASOHOL 95*/
                             break;
                         case "11620302":
                         	docDet.setID_ARTICULO("'017'");/*GASOHOL 90*/
                         	break;
                         case "11620303":
                         	docDet.setID_ARTICULO("'018'");/*GASOHOL 97*/
                         	break;	
                         case "11620304":
                             docDet.setID_ARTICULO("'022'");/*DIESEL B5 S-50 UV*/
                             break;
                         default:
                             docDet.setID_ARTICULO("'009'");/*kerosene*/

                     }
                         System.out.print(codigo);
                         //docDet.setID_ARTICULO("'" + "022" + "'");
                         docDet.setCANTIDAD("'" + BuscarDatoXml("cac:CreditNoteLine", "cbc:CreditedQuantity", NroItem, 0) + "'");
                         docDet.setPRECIO("'" + BuscarDatoXml("cac:Price", "cbc:PriceAmount", NroItem, 0) + "'");
                         docDet.setIMP_RODAJE("NULL");
                         docDet.setISC("NULL");
                         docDet.setTOTAL_DET("'" + BuscarDatoXml("cac:CreditNoteLine", "cbc:LineExtensionAmount", NroItem, 0) + "'");
                         docDet.setID_ESTADO("'" + "01" + "'");
                         docDet.setFECHA_ENVIO("NULL");
                         docDet.setUSUARIO_ENVIO("NULL");
                         docDet.setFECHA_SISTEMA(doc.getFECHA_SISTEMA());
                         docDet.setUSUARIO_SISTEMA("'" + Datos.FeUsuario.toUpperCase() + "'");
                         docDet.setFECHA_MOD("NULL");
                         docDet.setUSUARIO_MOD("NULL");
                         docDet.setFECHA_ANULA("NULL");
                         docDet.setUSUARIO_ANULA("NULL");
                         docDet.setFACTOR_COMPENSACION("NULL");
                         docDet.setID_UNIDAD_COMPRA("'" + "GL" + "'");
                         docDet.setPRECIO_CON_IGV("NULL");
                         docDet.setOTROS_PRECIO_U("NULL");
                         docDet.setSUBTOTAL_PRECIO_U("'" + BuscarDatoXml("cac:CreditNoteLine", "cbc:LineExtensionAmount", NroItem, 0) + "'");
                         docDet.setFISE_U("NULL");
                         docDet.setISC_U("NULL");
                         docDet.setRODAJE_U("NULL");
                         docDet.setTOTAL_U("'" + BuscarDatoXml("cac:CreditNoteLine", "cbc:LineExtensionAmount", NroItem, 0) + "'");
                         docDet.setTOTAL_DET_CON_FISE("'" + BuscarDatoXml("cac:CreditNoteLine", "cbc:LineExtensionAmount", NroItem, 0) + "'");
                         docDet.setGRAVEDAD_ESPECIFICA("NULL");
                         String querydocdet = "INSERT INTO DOCUMENTO_COMPRA_DET(CIA, ID_PLANTA, ID_PROVEEDOR, ID_TIPO_DOC, SERIE_DOC, NRO_DOC, ITEM, FLAG_ARTI_OTROS, ARTICULO_OTROS, ID_ARTICULO, CANTIDAD" 
                         		+ ", PRECIO, IMP_RODAJE, ISC, TOTAL_DET, ID_ESTADO, FECHA_ENVIO, USUARIO_ENVIO, FECHA_SISTEMA, USUARIO_SISTEMA, FECHA_MOD, USUARIO_MOD, FECHA_ANULA, USUARIO_ANULA, FACTOR_COMPENSACION" 
                         		+ ", ID_UNIDAD_COMPRA, PRECIO_CON_IGV, OTROS_PRECIO_U, SUBTOTAL_PRECIO_U, FISE_U, ISC_U, RODAJE_U, TOTAL_U, TOTAL_DET_CON_FISE, GRAVEDAD_ESPECIFICA) VALUES(" + docDet.getCIA() + "," + docDet.getID_PLANTA() + "," + docDet.getID_PROVEEDOR() + "," + docDet.getID_TIPO_DOC() + "," + SerieDOC + "," + docDet.getNRO_DOC() + ","
                                 + docDet.getITEM() + "," + docDet.getFLAG_ARTI_OTROS() + "," + docDet.getARTICULO_OTROS() + "," + docDet.getID_ARTICULO() + "," + docDet.getCANTIDAD() + "," + docDet.getPRECIO() + ","
                                 + docDet.getIMP_RODAJE() + "," + docDet.getISC() + "," + docDet.getTOTAL_DET() + "," + docDet.getID_ESTADO() + "," + docDet.getFECHA_ENVIO() + "," + docDet.getUSUARIO_ENVIO() + ","
                                 + docDet.getFECHA_SISTEMA() + "," + docDet.getUSUARIO_SISTEMA() + "," + docDet.getFECHA_MOD() + "," + docDet.getUSUARIO_MOD() + "," + docDet.getFECHA_ANULA() + "," + docDet.getUSUARIO_ANULA() + ","
                                 + docDet.getFACTOR_COMPENSACION() + "," + docDet.getID_UNIDAD_COMPRA() + "," + docDet.getPRECIO_CON_IGV() + "," + docDet.getOTROS_PRECIO_U() + "," + docDet.getSUBTOTAL_PRECIO_U() + ","
                                 + docDet.getFISE_U() + "," + docDet.getISC_U() + "," + docDet.getRODAJE_U() + "," + docDet.getTOTAL_U() + "," + docDet.getTOTAL_DET_CON_FISE() + "," + docDet.getGRAVEDAD_ESPECIFICA() + ");";
                         /*System.out.println("INSERT INTO DOCUMENTO_COMPRA_DET VALUES(" + docDet.getCIA() + "," + docDet.getID_PLANTA() + "," + docDet.getID_PROVEEDOR() + "," + docDet.getID_TIPO_DOC() + "," + docDet.getSERIE_DOC() + "," + docDet.getNRO_DOC() + ","
                     +docDet.getITEM() + "," + docDet.getFLAG_ARTI_OTROS() + "," + docDet.getARTICULO_OTROS() + "," + docDet.getID_ARTICULO() + "," + docDet.getCANTIDAD() + "," + docDet.getPRECIO() + ","
                             + docDet.getIMP_RODAJE() + "," + docDet.getISC() + "," + docDet.getTOTAL_DET() + "," + docDet.getID_ESTADO() + "," + docDet.getFECHA_ENVIO() + "," + docDet.getUSUARIO_ENVIO() + ","
                             + docDet.getFECHA_SISTEMA() + "," + docDet.getUSUARIO_SISTEMA() + "," + docDet.getFECHA_MOD() + "," + docDet.getUSUARIO_MOD() + "," + docDet.getFECHA_ANULA() + "," + docDet.getUSUARIO_ANULA() + ","
                             + docDet.getFACTOR_COMPENSACION() + "," + docDet.getID_UNIDAD_COMPRA() + "," + docDet.getPRECIO_CON_IGV() + "," + docDet.getOTROS_PRECIO_U() + "," + docDet.getSUBTOTAL_PRECIO_U() + ","
                             + docDet.getFISE_U() + "," + docDet.getISC_U() + "," + docDet.getRODAJE_U() + "," + docDet.getTOTAL_U() + "," + docDet.getTOTAL_DET_CON_FISE() + "," + docDet.getGRAVEDAD_ESPECIFICA() + ");"
                     );*/
                         //System.out.println(querydocdet);
                         LogMigraXml.InsertBdXml(querydocdet);

                     }
                 } else {
                     FrameMigraXml.ListDoc.add("F" + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                     FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                     //FrameMigraXml.ListCod.add("Error");
                     FrameMigraXml.ListDes.add("La migración no se realizó (Ruta " + FileXml + ")");
                 }
            }
            
		 } catch (ParserConfigurationException ex) {
	            Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SAXException ex) {
	            Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	            Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	private static String BuscarDatoXml(String Contenedor, String Detalle, int posicionCont, int posicionDeta) {
        NodeList listaEmpleados = document.getElementsByTagName(Contenedor);
        Node nodo = listaEmpleados.item(posicionCont);
        Element element = (Element) nodo;
        return element.getElementsByTagName(Detalle).item(posicionDeta).getTextContent();
    }
	
    private static String ObtenerNro(String Dato) {
        Integer cuenta = Dato.substring(4, Dato.length()).length();
        System.out.print(cuenta);
        String Nro = "";
        switch (cuenta) {
            case 1:
                Nro = "00000000" + Dato.substring(5, Dato.length());
                break;
            case 2:
                Nro = "0000000" + Dato.substring(5, Dato.length());
                break;
            case 3:
                Nro = "000000" + Dato.substring(5, Dato.length());
                break;
            case 4:
                Nro = "00000" + Dato.substring(5, Dato.length());
                break;
            case 5:
                Nro = "0000" + Dato.substring(5, Dato.length());
                break;
            case 6:
                Nro = "000" + Dato.substring(5, Dato.length());
                break;
            case 7:
                Nro = "00" + Dato.substring(5, Dato.length());
                break;
            case 8:
                Nro = Dato.substring(5, Dato.length());
                break;
            default:
        }
        return Nro;
    }
	
}
