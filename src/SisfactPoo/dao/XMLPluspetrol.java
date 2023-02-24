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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JBENITEZVALLEJOS
 */
public class XMLPluspetrol {
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
            
            for (int i = 0; i < 7; i++) {
            	//obtener fecha
            	Date myDate = new Date();
            	//obtener tido de documento
            	if(i == 0) {
            		doc.setID_TIPO_DOC("'D/O'");
            	}else{
            		doc.setID_TIPO_DOC("'D/P'");
            	}
            	//formato de fecha
            	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            	Date fechaInicial = null;
                Date fechaFinal = null;
            	//obtener la fecha inicial y final del documento
                try {
                    fechaInicial = formateador.parse(BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0));
                    fechaFinal = formateador.parse(BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0));
                } catch (ParseException ex) {
                    Logger.getLogger(XmlRepsol.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*if (("20304177552").equals(BuscarDatoXml("cac:OriginatorParty", "cac:PartyIdentification", i, 0))){
                    doc.setID_TIPO_DOC("'D/O'");
                } else {
                    doc.setID_TIPO_DOC("'D/P'");
                }*/
                //obtener tipo de moneda y variantes
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
                //obtener  la compa�ia, proveedor y serie del documento
                doc.setCIA("'" + Datos.FeCiaCod + "'");
                //doc.setID_PLANTA("'" + "01" + "'");
                doc.setID_PROVEEDOR("'" + BuscarDatoXml("cac:InvoiceLine", "cac:PartyIdentification", i, 0).trim() + "'");
                doc.setSERIE_DOC("'" + "00" + BuscarDatoXml("Invoice", "cbc:ID", 0, 0).substring(2, 4) + "'");
                //obtener la serie que va a la BD
                String SerieDOC = "";
                String NuevaSerie = BuscarDatoXml("Invoice", "cbc:ID", 0, 0).substring(0, 4);
                SerieDOC = "'"+NuevaSerie+"'";
                //obtener el terminal
                if("Terminal Callao".equals(BuscarDatoXml("CustomText", "Text", 0, 0)) || "Terminal: Callao".equals(BuscarDatoXml("CustomText", "Text", 0, 0))) {
                	doc.setID_PLANTA("'01'");/*CALLAO*/
                }else if("Terminal Pisco".equals(BuscarDatoXml("CustomText", "Text", 0, 0)) || "Terminal: Pisco".equals(BuscarDatoXml("CustomText", "Text", 0, 0))) {
                	doc.setID_PLANTA("'16'");/*PLUSPETROL*/
                }
                
                doc.setNRO_DOC("'" + ObtenerNro(BuscarDatoXml("Invoice", "cbc:ID", 0, 0)) + "'");
                doc.setID_CONDICION_PAGO("'00'");
                doc.setFECHA("'" + BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0) + "'");
                doc.setFECHA_VENCIMIENTO("'" + BuscarDatoXml("Invoice", "cbc:IssueDate", 0, 0) + "'");
                doc.setTIPO_CAMBIO("'" + tipo_cambio + "'");
                doc.setFACTOR_CONV_DOC_LOCAL("NULL");
                doc.setFACTOR_CONV_DOC_EXT("NULL");
                doc.setFACTOR_CONV_LOCAL_DOC("NULL");
                doc.setFACTOR_CONV_LOCAL_EXT("NULL");
                Double VentaTotal = Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cac:Price", i, 0)) * Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:InvoicedQuantity", i, 0));  
                doc.setTOTAL_PRODUCTO_ANTES_IMP("'" + VentaTotal.toString().trim()+ "'");
                doc.setSUBTOTAL("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:TaxableAmount", i, 0).trim() + "'");
                doc.setIMPUESTO("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", i, 1).trim() + "'");
                doc.setTOTAL("'" + BuscarDatoXml("cac:InvoiceLine", "cac:ItemPriceExtension", i, 1).trim() + "'");
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
                //preguntar
                doc.setOTROS_IMPUESTOS("'"+BuscarDatoXml("cac:InvoiceLine", "cbc:Amount", i, 0).trim()+"'");
                
                doc.setVALOR_VENTA("'"+VentaTotal.toString()+"'");
                doc.setFISE("'"+BuscarDatoXml("cac:InvoiceLine", "cbc:Amount", i, 0).trim()+"'");
                
                doc.setISC("'"+BuscarDatoXml("cac:SubInvoiceLine", "cbc:TaxAmount", i, 2).trim()+"'");
                doc.setRODAJE("'"+"0.00" + "'");
                Double SubTotal_fise = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:TaxableAmount", i, 0)) + Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:Amount", i, 0));
                doc.setSUBTOTAL_CON_FISE("'" + SubTotal_fise.toString() +"'");
                doc.setMONTO_PERCEPCION("'" + "0.00" + "'");
                doc.setTOTAL_A_PAGAR("'" + BuscarDatoXml("cac:InvoiceLine", "cac:ItemPriceExtension", i, 1).trim() + "'");
                doc.setPLACA_TRACTOR("NULL");
                doc.setPLACA_CISTERNA("NULL");
                doc.setSERIE_PER("NULL");
                doc.setNRO_DOC_PER("NULL");
                //obtener nuevo de scop
                doc.setNRO_SCOP("NULL");
                doc.setNRO_SCOP_VENTA("'"+BuscarDatoXml("Section", "Value", 9, 8).trim()+"'");
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
                System.out.print(querydoc);
                System.out.print("hola");
                if (LogMigraXml.InsertBdXml(querydoc) == true) {
                    FrameMigraXml.ListDoc.add(doc.getID_TIPO_DOC() + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                    FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                    FrameMigraXml.ListCod.add("Migrado");
                    FrameMigraXml.ListDes.add(FileXml);
                        docDet.setCIA(doc.getCIA());
                        docDet.setID_PLANTA(doc.getID_PLANTA());
                        //docDet.setID_PROVEEDOR("'" + BuscarDatoXml("cac:PartyIdentification", "cbc:ID", NroItem, 0) + "'");
                        docDet.setID_PROVEEDOR(doc.getID_PROVEEDOR()); 
                        docDet.setID_TIPO_DOC(doc.getID_TIPO_DOC());
                        docDet.setSERIE_DOC(doc.getSERIE_DOC());
                        docDet.setNRO_DOC(doc.getNRO_DOC());
                        docDet.setITEM("'" + 1 + "'");
                        docDet.setFLAG_ARTI_OTROS("NULL");
                        docDet.setARTICULO_OTROS("NULL");
                        //docDet.setID_ARTICULO("'" + "022" + "'");
                        docDet.setID_ARTICULO("'022'");//DIESEL B5 S-50  UV//
                                
                        docDet.setCANTIDAD("'" + BuscarDatoXml("cac:SubInvoiceLine", "cbc:InvoicedQuantity", i, 0).trim() + "'");
                        docDet.setPRECIO("'" + BuscarDatoXml("cac:SubInvoiceLine", "cac:Price", i, 0).trim() + "'");
                        docDet.setIMP_RODAJE("NULL");
                        docDet.setISC("'"+BuscarDatoXml("cac:SubInvoiceLine", "cbc:TaxAmount", i, 2).trim()+"'");
                        docDet.setTOTAL_DET("'" + BuscarDatoXml("cac:InvoiceLine", "cbc:TaxableAmount", i, 0).trim() + "'");
                        docDet.setID_ESTADO("'" + "01" + "'");
                        docDet.setFECHA_ENVIO("NULL");
                        docDet.setUSUARIO_ENVIO("NULL");
                        docDet.setFECHA_SISTEMA(doc.getFECHA_SISTEMA());
                        docDet.setUSUARIO_SISTEMA("'" + Datos.FeUsuario.toUpperCase() + "'");
                        docDet.setFECHA_MOD("NULL");
                        docDet.setUSUARIO_MOD("NULL");
                        docDet.setFECHA_ANULA("NULL");
                        docDet.setUSUARIO_ANULA("NULL");
                        docDet.setFACTOR_COMPENSACION("'"+BuscarDatoXml("cac:SubInvoiceLine", "cbc:Amount", i, 0).trim()+"'");
                        docDet.setID_UNIDAD_COMPRA("'" + "GL" + "'");
                        docDet.setPRECIO_CON_IGV("NULL");
                        docDet.setOTROS_PRECIO_U("NULL");
                        docDet.setSUBTOTAL_PRECIO_U("'"+BuscarDatoXml("cac:SubInvoiceLine", "cac:Price", i, 0).trim()+"'");
                        
                        Double fiseU = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:Amount", i, 0)) / Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:InvoicedQuantity", i, 0));
                        
                        docDet.setFISE_U("'"+fiseU.toString().trim()+"'");
                        Double isc_Unitario = (Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:TaxAmount", i, 2)) / Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:InvoicedQuantity", i, 0)));
                        docDet.setISC_U("'"+isc_Unitario.toString().trim()+"'");
                        docDet.setRODAJE_U("NULL");
                        
                        Double totalU =  Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cac:Price", i, 0)) + fiseU + isc_Unitario;
                        docDet.setTOTAL_U("'"+totalU.toString().trim()+"'");
                        docDet.setTOTAL_DET_CON_FISE("'" + SubTotal_fise.toString().trim() + "'");
                        
                        docDet.setGRAVEDAD_ESPECIFICA("NULL");
                        
                        String querydocdet = "INSERT INTO DOCUMENTO_COMPRA_DET(CIA, ID_PLANTA, ID_PROVEEDOR, ID_TIPO_DOC, SERIE_DOC, NRO_DOC, ITEM, FLAG_ARTI_OTROS, ARTICULO_OTROS, ID_ARTICULO, CANTIDAD" 
                        		+", PRECIO, IMP_RODAJE, ISC, TOTAL_DET, ID_ESTADO, FECHA_ENVIO, USUARIO_ENVIO, FECHA_SISTEMA, USUARIO_SISTEMA, FECHA_MOD, USUARIO_MOD, FECHA_ANULA, USUARIO_ANULA, FACTOR_COMPENSACION" 
                        		+", ID_UNIDAD_COMPRA, PRECIO_CON_IGV, OTROS_PRECIO_U, SUBTOTAL_PRECIO_U, FISE_U, ISC_U, RODAJE_U, TOTAL_U, TOTAL_DET_CON_FISE, GRAVEDAD_ESPECIFICA) VALUES(" + docDet.getCIA() + "," + docDet.getID_PLANTA() + "," + docDet.getID_PROVEEDOR() + "," + docDet.getID_TIPO_DOC() + "," + SerieDOC + "," + docDet.getNRO_DOC() + ","
                                + docDet.getITEM() + "," + docDet.getFLAG_ARTI_OTROS() + "," + docDet.getARTICULO_OTROS() + "," + docDet.getID_ARTICULO() + "," + docDet.getCANTIDAD() + "," + docDet.getPRECIO() + ","
                                + docDet.getIMP_RODAJE() + "," + docDet.getISC() + "," + docDet.getTOTAL_DET() + "," + docDet.getID_ESTADO() + "," + docDet.getFECHA_ENVIO() + "," + docDet.getUSUARIO_ENVIO() + ","
                                + docDet.getFECHA_SISTEMA() + "," + docDet.getUSUARIO_SISTEMA() + "," + docDet.getFECHA_MOD() + "," + docDet.getUSUARIO_MOD() + "," + docDet.getFECHA_ANULA() + "," + docDet.getUSUARIO_ANULA() + ","
                                + docDet.getFACTOR_COMPENSACION() + "," + docDet.getID_UNIDAD_COMPRA() + "," + docDet.getPRECIO_CON_IGV() + "," + docDet.getOTROS_PRECIO_U() + "," + docDet.getSUBTOTAL_PRECIO_U() + ","
                                + docDet.getFISE_U() + "," + docDet.getISC_U() + "," + docDet.getRODAJE_U() + "," + docDet.getTOTAL_U() + "," + docDet.getTOTAL_DET_CON_FISE() + "," + docDet.getGRAVEDAD_ESPECIFICA() + ");";
                        //System.out.print(querydocdet);
                        LogMigraXml.InsertBdXml(querydocdet+'\n');
                } else {
                    FrameMigraXml.ListDoc.add(doc.getID_TIPO_DOC() + doc.getSERIE_DOC().substring(2, 5) + "-" + doc.getNRO_DOC().substring(1, 9));
                    FrameMigraXml.ListFecha.add(doc.getFECHA().substring(1, 11));
                    FrameMigraXml.ListCod.add("Error");
                    FrameMigraXml.ListDes.add("La migración no se realizó (Ruta " + FileXml + ")");
                }
                
                
                /*
                Double precioU = Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cac:Price", i, 0));
                Double cantidad = Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:InvoicedQuantity", i, 0));
                Double VentaTotal1 = (precioU * cantidad);    
                Double precioT = Double.parseDouble(BuscarDatoXml("cac:ItemPriceExtension", "cbc:Amount", i, 0));
                Double isc_ValorVenta = Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:TaxAmount", i, 2));
                Double isc_Unitario = (isc_ValorVenta / cantidad);  
                Double factor_compesacion = Double.parseDouble(BuscarDatoXml("cac:SubInvoiceLine", "cbc:Amount", i, 0));
                Double subTotal = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:TaxableAmount", i, 0));
                Double IGV = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cbc:TaxAmount", i, 1));
                Double fise = Double.parseDouble(BuscarDatoXml("cac:AllowanceCharge", "cbc:Amount", i, 0));
                Double total_pagar = Double.parseDouble(BuscarDatoXml("cac:InvoiceLine", "cac:ItemPriceExtension", i, 1));
                
                System.out.print("Precio Unitario: " + precioU +"  ###  ");
            	System.out.print("Cantidad: " + cantidad+"  ###  ");
            	System.out.print("Venta Total: " + VentaTotal1+"  ###  ");
            	System.out.print("Precio total: " + precioT+'\n');
            	System.out.print("ISC Unitario: " + isc_Unitario+"  ###  ");
            	System.out.print("ISC Venta: " + isc_ValorVenta  +"  ###  ");
            	System.out.print("Factor de compensacion: " + factor_compesacion+'\n');
            	System.out.print("Sub Total: " + subTotal +"  ###  ");
            	System.out.print("Igv: " + IGV+"  ###  ");
            	System.out.print("fise: " + fise+'\n');
            	System.out.print("Total a pagar: " + total_pagar+'\n');
            	System.out.print("###############################################'\n'"*/
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
        		return "0.00";
        	}else {        		
        		return element.getElementsByTagName(Detalle).item(posicionDeta).getTextContent();
        	}
            //return element.getElementsByTagName(Detalle).item(posicionDeta).getTextContent();
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
