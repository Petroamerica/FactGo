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
public class LogCorrelativo {

    public static void CorrelativoLog() throws Exception {
        String ArmarConsulta = "SELECT SERIE,CORRELATIVO FROM PLANTA_CORRELATIVO_DOCUMENTO WHERE CIA='08' and ID_PLANTA='01' and ID_TIPO_DOC='GUIA'";
        int CalcularNumero = 0;
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            FrameGTRregister.GuiaSerie = Sql.SQLrss.getString(1);
            CalcularNumero = Sql.SQLrss.getInt(2);
        }
        CalcularNumero++;

        FrameGTRregister.GuiaNumero = ponerCeros(Integer.toString(CalcularNumero));
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
    public static void UpdateCorrelativoLog(String Numero) throws Exception {
        Integer Convertido=Integer.parseInt(Numero);
        String ArmarConsulta = "UPDATE PLANTA_CORRELATIVO_DOCUMENTO set CORRELATIVO="+Convertido+" where cia='08' and ID_PLANTA='01'";
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLpss.executeUpdate();
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }

    public static String ponerCeros(String CalcularNumero) {
        int contador = CalcularNumero.length();
        if (contador == 1) {
            CalcularNumero = "0000000" + CalcularNumero;
        } else if (contador == 2) {
            CalcularNumero = "000000" + CalcularNumero;
        } else if (contador == 3) {
            CalcularNumero = "00000" + CalcularNumero;
        } else if (contador == 4) {
            CalcularNumero = "0000" + CalcularNumero;
        } else if (contador == 5) {
            CalcularNumero = "000" + CalcularNumero;
        } else if (contador == 6) {
            CalcularNumero = "00" + CalcularNumero;
        } else if (contador == 7) {
            CalcularNumero = "0" + CalcularNumero;
        } else {
        }

        return CalcularNumero;
    }

}
