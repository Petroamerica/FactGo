/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import static LogicaSql.Sql.cadena_conexion;
import sistemafe.FrameReporte;

/**
 *
 * @author kbarreda
 */
public class LogReporte {
    // TODO
    public static void ReporteSQL(String Cia, String Planta, String FechaIni, String FechaFin) throws Exception {
        String ArmarConsulta = "SELECT CASE ID_TIPO_DOC WHEN 'BOL' THEN 'B'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "                +'-'+NRO_DOCUMENTO WHEN 'FAC' THEN 'F'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "	+'-'+NRO_DOCUMENTO WHEN 'N/C' THEN 'C'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "                +'-'+NRO_DOCUMENTO WHEN 'N/D' THEN 'D'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "	+'-'+NRO_DOCUMENTO END COMPROBANTE,CONVERT(VARCHAR(10), FECHA, 23) as FECHA,\n"
                + "TICKET_EFACT,RESPONSE_CODE_SUNAT,RESPONSE_DESC_SUNAT FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND ID_PLANTA='" + Planta + "' AND  FECHA BETWEEN '" + FechaIni
                + "' AND '" + FechaFin + "' and ID_ESTADO_DOC='01' \n"
                + " and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C') ORDER BY FECHA ASC";

        String SDocumento = "";
        String SFecha = "";
        String STicketEfac = "";
        String SResponseCodeSunat = "";
        String SResponseDesSunat = "";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {

            SDocumento = Sql.SQLrss.getString(1);
            SFecha = Sql.SQLrss.getString(2);
            STicketEfac = Sql.SQLrss.getString(3);
            SResponseCodeSunat = Sql.SQLrss.getString(4);
            SResponseDesSunat = Sql.SQLrss.getString(5);
            FrameReporte.ListDoc.add(SDocumento);
            FrameReporte.ListFecha.add(SFecha);
            if (STicketEfac == null) {
                FrameReporte.ListCod.add("300");
                FrameReporte.ListDes.add("Documento no enviado");
            } else {
                if (SResponseCodeSunat == null) {
                    FrameReporte.ListCod.add("400");
                    FrameReporte.ListDes.add("Esperando Respuesta de SUNAT");
                } else {
                    if ("0".equals(SResponseCodeSunat)) {
                        FrameReporte.ListCod.add("200");
                        FrameReporte.ListDes.add(SResponseDesSunat);
                    } else {
                        FrameReporte.ListCod.add(SResponseCodeSunat + "00");
                        FrameReporte.ListDes.add(SResponseDesSunat);
                    }
                }

            }
            System.out.println("doc : " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat
                    + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    // TODO
    public static void ReporteCantidadesSQL(String Cia, String Planta, String FechaIni, String FechaFin)
            throws Exception {
        String ArmarConsulta = "SELECT ID_TIPO_DOC,SERIE+'-'+NRO_DOCUMENTO as DOCUMENTO,CONVERT(VARCHAR(10), FECHA, 23) as FECHA,\n"
                + "TICKET_EFACT,RESPONSE_CODE_SUNAT,RESPONSE_DESC_SUNAT FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND ID_PLANTA='" + Planta
                + "' AND  FECHA BETWEEN '2018-11-09' AND '" + FechaFin + "' and ID_ESTADO_DOC='01' \n"
                + " and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C') ORDER BY FECHA ASC";
        String SIdTipoDoc = "";
        String SDocumento = "";
        String SFecha = "";
        String STicketEfac = "";
        String SResponseCodeSunat = "";
        String SResponseDesSunat = "";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            SIdTipoDoc = Sql.SQLrss.getString(1);
            SDocumento = Sql.SQLrss.getString(2);
            SFecha = Sql.SQLrss.getString(3);
            STicketEfac = Sql.SQLrss.getString(4);
            SResponseCodeSunat = Sql.SQLrss.getString(5);
            SResponseDesSunat = Sql.SQLrss.getString(6);
            if (STicketEfac == null) {
                FrameReporte.ContarSinEnviar++;
            } else {
                if (SResponseCodeSunat == null) {
                    FrameReporte.ContarEspera++;
                } else {
                    if ("0".equals(SResponseCodeSunat)) {
                    } else {
                        FrameReporte.ContarErrores++;
                    }
                }

            }
            System.out.println("doc : " + SIdTipoDoc + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - "
                    + SResponseCodeSunat + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    // TODO
    public static void ReportePorEstadoSQL(String Cia, String Planta, String FechaIni, String FechaFin, String Estado)
            throws Exception {
        FrameReporte.list.clear();
        FrameReporte.ListDes.clear();
        FrameReporte.ListFecha.clear();
        FrameReporte.ListCod.clear();
        FrameReporte.ListDoc.clear();
        String ArmarConsulta = "SELECT CASE ID_TIPO_DOC WHEN 'BOL' THEN 'B'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "                +'-'+NRO_DOCUMENTO WHEN 'FAC' THEN 'F'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "	+'-'+NRO_DOCUMENTO WHEN 'N/C' THEN 'C'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "                +'-'+NRO_DOCUMENTO WHEN 'N/D' THEN 'D'+SUBSTRING(CONVERT(VARCHAR,SERIE), 2, 4)\n" +
                "	+'-'+NRO_DOCUMENTO END COMPROBANTE,CONVERT(VARCHAR(10), FECHA, 23) as FECHA,\n"
                + "TICKET_EFACT,RESPONSE_CODE_SUNAT,RESPONSE_DESC_SUNAT FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND ID_PLANTA='" + Planta
                + "' AND  FECHA BETWEEN '2018-11-09' AND '" + FechaFin
                + "'and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C') ";
        if ("SinEnviar".equals(Estado)) {
            ArmarConsulta = ArmarConsulta
                    + " and ID_ESTADO_DOC='01' AND  ISNULL (TICKET_EFACT,'')='' ORDER BY FECHA ASC";
        } else if ("Esperando".equals(Estado)) {
            ArmarConsulta = ArmarConsulta
                    + " and ID_ESTADO_DOC='01' AND  ISNULL (TICKET_EFACT,'')!='' AND  ISNULL (RESPONSE_CODE_SUNAT,'')='' ORDER BY FECHA ASC";
        } else if ("Bajas".equals(Estado)) {
            ArmarConsulta = ArmarConsulta
                    + " and ID_ESTADO_DOC='02' and RESPONSE_CODE_SUNAT='0' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1'  ORDER BY FECHA ASC";
        } else /* error */ {
            ArmarConsulta = ArmarConsulta + " and ID_ESTADO_DOC='01' and RESPONSE_CODE_SUNAT='1' ORDER BY FECHA ASC";
        }

        String SDocumento = "";
        String SFecha = "";
        String STicketEfac = "";
        String SResponseCodeSunat = "";
        String SResponseDesSunat = "";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            SDocumento = Sql.SQLrss.getString(1);
            SFecha = Sql.SQLrss.getString(2);
            STicketEfac = Sql.SQLrss.getString(3);
            SResponseCodeSunat = Sql.SQLrss.getString(4);
            SResponseDesSunat = Sql.SQLrss.getString(5);
            FrameReporte.ListDoc.add(SDocumento);
            FrameReporte.ListFecha.add(SFecha);
            if ("Bajas".equals(Estado)) {
                FrameReporte.ListCod.add("600");
                FrameReporte.ListDes.add("No se envio baja del comprobante anulado");
            } else {
                if (STicketEfac == null) {
                    FrameReporte.ListCod.add("300");
                    FrameReporte.ListDes.add("Documento no enviado");
                } else {
                    if (SResponseCodeSunat == null) {
                        FrameReporte.ListCod.add("400");
                        FrameReporte.ListDes.add("Esperando Respuesta de SUNAT");
                    } else {
                        if ("0".equals(SResponseCodeSunat)) {
                            FrameReporte.ListCod.add("200");
                            FrameReporte.ListDes.add(SResponseDesSunat);
                        } else {
                            FrameReporte.ListCod.add(SResponseCodeSunat + "00");
                            FrameReporte.ListDes.add(SResponseDesSunat);
                        }
                    }

                }
            }
            System.out.println("doc : " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat
                    + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    // TODO
    public static void ReporteConsultaPlantaSQL(String Cia, String IdPlanta) throws Exception {

        String ArmarConsulta = "select DESCRIPCION from PLANTA where cia='" + Cia
                + "' and ID_ESTADO='01' and ID_PLANTA='" + IdPlanta + "'";

        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {

            FrameReporte.PlantaDescrip = Sql.SQLrss.getString(1);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    // TODO
    public static void ReporteCantidadBajasPendeientesSQL(String Cia, String Planta, String FechaFin) throws Exception {

        String ArmarConsulta = "select ID_TIPO_DOC FROM \n"
                + "DOCUMENTO where cia='" + Cia + "' AND ID_PLANTA='" + Planta
                + "' AND  FECHA BETWEEN '2018-11-09' AND '" + FechaFin + "' and ID_ESTADO_DOC='02'\n"
                + "and ID_TIPO_DOC IN('FAC','BOL','N/D','N/C')and RESPONSE_CODE_SUNAT='0' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1'  ORDER BY FECHA ASC";

        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            String SIdTipoDoc = Sql.SQLrss.getString(1);
            FrameReporte.ContarBajas++;

        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
