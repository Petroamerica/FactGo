/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo.dao;

import SisfactPoo.beans.Documento;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kbarreda
 */
public interface DocumentoDao {
    public List<Documento> listar() throws SQLException;

    public List<Documento> listarbajas() throws SQLException;

}
