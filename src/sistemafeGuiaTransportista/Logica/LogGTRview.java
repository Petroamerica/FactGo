/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafeGuiaTransportista.Logica;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import sistemafeGuiaTransportista.FrameGRTview;

/**
 *
 * @author kbarreda
 */
public class LogGTRview {

    public static void VistaLog() throws Exception {
        String ArmarConsulta = "select TOP 25. SUBSTRING(g.SERIE_GUIA, 2, 3),g.NRO_GUIA,convert(varchar(10),g.FECHA,110) as FECHA,c.DESCRIPCION,DIRECCION_ORIGEN,g.DIRECCION_DESTINO,CONVERT(int, round(g.TOTAL_CANTIDAD , 0)) as CANTIDAD \n"
                + "from GUIA_REMISION g inner join CLIENTE c on g.CIA=c.CIA and g.ID_CLIENTE=c.ID_CLIENTE  \n"
                + "where g.cia='01' and g.FECHA>='2019-05-01' order by g.FECHA_SISTEMA desc;";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            FrameGRTview.GTRviewSERIE_NRO_GUIA.add("G"+Sql.SQLrss.getString(1)+"-"+Sql.SQLrss.getString(2));
            FrameGRTview.GTRviewFECHA.add(Sql.SQLrss.getString(3));
            FrameGRTview.GTRviewDESCRIPCION.add(Sql.SQLrss.getString(4));
            FrameGRTview.GTRviewDIRECCION_ORIGEN.add(Sql.SQLrss.getString(5));
            FrameGRTview.GTRviewDIRECCION_DESTINO.add(Sql.SQLrss.getString(6));
            FrameGRTview.GTRviewTOTAL_CANTIDAD.add(Sql.SQLrss.getString(7));
            //System.out.println("doc :" + SIdPlanta + " - " + SIdPlantaDescrip + " - " + SDocumento + " - " + SFecha + " - " + STicketEfac + " - " + SResponseCodeSunat + " - " + SResponseDesSunat);
        }

        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
}
