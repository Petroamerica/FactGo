package SisfactPoo.dao;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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

public class XmlValero {
	
	private static Document document;

    public static void ValidadXmlPluspetrol(String FileXml) {
    	try {
    		
    		System.out.println(FileXml);
            File archivoDatos = new File(FileXml);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(archivoDatos);
            document.getDocumentElement(). normalize();
            MigraDocumentoCompra doc = new MigraDocumentoCompra();
            MigraDocumentoCompraDet docDet = new MigraDocumentoCompraDet();
            	Date myDate = new Date();
            	doc.setID_TIPO_DOC("'FAC'");
            	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            	Date fechaInicial = null;
                Date fechaFinal = null;
                try {
                    fechaInicial = formateador.parse(BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0));
                    fechaFinal = formateador.parse(BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0));
                } catch (ParseException ex) {
                    Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
                }
                String tipo_cambio = LogMigraXml.consultarTipoCambio(BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0));
                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
                if (("PEN").equals(BuscarDatoXml("Invoice", "cbc:DocumentCurrencyCode", 0, 0))) {
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
                String aqweqwe = BuscarDatoXml("Invoice", "cbc:ID", 0, 0).substring(1, 4);
                doc.setSERIE_DOC("'" + aqweqwe + "'");
                doc.setID_PLANTA("'15'");
                String SerieDOC = "";
                String NuevaSerie = BuscarDatoXml("Invoice", "cbc:ID", 0, 0).substring(0, 4);
                SerieDOC = "'"+NuevaSerie+"'";
                doc.setNRO_DOC("'" + BuscarDatoXml("Invoice", "cbc:ID", 0, 0).substring(5, 13) + "'");
                doc.setID_CONDICION_PAGO("'00'");
                doc.setFECHA("'" + BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0) + "'");
                doc.setFECHA_VENCIMIENTO("'" + BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0) + "'");
                doc.setTIPO_CAMBIO("'" + tipo_cambio + "'");
                doc.setFACTOR_CONV_DOC_LOCAL("NULL");
                doc.setFACTOR_CONV_DOC_EXT("NULL");
                doc.setFACTOR_CONV_LOCAL_DOC("NULL");
                doc.setFACTOR_CONV_LOCAL_EXT("NULL");
                
                doc.setIMPUESTO("'" + BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxAmount", 0, 0).trim() + "'");
                doc.setTOTAL("'" + BuscarDatoXml("cac:LegalMonetaryTotal", "cbc:PayableAmount", 0, 0).trim() + "'");
                doc.setID_MOTIVO("'" + "01" + "'");
                doc.setOBSERVACION("NULL");
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
                
                doc.setFISE("'"+BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 1, 0).trim()+"'");
                String isc = "";
                if(BuscarDatoXml("cac:TaxTotal", "cac:TaxSubtotal", 0, 2) == null) {
                	isc = "0";
                	doc.setISC("'"+"0.00" + "'");
                }else {
                	isc = BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxAmount", 2, 0);
                	doc.setISC("'"+BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxAmount", 2, 0)+"'");                	
                }
                
                doc.setOTROS_IMPUESTOS("'"+BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 1, 0)+"'");
                
                Double VentaTotal = Double.parseDouble(BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 0, 0)) + Double.parseDouble(isc); 
                doc.setTOTAL_PRODUCTO_ANTES_IMP("'" + VentaTotal + "'");
                doc.setSUBTOTAL("'" + VentaTotal+ "'");
                doc.setVALOR_VENTA("'"+VentaTotal+"'");
                
                doc.setRODAJE("'"+"0.00" + "'");
                
                Double SubTotal_fise = Double.parseDouble(BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 0, 0)) + Double.parseDouble(BuscarDatoXml("cac:TaxSubtotal", "cbc:TaxableAmount", 1, 0)) + Double.parseDouble(isc);
                doc.setSUBTOTAL_CON_FISE("'" + SubTotal_fise +"'");
                doc.setMONTO_PERCEPCION("'" + "0.00" + "'");
                doc.setTOTAL_A_PAGAR("'" + BuscarDatoXml("cac:LegalMonetaryTotal", "cbc:PayableAmount", 0, 0).trim() + "'");
                doc.setPLACA_TRACTOR("NULL");
                doc.setPLACA_CISTERNA("NULL");
                doc.setSERIE_PER("NULL");
                doc.setNRO_DOC_PER("NULL");
                doc.setNRO_SCOP("NULL");
                String scop = BuscarDatoXml("Invoice", "cbc:Note", 0, 9).trim();
                doc.setNRO_SCOP_VENTA("'"+scop.substring(6, scop.length())+"'");
                
                FrameMigraXml.sFechaSistema = doc.getFECHA_SISTEMA();
                String querydoc = "INSERT INTO DOCUMENTO_COMPRA(CIA, ID_PLANTA, ID_PROVEEDOR, ID_TIPO_DOC, SERIE_DOC, NRO_DOC, ID_CONDICION_PAGO, FECHA, FECHA_VENCIMIENTO, ID_MONEDA_DOC" 
                		+",ID_MONEDA_LOCAL, ID_MONEDA_EXT, TIPO_CAMBIO, FACTOR_CONV_DOC_LOCAL, FACTOR_CONV_DOC_EXT, FACTOR_CONV_LOCAL_DOC, FACTOR_CONV_LOCAL_EXT, TOTAL_PRODUCTO_ANTES_IMP" 
                		+",SUBTOTAL, IMPUESTO, TOTAL, ID_MOTIVO, OBSERVACION, FLAG_AFECTO_IGV, ID_ESTADO, FECHA_ENVIO, USUARIO_ENVIO, FECHA_SISTEMA, USUARIO_SISTEMA, FECHA_MOD, USUARIO_MOD, FECHA_ANULA" 
                		+",USUARIO_ANULA, VALOR_NO_AFECTO, OTROS_IMPUESTOS, VALOR_VENTA, FISE, ISC, RODAJE, SUBTOTAL_CON_FISE, MONTO_PERCEPCION, TOTAL_A_PAGAR, PLACA_TRACTOR, PLACA_CISTERNA, SERIE_PER" 
                		+",NRO_DOC_PER, NRO_SCOP, FLAG_ES_DEBITO, SALDO_DOC, NRO_SCOP_VENTA) VALUES (" + doc.getCIA() + "," + doc.getID_PLANTA() + "," + doc.getID_PROVEEDOR() + "," + doc.getID_TIPO_DOC() + "," + SerieDOC + "," + doc.getNRO_DOC() + "," + doc.getID_CONDICION_PAGO() + ","
                        + doc.getFECHA() + "," + doc.getFECHA_VENCIMIENTO() + "," + doc.getID_MONEDA_DOC() + "," + doc.getID_MONEDA_LOCAL() + "," + doc.getID_MONEDA_EXT() + "," + doc.getTIPO_CAMBIO() + ","
                        + doc.getFACTOR_CONV_DOC_LOCAL() + "," + doc.getFACTOR_CONV_DOC_EXT() + "," + doc.getFACTOR_CONV_LOCAL_DOC() + "," + doc.getFACTOR_CONV_LOCAL_EXT() + "," + doc.getTOTAL_PRODUCTO_ANTES_IMP() + ","
                        + doc.getSUBTOTAL() + "," + doc.getIMPUESTO() + "," + doc.getTOTAL() + "," + doc.getID_MOTIVO() + "," + doc.getOBSERVACION() + "," + doc.getFLAG_AFECTO_IGV() + "," + doc.getID_ESTADO() + ","
                        + doc.getFECHA_ENVIO() + "," + doc.getUSUARIO_ENVIO() + "," + doc.getFECHA_SISTEMA() + "," + doc.getUSUARIO_SISTEMA() + "," + doc.getFECHA_MOD() + "," + doc.getUSUARIO_MOD() + ","
                        + doc.getFECHA_ANULA() + "," + doc.getUSUARIO_ANULA() + "," + doc.getVALOR_NO_AFECTO() + "," + doc.getOTROS_IMPUESTOS() + "," + doc.getVALOR_VENTA() + "," + doc.getFISE() + ","
                        + doc.getISC() + "," + doc.getRODAJE() + "," + doc.getSUBTOTAL_CON_FISE() + "," + doc.getMONTO_PERCEPCION() + "," + doc.getTOTAL_A_PAGAR() + "," + doc.getPLACA_TRACTOR() + ","
                        + doc.getPLACA_CISTERNA() + "," + doc.getSERIE_PER() + "," + doc.getNRO_DOC_PER() + "," + doc.getNRO_SCOP() + ",NULL,NULL" + "," + doc.getNRO_SCOP_VENTA() + ");";
                
                if (LogMigraXml.InsertBdXml(querydoc) == true) {
                    FrameMigraXml.ListDoc.add(doc.getID_TIPO_DOC() + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                    FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                    FrameMigraXml.ListCod.add("Migrado");
                    FrameMigraXml.ListDes.add(FileXml);
                    Integer contador = 1;
                    for (Integer NroItem = 0; NroItem < Integer.parseInt(BuscarDatoXml("Invoice", "cbc:LineCountNumeric", 0, 0)); NroItem++) {
                    	docDet.setCIA(doc.getCIA());
                        docDet.setID_PLANTA(doc.getID_PLANTA());
                        docDet.setID_PROVEEDOR(doc.getID_PROVEEDOR());
                        docDet.setID_TIPO_DOC(doc.getID_TIPO_DOC());
                        docDet.setSERIE_DOC(doc.getSERIE_DOC());
                        docDet.setNRO_DOC(doc.getNRO_DOC());
                        docDet.setITEM("'" + String.valueOf(contador++) + "'");
                        docDet.setFLAG_ARTI_OTROS("NULL");
                        docDet.setARTICULO_OTROS("NULL");
                        String codigo = BuscarDatoXml("cac:SellersItemIdentification", "cbc:ID", NroItem, 0);
                        String iscU = "";
                        switch (codigo) {
                        case "90RON/7.8E":
                            docDet.setID_ARTICULO("'017'");/*GASOHOL 90 PLUS*/
                            iscU = "0.0";
                            break;
                        case "90RON/7.8E.":
                            docDet.setID_ARTICULO("'017'");/*GASOHOL 90 PLUS*/
                            iscU = "0.0";
                            break;
                        case "95RON/7.8E":
                            docDet.setID_ARTICULO("'015'");/*GASOHOL 95 PLUS*/
                            iscU = "0.0";
                            break;
                        case "97RON/7.8E":
                            docDet.setID_ARTICULO("'018'");/*GASOHOL 97 PLUS*/
                            iscU = "0.0";
                            break;
                        case "90RON/7.8E.DOM":
                            docDet.setID_ARTICULO("'017'");/*GASOHOL 97 PLUS*/
                            iscU = "0.0";
                            break;
                        case "84RON/7.8E":
                            docDet.setID_ARTICULO("'016'");/*GASOHOL 84 PLUS*/
                            iscU = "0.0";
                            break;
                        case "ULSD UV":
                            docDet.setID_ARTICULO("'023'");/*DIESEL 2 S-50 UV*/
                            iscU = "0.0";
                            break;
                        default:
                        	docDet.setID_ARTICULO("'022'");/*diesel*/ 
                            iscU = "0.0";
                        }
                        
                        docDet.setCANTIDAD("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:InvoicedQuantity", NroItem, 0) + "'");
                        docDet.setPRECIO("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:PriceAmount", NroItem, 1) + "'");
                        docDet.setIMP_RODAJE("NULL");
                        //docDet.setISC("'" + ((BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", NroItem, 2) == null) ? "0": BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", NroItem, 2))  + "'");
                        String isc_DET = "";
                        if(BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", NroItem, 2) == null) {
                        	isc_DET = "0";
                        	docDet.setISC("0");
                        }else {
                        	isc_DET = BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", NroItem, 2);
                        	docDet.setISC("'"+BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", NroItem, 2)+"'");                	
                        }
                        
                        Double totalDet = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:TaxableAmount", NroItem, 0));
                        docDet.setTOTAL_DET("'" + totalDet.toString() + "'") ;
                        
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
                        DecimalFormat df = new DecimalFormat("##.#####");
                        
                        docDet.setSUBTOTAL_PRECIO_U("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:PriceAmount", NroItem, 1) + "'");
                        Double fiseU = 0.0238095238095238 * Double.parseDouble(tipo_cambio); 
                        
                        docDet.setFISE_U("'"+df.format(fiseU).toString()+"'");
                        docDet.setISC_U("'"+iscU+"'");
                        docDet.setRODAJE_U("NULL");
                        
                        Double TotalU = fiseU+ Double.parseDouble(iscU) + Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:PriceAmount", NroItem, 1));
                        docDet.setTOTAL_U("'"+df.format(TotalU).toString()+"'");
                        
                        Double totalConFise =  (Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:InvoicedQuantity", NroItem, 0)) * fiseU) + totalDet ;
                        docDet.setTOTAL_DET_CON_FISE("'" + df.format(totalConFise).toString() + "'");
                        
                        docDet.setGRAVEDAD_ESPECIFICA("NULL");
                        
                        String querydocdet = "INSERT INTO DOCUMENTO_COMPRA_DET(CIA, ID_PLANTA, ID_PROVEEDOR, ID_TIPO_DOC, SERIE_DOC, NRO_DOC, ITEM, FLAG_ARTI_OTROS, ARTICULO_OTROS, ID_ARTICULO, CANTIDAD"
                         		+", PRECIO, IMP_RODAJE, ISC, TOTAL_DET, ID_ESTADO, FECHA_ENVIO, USUARIO_ENVIO, FECHA_SISTEMA, USUARIO_SISTEMA, FECHA_MOD, USUARIO_MOD, FECHA_ANULA, USUARIO_ANULA, FACTOR_COMPENSACION"
                         		+", ID_UNIDAD_COMPRA, PRECIO_CON_IGV, OTROS_PRECIO_U, SUBTOTAL_PRECIO_U, FISE_U, ISC_U, RODAJE_U, TOTAL_U, TOTAL_DET_CON_FISE, GRAVEDAD_ESPECIFICA) VALUES(" + docDet.getCIA() + "," + docDet.getID_PLANTA() + "," + docDet.getID_PROVEEDOR() + "," + docDet.getID_TIPO_DOC() + "," + SerieDOC + "," + docDet.getNRO_DOC() + ","
                                 + docDet.getITEM() + "," + docDet.getFLAG_ARTI_OTROS() + "," + docDet.getARTICULO_OTROS() + "," + docDet.getID_ARTICULO() + "," + docDet.getCANTIDAD() + "," + docDet.getPRECIO() + ","
                                 + docDet.getIMP_RODAJE() + "," + docDet.getISC() + "," + docDet.getTOTAL_DET() + "," + docDet.getID_ESTADO() + "," + docDet.getFECHA_ENVIO() + "," + docDet.getUSUARIO_ENVIO() + ","
                                 + docDet.getFECHA_SISTEMA() + "," + docDet.getUSUARIO_SISTEMA() + "," + docDet.getFECHA_MOD() + "," + docDet.getUSUARIO_MOD() + "," + docDet.getFECHA_ANULA() + "," + docDet.getUSUARIO_ANULA() + ","
                                 + docDet.getFACTOR_COMPENSACION() + "," + docDet.getID_UNIDAD_COMPRA() + "," + docDet.getPRECIO_CON_IGV() + "," + docDet.getOTROS_PRECIO_U() + "," + docDet.getSUBTOTAL_PRECIO_U() + ","
                                 + docDet.getFISE_U() + "," + docDet.getISC_U() + "," + docDet.getRODAJE_U() + "," + docDet.getTOTAL_U() + "," + docDet.getTOTAL_DET_CON_FISE() + "," + docDet.getGRAVEDAD_ESPECIFICA() + ");";
                        LogMigraXml.InsertBdXml(querydocdet);
                    }
                } else {
                    FrameMigraXml.ListDoc.add("F" + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                    FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                    FrameMigraXml.ListCod.add("Error");
                    FrameMigraXml.ListDes.add("La migración no se realizó (Ruta " + FileXml + ")");
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
        if (element==null) {
            return "";
        } else {
        	if(element.getElementsByTagName(Detalle).item(posicionDeta) == null) {
        		return null;
        	}else {        		
        		return element.getElementsByTagName(Detalle).item(posicionDeta).getTextContent();
        	}
        }
        
    }
    
    private static String ObtenerNro(String Dato) {
        Integer cuenta = Dato.substring(4, Dato.length()).length();
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
