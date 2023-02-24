/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.DialogChofer;

/**
 *
 * @author kbarreda
 */
public class LogChofer {

    public static void ChoferLog() throws Exception {
        String ArmarConsulta = "select ID_CHOFER,DESCRIPCION from CHOFER where  ID_ESTADO='01' and  cia='01'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogChofer.ListIdChofer.add(Sql.SQLrss.getString(1));
            DialogChofer.ListDescripChofer.add(Sql.SQLrss.getString(2));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
    public static void BuscarChoferLog(String Busqueda) throws Exception {
        String ArmarConsulta = "select ID_CHOFER,DESCRIPCION from CHOFER where  ID_ESTADO='01' and  cia='01' and DESCRIPCION like '%"+Busqueda+"%'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogChofer.ListIdChofer.add(Sql.SQLrss.getString(1));
            DialogChofer.ListDescripChofer.add(Sql.SQLrss.getString(2));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
