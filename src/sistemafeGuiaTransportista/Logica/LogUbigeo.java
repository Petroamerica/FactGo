/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.FrameGTRregister;

/**
 *
 * @author kbarreda
 */
public class LogUbigeo {
    

    public static void BuscarUbigeo(String ubigeo) throws Exception {
        FrameGTRregister.RestidUbigeo.clear();
        FrameGTRregister.RestDescripcion.clear();
        String Departamento = ubigeo.substring(0, 2);
        String Provincia = ubigeo.substring(0, 4);
        String Distrito = ubigeo.substring(0, 6);
        String ArmarConsulta = "select ID_DEPARTAMENTO,DESCRIPCION from DEPARTAMENTO where ID_DEPARTAMENTO='"+Departamento+"'\n"
                + "UNION ALL\n"
                + "select ID_PROVINCIA,DESCRIPCION from PROVINCIA where ID_PROVINCIA='"+Provincia+"'\n"
                + "UNION ALL\n"
                + "select ID_DISTRITO,DESCRIPCION from DISTRITO where ID_DISTRITO='"+Distrito+"'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            FrameGTRregister.RestidUbigeo.add(Sql.SQLrss.getString(1));
            FrameGTRregister.RestDescripcion.add(Sql.SQLrss.getString(2));
            //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

}
