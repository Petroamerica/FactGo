/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import java.util.Calendar;

/**
 *
 * @author kbarreda
 */
public class LogGuiaGuiadet {

    public static void InsertGuia(String IdPlanta, String Serie, String Numero, String Fecha,
            String UbigeoOrigen, String DireccionOrigen, String UbigeoDestino, String DireccionDestino,
            String Id_cliente, String PuntoVenta, String Tracto, String Cisterna, String Ifchofer,
            Integer CantidadGlobal, String FechaSistema, String Usuario,
            String Scop, String FechaTraslado) throws Exception {
        String ArmarInsert = "INSERT INTO GUIA_REMISION VALUES(\n"
                + "'01','" + IdPlanta + "','" + Serie + "','" + Numero + "','" + Fecha + "','07','0','1','1','1'\n"
                + ",'" + UbigeoOrigen + "',NULL,NULL,NULL,'" + DireccionOrigen + "','" + UbigeoDestino + "',NULL,NULL,NULL,'" + DireccionDestino + "'\n"
                + ",NULL,NULL,NULL,NULL,'" + Id_cliente + "','" + PuntoVenta + "','" + Tracto + "','" + Cisterna + "',NULL,'" + Ifchofer + "',\n"
                + "'" + CantidadGlobal + "','01',NULL,NULL,NULL,NULL,'" + FechaSistema + "','" + Usuario + "',NULL,NULL\n"
                + ",NULL,NULL,'50',NULL,NULL,NULL,NULL,NULL,NULL,NULL\n"
                + ",'" + Scop + "',NULL,'" + FechaTraslado + "')";
        //System.out.println(ArmarInsert);
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarInsert);
        Sql.SQLpss.executeUpdate();
        //System.out.println(Sql.SQLrss.getStatement());
        /*Sql.SQLrsm = Sql.SQLrss.getMetaData();
        System.out.println(Sql.SQLrsm.toString());*/
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static void InsertGuiaDet(String IdPlanta, String Serie, String Numero, int OrdenItem, String DescripItems, String IdItems,
            String Cantidad, String FechaSistema, String Usuario) throws Exception {
        String ArmarInsert = "INSERT INTO GUIA_REMISION_DET VALUES(\n"
                + "'01','" + IdPlanta + "','" + Serie + "','" + Numero + "'," + OrdenItem + ",'" + DescripItems + "','" + IdItems + "',\n"
                + "'" + Cantidad + "','01',NULL,NULL,NULL,NULL,'" + FechaSistema + "','" + Usuario + "',NULL,NULL,'50',NULL)";
         Sql.SQLpss = cadena_conexion().prepareStatement(ArmarInsert);
        Sql.SQLpss.executeUpdate();
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
