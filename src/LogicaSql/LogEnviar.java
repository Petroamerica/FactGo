/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import java.sql.SQLException;

/**
 *
 * @author kbarreda
 */
public class LogEnviar {

    public static void InsertarTokentSQL(String sTicket, String Cia, String sPlanta, String sSerie, String sNroDoc, String sTipoDoc) throws  SQLException {
        Sql.cadena_conexion();
        if ("n-c".equals(sTipoDoc)) {
            sTipoDoc = "N/C";
        } else {
            if ("n-d".equals(sTipoDoc)) {
                sTipoDoc = "N/D";
            } else {
            }
        }
        Sql.SQLpss = Sql.SQLcnu.prepareStatement("update DOCUMENTO set TICKET_EFACT='" + sTicket + "' , FECHA_ENVIO_SUNAT=GETDATE() where cia='" + Cia + "' and ID_PLANTA LIKE '%" + sPlanta + "%' and SERIE LIKE '%" + sSerie + "%' and NRO_DOCUMENTO='" + sNroDoc + "' " + " AND ID_TIPO_DOC='" + sTipoDoc + "'");
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        if (Sql.SQLrss.next()) {
            System.out.println("Comprobante :" + sSerie + sNroDoc + " - Ticket :" + sTicket);
        }
        Sql.SQLcnu.close();
    }

    public static void InsertarBajaSQL(String Cia, String sSerie, String sNroDoc, String sTipoDoc) throws SQLException {//TODO si
        Sql.cadena_conexion();
        Sql.SQLpss = Sql.SQLcnu.prepareStatement("update DOCUMENTO set RESPONSE_CODE_SUNAT_BAJA='1' where cia='" + Cia + "' and SERIE LIKE '%" + sSerie + "%' and NRO_DOCUMENTO LIKE '%" + sNroDoc + "%' AND ID_TIPO_DOC='%" + sTipoDoc + "%' and ID_ESTADO_DOC='02'");
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        if (Sql.SQLrss.next()) {
            System.out.println("Comprobante :" + sSerie + sNroDoc + " - Ticket :" + sTipoDoc);
        }
        Sql.SQLcnu.close();
    }

    public static void InsertarBajaSQLAuto() throws SQLException {
        Sql.cadena_conexion();
        //NUEVA CONSULTA PARA COMPARAR LA FEHA DE BAJA AGREGADO EN LA LINEA 47 => FECHA <= CAST(GETDATE() AS DATE) --(POSIBLE CAMBIO)--
        Sql.SQLpss = Sql.SQLcnu.prepareStatement("update DOCUMENTO set RESPONSE_CODE_SUNAT_BAJA='1' where cia in('07','05','01','06') and ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') and ID_ESTADO_DOC='02' \n"
                + " and TICKET_EFACT IS NOT NULL AND FECHA>='2019-04-01' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1' and DATEDIFF(d, FECHA_ANULA, GETDATE()) >=0");
        /*Sql.SQLpss = Sql.SQLcnu.prepareStatement("update DOCUMENTO set RESPONSE_CODE_SUNAT_BAJA='1' where cia in('07','05','01','06') and ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') and ID_ESTADO_DOC='02' \n"
                + " and TICKET_EFACT IS NOT NULL AND FECHA>='2019-04-01' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1' ");*/
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        if (Sql.SQLrss != null) {
	        if (Sql.SQLrss.next()) {
	            System.out.println("Comprobante :");
	        }
        }
	    Sql.SQLcnu.close();
    }
    
    public static void ModificarCode(String FECHA, String CIA, String NRO_DOCUMENTO, String SERIE, String tipoD, String COMPROBANTE) throws SQLException {
    	String tipo = "";
    	if("FAC".equals(tipoD)) {
    		tipo = "Factura";
    	}else if("BOL".equals(tipoD)) {
    		tipo = "Boleta";
    	}else if("N/C".equals(tipoD)){
    		tipo =  "Nota de credito";
    	}else if("N/D".equals(tipoD)){
    		tipo =  "Nota de debito";
    	}else {
    		tipo = tipoD;
    	}
    	Sql.cadena_conexion();
        try {
        String descrip = "La "+tipo+" "+COMPROBANTE+" ha sido aceptada.";
        System.out.print("update DOCUMENTO set RESPONSE_CODE_SUNAT = '0', RESPONSE_DESC_SUNAT = '"+descrip+"' WHERE ISNULL (TICKET_EFACT,'')!='' and FECHA >= '"+FECHA+"' and CIA = '"+CIA+"' and NRO_DOCUMENTO = '"+NRO_DOCUMENTO+"' and SERIE = '"+SERIE+"' and ID_TIPO_DOC= '"+tipoD+"' and ID_ESTADO_DOC = '01';");
    	Sql.SQLpss = Sql.SQLcnu.prepareStatement("update DOCUMENTO set RESPONSE_CODE_SUNAT = '0', RESPONSE_DESC_SUNAT = '"+descrip+"' WHERE ISNULL (TICKET_EFACT,'')!='' and FECHA >= '"+FECHA+"' and CIA = '"+CIA+"' and NRO_DOCUMENTO = '"+NRO_DOCUMENTO+"' and SERIE = '"+SERIE+"' and ID_TIPO_DOC= '"+tipoD+"' and ID_ESTADO_DOC = '01';");
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        System.out.print(Sql.SQLrss);
        if (Sql.SQLrss != null) {
	        if (Sql.SQLrss.next()) {
	            System.out.println("Modify");
	        }
        }
        } catch (Exception e) {
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
}
