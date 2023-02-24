/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.DialogRemitente;

/**
 *
 * @author kbarreda
 */
public class LogRemitente {

    public static void RemitenteLog() throws Exception {
        String ArmarConsulta = "select ID_CLIENTE,NRO_DI,DESCRIPCION from CLIENTE where cia='01' and ID_TIPO_DI='02' and ID_ESTADO='01'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogRemitente.ListIdRemitente.add(Sql.SQLrss.getString(1));
            DialogRemitente.ListNroidRemite.add(Sql.SQLrss.getString(2));
            DialogRemitente.ListDescripReminte.add(Sql.SQLrss.getString(3));
            //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
    public static void BuscarRemitenteLog(String Busqueda) throws Exception {
        String ArmarConsulta = "select ID_CLIENTE,NRO_DI,DESCRIPCION from CLIENTE where cia='01' and ID_TIPO_DI='02' and ID_ESTADO='01' and DESCRIPCION like'%"+Busqueda+"%'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogRemitente.ListIdRemitente.add(Sql.SQLrss.getString(1));
            DialogRemitente.ListNroidRemite.add(Sql.SQLrss.getString(2));
            DialogRemitente.ListDescripReminte.add(Sql.SQLrss.getString(3));
            //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
