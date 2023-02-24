/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import static LogicaSql.Sql.cadena_conexion;

import javax.swing.JOptionPane;

import Datos.Datos;

/**
 *
 * @author kbarreda
 */
public class LogLoginSql {
    public static void LoginSQL(String Cia, String usuario, String Clave) throws Exception {
        Datos.ListPlantas.clear();
        Sql.SQLpss = cadena_conexion().prepareStatement("declare @cia VARCHAR(2),@usuario VARCHAR(20),@clave VARCHAR(20),@sOk varchar(20)='', @sAdmin varchar(1)\n"
                + " 	set @cia='" + Cia + "'set @usuario='" + usuario + "'set @clave='" + Clave + "'\n"
                + "select @sOk=ID_USUARIO from USUARIO \n"
                + "where ID_USUARIO=@usuario and CONVERT(VARCHAR(30),  DecryptByPassphrase('bi1907', PASS_WORD) )=@clave\n"
                + "and ID_ESTADO='01'\n"
                + "\n"
                + "if len(@sOk) > 0\n"
                + " BEGIN\n"
                + "	select @sAdmin = isnull(FLAG_ADMIN_CIA,0) from CIA_USUARIO where ID_USUARIO=@usuario and CIA=@cia and ID_ESTADO='01'\n"
                + "	if @sAdmin = '1'\n"
                + "	 BEGIN\n"
                + "		select ID_PLANTA from PLANTA where cia=@cia and ID_ESTADO='01'\n"
                + "	 END\n"
                + "	 \n"
                + "	else\n"
                + "	 BEGIN\n"
                + "		select ID_PLANTA from USUARIO_CIA_FACTURACION_PLANTA where cia=@cia and  ID_USUARIO=@usuario and ID_ESTADO='01'\n"
                + "	 END\n"
                + " END\n"
                + " else\n"
                + "  BEGIN\n"
                + " select 'ERROR' as ID_PLANTA\n"
                + "  END");
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            Datos.EstadoLogin=Sql.SQLrss.getString(1);
            Datos.ListPlantas.add(Sql.SQLrss.getString(1));
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
    }
}
