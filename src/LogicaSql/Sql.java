/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * D
 * 
 * @author kbarreda
 */
public class Sql {

    public static Connection SQLcnu = null;
    public static PreparedStatement SQLpss;
    public static ResultSet SQLrss;
    public static String EstadoConeccionSql;
    public static ResultSetMetaData SQLrsm;

    public static Connection cadena_conexion() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // PRUEBAS
            // SQLcnu = DriverManager.getConnection("jdbc:sqlserver://192.168.2.12;database=PETRODES",
            //        "sa", "sa");
            // PRODUCCION
            SQLcnu =
            DriverManager.getConnection("jdbc:sqlserver://192.168.2.12;database=PETROAMER",
            "sa", "sa");
            EstadoConeccionSql = "conectadoSQL";
        } catch (ClassNotFoundException | SQLException e) {
            EstadoConeccionSql = "errorSQL";
        }
        return SQLcnu;
    }
}
