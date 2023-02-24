/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.DialogSelectItem;

/**
 *
 * @author kbarreda
 */
public class LogItem {

    public static void ItemLog() throws Exception {
        String ArmarConsulta = "select ID_ARTICULO,DESCRIPCION_CORTA,ID_UNIDAD_ALMACEN from ARTICULO \n"
                + "where cia='01' and ID_ESTADO='01' and ID_ARTICULO_CLASE in ('COM') AND ID_UNIDAD_ALMACEN='GL'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogSelectItem.ListIdArticulo.add(Sql.SQLrss.getString(1));
            DialogSelectItem.ListDescripArticulo.add(Sql.SQLrss.getString(2));
            DialogSelectItem.ListUnidadArticulo.add(Sql.SQLrss.getString(3));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }


}
