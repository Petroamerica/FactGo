/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import static LogicaSql.Sql.cadena_conexion;
//import sistemafe.FrameEnviar;

/**
 *
 * @author kbarreda__Update_PPVB
 */
public class cLogControlEnvio {
	public static String consultaRes = "";
	public static String sDiferenciaDias = "";
	
    public static void mControlEnvio(String cia, String VerPlanta, String tipo, String serie, String doc) throws Exception {
    	//ACA LE CAMBIE LOS DIAS DE BOLETA A 7 de 5
    	//ESTE CAMBIO FUE REALIZADO EL 16/12/2021 POR EFACT PARA EL ENVIO DE DOCUMENTOS ELECTRONICOS -> LAS FACTURAS Y N/C RELACIONADAS A FACTURA DE 7 DIAS A 4 DIAS
    	//ESTE CAMBIO SE APLICARA ANTES DEL 31/12/2022 PAR ENVIAR LOS DOCUMENTOS ELECTRONICOS A EFACT -> FACTURAS Y N/C RELACIONADAS A FACTURAS DE 4 A 3 DIA
        String sConsulta = "SELECT ISNULL(CAST(FECHA_ENVIO_SUNAT AS VARCHAR), 'ok'), \n"
        		+ "CASE WHEN id_tipo_doc ='FAC' OR (id_tipo_doc ='N/C' and id_tipo_doc_ref='FAC') THEN \n" 
        		+ "			case when DATEDIFF(d, fecha, GETDATE()) <= 3 then 'ok' else 'no' end  \n"
				+ "     WHEN id_tipo_doc ='BOL' OR (id_tipo_doc ='N/C' and id_tipo_doc_ref='BOL') THEN \n"
				+ "			case when DATEDIFF(d, fecha, GETDATE()) <= 5 then 'ok' else 'no' end  \n"
				+ "     ELSE 'ok'  \n"
				+ "END  \n"
        		+ "FROM DOCUMENTO \n" 
                + "WHERE CIA='" + cia + "' and ID_PLANTA='" + VerPlanta + "' and ID_TIPO_DOC='" + tipo + "' and SERIE='" + serie + "' and NRO_DOCUMENTO='" + doc + "'\n";
        
        Sql.SQLpss = cadena_conexion().prepareStatement(sConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        
        while (Sql.SQLrss.next()) {
            //FrameEnviar.consultaRes = Sql.SQLrss.getString(1);
            //FrameEnviar.sDiferenciaDias = Sql.SQLrss.getString(2);
            consultaRes = Sql.SQLrss.getString(1);
            sDiferenciaDias = Sql.SQLrss.getString(2);
        }
        
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
}
