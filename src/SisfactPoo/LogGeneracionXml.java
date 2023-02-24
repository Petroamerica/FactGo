/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo;

import Datos.Datos;
import LogicaSql.LogPercepcion;
import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import SisfactPoo.beans.Documento;
import SisfactPoo.beans.DocumentoDet;
import SisfactPoo.beans.DocumentoRefPer;
import SisfactPoo.beans.Xml;
import SisfactPoo.dao.DocumentoDao;
import SisfactPoo.dao.DocumentoDaoImpl;
import SisfactPoo.dao.DocumentoDetDao;
import SisfactPoo.dao.DocumentoDetDaoImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author kbarreda
 */
public class LogGeneracionXml {

        public static void GenerarXml() {

                try {
                        DocumentoDao documentoDao = new DocumentoDaoImpl();
                        List<Documento> documentos = documentoDao.listar();
                        Xml xml = new Xml();
                        for (Documento documento : documentos) {
                                System.out.println("TIPO DOC: " + documento.getID_TIPO_DOC() + " MONTO_PER: "
                                                + documento.getMONTO_PER() + " MONTO_TOTAL_PER: "
                                                + documento.getTOTAL_MONTO_PER());

                                xml.setSaltoLinea("\n");
                                xml.setDatos("");
                                xml.setAbreviaturaMoneda(
                                                ("SOLES".equals(documento.getMONEDA_DESCRIPCION()) ? "PEN" : "USD"));
                                xml.setAbreviaturaMonedaDescrip(
                                                ("SOLES".equals(documento.getMONEDA_DESCRIPCION()) ? "SOLES"
                                                                : "DOLARES AMERICANOS"));

                                if ("PER".equals(documento.getID_TIPO_DOC())) {
                                        xml.setDatos("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<Perception xmlns=\"urn:sunat:names:specification:ubl:peru:schema:xsd:Perception-1\" xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\" xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\" xmlns:sac=\"urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1\">"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:UBLVersionID>2.0</cbc:UBLVersionID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CustomizationID>1.0</cbc:CustomizationID>"
                                                                        + xml.getSaltoLinea());

                                        switch (documento.getCIA()) {
                                                case "06":
                                                        xml.setNombreArchivo(
                                                                        "20600427734-40-P" + documento.getSERIE() + "-"
                                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                default:
                                                        xml.setNombreArchivo("");
                                                        break;
                                        }

                                        if ("06".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20600427734</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20600427734</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20600427734</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cbc:ID>P" + documento.getSERIE() + "-"
                                                                + documento.getNRO_DOCUMENTO() + "</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:IssueDate>" + documento.getFECHA()
                                                                + "</cbc:IssueDate>"
                                                                + xml.getSaltoLinea());
                                                // cambio percepcion 06/02/2023
                                                // if ("PER".equals(documento.getID_TIPO_DOC())){

                                                if("01".equals(documento.getID_CLASIFICACION_TIPO_NEGOCIO()) && "0".equals(documento.getFLAG_CONDICION_PAGO_CREDITO_REF())) {
                                                	System.out.println("\n ES CONTADO!!!!!! \n");

                                            		xml.setDatos(xml.getDatos() + "<sac:ExceptionalIndicator>01</sac:ExceptionalIndicator>" + xml.getSaltoLinea());
                                                }else if("02".equals(documento.getID_CLASIFICACION_TIPO_NEGOCIO())) {
                                                	System.out.println("\n ES CONTADO!!!!!! \n");
                                                	xml.setDatos(xml.getDatos() + "<sac:ExceptionalIndicator>01</sac:ExceptionalIndicator>" + xml.getSaltoLinea());
                                                }


                                                // }
                                                xml.setDatos(xml.getDatos() + "<cac:AgentParty>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(
                                                                xml.getDatos() + "<cbc:ID schemeID=\"6\">20600427734</cbc:ID>"
                                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PostalAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">150122</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:StreetName>AV. BENAVIDES NRO. 620 INT. 703</cbc:StreetName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PostalAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AgentParty>"
                                                                + xml.getSaltoLinea());
                                        }

                                        xml.setDatos(xml.getDatos() + "<cac:ReceiverParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID schemeID=\""
                                                        + ((11 == documento.getNRO_DI().length()) ? "6" : "1") + "\">"
                                                        + documento.getNRO_DI()
                                                        + "</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>" + documento.getDESCRIPCION_CLIENTE()
                                                        + "</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID >" + documento.getID_DISTRITO()
                                                        + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:StreetName>"
                                                        + documento.getDIRECCION_CLIENTE()
                                                        + "</cbc:StreetName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>"
                                                        + documento.getDEPARTAMENTO_CLIENTE()
                                                        + "</cbc:CityName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CountrySubentity>"
                                                        + documento.getPROVINCIA_CLIENTE()
                                                        + "</cbc:CountrySubentity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>"
                                                        + documento.getPROVINCIA_CLIENTE()
                                                        + "</cbc:District>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:RegistrationName>"
                                                        + documento.getDESCRIPCION_CLIENTE()
                                                        + "</cbc:RegistrationName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Contact>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ElectronicMail>"
                                                        + documento.getEMAIL_CLIENTE()
                                                        + "</cbc:ElectronicMail>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Contact>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ReceiverParty>" + xml.getSaltoLinea());
                                        documento.setPORC_PERCEPCION(ConsultarPORC_PERCEPCION(documento.getCIA(),
                                                        documento.getID_PLANTA_REF(), documento.getSERIE_REF(),
                                                        documento.getNRO_DOCUMENTO_REF()));
                                        System.out.println("PORC_PERCEPCION " + documento.getPORC_PERCEPCION()
                                                        + " SERIE_REF: " + documento.getNRO_DOCUMENTO());
                                        String codigoPER = "";
                                        String tasaPER = "";
                                        switch (documento.getPORC_PERCEPCION()) {
                                                case "0.02":
                                                        codigoPER = "01";
                                                        tasaPER = "2.00";
                                                        break;
                                                case "0.01":
                                                        codigoPER = "02";
                                                        tasaPER = "1.00";
                                                        break;
                                                case "0.005":
                                                        codigoPER = "03";
                                                        tasaPER = "0.5";
                                                        break;
                                                default:
                                                        codigoPER = "0";
                                                        tasaPER = "0.00";
                                                        break;
                                        }
                                        // TODO cambio 31/01/2023
                                        /*
                                         * if ("1".equals(documento.getFLAG_CONDICION_PAGO_CREDITO())) {
                                         * System.out.println("es una factura");
                                         * System.out.println(documento.getID_CLASIFICACION_TIPO_NEGOCIO());
                                         * if ("02".equals(documento.getID_CLASIFICACION_TIPO_NEGOCIO())) {
                                         * System.out.println("es una factura al contado");
                                         * System.out.println("total:"+" "+documento.getTOTAL());
                                         * documento.setMONTO_APLICA_DEBITO_REF(documento.getTOTAL());
                                         * }
                                         * }
                                         */

                                        System.out.println(documento.getMONTO_APLICA_DEBITO_REF());
                                        Double Percep_Total = Double.parseDouble(documento.getMONTO_APLICA_DEBITO_REF())
                                                        + Double.parseDouble(documento.getTOTAL());
                                        Double Percep_TotalRound = Math.round(Percep_Total * 100.0) / 100.0;
                                        System.out.println("PERecp total round: " + Percep_Total);
                                        Double TotalRound = Math
                                                        .round(Double.parseDouble(
                                                                        documento.getMONTO_APLICA_DEBITO_REF()) * 100.0)
                                                        / 100.0;
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionSystemCode>" + codigoPER
                                                        + "</sac:SUNATPerceptionSystemCode>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionPercent>" + tasaPER
                                                        + "</sac:SUNATPerceptionPercent>" + xml.getSaltoLinea());
                                        // SUMA DE TODOS LOS sac:SUNATPerceptionInformation/sac:SUNATPerceptionAmount
                                        xml.setDatos(xml.getDatos() + "<cbc:TotalInvoiceAmount currencyID=\"PEN\">"
                                                        + documento.getTOTAL()
                                                        + "</cbc:TotalInvoiceAmount>" + xml.getSaltoLinea());
                                        // SUMA DE TODOS LOS sac:SUNATPerceptionInformation/sac:SUNATNetTotalCashed
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATTotalCashed currencyID=\"PEN\">"
                                                        + Percep_TotalRound
                                                        + "</sac:SUNATTotalCashed>" + xml.getSaltoLinea());
                                        String fechaReferencia = ConsultarFechaDocumentoReferencia(documento.getCIA(),
                                                        documento.getID_PLANTA_REF(), documento.getSERIE_REF(),
                                                        documento.getNRO_DOCUMENTO_REF());
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionDocumentReference>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID schemeID=\"01\">F"
                                                        + documento.getSERIE_REF() + "-"
                                                        + documento.getNRO_DOCUMENTO_REF() + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:IssueDate>" + fechaReferencia
                                                        + "</cbc:IssueDate>"
                                                        + xml.getSaltoLinea());
                                        // MONTO DEL DOCUMENTO
                                        xml.setDatos(xml.getDatos() + "<cbc:TotalInvoiceAmount currencyID=\"PEN\">"
                                                        + consultarTotalDocumento_PERCEPCION(documento.getCIA(),
                                                                        documento.getID_PLANTA_REF(),
                                                                        documento.getSERIE_REF(),
                                                                        documento.getNRO_DOCUMENTO_REF())
                                                        + "</cbc:TotalInvoiceAmount>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Payment>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>"
                                                        + documento.getPER_CORRELATIVO_CANCELACION() + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        // MONTO PAGADO
                                        xml.setDatos(xml.getDatos() + "<cbc:PaidAmount currencyID=\"PEN\">" + TotalRound
                                                        + "</cbc:PaidAmount>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:PaidDate>" + documento.getFECHA()
                                                        + "</cbc:PaidDate>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Payment>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionInformation>"
                                                        + xml.getSaltoLinea());
                                        // MONTO PAGADO X PORCENTAJE DE PERCEPCION
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionAmount currencyID=\"PEN\">"
                                                        + documento.getTOTAL() + "</sac:SUNATPerceptionAmount>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATPerceptionDate>" + documento.getFECHA()
                                                        + "</sac:SUNATPerceptionDate>" + xml.getSaltoLinea());
                                        // MONTO PAGADO + (MONTO PAGADO X PORCENTAJE DE PERCEPCION)
                                        xml.setDatos(xml.getDatos() + "<sac:SUNATNetTotalCashed currencyID=\"PEN\">"
                                                        + Percep_TotalRound
                                                        + "</sac:SUNATNetTotalCashed>" + xml.getSaltoLinea()); //
                                        xml.setDatos(xml.getDatos() + "</sac:SUNATPerceptionInformation>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</sac:SUNATPerceptionDocumentReference>"
                                                        + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "</Perception>" + xml.getSaltoLinea());

                                        if (xml.getListaNombreArchivo() == null) {
                                                xml.setListaNombreArchivo(xml.getNombreArchivo()
                                                                + documento.getID_PLANTA()
                                                                + documento.getID_TIPO_DOC() + xml.getSaltoLinea());
                                        } else {
                                                xml.setListaNombreArchivo(xml.getListaNombreArchivo()
                                                                + xml.getNombreArchivo()
                                                                + documento.getID_PLANTA() + documento.getID_TIPO_DOC()
                                                                + xml.getSaltoLinea());
                                        }

                                } else {
                                        switch (documento.getID_TIPO_DOC()) {
                                                case "FAC":
                                                        xml.setCodigo("01");
                                                        xml.setDescripcion("Invoice");
                                                        xml.setTipoDoc(documento.getID_TIPO_DOC().substring(0, 1));
                                                        break;
                                                case "BOL":
                                                        xml.setCodigo("03");
                                                        xml.setDescripcion("Invoice");
                                                        xml.setTipoDoc(documento.getID_TIPO_DOC().substring(0, 1));
                                                        break;
                                                case "N-C":
                                                        xml.setCodigo("07");
                                                        xml.setDescripcion("CreditNote");
                                                        xml.setTipoDoc(documento.getID_TIPO_DOC_REF().substring(0, 1));
                                                        break;
                                                case "N-D":
                                                        xml.setCodigo("08");
                                                        xml.setDescripcion("DebitNote");
                                                        if ("N".equals(documento.getID_TIPO_DOC_REF().substring(0,
                                                                        1))) {
                                                                String tipoDoc = ConsultarTipoDumentoReferencia(
                                                                                documento.getCIA(),
                                                                                documento.getID_PLANTA_REF(),
                                                                                documento.getSERIE_REF(),
                                                                                documento.getID_TIPO_DOC_REF(),
                                                                                documento.getNRO_DOCUMENTO_REF());
                                                                xml.setTipoDoc(tipoDoc.substring(0, 1));
                                                        } else {
                                                                xml.setTipoDoc(documento.getID_TIPO_DOC_REF()
                                                                                .substring(0, 1));
                                                        }
                                                        break;
                                                default:
                                                        xml.setCodigo("DEFAULT");
                                                        xml.setDescripcion("DEFAULT");
                                                        xml.setTipoDoc("DEFAULT");
                                                        break;
                                        }

                                        // System.out.print("asdsadsad");
                                        switch (documento.getCIA()) {
                                                case "01":
                                                        xml.setNombreArchivo("20332711157-" + xml.getCodigo() + "-"
                                                                        + xml.getTipoDoc()
                                                                        + documento.getSERIE() + "-"
                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                case "02":
                                                        xml.setNombreArchivo("20100005485-" + xml.getCodigo() + "-"
                                                                        + xml.getTipoDoc()
                                                                        + documento.getSERIE() + "-"
                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                case "05":
                                                        xml.setNombreArchivo("20524417490-" + xml.getCodigo() + "-"
                                                                        + xml.getTipoDoc()
                                                                        + documento.getSERIE() + "-"
                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                case "06":
                                                        xml.setNombreArchivo("20600427734-" + xml.getCodigo() + "-"
                                                                        + xml.getTipoDoc()
                                                                        + documento.getSERIE() + "-"
                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                case "07":
                                                        xml.setNombreArchivo("20602359981-" + xml.getCodigo() + "-"
                                                                        + xml.getTipoDoc()
                                                                        + documento.getSERIE() + "-"
                                                                        + documento.getNRO_DOCUMENTO());
                                                        break;
                                                default:
                                                        xml.setNombreArchivo("");
                                                        break;
                                        }
                                        // PARA ENVIAR A TODOS LOS DOCUMENTOS DE 06 EN FAC O BOL COMO CONTADO ESPECIAL
                                        // NUEVO CAMBIO PARA ENVIAR DOCUMENTO 06, CUANDO SEA FACTURA Y ESTACIONES
                                        // PROPIAS
                                        if (documento.getCIA().equals("06")
                                                        && documento.getID_CLASIFICACION_TIPO_NEGOCIO().equals("02")) {
                                                if ("FAC".equals(documento.getID_TIPO_DOC())
                                                                || "BOL".equals(documento.getID_TIPO_DOC())) {
                                                        documento.setFECHA_VENCE(documento.getFECHA());
                                                        documento.setCONDICION_PAGO("CONTADO ESPECIAL");
                                                        documento.setFLAG_CONDICION_PAGO_CREDITO("0");
                                                }
                                        }

                                        xml.setDatos("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<" + xml.getDescripcion()
                                                        + " xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\""
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\""
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:"
                                                        + xml.getDescripcion() + "-2\"" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\">"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:UBLVersionID>2.1</cbc:UBLVersionID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CustomizationID>2.0</cbc:CustomizationID>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>" + xml.getTipoDoc()
                                                        + documento.getSERIE() + "-"
                                                        + documento.getNRO_DOCUMENTO() + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:IssueDate>" + documento.getFECHA()
                                                        + "</cbc:IssueDate>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos((documento.getFECHA_VENCE() == null
                                                        || "N-D".equals(documento.getID_TIPO_DOC())
                                                        || "N-C".equals(documento.getID_TIPO_DOC()))
                                                                        ? xml.getDatos() + ""
                                                                        : xml.getDatos() + "<cbc:DueDate>"
                                                                                        + documento.getFECHA_VENCE()
                                                                                        + "</cbc:DueDate>"
                                                                                        + xml.getSaltoLinea());

                                        // SOLO PARA CIA 06 CAMBIO DE PERCEPCION PERO PARA TODOS NO!
                                        String estadoPER = "";
                                        if ("06".equals(documento.getCIA())) {
                                                if ("FAC".equals(documento.getID_TIPO_DOC())
                                                                || "BOL".equals(documento.getID_TIPO_DOC())) {

                                                        if (!"1".equals(documento.getFLAG_CONDICION_PAGO_CREDITO())) {
                                                                if ((Double.parseDouble(
                                                                                documento.getMONTO_PER())) == 0.0) {
                                                                        estadoPER = "0101";
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:InvoiceTypeCode listID=\"0101\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\" name=\"Tipo de Operacion\">"
                                                                                        + xml.getCodigo()
                                                                                        + "</cbc:InvoiceTypeCode>"
                                                                                        + xml.getSaltoLinea());
                                                                } else {
                                                                        estadoPER = "2001";
                                                                        // cambio 2001 por 0101
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:InvoiceTypeCode listID=\"0101\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\" name=\"Tipo de Operacion\">"
                                                                                        + xml.getCodigo()
                                                                                        + "</cbc:InvoiceTypeCode>"
                                                                                        + xml.getSaltoLinea());
                                                                        /*
                                                                         * xml.setDatos(xml.getDatos()
                                                                         * +
                                                                         * "<cbc:Note languageLocaleID=\"2000\">comprobante de percepcion</cbc:Note>"
                                                                         * + xml.getSaltoLinea());
                                                                         */
                                                                }
                                                        } else {
                                                                estadoPER = "0101";
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:InvoiceTypeCode listID=\"0101\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\" name=\"Tipo de Operacion\">"
                                                                                + xml.getCodigo()
                                                                                + "</cbc:InvoiceTypeCode>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                }
                                        } else {
                                                if ("FAC".equals(documento.getID_TIPO_DOC())
                                                                || "BOL".equals(documento.getID_TIPO_DOC())) {
                                                        if ((Double.parseDouble(documento.getMONTO_PER())) == 0.0) {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:InvoiceTypeCode listID=\"0101\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\" name=\"Tipo de Operacion\">"
                                                                                + xml.getCodigo()
                                                                                + "</cbc:InvoiceTypeCode>"
                                                                                + xml.getSaltoLinea());
                                                        } else {
                                                                // cambio 2001 por 0101
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:InvoiceTypeCode listID=\"0101\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\" name=\"Tipo de Operacion\">"
                                                                                + xml.getCodigo()
                                                                                + "</cbc:InvoiceTypeCode>"
                                                                                + xml.getSaltoLinea());
                                                                /*
                                                                 * xml.setDatos(xml.getDatos()
                                                                 * +
                                                                 * "<cbc:Note languageLocaleID=\"2000\">comprobante de percepcion</cbc:Note>"
                                                                 * + xml.getSaltoLinea());
                                                                 */
                                                        }
                                                }
                                        }

                                        xml.setDecimal(documento.getTOTAL()
                                                        .substring(documento.getTOTAL().indexOf('.') + 1));
                                        xml.setDecimal((xml.getDecimal().length() == 1)
                                                        ? " con 0" + xml.getDecimal() + "/100 "
                                                                        + xml.getAbreviaturaMonedaDescrip()
                                                        : " con " + xml.getDecimal() + "/100 "
                                                                        + xml.getAbreviaturaMonedaDescrip());
                                        NumeroEnTexto nt = new NumeroEnTexto();

                                        if ("N-C".equals(documento.getID_TIPO_DOC())
                                                        || "N-D".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(xml.getDatos() + "<cbc:Note>"
                                                                + (documento.getMOTIVO_SUNAT() == null ? " "
                                                                                : documento.getMOTIVO_SUNAT())
                                                                + " : "
                                                                + (documento.getDESCRIPCION_NOTA() == null ? "  "
                                                                                : documento.getDESCRIPCION_NOTA())
                                                                + "</cbc:Note>" + xml.getSaltoLinea());
                                                if ("07".equals(documento.getCIA())) {
                                                        xml.setDatos(((documento.getCONDICION_PAGO() == null)
                                                                        ? xml.getDatos() + ""
                                                                        : xml.getDatos() + "<cbc:Note languageID=\"L\">"
                                                                                        + ((documento.getCONDICION_PAGO() == null)
                                                                                                        ? ""
                                                                                                        : documento.getCONDICION_PAGO())
                                                                                        + "</cbc:Note>"
                                                                                        + xml.getSaltoLinea()));
                                                        xml.setDatos(((documento.getFECHA_VENCE() == null)
                                                                        ? xml.getDatos() + ""
                                                                        : xml.getDatos() + "<cbc:Note languageID=\"C\">"
                                                                                        + ((documento.getFECHA_VENCE() == null)
                                                                                                        ? ""
                                                                                                        : documento.getFECHA_VENCE())
                                                                                        + "</cbc:Note>"
                                                                                        + xml.getSaltoLinea()));
                                                }
                                        }

                                        xml.setDatos(xml.getDatos() + "<cbc:Note languageLocaleID=\"1000\">"
                                                        + nt.numberToText(Integer
                                                                        .valueOf(documento.getTOTAL().substring(0,
                                                                                        documento.getTOTAL()
                                                                                                        .indexOf('.'))))
                                                        + xml.getDecimal() + "</cbc:Note>" + xml.getSaltoLinea());

                                        if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(((documento.getID_CLIENTE() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"A\">"
                                                                                + documento.getID_CLIENTE()
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getNRO_SCOP() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"B\">"
                                                                                + ((documento.getNRO_SCOP() == null)
                                                                                                ? ""
                                                                                                : documento.getNRO_SCOP())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getPLACA_TRACTOR() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"C\">"
                                                                                + ((documento.getPLACA_TRACTOR() == null)
                                                                                                ? ""
                                                                                                : documento.getPLACA_TRACTOR())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getPLACA_CISTERNA() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"D\">"
                                                                                + ((documento.getPLACA_CISTERNA() == null)
                                                                                                ? ""
                                                                                                : documento.getPLACA_CISTERNA())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getNRO_AUTORIZACION() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"E\">"
                                                                                + ((documento.getNRO_AUTORIZACION() == null)
                                                                                                ? ""
                                                                                                : documento.getNRO_AUTORIZACION())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getCHOFER_DESCRIP() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"G\">"
                                                                                + ((documento.getCHOFER_DESCRIP() == null)
                                                                                                ? ""
                                                                                                : documento.getCHOFER_DESCRIP())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getID_CHOFER() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"H\">"
                                                                                + ((documento.getID_CHOFER() == null)
                                                                                                ? ""
                                                                                                : documento.getID_CHOFER())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getCONDICION_PAGO() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"I\">"
                                                                                + ((documento.getCONDICION_PAGO() == null)
                                                                                                ? ""
                                                                                                : documento.getCONDICION_PAGO())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getID_TIPO_DOC_CREDITO() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"J\">"
                                                                                + ((documento.getID_TIPO_DOC_CREDITO() == null)
                                                                                                ? ""
                                                                                                : documento.getID_TIPO_DOC_CREDITO())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getNRO_PAGO() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"K\">"
                                                                                + ((documento.getNRO_PAGO() == null)
                                                                                                ? ""
                                                                                                : documento.getNRO_PAGO())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getHORA() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"L\">"
                                                                                + ((documento.getHORA() == null) ? ""
                                                                                                : documento.getHORA())
                                                                                + "</cbc:Note>"
                                                                                + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getRAZON_SOCIAL_TRANSPORTE() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"M\">"
                                                                                + ((documento.getRAZON_SOCIAL_TRANSPORTE() == null)
                                                                                                ? ""
                                                                                                : documento.getRAZON_SOCIAL_TRANSPORTE())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                if ("01".equals(documento.getCIA())) {
                                                        xml.setDatos(((documento.getTOTAL_PESO() == null)
                                                                        ? xml.getDatos() + ""
                                                                        : xml.getDatos() + "<cbc:Note languageID=\"N\">"
                                                                                        + ((documento.getTOTAL_PESO() == null)
                                                                                                        ? ""
                                                                                                        : documento.getTOTAL_PESO())
                                                                                        + "</cbc:Note>"
                                                                                        + xml.getSaltoLinea()));
                                                }
                                                if ("06".equals(documento.getCIA())) {
                                                        xml.setDatos(((documento.getTOTAL_PESO() == null)
                                                                        ? xml.getDatos() + ""
                                                                        : xml.getDatos() + "<cbc:Note languageID=\"N\">"
                                                                                        + ((documento.getTOTAL_PESO() == null)
                                                                                                        ? ""
                                                                                                        : documento.getTOTAL_PESO())
                                                                                        + "</cbc:Note>"
                                                                                        + xml.getSaltoLinea()));
                                                }
                                                xml.setDatos(((documento.getRUC_EMP_TRANSPORTE() == null)
                                                                ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"O\">"
                                                                                + ((documento.getRUC_EMP_TRANSPORTE() == null)
                                                                                                ? ""
                                                                                                : documento.getRUC_EMP_TRANSPORTE())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getSUBTOTAL() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"P\">"
                                                                                + ((documento.getSUBTOTAL() == null)
                                                                                                ? ""
                                                                                                : documento.getSUBTOTAL())
                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getIGV() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"Q\">"
                                                                                + ((documento.getIGV() == null) ? ""
                                                                                                : documento.getIGV())
                                                                                + "</cbc:Note>"
                                                                                + xml.getSaltoLinea()));
                                                xml.setDatos(((documento.getTOTAL() == null) ? xml.getDatos() + ""
                                                                : xml.getDatos() + "<cbc:Note languageID=\"R\">"
                                                                                + ((documento.getTOTAL() == null) ? ""
                                                                                                : documento.getTOTAL())
                                                                                + "</cbc:Note>"
                                                                                + xml.getSaltoLinea()));

                                                // CAMBIOS RETENCION - FALTA PAGAR
                                                if ("FAC".equals(documento.getID_TIPO_DOC())
                                                                && "1".equals(documento.getFLAG_ES_AGENTE_RETENEDOR())
                                                                && "07".equals(documento.getCIA())
                                                                && "0".equals(documento
                                                                                .getFLAG_CONDICION_PAGO_CREDITO())) {
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:Note languageID=\"AMOUNTNET\">"
                                                                        + documento.getMONTO_RETENEDOR() + "</cbc:Note>"
                                                                        + xml.getSaltoLinea());
                                                }

                                                // CAMBIOS PERCEPCION
                                                if ("06".equals(documento.getCIA())) {
                                                        if ("FAC".equals(documento.getID_TIPO_DOC())
                                                            || "BOL".equals(documento.getID_TIPO_DOC())) {
		                                                        if ("2001".equals(estadoPER)) {
		                                                                xml.setDatos(((ConsultarPercepcion(
                                                                        documento.getCIA(),
                                                                        documento.getID_PLANTA(),
                                                                        documento.getID_TIPO_DOC(),
                                                                        documento.getSERIE(),
                                                                        documento.getNRO_DOCUMENTO()) == null)
                                                                                        ? xml.getDatos() + ""
                                                                                        : xml.getDatos() + "<cbc:Note languageID=\"S\">"
                                                                                        + "Operación sujeta a percepción "
                                                                                        + documento.getPORC_PERCEPCION()
                                                                                        + " % de : S/ "
                                                                                        + documento.getMONTO_PER()
                                                                                        + " - Total a cobrar: S/ "
                                                                                        + documento.getTOTAL_MONTO_PER()
                                                                        				+ ", Nro. Percepción : "
                                                                                        + ConsultarPercepcion(documento.getCIA(),
                                                                                                documento.getID_PLANTA(),
                                                                                                documento.getID_TIPO_DOC(), documento.getSERIE(),
                                                                                                documento.getNRO_DOCUMENTO())
                                                                                                + "</cbc:Note>" + xml.getSaltoLinea()));
		                                                        } else {
		                                                                xml.setDatos(((documento.getMONTO_PER() == null
                                                                                && documento.getPORC_PERCEPCION() == null
                                                                                && documento.getTOTAL_MONTO_PER() == null)
                                                                                ? xml.getDatos() + ""
                                                                                : xml.getDatos() + "<cbc:Note languageID=\"S\">"
                                                                                                + ((documento.getMONTO_PER() == null
                                                                                                                && documento.getPORC_PERCEPCION() == null
                                                                                                                && documento.getTOTAL_MONTO_PER() == null)
                                                                                                                                ? ""
                                                                                                                                : "Operación sujeta a percepción "
                                                                                                                                                + documento.getPORC_PERCEPCION()
                                                                                                                                                + " % de : S/ "
                                                                                                                                                + documento.getMONTO_PER()
                                                                                                                                                + " - Total a cobrar: S/ "
                                                                                                                                                + documento.getTOTAL_MONTO_PER())
		                                                                                                                + "</cbc:Note>"
		                                                                                                                + xml.getSaltoLinea()));
                                                        }
                                                        }
                                                } else {
                                                        xml.setDatos(((ConsultarPercepcion(documento.getCIA(),
                                                                        documento.getID_PLANTA(),
                                                                        documento.getID_TIPO_DOC(),
                                                                        documento.getSERIE(),
                                                                        documento.getNRO_DOCUMENTO()) == null)
                                                                                        ? xml.getDatos() + ""
                                                                                        : xml.getDatos() + "<cbc:Note languageID=\"S\">"
                                                                                                        + ConsultarPercepcion(
                                                                                                                        documento.getCIA(),
                                                                                                                        documento.getID_PLANTA(),
                                                                                                                        documento.getID_TIPO_DOC(),
                                                                                                                        documento.getSERIE(),
                                                                                                                        documento.getNRO_DOCUMENTO())
                                                                                                        + "</cbc:Note>"
                                                                                                        + xml.getSaltoLinea()));
                                                }
                                        }

                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:DocumentCurrencyCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 4217 Alpha\" listName=\"Currency\">"
                                                        + xml.getAbreviaturaMoneda() + "</cbc:DocumentCurrencyCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:LineCountNumeric>"
                                                        + documento.getCANTIDADITEM()
                                                        + "</cbc:LineCountNumeric>" + xml.getSaltoLinea());

                                        if ("N-C".equals(documento.getID_TIPO_DOC())
                                                        || "N-D".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(xml.getDatos() + "<cac:DiscrepancyResponse>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ReferenceID>" + xml.getTipoDoc()
                                                                + documento.getSERIE_REF()
                                                                + "-" + documento.getNRO_DOCUMENTO_REF()
                                                                + "</cbc:ReferenceID>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ResponseCode listAgencyName=\"PE:SUNAT\" listName=\""
                                                                + ("N-C".equals(documento.getID_TIPO_DOC())
                                                                                ? "Nota de credito"
                                                                                : "Nota de debito")
                                                                + "\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo"
                                                                + ("N-C".equals(documento.getID_TIPO_DOC()) ? "09"
                                                                                : "10")
                                                                + "\">"
                                                                + documento.getID_MOTIVO_TIPO_SUNAT()
                                                                + "</cbc:ResponseCode>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Description>"
                                                                + (documento.getDESCRIPCION_NOTA().trim() == ""
                                                                                ? documento.getID_MOTIVO_TIPO_SUNAT()
                                                                                                + " "
                                                                                                + documento.getMOTIVO_SUNAT()
                                                                                : documento.getDESCRIPCION_NOTA())
                                                                + "</cbc:Description>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DiscrepancyResponse>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:BillingReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:InvoiceDocumentReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>" + xml.getTipoDoc()
                                                                + documento.getSERIE_REF() + "-"
                                                                + documento.getNRO_DOCUMENTO_REF() + "</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:IssueDate>"
                                                                + ConsultarFechaFacturaREF(documento.getCIA(),
                                                                                documento.getID_TIPO_DOC_REF(),
                                                                                "0" + documento.getSERIE_REF(),
                                                                                documento.getNRO_DOCUMENTO_REF())
                                                                + "</cbc:IssueDate>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:DocumentTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Documento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01\">"
                                                                +
                                                                ("F".equals(xml.getTipoDoc())
                                                                                ? "01"
                                                                                : "03")
                                                                + "</cbc:DocumentTypeCode>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:InvoiceDocumentReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:BillingReference>"
                                                                + xml.getSaltoLinea());
                                        }

                                        if ("01".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20332711157</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>20332711157</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>PETROLEOS DE AMERICA S.A.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20332711157</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20332711157</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>PETROLEOS DE AMERICA S.A.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>PETROLEOS DE AMERICA S.A.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">150122</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0014</cbc:AddressTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CityName>LIMA                                                                                                              Razon Social: PETROLEOS DE AMERICA S.A.</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Line>AV. 28 DE JULIO 554</cbc:Line>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                        }
                                        if ("02".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20100005485</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>20100005485</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>DELTA GAS S.A.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20100005485</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20100005485</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>DELTA GAS S.A.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>DELTA GAS S.A.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">070101</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0003</cbc:AddressTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CityName>LIMA                                                                                                              Razon Social: DELTA GAS S.A.</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>CALLAO</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>CALLAO</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Line>AV. NESTOR GAMBETTA NRO. 4765</cbc:Line>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                        }
                                        if ("05".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20524417490</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>20524417490</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>AMERICA GAS S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20524417490</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20524417490</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>AMERICA GAS S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>AMERICA GAS S.A.C.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">150122</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0002</cbc:AddressTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CityName>LIMA                                                                                                              Razon Social: AMERICA GAS S.A.C.</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(
                                                                xml.getDatos() + "<cbc:Line>CAL.SAN MARTIN NRO. 583</cbc:Line>"
                                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                        }
                                        if ("06".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20600427734</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>20600427734</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20600427734</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20600427734</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">150122</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0000</cbc:AddressTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CityName>LIMA                                                                                                            Razon Social: HIDROCARBUROS DEL MUNDO S.A.C.</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Line>AV. BENAVIDES NRO. 620 INT. 703</cbc:Line>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                        }
                                        if ("07".equals(documento.getCIA())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>sign20602359981</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>20602359981</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>PUNTO GAS S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20602359981</cbc:URI>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ExternalReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">20602359981</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>PUNTO GAS S.A.C.</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:RegistrationName>PUNTO GAS S.A.C.</cbc:RegistrationName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">150103</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0001</cbc:AddressTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CityName>LIMA                                                                                                              Razon Social: PUNTO GAS S.A.C.</cbc:CityName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>ATE</cbc:District>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:Line>MARIE CURIE MZA. O LOTE. 3 URB. LOTIZACION INDUSTRIAL SANTA ROSA</cbc:Line>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                                + xml.getSaltoLinea());
                                        }

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingCustomerParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + " <cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\""
                                                        + ((11 == documento.getNRO_DI().length()) ? "6" : "1")
                                                        + "\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">"
                                                        + documento.getNRO_DI() + "</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>"
                                                                + documento.getDESCRIPCION_CLIENTE() + "</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        }
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:RegistrationName>"
                                                        + documento.getDESCRIPCION_CLIENTE()
                                                        + "</cbc:RegistrationName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:RegistrationAddress>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">"
                                                        + documento.getID_DISTRITO() + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>"
                                                        + documento.getDEPARTAMENTO_CLIENTE()
                                                        + "</cbc:CityName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CountrySubentity>"
                                                        + documento.getPROVINCIA_CLIENTE()
                                                        + "</cbc:CountrySubentity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>"
                                                        + documento.getPROVINCIA_CLIENTE()
                                                        + "</cbc:District>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:AddressLine>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Line>" + documento.getDIRECCION_CLIENTE()
                                                        + "</cbc:Line>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AddressLine>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode listAgencyName=\"United Nations Economic Commission for Europe\" listID=\"ISO 3166-1\" listName=\"Country\">PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:RegistrationAddress>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Contact>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ElectronicMail>"
                                                        + documento.getEMAIL_CLIENTE()
                                                        + "</cbc:ElectronicMail>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Contact>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingCustomerParty>"
                                                        + xml.getSaltoLinea());

                                        if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(xml.getDatos() + "<cac:Delivery>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Shipment>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeName=\"Motivo de Traslado\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo20\"/>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Information/>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:ShipmentStage>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Instructions/>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:CarrierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:SUNAT\" schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\"/>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:RegistrationName/>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CompanyID/>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:CarrierParty>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:TransportMeans>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:RegistrationNationalityID>"
                                                                + ((documento.getDEPART_DESTINO() == null) ? ""
                                                                                : documento.getPLANTAORIGEN())
                                                                + "</cbc:RegistrationNationalityID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:RoadTransport>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:LicensePlateID schemeName=\"\">2O</cbc:LicensePlateID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RoadTransport>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:TransportMeans>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:ShipmentStage>"
                                                                + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:Delivery>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:DeliveryAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">"
                                                                + ((documento.getUBIGEO_DESTINO() == null) ? ""
                                                                                : documento.getUBIGEO_DESTINO())
                                                                + "</cbc:ID>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CitySubdivisionName>2J</cbc:CitySubdivisionName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CityName>"
                                                                + ((documento.getDEPART_DESTINO() == null) ? ""
                                                                                : documento.getDEPART_DESTINO())
                                                                + "</cbc:CityName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CountrySubentity>"
                                                                + ((documento.getPROVIN_DESTINO() == null) ? ""
                                                                                : documento.getPROVIN_DESTINO())
                                                                + "</cbc:CountrySubentity>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>"
                                                                + ((documento.getDISTRI_DESTINO() == null) ? ""
                                                                                : documento.getDISTRI_DESTINO())
                                                                + "</cbc:District>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Line>"
                                                                + ((documento.getDIRECCION_DESTINO() == null) ? ""
                                                                                : documento.getDIRECCION_DESTINO())
                                                                + "</cbc:Line>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:DeliveryAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Delivery>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:OriginAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ID schemeAgencyName=\"PE:INEI\" schemeName=\"Ubigeos\">"
                                                                + ((documento.getUBIGEO_ORIGEN() == null) ? ""
                                                                                : documento.getUBIGEO_ORIGEN())
                                                                + "</cbc:ID>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:CitySubdivisionName>2C</cbc:CitySubdivisionName>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CityName>"
                                                                + ((documento.getDISTRI_ORIGEN() == null) ? ""
                                                                                : documento.getDISTRI_ORIGEN())
                                                                + "</cbc:CityName>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:CountrySubentity>"
                                                                + ((documento.getPROVIN_ORIGEN() == null) ? ""
                                                                                : documento.getPROVIN_ORIGEN())
                                                                + "</cbc:CountrySubentity>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:District>"
                                                                + ((documento.getDEPART_ORIGEN() == null) ? ""
                                                                                : documento.getDEPART_ORIGEN())
                                                                + "</cbc:District>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AddressLine> "
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Line>"
                                                                + ((documento.getDIRECCION_ORIGEN() == null) ? ""
                                                                                : documento.getDIRECCION_ORIGEN())
                                                                + "</cbc:Line>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AddressLine>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:OriginAddress>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Shipment>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "</cac:Delivery>" + xml.getSaltoLinea());
                                        }

                                        if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                if (documento.getFECHA().equals(documento.getFECHA_VENCE())) {
                                                        xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID >FormaPago</cbc:ID >"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:PaymentMeansID>Contado</cbc:PaymentMeansID>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                } else {
                                                        xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID >FormaPago</cbc:ID >"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:PaymentMeansID>Credito</cbc:PaymentMeansID>"
                                                                        + xml.getSaltoLinea());

                                                        // xml.setDatos(xml.getDatos()+"<cbc:Amount
                                                        // currencyID=\""+xml.getAbreviaturaMoneda()+"\">"+
                                                        // documento.getTOTAL()
                                                        // +"</cbc:Amount>"+xml.getSaltoLinea()); //BORRAR CUANDO PAGUEN
                                                        // - RETENCION
                                                        // CAMBIOS RETENCION - FALTA PAGAR
                                                        if (documento.getCIA().equals("07")) {
                                                                if ("1".equals(documento
                                                                                .getFLAG_ES_AGENTE_RETENEDOR())) {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:Amount currencyID=\""
                                                                                        + xml.getAbreviaturaMoneda()
                                                                                        + "\">"
                                                                                        + documento.getMONTO_RETENEDOR()
                                                                                        + "</cbc:Amount>"
                                                                                        + xml.getSaltoLinea());
                                                                } else {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:Amount currencyID=\""
                                                                                        + xml.getAbreviaturaMoneda()
                                                                                        + "\">" + documento.getTOTAL()
                                                                                        + "</cbc:Amount>"
                                                                                        + xml.getSaltoLinea());
                                                                }
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + documento.getTOTAL()
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        }

                                                        xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID >FormaPago</cbc:ID >"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:PaymentMeansID>Cuota001</cbc:PaymentMeansID>"
                                                                        + xml.getSaltoLinea());

                                                        // xml.setDatos(xml.getDatos() +"<cbc:Amount
                                                        // currencyID=\""+xml.getAbreviaturaMoneda()+"\">"+
                                                        // documento.getTOTAL()
                                                        // +"</cbc:Amount>"+xml.getSaltoLinea()); //BORRAR CUANDO PAGUEN
                                                        // - RETENCION
                                                        // CAMBIOS RETENCION - FALTA PAGAR
                                                        if (documento.getCIA().equals("07")) {
                                                                if ("1".equals(documento
                                                                                .getFLAG_ES_AGENTE_RETENEDOR())) {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:Amount currencyID=\""
                                                                                        + xml.getAbreviaturaMoneda()
                                                                                        + "\">"
                                                                                        + documento.getMONTO_RETENEDOR()
                                                                                        + "</cbc:Amount>"
                                                                                        + xml.getSaltoLinea());
                                                                } else {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:Amount currencyID=\""
                                                                                        + xml.getAbreviaturaMoneda()
                                                                                        + "\">" + documento.getTOTAL()
                                                                                        + "</cbc:Amount>"
                                                                                        + xml.getSaltoLinea());
                                                                }
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + documento.getTOTAL()
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        }

                                                        xml.setDatos(xml.getDatos() + "<cbc:PaymentDueDate>"
                                                                        + documento.getFECHA_VENCE()
                                                                        + "</cbc:PaymentDueDate>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                }

                                        } else if ("N-C".equals(documento.getID_TIPO_DOC())) {
                                                String fechaI = ConsultarFechaFacturaREF(documento.getCIA(),
                                                                documento.getID_TIPO_DOC_REF(),
                                                                "0" + documento.getSERIE_REF(),
                                                                documento.getNRO_DOCUMENTO_REF());
                                                String fechaF = ConsultarFechaFacturaREF_FECHA_FIN(documento.getCIA(),
                                                                documento.getID_TIPO_DOC_REF(),
                                                                "0" + documento.getSERIE_REF(),
                                                                documento.getNRO_DOCUMENTO_REF());
                                                if (!fechaI.equals(fechaF)) {
                                                        String fechaCuota = agregarUnaFecha(documento.getFECHA());
                                                        xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID >FormaPago</cbc:ID >"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:PaymentMeansID>Credito</cbc:PaymentMeansID>"
                                                                        + xml.getSaltoLinea());
                                                        // NOTA! -> CUANDO EL CODIGO ES 13, NO PUEDE IR UNA CUOTA A
                                                        // MONTO 0, DEBEMOS
                                                        // COLOCARLE AL MENOS 0.01
                                                        String totalRefrerencia = ConsultarPrecioFacturaREF(
                                                                        documento.getCIA(),
                                                                        documento.getID_TIPO_DOC_REF(),
                                                                        "0" + documento.getSERIE_REF(),
                                                                        documento.getNRO_DOCUMENTO_REF());
                                                        if (documento.getID_MOTIVO_TIPO_SUNAT().equals("13")) {
                                                                System.out.print(totalRefrerencia);
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + totalRefrerencia
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + documento.getTOTAL()
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                        xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());

                                                        xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID >FormaPago</cbc:ID >"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:PaymentMeansID>Cuota001</cbc:PaymentMeansID>"
                                                                        + xml.getSaltoLinea());
                                                        if (documento.getID_MOTIVO_TIPO_SUNAT().equals("13")) {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + totalRefrerencia
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">" + documento.getTOTAL()
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                        }

                                                        if ("07".equals(documento.getCIA())) {
                                                                if (documento.getFECHA().equals(
                                                                                ((documento.getFECHA_VENCE() == null)
                                                                                                ? documento.getFECHA()
                                                                                                : documento.getFECHA_VENCE()))) {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:PaymentDueDate>"
                                                                                        + fechaCuota
                                                                                        + "</cbc:PaymentDueDate>"
                                                                                        + xml.getSaltoLinea());
                                                                } else {
                                                                        xml.setDatos(xml.getDatos()
                                                                                        + "<cbc:PaymentDueDate>" +
                                                                                        documento.getFECHA_VENCE()
                                                                                        + "</cbc:PaymentDueDate>"
                                                                                        + xml.getSaltoLinea());
                                                                }
                                                        } else {
                                                                xml.setDatos(xml.getDatos() + "<cbc:PaymentDueDate>"
                                                                                + fechaCuota
                                                                                + "</cbc:PaymentDueDate>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                        xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>"
                                                                        + xml.getSaltoLinea());
                                                }
                                        }

                                        // CAMBIOS RETENCION - FALTA PAGAR (YA SE PAGO)
                                        if ("FAC".equals(documento.getID_TIPO_DOC())
                                                        && "1".equals(documento.getFLAG_ES_AGENTE_RETENEDOR())
                                                        && documento.getCIA().equals("07")) {
                                                xml.setDatos(xml.getDatos() + "<cac:AllowanceCharge>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:ChargeIndicator>false</cbc:ChargeIndicator>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:AllowanceChargeReasonCode listAgencyName=\"PE:SUNAT\" listName=\"Cargo/descuento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53\" >62</cbc:AllowanceChargeReasonCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:MultiplierFactorNumeric>"
                                                                + Float.parseFloat(documento.getPORC_NOS_RETIENEN())
                                                                                / 100
                                                                + "</cbc:MultiplierFactorNumeric>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Amount currencyID=\"PEN\">"
                                                                + documento.getMONTO_PORC_RETENCION() + "</cbc:Amount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:BaseAmount currencyID=\"PEN\">"
                                                                + documento.getTOTAL_MONTO_PER() + "</cbc:BaseAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AllowanceCharge>"
                                                                + xml.getSaltoLinea());
                                        }

                                        // CAMBIOS PERCEPCION
                                        if ("06".equals(documento.getCIA())) {
                                                if ("2001".equals(estadoPER)) {
                                                        /*
                                                         * xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:ID>Percepcion</cbc:ID>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:Amount currencyID=\"" +
                                                         * xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getTOTAL_MONTO_PER() + "</cbc:Amount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>" +
                                                         * xml.getSaltoLinea());
                                                         * 
                                                         * xml.setDatos(xml.getDatos() + "<cac:AllowanceCharge>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() +
                                                         * "<cbc:ChargeIndicator>true</cbc:ChargeIndicator>"
                                                         * + xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos()
                                                         * +
                                                         * "<cbc:AllowanceChargeReasonCode listAgencyName=\"PE:SUNAT\" listName=\"Cargo/descuento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53\">52</cbc:AllowanceChargeReasonCode>"
                                                         * + xml.getSaltoLinea());
                                                         * xml.setDatos(
                                                         * xml.getDatos() + "<cbc:MultiplierFactorNumeric>" +
                                                         * documento.getPORC_PERCEPCION()
                                                         * + "</cbc:MultiplierFactorNumeric>" + xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:Amount currencyID=\"" +
                                                         * xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getMONTO_PER() + "</cbc:Amount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:BaseAmount currencyID=\""
                                                         * + xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getTOTAL() + "</cbc:BaseAmount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "</cac:AllowanceCharge>" +
                                                         * xml.getSaltoLinea());
                                                         */
                                                }

                                        } else {
                                                if ((Double.parseDouble(documento.getMONTO_PER())) == 0.0) {
                                                } else {
                                                        /*
                                                         * xml.setDatos(xml.getDatos() + "<cac:PaymentTerms>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:ID>Percepcion</cbc:ID>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:Amount currencyID=\"" +
                                                         * xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getTOTAL_MONTO_PER() + "</cbc:Amount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "</cac:PaymentTerms>" +
                                                         * xml.getSaltoLinea());
                                                         * 
                                                         * xml.setDatos(xml.getDatos() + "<cac:AllowanceCharge>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() +
                                                         * "<cbc:ChargeIndicator>true</cbc:ChargeIndicator>"
                                                         * + xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos()
                                                         * +
                                                         * "<cbc:AllowanceChargeReasonCode listAgencyName=\"PE:SUNAT\" listName=\"Cargo/descuento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53\">52</cbc:AllowanceChargeReasonCode>"
                                                         * + xml.getSaltoLinea());
                                                         * xml.setDatos(
                                                         * xml.getDatos() + "<cbc:MultiplierFactorNumeric>" +
                                                         * documento.getPORC_PERCEPCION()
                                                         * + "</cbc:MultiplierFactorNumeric>" + xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:Amount currencyID=\"" +
                                                         * xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getMONTO_PER() + "</cbc:Amount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "<cbc:BaseAmount currencyID=\""
                                                         * + xml.getAbreviaturaMoneda()
                                                         * + "\">" + documento.getTOTAL() + "</cbc:BaseAmount>" +
                                                         * xml.getSaltoLinea());
                                                         * xml.setDatos(xml.getDatos() + "</cac:AllowanceCharge>" +
                                                         * xml.getSaltoLinea());
                                                         */
                                                }
                                        }

                                        xml.setDatos(xml.getDatos() + "<cac:TaxTotal>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:TaxAmount currencyID=\""
                                                        + xml.getAbreviaturaMoneda() + "\">"
                                                        + documento.getIGV() + "</cbc:TaxAmount>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:TaxSubtotal>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:TaxableAmount currencyID=\""
                                                        + xml.getAbreviaturaMoneda()
                                                        + "\">" + documento.getSUBTOTAL() + "</cbc:TaxableAmount>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:TaxAmount currencyID=\""
                                                        + xml.getAbreviaturaMoneda() + "\">"
                                                        + documento.getIGV() + "</cbc:TaxAmount>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:TaxCategory>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:TaxScheme>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:ID schemeAgencyID=\"6\" schemeID=\"UN/ECE 5153\">1000</cbc:ID>"
                                                        + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cbc:Name>IGV</cbc:Name>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:TaxScheme>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:TaxCategory>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:TaxSubtotal>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:TaxTotal>" + xml.getSaltoLinea());
                                        if ("N-D".equals(documento.getID_TIPO_DOC())) {
                                                xml.setDatos(xml.getDatos() + "<cac:RequestedMonetaryTotal>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:LineExtensionAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                + documento.getSUBTOTAL()
                                                                + "</cbc:LineExtensionAmount>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxInclusiveAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                + documento.getTOTAL()
                                                                + "</cbc:TaxInclusiveAmount>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:PayableAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documento.getTOTAL() + "</cbc:PayableAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:RequestedMonetaryTotal>"
                                                                + xml.getSaltoLinea());

                                        } else {
                                                xml.setDatos(xml.getDatos() + "<cac:LegalMonetaryTotal>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:LineExtensionAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                + documento.getSUBTOTAL()
                                                                + "</cbc:LineExtensionAmount>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxInclusiveAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                + documento.getTOTAL()
                                                                + "</cbc:TaxInclusiveAmount>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:PayableAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documento.getTOTAL() + "</cbc:PayableAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:LegalMonetaryTotal>"
                                                                + xml.getSaltoLinea());

                                        }

                                        DocumentoDetDao documentoDaoDet = new DocumentoDetDaoImpl();
                                        List<DocumentoDet> documentosDets = documentoDaoDet.listar(documento.getCIA(),
                                                        documento.getID_PLANTA(), documento.getID_TIPO_DOC(),
                                                        documento.getSERIE(),
                                                        documento.getNRO_DOCUMENTO());

                                        documentosDets.forEach((documentosDet) -> {
                                                xml.setDatos(xml.getDatos() + "<cac:" + xml.getDescripcion() + "Line>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:ID>" + documentosDet.getITEM()
                                                                + "</cbc:ID>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Note>"
                                                                + documentosDet.getID_UNIDAD() + "</cbc:Note>"
                                                                + xml.getSaltoLinea());
                                                if ("N-C".equals(documento.getID_TIPO_DOC())) {
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:CreditedQuantity unitCode=\"ZZ\" unitCodeListAgencyName=\"United Nations Economic Commission for Europe\" unitCodeListID=\"UN/ECE rec 20\">"
                                                                        + documentosDet.getCANTIDAD_FAC()
                                                                        + "</cbc:CreditedQuantity>"
                                                                        + xml.getSaltoLinea());
                                                } else if ("N-D".equals(documento.getID_TIPO_DOC())) {
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:DebitedQuantity unitCode=\"ZZ\" unitCodeListAgencyName=\"United Nations Economic Commission for Europe\" unitCodeListID=\"UN/ECE rec 20\">"
                                                                        + documentosDet.getCANTIDAD_FAC()
                                                                        + "</cbc:DebitedQuantity>"
                                                                        + xml.getSaltoLinea());
                                                } else {
                                                        xml.setDatos(xml.getDatos()
                                                                        + "<cbc:InvoicedQuantity unitCode=\"ZZ\" unitCodeListAgencyName=\"United Nations Economic Commission for Europe\" unitCodeListID=\"UN/ECE rec 20\">"
                                                                        + documentosDet.getCANTIDAD_FAC()
                                                                        + "</cbc:InvoicedQuantity>"
                                                                        + xml.getSaltoLinea());
                                                }
                                                xml.setDatos(xml.getDatos() + "<cbc:LineExtensionAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                + documentosDet.getSUBTOTAL_CON_DESCUENTO()
                                                                + "</cbc:LineExtensionAmount>" + xml.getSaltoLinea());
                                                if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                        xml.setDatos(xml.getDatos() + "<cac:BillingReference>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID schemeID=\"AN\">"
                                                                        + documentosDet.getTEMPERATURA()
                                                                        + "</cbc:ID>" + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID schemeID=\"AO\">"
                                                                        + documentosDet.getAPI()
                                                                        + "</cbc:ID>" + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "<cbc:ID schemeID=\"AA\">"
                                                                        + documentosDet.getTOTAL()
                                                                        + "</cbc:ID>" + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:BillingReferenceLine>"
                                                                        + xml.getSaltoLinea());
                                                        xml.setDatos(xml.getDatos() + "</cac:BillingReference>"
                                                                        + xml.getSaltoLinea());
                                                }
                                                xml.setDatos(xml.getDatos() + "<cac:PricingReference>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:AlternativeConditionPrice>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:PriceAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documentosDet.getSUBTOTAL_CON_DESCUENTO_IGV()
                                                                + "</cbc:PriceAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:PriceTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Precio\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16\">01</cbc:PriceTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:AlternativeConditionPrice>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:PricingReference>"
                                                                + xml.getSaltoLinea());
                                                if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                        if ("0.00".equals(documentosDet.getPRECIO_DESCUENTO())) {
                                                        } else {
                                                                xml.setDatos(xml.getDatos() + "<cac:AllowanceCharge>"
                                                                                + xml.getSaltoLinea());
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:ChargeIndicator>false</cbc:ChargeIndicator>"
                                                                                + xml.getSaltoLinea());
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:AllowanceChargeReasonCode listAgencyName=\"PE:SUNAT\" listName=\"Cargo/descuento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53\">00</cbc:AllowanceChargeReasonCode>"
                                                                                + xml.getSaltoLinea());
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:MultiplierFactorNumeric>"
                                                                                + documentosDet.getPRECIO_DESCUENTO()
                                                                                + "</cbc:MultiplierFactorNumeric>"
                                                                                + xml.getSaltoLinea());
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:Amount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda()
                                                                                + "\">"
                                                                                + documentosDet.getMONTO_DESCUENTO()
                                                                                + "</cbc:Amount>"
                                                                                + xml.getSaltoLinea());
                                                                DecimalFormat df = new DecimalFormat("#.00");
                                                                Double BaseAmount = Double.parseDouble(
                                                                                documentosDet.getMONTO_DESCUENTO())
                                                                                / Double.parseDouble(documentosDet
                                                                                                .getPRECIO_DESCUENTO());
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:BaseAmount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                                + df.format(BaseAmount)
                                                                                + "</cbc:BaseAmount>"
                                                                                + xml.getSaltoLinea());
                                                                xml.setDatos(xml.getDatos() + "</cac:AllowanceCharge>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                }
                                                xml.setDatos(xml.getDatos() + "<cac:TaxTotal>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documentosDet.getIGV() + "</cbc:TaxAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:TaxSubtotal>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxableAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documentosDet.getSUBTOTAL_CON_DESCUENTO()
                                                                + "</cbc:TaxableAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxAmount currencyID=\""
                                                                + xml.getAbreviaturaMoneda()
                                                                + "\">" + documentosDet.getIGV() + "</cbc:TaxAmount>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:TaxCategory>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Percent>"
                                                                + documentosDet.getPORC_IGV() + "</cbc:Percent>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos()
                                                                + "<cbc:TaxExemptionReasonCode listAgencyName=\"PE:SUNAT\" listName=\"Afectacion del IGV\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07\">10</cbc:TaxExemptionReasonCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cac:TaxScheme>" + xml.getSaltoLinea());
                                                xml.setDatos(
                                                                xml.getDatos() + "<cbc:ID schemeAgencyID=\"6\" schemeID=\"UN/ECE 5153\">1000</cbc:ID>"
                                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Name>IGV</cbc:Name>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:TaxScheme>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:TaxCategory>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:TaxSubtotal>"
                                                                + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:TaxTotal>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:Item>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "<cbc:Description>"
                                                                + documentosDet.getDESCRIPCION_PRODUCTO()
                                                                + "</cbc:Description>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:Item>" + xml.getSaltoLinea());

                                                xml.setDatos(xml.getDatos() + "<cac:Price>" + xml.getSaltoLinea());
                                                if ("BOL".equals(documento.getID_TIPO_DOC())) {
                                                        xml.setDatos(xml.getDatos() + "<cbc:PriceAmount currencyID=\""
                                                                        + xml.getAbreviaturaMoneda()
                                                                        + "\">" + documentosDet.getPRECIO_UNITARIO()
                                                                        + "</cbc:PriceAmount>"
                                                                        + xml.getSaltoLinea());
                                                } else if ("FAC".equals(documento.getID_TIPO_DOC())) {
                                                        xml.setDatos(xml.getDatos() + "<cbc:PriceAmount currencyID=\""
                                                                        + xml.getAbreviaturaMoneda()
                                                                        + "\">" + documentosDet.getPRECIO_UNITARIO()
                                                                        + "</cbc:PriceAmount>"
                                                                        + xml.getSaltoLinea());
                                                } else if ("N-C".equals(documento.getID_TIPO_DOC())) {
                                                        if ("1.000".equals(documentosDet.getCANTIDAD_FAC())) {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:PriceAmount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                                + documentosDet.getSUBTOTAL_CON_DESCUENTO()
                                                                                + "</cbc:PriceAmount>"
                                                                                + xml.getSaltoLinea());
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:PriceAmount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                                + documentosDet.getPRECIO_UNITARIO()
                                                                                + "</cbc:PriceAmount>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                } else if ("N-D".equals(documento.getID_TIPO_DOC())) {
                                                        if ("1.000".equals(documentosDet.getCANTIDAD_FAC())) {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:PriceAmount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                                + documentosDet.getSUBTOTAL_CON_DESCUENTO()
                                                                                + "</cbc:PriceAmount>"
                                                                                + xml.getSaltoLinea());
                                                        } else {
                                                                xml.setDatos(xml.getDatos()
                                                                                + "<cbc:PriceAmount currencyID=\""
                                                                                + xml.getAbreviaturaMoneda() + "\">"
                                                                                + documentosDet.getPRECIO_UNITARIO()
                                                                                + "</cbc:PriceAmount>"
                                                                                + xml.getSaltoLinea());
                                                        }
                                                } else {

                                                }
                                                xml.setDatos(xml.getDatos() + "</cac:Price>" + xml.getSaltoLinea());
                                                xml.setDatos(xml.getDatos() + "</cac:" + xml.getDescripcion() + "Line>"
                                                                + xml.getSaltoLinea());
                                        });
                                        xml.setDatos(xml.getDatos() + "</" + xml.getDescripcion() + ">");
                                        if (xml.getListaNombreArchivo() == null) {
                                                xml.setListaNombreArchivo(xml.getNombreArchivo()
                                                                + documento.getID_PLANTA()
                                                                + documento.getID_TIPO_DOC() + xml.getSaltoLinea());
                                        } else {
                                                xml.setListaNombreArchivo(xml.getListaNombreArchivo()
                                                                + xml.getNombreArchivo()
                                                                + documento.getID_PLANTA() + documento.getID_TIPO_DOC()
                                                                + xml.getSaltoLinea());
                                        }
                                        // ABAJO TERMINA EL ELSE
                                }
                                ImprimirXml(xml.getNombreArchivo() + ".xml", xml.getDatos());
                        }
                        ImprimirXml("ADMIN.txt", xml.getListaNombreArchivo());
                        System.out.println("Proceso terminado");

                } catch (SQLException ex) {
                        Logger.getLogger(FrameEnviarAutomatico.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage() + ex);
                        System.out.println("no se creo " + ex);
                }
        }

        public static void GenerarBajas() {
                String FechaParseada = "";
                try {
                        DocumentoDao documentoDaoBaja = new DocumentoDaoImpl();
                        List<Documento> documentosBaja = documentoDaoBaja.listarbajas();
                        Xml xml = new Xml();
                        xml.setDescripcion("VoidedDocuments");
                        for (Documento documento : documentosBaja) {
                                xml.setCodigo("01");
                                xml.setSaltoLinea("\n");
                                xml.setDatos("");
                                switch (documento.getID_TIPO_DOC()) {
                                        case "FAC":
                                                xml.setCodigo("01");
                                                xml.setTipoDoc(documento.getID_TIPO_DOC().substring(0, 1));
                                                break;
                                        case "BOL":
                                                xml.setCodigo("03");
                                                xml.setTipoDoc(documento.getID_TIPO_DOC().substring(0, 1));
                                                break;
                                        case "N/C":
                                                xml.setCodigo("07");
                                                xml.setTipoDoc("F");
                                                break;
                                        case "N/D":
                                                xml.setCodigo("08");
                                                xml.setTipoDoc("F");
                                                break;
                                        case "PER":
                                                xml.setCodigo("40");
                                                xml.setTipoDoc("P");
                                                break;
                                        default:
                                                xml.setCodigo("DEFAULT");
                                                xml.setDescripcion("DEFAULT");
                                                xml.setTipoDoc("DEFAULT");
                                                break;
                                }
                                SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
                                Date date;
                                try {
                                        date = parseador.parse(documento.getFECHA());
                                        FechaParseada = formateador.format(date);

                                        switch (documento.getCIA()) {
                                                case "01":
                                                        if ("PER".equals(documento.getID_TIPO_DOC())) {
                                                                xml.setNombreArchivo("20332711157" + "-RR-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        } else {
                                                                xml.setNombreArchivo("20332711157" + "-RA-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        }
                                                        break;
                                                case "02":
                                                        if ("PER".equals(documento.getID_TIPO_DOC())) {
                                                                xml.setNombreArchivo("20100005485" + "-RR-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        } else {
                                                                xml.setNombreArchivo("20100005485" + "-RA-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        }
                                                        break;
                                                case "05":
                                                        if ("PER".equals(documento.getID_TIPO_DOC())) {
                                                                xml.setNombreArchivo("20524417490" + "-RR-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        } else {
                                                                xml.setNombreArchivo("20524417490" + "-RA-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        }
                                                        break;
                                                case "06":
                                                        if ("PER".equals(documento.getID_TIPO_DOC())) {
                                                                xml.setNombreArchivo("20600427734" + "-RR-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        } else {
                                                                xml.setNombreArchivo("20600427734" + "-RA-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        }
                                                        break;
                                                case "07":
                                                        if ("PER".equals(documento.getID_TIPO_DOC())) {
                                                                xml.setNombreArchivo("20602359981" + "-RR-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        } else {
                                                                xml.setNombreArchivo("20602359981" + "-RA-"
                                                                                + FechaParseada + "-"
                                                                                + Integer.parseInt(documento.getSERIE()
                                                                                                .substring(3, 4))
                                                                                + Integer.parseInt(documento
                                                                                                .getNRO_DOCUMENTO()
                                                                                                .substring(4, 8)));
                                                        }
                                                        break;
                                                default:
                                                        xml.setNombreArchivo("");
                                                        break;
                                        }

                                } catch (ParseException ex) {
                                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                xml.setDatos("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<VoidedDocuments\n"
                                                + "xmlns:sac=\"urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1\" \n"
                                                + "xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" \n"
                                                + "xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\"\n"
                                                + "xmlns=\"urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1\"\n"
                                                + "xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\">"
                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<cbc:UBLVersionID>2.0</cbc:UBLVersionID>"
                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<cbc:CustomizationID>1.0</cbc:CustomizationID>"
                                                + xml.getSaltoLinea());
                                if ("PER".equals(documento.getID_TIPO_DOC())) {
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>RR-" + FechaParseada + "-"
                                                        + Integer.parseInt(documento.getSERIE().substring(3, 4))
                                                        + Integer.parseInt(documento.getNRO_DOCUMENTO().substring(4, 8))
                                                        + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                } else {
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>RA-" + FechaParseada + "-"
                                                        + Integer.parseInt(documento.getSERIE().substring(3, 4))
                                                        + Integer.parseInt(documento.getNRO_DOCUMENTO().substring(4, 8))
                                                        + "</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                }
                                xml.setDatos(xml.getDatos() + "<cbc:ReferenceDate>" + documento.getFECHA()
                                                + "</cbc:ReferenceDate>"
                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<cbc:IssueDate>" + documento.getFECHA()
                                                + "</cbc:IssueDate>"
                                                + xml.getSaltoLinea());
                                if ("01".equals(documento.getCIA())) {
                                        xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>sign20332711157</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>20332711157</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:Name>PETROLEOS DE AMERICA S.A.</cbc:Name>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20332711157</cbc:URI>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CustomerAssignedAccountID>20332711157</cbc:CustomerAssignedAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:Name>PETROLEOS DE AMERICA S.A.</cbc:Name>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>150122</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:StreetName>AV. 28 DE JULIO 554</cbc:StreetName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:RegistrationName>PETROLEOS DE AMERICA S.A.</cbc:RegistrationName>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                }
                                if ("02".equals(documento.getCIA())) {
                                        xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>sign20100005485</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>20100005485</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>DELTA GAS S.A.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20100005485</cbc:URI>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CustomerAssignedAccountID>20100005485</cbc:CustomerAssignedAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>DELTA GAS S.A.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>070101</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:StreetName>AV. NESTOR GAMBETTA NRO. 4765</cbc:StreetName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CountrySubentity>CALLAO</cbc:CountrySubentity>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>CALLAO</cbc:District>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:RegistrationName>DELTA GAS S.A.</cbc:RegistrationName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                }
                                if ("05".equals(documento.getCIA())) {
                                        xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>sign20524417490</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>20524417490</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>AMERICA GAS S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20524417490</cbc:URI>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CustomerAssignedAccountID>20524417490</cbc:CustomerAssignedAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>AMERICA GAS S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>150103</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:StreetName>CAL.MARIE CURIE MZA. O LOTE. 3 Z.I. SANTA ROSA</cbc:StreetName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>ATE</cbc:District>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:RegistrationName>AMERICA GAS S.A.C.</cbc:RegistrationName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                }
                                if ("06".equals(documento.getCIA())) {
                                        xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>sign20600427734</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>20600427734</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20600427734</cbc:URI>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CustomerAssignedAccountID>20600427734</cbc:CustomerAssignedAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:Name>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>150122</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:StreetName>AV. BENAVIDES NRO. 620 INT. 703</cbc:StreetName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>MIRAFLORES</cbc:District>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:RegistrationName>HIDROCARBUROS DEL MUNDO S.A.C.</cbc:RegistrationName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                }
                                if ("07".equals(documento.getCIA())) {
                                        xml.setDatos(xml.getDatos() + "<cac:Signature>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>sign20602359981</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>20602359981</cbc:ID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyIdentification>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>PUNTO GAS S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:SignatoryParty>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:URI>#sign20602359981</cbc:URI>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:ExternalReference>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:DigitalSignatureAttachment>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Signature>" + xml.getSaltoLinea());

                                        xml.setDatos(xml.getDatos() + "<cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:CustomerAssignedAccountID>20602359981</cbc:CustomerAssignedAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:AdditionalAccountID>6</cbc:AdditionalAccountID>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:Name>PUNTO GAS S.A.C.</cbc:Name>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyName>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:ID>150103</cbc:ID>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:StreetName>MARIE CURIE MZA. O LOTE. 3 URB. LOTIZACION INDUSTRIAL SANTA ROSA</cbc:StreetName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:CityName>LIMA</cbc:CityName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(
                                                        xml.getDatos() + "<cbc:CountrySubentity>LIMA</cbc:CountrySubentity>"
                                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cbc:District>ATE</cbc:District>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:IdentificationCode>PE</cbc:IdentificationCode>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Country>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PostalAddress>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "<cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos()
                                                        + "<cbc:RegistrationName>PUNTO GAS S.A.C.</cbc:RegistrationName>"
                                                        + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:PartyLegalEntity>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:Party>" + xml.getSaltoLinea());
                                        xml.setDatos(xml.getDatos() + "</cac:AccountingSupplierParty>"
                                                        + xml.getSaltoLinea());
                                }
                                xml.setDatos(xml.getDatos() + "<sac:VoidedDocumentsLine>" + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<cbc:LineID>1</cbc:LineID>" + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<cbc:DocumentTypeCode>" + xml.getCodigo()
                                                + "</cbc:DocumentTypeCode>"
                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<sac:DocumentSerialID>" + xml.getTipoDoc()
                                                + (documento.getSERIE().substring(1, 4)) + "</sac:DocumentSerialID>"
                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "<sac:DocumentNumberID>" + documento.getNRO_DOCUMENTO()
                                                + "</sac:DocumentNumberID>" + xml.getSaltoLinea());
                                xml.setDatos(
                                                xml.getDatos() + "<sac:VoidReasonDescription>ANULACION DE DOCUMENTO</sac:VoidReasonDescription>"
                                                                + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "</sac:VoidedDocumentsLine>" + xml.getSaltoLinea());
                                xml.setDatos(xml.getDatos() + "</" + xml.getDescripcion() + ">");
                                if (xml.getListaNombreArchivo() == null) {
                                        xml.setListaNombreArchivo(xml.getNombreArchivo() + xml.getSaltoLinea());
                                } else {
                                        xml.setListaNombreArchivo(
                                                        xml.getListaNombreArchivo() + xml.getNombreArchivo()
                                                                        + xml.getSaltoLinea());
                                }
                                ImprimirXml(xml.getNombreArchivo() + ".xml", xml.getDatos());
                                System.err.println(xml.getNombreArchivo());

                        }
                        ImprimirXml("ADMIN.txt", xml.getListaNombreArchivo());
                } catch (SQLException ex) {
                        Logger.getLogger(FrameEnviarAutomatico.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        public static boolean ImprimirXml(String nombre, String Contenido) {
                boolean EstadoArchivo = false;
                try {
                        String ruta = Datos.RutaDocumentos + nombre;
                        File file = new File(ruta);
                        Boolean exists = file.exists();
                        if (exists == true) {
                                file.setExecutable(true);
                                file.setReadable(true);
                                file.setWritable(true);
                                file.delete();
                                // System.out.println("borrado");
                        }
                        
                        OutputStream fw = new FileOutputStream(file);
                        fw.write(Contenido.getBytes(Charset.forName("UTF-8")));
                        fw.close();
                       
                        EstadoArchivo = true;
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        EstadoArchivo = false;
                }
                return EstadoArchivo;
        }

        public static String ConsultarFechaFacturaREF(String cia, String id_tipo_doc, String Serie, String Numero) {
                String Respuesta = "";
                try {
                        String ArmarConsulta = "SELECT convert(varchar(10), FECHA, 120) as FECHA FROM DOCUMENTO WHERE CIA=? AND ID_TIPO_DOC=? AND SERIE=? AND \n"
                                        + " NRO_DOCUMENTO=?";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, id_tipo_doc);
                        Sql.SQLpss.setString(3, Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String agregarUnaFecha(String fecha) {
                String Respuesta = "";
                try {
                        String ArmarConsulta = "SELECT convert(varchar(10), DATEADD(DAY, 1, ?) , 120) as FECHA ";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, fecha);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String ContarItems(String cia, String id_planta, String id_tipo_doc, String Serie,
                        String Numero) {
                String Respuesta = "";
                try {
                        String ArmarConsulta = "select COUNT(ITEM) from DOCUMENTO_DET where CIA=? and ID_PLANTA=? and ID_TIPO_DOC=?\n"
                                        + "and SERIE=? and NRO_DOCUMENTO=? and ID_ESTADO='01'";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, id_planta);
                        Sql.SQLpss.setString(3, id_tipo_doc);
                        Sql.SQLpss.setString(4, "0" + Serie);
                        Sql.SQLpss.setString(5, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String ConsultarFechaFacturaREF_FECHA_FIN(String cia, String id_tipo_doc, String Serie,
                        String Numero) {
                String Respuesta = "";
                try {
                        String ArmarConsulta = "SELECT convert(varchar(10), FECHA_VENCIMIENTO, 120) as FECHA_F FROM DOCUMENTO WHERE CIA=? AND ID_TIPO_DOC=? AND SERIE=? AND \n"
                                        + " NRO_DOCUMENTO=?";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, id_tipo_doc);
                        Sql.SQLpss.setString(3, Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String ConsultarPrecioFacturaREF(String cia, String id_tipo_doc, String Serie, String Numero) {
                String Respuesta = "";
                try {
                        String ArmarConsulta = "SELECT TOTAL FROM DOCUMENTO WHERE CIA=? AND ID_TIPO_DOC=? AND SERIE=? AND \n"
                                        + " NRO_DOCUMENTO=?";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, id_tipo_doc);
                        Sql.SQLpss.setString(3, Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String ConsultarPercepcion(String cia, String planta, String id_tipo_doc, String Serie,
                        String Numero) {
                String Respuesta = null;
                try {
                        /*
                         * String ArmarConsulta =
                         * "SELECT convert(varchar(10), FECHA, 120) as FECHA FROM DOCUMENTO WHERE CIA='"
                         * +cia+"' AND ID_TIPO_DOC='"+id_tipo_doc+"' AND SERIE='"+Serie+"' AND \n"
                         * + " NRO_DOCUMENTO='"+Numero+"'";
                         */
                        String ArmarConsulta = "select SERIE+'-'+nro_documento from DOCUMENTO where CIA=? and ID_PLANTA_REF=? and ID_TIPO_DOC_REF=?\n"
                                        + "and SERIE_REF=? and NRO_DOCUMENTO_REF=? and ID_TIPO_DOC='PER'";
                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, id_tipo_doc);
                        Sql.SQLpss.setString(4, "0" + Serie);
                        Sql.SQLpss.setString(5, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();
                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();
                        // System.out.println(ArmarConsulta);
                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                return Respuesta;
        }

        public static String ConsultarTipoDumentoReferencia(String cia, String planta, String Serie, String id_tipo_doc,
                        String Numero_doc) {
                String Respuesta = null;
                try {
                        String ArmarConsulta = "select ID_TIPO_DOC_REF from DOCUMENTO where CIA=? and ID_PLANTA=? and SERIE=?\n"
                                        + " and ID_TIPO_DOC=? and NRO_DOCUMENTO=?";

                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, "0" + Serie);
                        Sql.SQLpss.setString(4, id_tipo_doc);
                        Sql.SQLpss.setString(5, Numero_doc);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }

                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }

                return Respuesta;
        }

        public static String ConsultarFechaDocumentoReferencia(String cia, String planta, String Serie, String Numero) {
                String Respuesta = null;
                try {
                        String ArmarConsulta = "select convert(varchar(10), FECHA, 120)AS FECHA from DOCUMENTO where CIA=? and ID_PLANTA=? \n"
                                        + "and SERIE=? and NRO_DOCUMENTO=? and ID_TIPO_DOC='FAC'";

                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, "0" + Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }

                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }

                return Respuesta;
        }

        public static String ConsultarNroPago(String cia, String planta, String Serie, String Numero) {
                String Respuesta = null;
                try {
                        String ArmarConsulta = "SELECT COUNT(NRO_DOCUMENTO) AS ID_NRO FROM DOCUMENTO where CIA=? and ID_PLANTA=? \n"
                                        + "and SERIE_REF=? and NRO_DOCUMENTO_REF=? and ID_TIPO_DOC_REF='FAC'";

                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, "0" + Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }

                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }

                return Respuesta;
        }

        public static String consultarTipoCambio(String fecha) {
                String respuesta = "";
                try {
                        String Consulta = "SELECT top 1 FACTOR_DESTINO_VENTA  FROM TIPO_CAMBIO  WHERE   CIA = '06' AND ID_ESTADO = '01' and FECHA = '"
                                        + fecha + "' ORDER BY FECHA DESC";
                        Sql.SQLpss = cadena_conexion().prepareStatement(Consulta);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                respuesta = Sql.SQLrss.getString(1);
                        }
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        System.out.println(ex);
                }

                return respuesta;
        }

        public static String ConsultarPORC_PERCEPCION(String cia, String planta, String Serie, String Numero) {
                System.out.println(cia + " " + planta + " " + Serie + " " + Numero);
                String Respuesta = null;
                try {
                        String ArmarConsulta = "select case when PORC_PERCEPCION=2 then '0.02' when PORC_PERCEPCION=1 then '0.01' when PORC_PERCEPCION=0.5 then '0.005' end AS PORC_PERCEPCION  from DOCUMENTO where CIA=? and ID_PLANTA=? \n"
                                        + "and SERIE=? and NRO_DOCUMENTO=? and ID_TIPO_DOC='FAC'";

                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, "0" + Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }

                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }

                return Respuesta;
        }

        public static String consultarTotalDocumento_PERCEPCION(String cia, String planta, String Serie,
                        String Numero) {
                String Respuesta = null;
                try {
                        String ArmarConsulta = "select TOTAL from DOCUMENTO where CIA=? and ID_PLANTA=? \n"
                                        + "and SERIE=? and NRO_DOCUMENTO=? and ID_TIPO_DOC='FAC'";

                        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                        Sql.SQLpss.setString(1, cia);
                        Sql.SQLpss.setString(2, planta);
                        Sql.SQLpss.setString(3, "0" + Serie);
                        Sql.SQLpss.setString(4, Numero);
                        Sql.SQLrss = Sql.SQLpss.executeQuery();
                        Sql.SQLrsm = Sql.SQLrss.getMetaData();

                        while (Sql.SQLrss.next()) {
                                Respuesta = Sql.SQLrss.getString(1);
                        }

                        Sql.SQLrss.close();
                        Sql.SQLcnu.close();
                        Sql.SQLpss.close();

                } catch (SQLException ex) {
                        Logger.getLogger(LogGeneracionXml.class.getName()).log(Level.SEVERE, null, ex);
                }

                return Respuesta;
        }

}
