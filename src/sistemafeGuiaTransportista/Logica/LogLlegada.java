/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.DialogDireccionLlegada;
import sistemafeGuiaTransportista.FrameGTRregister;

/**
 *
 * @author kbarreda
 */
public class LogLlegada {
           public static void LlegadaLog() throws Exception {
        String ArmarConsulta = "select ID_CLIENTE,ID_DISTRITO,DESCRIPCION,DIRECCION from PUNTO_VENTA where cia='01' and ID_ESTADO='01' AND ID_CLIENTE='"+FrameGTRregister.RemiteId+"'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DialogDireccionLlegada.ListIdLlegada.add(Sql.SQLrss.getString(1));
            DialogDireccionLlegada.ListUbigeoLlegada.add(Sql.SQLrss.getString(2));
            DialogDireccionLlegada.ListNombreLlegada.add(Sql.SQLrss.getString(3));
            DialogDireccionLlegada.ListDireccionLlegada.add(Sql.SQLrss.getString(4));
            //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
        }
             //  System.out.println(FrameGTRregister.RemiteId);
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
    
}
