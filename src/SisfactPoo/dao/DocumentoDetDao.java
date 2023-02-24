/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo.dao;

import SisfactPoo.beans.DocumentoDet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kbarreda
 */
public interface DocumentoDetDao {
    public List<DocumentoDet> listar(String cia,String id_planta,String id_tipo_doc,String Serie,String Numero)throws SQLException;
}
