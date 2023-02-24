/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo.dao;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import SisfactPoo.beans.DocumentoDet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarreda
 */
public class DocumentoDetDaoImpl implements DocumentoDetDao {

    @Override
    public List<DocumentoDet> listar(String cia, String id_planta, String id_tipo_doc, String Serie, String Numero) throws SQLException {
        if ("N-C".equals(id_tipo_doc)) {
            id_tipo_doc = "N/C";
        } else if ("N-D".equals(id_tipo_doc)) {
            id_tipo_doc = "N/D";
        } else {

        }

        String ArmarConsulta = "select dd.ITEM,\n"
                + "convert(varchar, case when dd.CANTIDAD_FAC = 0 then 1 else dd.CANTIDAD_FAC end) AS CANTIDAD_FAC,\n"
                + "convert(varchar, dd.SUBTOTAL_CON_DESCUENTO) AS SUBTOTAL_CON_DESCUENTO,\n"
                + "convert(varchar, dd.TOTAL) AS TOTAL,\n"
                + "u.ID_UNIDAD AS ID_UNIDAD,\n"
                + "convert(varchar, convert(decimal(30,2), (dd.SUBTOTAL_CON_DESCUENTO+IGV)/case when dd.CANTIDAD_FAC = 0 then 1 else dd.CANTIDAD_FAC end )) AS SUBTOTAL_CON_DESCUENTO_IGV ,\n"
                + "convert(varchar, dd.IGV) AS IGV,\n"
                + "convert(varchar, dd.PORC_IGV) AS PORC_IGV,\n"
                + "a.DESCRIPCION AS DESCRIPCION_PRODUCTO,convert(varchar, convert(decimal(20,4), dd.PRECIO_UNITARIO)) AS PRECIO_UNITARIO,\n"
                + "convert(varchar, isnull(dd.MONTO_DESCUENTO, 0)) AS MONTO_DESCUENTO,\n"
                + "convert(varchar, convert(decimal(2,0), dd.PORC_IGV)) AS PORC_IGV_SIN_DECIMAL,\n"
                + "convert(varchar, dd.SUBTOTAL_SIN_DESCUENTO) AS SUBTOTAL_SIN_DESCUENTO,\n"
                + "convert(varchar, convert(decimal(30,9), (dd.SUBTOTAL_CON_DESCUENTO+IGV)/case when dd.CANTIDAD_FAC = 0 then 1 else dd.CANTIDAD_FAC end ) ) AS SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES,\n"
                + "convert(varchar,dd.MONTO_DESCUENTO) AS MONTO_DESCUENTO_CON_DECIMALES\n"
                + ",dna.DESCRIPCION_NOTA AS DESCRIPCION_NOTA\n"
                + ",convert(varchar,dd.TEMPERATURA) AS TEMPERATURA, convert(varchar,dd.API) AS API,convert(varchar, convert(decimal(20,2), dd.PRECIO_DESCUENTO)) AS PRECIO_DESCUENTO\n"
                + "\n"
                + "from DOCUMENTO_DET dd left join ARTICULO a on a.CIA=dd.CIA and a.ID_ARTICULO=dd.ID_ARTICULO\n"
                + "left join UNIDAD u on u.ID_UNIDAD=dd.ID_UNIDAD\n"
                + "left join DOCUMENTO_NOTA_AJUSTE dna on dna.CIA=dd.CIA and dna.ID_PLANTA=dd.ID_PLANTA and dna.ID_TIPO_DOC=dd.ID_TIPO_DOC\n"
                + "	and dna.SERIE=dd.SERIE and dna.NRO_DOCUMENTO=dd.NRO_DOCUMENTO\n"
                + "where dd.CIA=? and dd.ID_PLANTA=? and dd.ID_TIPO_DOC=?\n"
                + "and dd.SERIE=? and dd.NRO_DOCUMENTO=? and dd.ID_ESTADO='01' \n"
                + "order by dd.ITEM";
        List<DocumentoDet> documentoDets = new ArrayList<DocumentoDet>();
        Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
        Sql.SQLpss.setString(1, cia);
        Sql.SQLpss.setString(2, id_planta);
        Sql.SQLpss.setString(3, id_tipo_doc);
        Sql.SQLpss.setString(4, "0" + Serie);
        Sql.SQLpss.setString(5, Numero);
        Sql.SQLrss = Sql.SQLpss.executeQuery();
        Sql.SQLrsm = Sql.SQLrss.getMetaData();
        while (Sql.SQLrss.next()) {
            DocumentoDet documentodet = new DocumentoDet();
            documentodet.setITEM(Sql.SQLrss.getString("ITEM"));
            documentodet.setCANTIDAD_FAC(Sql.SQLrss.getString("CANTIDAD_FAC"));
            documentodet.setSUBTOTAL_CON_DESCUENTO(Sql.SQLrss.getString("SUBTOTAL_CON_DESCUENTO"));
            //CAMBIO
            documentodet.setTOTAL(Sql.SQLrss.getString("TOTAL"));
            //
            documentodet.setID_UNIDAD(Sql.SQLrss.getString("ID_UNIDAD"));
            documentodet.setSUBTOTAL_CON_DESCUENTO_IGV(Sql.SQLrss.getString("SUBTOTAL_CON_DESCUENTO_IGV"));
            documentodet.setIGV(Sql.SQLrss.getString("IGV"));
            documentodet.setPORC_IGV(Sql.SQLrss.getString("PORC_IGV"));
            documentodet.setDESCRIPCION_PRODUCTO(Sql.SQLrss.getString("DESCRIPCION_PRODUCTO"));
            //CAMBIO
            documentodet.setPRECIO_UNITARIO(Sql.SQLrss.getString("PRECIO_UNITARIO"));
            //
            documentodet.setMONTO_DESCUENTO(Sql.SQLrss.getString("MONTO_DESCUENTO"));
            documentodet.setPORC_IGV_SIN_DECIMAL(Sql.SQLrss.getString("PORC_IGV_SIN_DECIMAL"));
            documentodet.setSUBTOTAL_SIN_DESCUENTO(Sql.SQLrss.getString("SUBTOTAL_SIN_DESCUENTO"));
            documentodet.setSUBTOTAL_CON_DESCUENTO_MAS_DECIMALES(Sql.SQLrss.getString("SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES"));
            documentodet.setMONTO_DESCUENTO_CON_DECIMALES(Sql.SQLrss.getString("MONTO_DESCUENTO_CON_DECIMALES"));
            documentodet.setDESCRIPCION_NOTA(Sql.SQLrss.getString("DESCRIPCION_NOTA"));
            documentodet.setTEMPERATURA(Sql.SQLrss.getString("TEMPERATURA"));
            documentodet.setAPI(Sql.SQLrss.getString("API"));
            documentodet.setPRECIO_DESCUENTO(Sql.SQLrss.getString("PRECIO_DESCUENTO"));
            documentoDets.add(documentodet);
        }
        Sql.SQLrss.close();
        Sql.SQLcnu.close();
        Sql.SQLpss.close();
        return documentoDets;

    }

}
