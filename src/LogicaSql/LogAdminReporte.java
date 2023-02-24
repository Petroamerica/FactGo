/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import static LogicaSql.Sql.cadena_conexion;
import sistemafe.FrameAdminReporte;

/**
 *
 * @author kbarreda
 */
public class LogAdminReporte {

    public static void ReporteAdminReportSQL(String Cia) throws Exception {
    	
        String ArmarConsulta = "SELECT d.ID_PLANTA,p.DESCRIPCION,\n"
                + "CASE d.ID_TIPO_DOC WHEN 'BOL' THEN 'B'+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO WHEN 'FAC' THEN 'F'+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO WHEN 'N/C' THEN LEFT(d.ID_TIPO_DOC_REF,1)+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"//TODO
                + "+'-'+d.NRO_DOCUMENTO WHEN 'N/D' THEN LEFT(d.ID_TIPO_DOC_REF,1)+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO END COMPROBANTE,CONVERT(VARCHAR(10), d.FECHA, 23) as FECHA,\n"
                + "d.TICKET_EFACT,d.RESPONSE_CODE_SUNAT,d.RESPONSE_DESC_SUNAT, ID_TIPO_DOC FROM DOCUMENTO  d \n"
                + "inner join PLANTA p on d.CIA=p.CIA and d.ID_PLANTA=p.ID_PLANTA \n"
                + "left join cierre_diario cx ON d.cia = cx.cia AND d.id_planta=cx.id_planta AND d.fecha=cx.fecha \n"
                + "where d.FECHA>='2020-10-01' and d.ID_ESTADO_DOC='01' and d.ID_TIPO_DOC in ('BOL','fac','N/C','N/D') AND TICKET_EFACT IS NULL \n" 
                + "and ( \n"  
                + "(d.CIA='" + Cia + "' and isnull(p.FLAG_ENVIO_AUTOMATICO,0)='1') \n"  
                + "OR (d.CIA='" + Cia + "' and isnull(p.FLAG_ENVIO_AUTOMATICO,0)!='1' and cx.FLAG_CERRADO ='1') \n"  
                + "OR (d.CIA='" + Cia + "') \n"
                + ") \n" 
                + "ORDER BY d.cia, d.FECHA_SISTEMA";
//                + "where d.cia='" + Cia + "'  AND  d.FECHA>='2020-10-01' and d.ID_ESTADO_DOC='01'\n" //d.FECHA>='2019-08-01'
//                + "and d.ID_TIPO_DOC IN('FAC','BOL','N/D','N/C')AND  ISNULL (d.TICKET_EFACT,'')='' ORDER BY d.FECHA,d.ID_PLANTA,d.NRO_DOCUMENTO ASC";
       
        String SIdPlanta = "";
        String SIdPlantaDescrip = "";
        String SDocumento = "";
        String SFecha = "";
        String STicketEfac = "";
        String SResponseCodeSunat = "";
        String SResponseDesSunat = "";
        String STipoDoc = "";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            SIdPlanta = Sql.SQLrss.getString(1);
            SIdPlantaDescrip = Sql.SQLrss.getString(2);
            SDocumento = Sql.SQLrss.getString(3);
            SFecha = Sql.SQLrss.getString(4);
            STicketEfac = Sql.SQLrss.getString(5);
            SResponseCodeSunat = Sql.SQLrss.getString(6);
            SResponseDesSunat = Sql.SQLrss.getString(7);
            STipoDoc = Sql.SQLrss.getString(8);
            
            /* Solo un intento
            STipoDoc = SDocumento.substring(0,1);
            if (STipoDoc.equals("F")) {
            	STipoDoc = "Factura";
            } else if(STipoDoc.equals("B")) {
            	STipoDoc = "Boleta";
            } else if(STipoDoc.equals("C")) {
            	STipoDoc = "Nota de crédito";
            } else if(STipoDoc.equals("D")) {
            	STipoDoc = "Nota de débito";
            }
            */
            
            
            FrameAdminReporte.ListTipoDoc.add(STipoDoc);
            FrameAdminReporte.ListIdPlantaRADMIN.add(SIdPlanta);
            FrameAdminReporte.ListPlantaDescripRADMIN.add(SIdPlantaDescrip);
            FrameAdminReporte.ListDocRADMIN.add(SDocumento);
            FrameAdminReporte.ListFechaRADMIN.add(SFecha);

            if (STicketEfac == null) {
                FrameAdminReporte.ContarAdminSinEnviar++;
                FrameAdminReporte.ListCodRADMIN.add("300");
                FrameAdminReporte.ListDesRADMIN.add("Documento no enviado");
            } else {
                if (SResponseCodeSunat == null) {
                    FrameAdminReporte.ListCodRADMIN.add("400");
                    FrameAdminReporte.ListDesRADMIN.add("Esperando Respuesta de SUNAT");
                } else {
                    if ("0".equals(SResponseCodeSunat)) {
                        FrameAdminReporte.ListCodRADMIN.add("200");
                        FrameAdminReporte.ListDesRADMIN.add(SResponseDesSunat);

                    } else {
                        FrameAdminReporte.ListCodRADMIN.add(SResponseCodeSunat + "00");
                        FrameAdminReporte.ListDesRADMIN.add(SResponseDesSunat);
                    }
                }
            }
            System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat + " - " + STipoDoc);
        }
      Sql.SQLrss.close();
     Sql.SQLcnu.close();
      Sql.SQLpss.close();
    }

    public static void ReporteCantidadBajasPendeientesSQL(String Cia) throws Exception {

        String ArmarConsulta = "Select ID_TIPO_DOC FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND  FECHA>='2019-08-01' and ID_ESTADO_DOC='02'\n"
                + "and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C')and RESPONSE_CODE_SUNAT='0' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1'  ORDER BY FECHA ASC";

        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            String SIdTipoDoc = Sql.SQLrss.getString(1);
            FrameAdminReporte.ContarAdminBajas++;
            System.out.println(SIdTipoDoc);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void ReporteCantidadErroresSQL(String Cia) throws Exception {

        String ArmarConsulta = "Select ID_TIPO_DOC FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND  FECHA>='2021-01-01' \n" //FECHA>='2020-10-01'
                + "and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C')and ID_ESTADO_DOC='01' and RESPONSE_CODE_SUNAT='1' ORDER BY FECHA ASC";
        
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            String SIdTipoDoc = Sql.SQLrss.getString(1);
            FrameAdminReporte.ContarAdminErrores++;
            System.out.println(SIdTipoDoc);

        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void ReporteCantidadEsperaSQL(String Cia) throws Exception {

        String ArmarConsulta = "Select ID_TIPO_DOC FROM \n"
                + " DOCUMENTO where cia='" + Cia + "' AND  FECHA>='2020-10-01' and ID_ESTADO_DOC='01' \n" //AND  FECHA>='2019-08-01'
                + " and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C') AND  ISNULL (TICKET_EFACT,'')!='' AND  ISNULL (RESPONSE_CODE_SUNAT,'')='' ORDER BY FECHA ASC";

        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            String SIdTipoDoc = Sql.SQLrss.getString(1);
            FrameAdminReporte.ContarAdminEspera++;
            System.out.println(SIdTipoDoc);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void ReportePorEstadoSQL(String Cia, String Fecha, String Estado) throws Exception {
        FrameAdminReporte.ListIdPlantaRADMIN.clear();
        FrameAdminReporte.ListPlantaDescripRADMIN.clear();
        FrameAdminReporte.ListFechaRADMIN.clear();
        FrameAdminReporte.ListDocRADMIN.clear();
        FrameAdminReporte.ListCodRADMIN.clear();
        FrameAdminReporte.ListDesRADMIN.clear();
        FrameAdminReporte.ListTipoDoc.clear();
        FrameAdminReporte.listss.clear();
        String ArmarConsulta = "SELECT d.ID_PLANTA,p.DESCRIPCION,\n"
                + "CASE d.ID_TIPO_DOC WHEN 'BOL' THEN 'B'+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO WHEN 'FAC' THEN 'F'+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO WHEN 'N/C' THEN LEFT(d.ID_TIPO_DOC_REF,1)+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"
                + "+'-'+d.NRO_DOCUMENTO WHEN 'N/D' THEN LEFT(d.ID_TIPO_DOC_REF,1)+SUBSTRING(CONVERT(VARCHAR,d.SERIE), 2, 4)\n"//TODO
                + "+'-'+d.NRO_DOCUMENTO END COMPROBANTE,CONVERT(VARCHAR(10), FECHA, 23) as FECHA,\n"
                + "d.TICKET_EFACT,d.RESPONSE_CODE_SUNAT,d.RESPONSE_DESC_SUNAT, ID_TIPO_DOC FROM DOCUMENTO  d \n"
                + "inner join PLANTA p on d.CIA=p.CIA and d.ID_PLANTA=p.ID_PLANTA \n"
                + "where d.cia='" + Cia + "'  \n" //AND  d.FECHA>='2019-08-01'
                + "and d.ID_TIPO_DOC IN('FAC','BOL','N/D','N/C') ";
        if ("SinEnviar".equals(Estado)) {
            ArmarConsulta = ArmarConsulta + " AND  d.FECHA>='2020-10-01' and d.ID_ESTADO_DOC='01' AND  ISNULL (d.TICKET_EFACT,'')='' ORDER BY d.FECHA,d.ID_PLANTA,d.NRO_DOCUMENTO ASC";
        } else if ("Esperando".equals(Estado)) {
            ArmarConsulta = ArmarConsulta + " AND  d.FECHA>='2020-10-01' and d.ID_ESTADO_DOC='01' AND  ISNULL (d.TICKET_EFACT,'')!='' AND  ISNULL (d.RESPONSE_CODE_SUNAT,'')='' ORDER BY d.FECHA,d.ID_PLANTA,d.NRO_DOCUMENTO ASC";
        } else if ("Bajas".equals(Estado)) {
            ArmarConsulta = ArmarConsulta + " AND  d.FECHA>='2019-08-01'  and d.ID_ESTADO_DOC='02' and d.RESPONSE_CODE_SUNAT='0' and isnull(d.RESPONSE_CODE_SUNAT_baja,0)!='1'  ORDER BY d.FECHA,d.ID_PLANTA,d.NRO_DOCUMENTO ASC";
        } else /*error*/ {
            ArmarConsulta = ArmarConsulta + " AND  d.FECHA>='2020-10-01' and d.ID_ESTADO_DOC='01' and d.RESPONSE_CODE_SUNAT='1' ORDER BY d.FECHA,d.ID_PLANTA ASC";
        }
        String SIdPlanta = "";
        String SIdPlantaDescrip = "";
        String SDocumento = "";
        String SFecha = "";
        String STicketEfac = "";
        String SResponseCodeSunat = "";
        String SResponseDesSunat = "";
        String STipoDoc = "";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            SIdPlanta = Sql.SQLrss.getString(1);
            SIdPlantaDescrip = Sql.SQLrss.getString(2);
            SDocumento = Sql.SQLrss.getString(3);
            SFecha = Sql.SQLrss.getString(4);
            STicketEfac = Sql.SQLrss.getString(5);
            SResponseCodeSunat = Sql.SQLrss.getString(6);
            SResponseDesSunat = Sql.SQLrss.getString(7);
            STipoDoc = Sql.SQLrss.getString(8);
            FrameAdminReporte.ListTipoDoc.add(STipoDoc);
            FrameAdminReporte.ListIdPlantaRADMIN.add(SIdPlanta);
            FrameAdminReporte.ListPlantaDescripRADMIN.add(SIdPlantaDescrip);
            FrameAdminReporte.ListDocRADMIN.add(SDocumento);
            FrameAdminReporte.ListFechaRADMIN.add(SFecha);
            if ("Bajas".equals(Estado)) {
                FrameAdminReporte.ListCodRADMIN.add("600");
                FrameAdminReporte.ListDesRADMIN.add("No se envio baja del comprobante anulado");
            } else {
                if (STicketEfac == null) {
                    FrameAdminReporte.ContarAdminSinEnviar++;
                    FrameAdminReporte.ListCodRADMIN.add("300");
                    FrameAdminReporte.ListDesRADMIN.add("Documento no enviado");
                } else {
                    if (SResponseCodeSunat == null) {
                        FrameAdminReporte.ListCodRADMIN.add("400");
                        FrameAdminReporte.ListDesRADMIN.add("Esperando Respuesta de SUNAT");
                    } else {
                        if ("0".equals(SResponseCodeSunat)) {
                            FrameAdminReporte.ListCodRADMIN.add("200");
                            FrameAdminReporte.ListDesRADMIN.add(SResponseDesSunat);

                        } else {
                            FrameAdminReporte.ListCodRADMIN.add(SResponseCodeSunat + "00");
                            FrameAdminReporte.ListDesRADMIN.add(SResponseDesSunat);
                        }
                    }
                }
            }
            System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat + " - " + STipoDoc);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();

    }

}
