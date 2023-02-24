/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.DialogVehiculo;

/**
 *
 * @author kbarreda
 */
public class LogVehiculo {

    public static void VehiculoLog() throws Exception {
        String ArmarConsulta = "select PLACA_TRACTOR,PLACA_CISTERNA,MARCA from VEHICULO where cia='01' and ID_ESTADO='01'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogVehiculo.ListTracto.add(Sql.SQLrss.getString(1));
            DialogVehiculo.ListCisterna.add(Sql.SQLrss.getString(2));
            DialogVehiculo.ListMarca.add(Sql.SQLrss.getString(3));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
    public static void BuscarVehiculoLog(String Busqueda) throws Exception {
        String ArmarConsulta = "select PLACA_TRACTOR,PLACA_CISTERNA,MARCA from VEHICULO where cia='01' and ID_ESTADO='01' and PLACA_TRACTOR like '%"+Busqueda+"%'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogVehiculo.ListTracto.add(Sql.SQLrss.getString(1));
            DialogVehiculo.ListCisterna.add(Sql.SQLrss.getString(2));
            DialogVehiculo.ListMarca.add(Sql.SQLrss.getString(3));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
