/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemafeGuiaTransportista.DialogDireccionPartida;


/**
 *
 * @author kbarreda
 */
public class LogPartida {
    
        public static void PartidaLog()  {
            try {
                String ArmarConsulta = "select ID_PLANTA,ID_DISTRITO,DESCRIPCION,DIRECCION from PLANTA where cia='08'";
                Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                Sql.SQLrss = Sql.SQLpss.executeQuery();
                Sql.SQLrsm = Sql.SQLrss.getMetaData();
                while (Sql.SQLrss.next()) {
                    DialogDireccionPartida.ListIdPlanta.add(Sql.SQLrss.getString(1));
                    DialogDireccionPartida.ListUbigeoPlanta.add(Sql.SQLrss.getString(2));
                    DialogDireccionPartida.ListNombrePlanta.add(Sql.SQLrss.getString(3));
                    DialogDireccionPartida.ListDireccionPlanta.add(Sql.SQLrss.getString(4));
                    //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
                }
                Sql.SQLrss.close();
                Sql.SQLcnu.close();
                Sql.SQLpss.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                 System.out.println("------------------------");
                  System.out.println(ex.getErrorCode());
                   System.out.println("------------------------");
                   System.out.println(ex.getNextException());
                    System.out.println("------------------------");
                    System.out.println(ex.getSQLState());
                     System.out.println("------------------------");
                     System.out.println(ex.iterator());
                      System.out.println("------------------------");
                      System.out.println(ex.toString());
            }
    }

}
