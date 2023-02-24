package LogicaSql;
import static LogicaSql.Sql.cadena_conexion;

public class LogPercepcion {
	public static String GetEsContado(String Cia, String idPlanta, String sSerie, String NroDoc) throws Exception {
        String resp = null;
        System.out.println("Cia: " + Cia + " Planta: "+ idPlanta + " Serie: "+ sSerie + " NroDocRef: " + NroDoc);
        Sql.SQLpss = cadena_conexion().prepareStatement(" "
        		+ "SELECT DATEDIFF(d,FECHA ,FECHA_VENCIMIENTO), FECHA, FECHA_VENCIMIENTO FROM DOCUMENTO "
        		+ "WHERE CIA='"+ Cia + "' and ID_PLANTA='"+ idPlanta + "' and serie='"+ sSerie +"' and NRO_DOCUMENTO='"+ NroDoc +"'and ID_TIPO_DOC='FAC';");
        
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();

        while (Sql.SQLrss.next()) {
            resp = Sql.SQLrss.getString(1);
        }


        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
        return resp;
    }
}
