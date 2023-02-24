/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import Datos.Datos;
import static LogicaSql.Sql.cadena_conexion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import sistemafe.FrameAdminCdr;

/**
 *
 * @author kbarreda
 */
public class LogAdminCdr {

    public static void GenerarListaCdr() throws Exception {
        /*Sql.SQLpss = cadena_conexion().prepareStatement("select cia+TICKET_EFACT+ID_TIPO_DOC+SERIE+NRO_DOCUMENTO+ID_PLANTA from DOCUMENTO where \n"
                + " FECHA>='2020-10-01' and ID_ESTADO_DOC='01' and ID_TIPO_DOC in('n/c','fac','n/d','bol') AND  ISNULL (TICKET_EFACT,'')!=''  AND  ISNULL (RESPONSE_CODE_SUNAT,'')='' order by FECHA asc");*/
        Sql.SQLpss = cadena_conexion().prepareStatement("select cia+TICKET_EFACT+ID_TIPO_DOC+SERIE+NRO_DOCUMENTO+ID_PLANTA from DOCUMENTO where \n"
                + " FECHA>='2020-10-01' and ID_ESTADO_DOC='01' and ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') AND  ISNULL (TICKET_EFACT,'')!=''  AND  ISNULL (RESPONSE_CODE_SUNAT,'')='' order by FECHA asc");
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        ArrayList<Object[]> datos = new ArrayList<>();
        while (Sql.SQLrss.next()) {
            Object[] filas = new Object[Sql.SQLrsm.getColumnCount()];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = Sql.SQLrss.getObject(i + 1);
            }
            datos.add(filas);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(Datos.RutaCdr));
        for (int x = 0; x < datos.size(); x++) {
            bw.write(Arrays.toString(datos.get(x)) + "\n");
        }
        bw.close();
        System.out.println("Lista hecha”");
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void UpdateCamposSunat(String Cia, String RCS, String RDS, String planta, String Serie, String Nro, String TipoDoc) throws SQLException {
        try {
            System.out.println("update DOCUMENTO set RESPONSE_CODE_SUNAT='"+RCS+"' , " + "RESPONSE_DESC_SUNAT='"+RDS+"' "
                    + " where cia='"+Cia+"' and ID_PLANTA='"+planta+"' and SERIE='"+Serie+"' and "
                    + " NRO_DOCUMENTO='"+Nro+"' " + " AND ID_TIPO_DOC='"+TipoDoc+"'");
            Sql.SQLpss = cadena_conexion().prepareStatement("update DOCUMENTO set RESPONSE_CODE_SUNAT='"+RCS+"' , " + "RESPONSE_DESC_SUNAT='"+RDS+"' "
                    + " where cia='"+Cia+"' and ID_PLANTA='"+planta+"' and SERIE='"+Serie+"' and "
                    + " NRO_DOCUMENTO='"+Nro+"' " + " AND ID_TIPO_DOC='"+TipoDoc+"'");
            Sql.SQLrss = Sql.SQLpss.executeQuery();
            if (Sql.SQLrss.next()) {
                System.out.println("UPDATE hecho"+Serie+"-"+Nro);
            }
            System.out.println("UPDATE hecho"+Serie+"-"+Nro);
        } catch (Exception e) {
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void ObtenerFechaHoraSQL() throws SQLException {
        String ArmarConsulta = "select convert(char(8), getdate(), 108) as HORA,CONVERT(VARCHAR(10), GETDATE(), 23) as FECHA;";

        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {

            FrameAdminCdr.HoraCdrSis = Sql.SQLrss.getString(1);
            FrameAdminCdr.FechaCdrSis = Sql.SQLrss.getString(2);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
        
    }

}
