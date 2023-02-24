/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo.beans;

/**
 *
 * @author kbarreda
 */
public class DocumentoDet {
    private String ITEM;
    private String CANTIDAD_FAC;
    private String SUBTOTAL_CON_DESCUENTO;
    private String TOTAL;
    private String ID_UNIDAD;
    private String SUBTOTAL_CON_DESCUENTO_IGV;
    private String IGV;
    private String PORC_IGV;
    private String DESCRIPCION_PRODUCTO;
    private String PRECIO_UNITARIO;
    private String MONTO_DESCUENTO;
    private String PORC_IGV_SIN_DECIMAL;
    private String SUBTOTAL_SIN_DESCUENTO;
    private String SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES;
    private String MONTO_DESCUENTO_CON_DECIMALES;
    private String DESCRIPCION_NOTA;
    private String TEMPERATURA;
    private String API;
    private String PRECIO_DESCUENTO;

    public DocumentoDet() {
    }

    
    public String getITEM() {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getCANTIDAD_FAC() {
        return CANTIDAD_FAC;
    }

    public void setCANTIDAD_FAC(String CANTIDAD_FAC) {
        this.CANTIDAD_FAC = CANTIDAD_FAC;
    }

    public String getSUBTOTAL_CON_DESCUENTO() {
        return SUBTOTAL_CON_DESCUENTO;
    }

    public void setSUBTOTAL_CON_DESCUENTO(String SUBTOTAL_CON_DESCUENTO) {
        this.SUBTOTAL_CON_DESCUENTO = SUBTOTAL_CON_DESCUENTO;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getID_UNIDAD() {
        return ID_UNIDAD;
    }

    public void setID_UNIDAD(String ID_UNIDAD) {
        this.ID_UNIDAD = ID_UNIDAD;
    }

    public String getSUBTOTAL_CON_DESCUENTO_IGV() {
        return SUBTOTAL_CON_DESCUENTO_IGV;
    }

    public void setSUBTOTAL_CON_DESCUENTO_IGV(String SUBTOTAL_CON_DESCUENTO_IGV) {
        this.SUBTOTAL_CON_DESCUENTO_IGV = SUBTOTAL_CON_DESCUENTO_IGV;
    }

    public String getIGV() {
        return IGV;
    }

    public void setIGV(String IGV) {
        this.IGV = IGV;
    }

    public String getPORC_IGV() {
        return PORC_IGV;
    }

    public void setPORC_IGV(String PORC_IGV) {
        this.PORC_IGV = PORC_IGV;
    }

    public String getDESCRIPCION_PRODUCTO() {
        return DESCRIPCION_PRODUCTO;
    }

    public void setDESCRIPCION_PRODUCTO(String DESCRIPCION_PRODUCTO) {
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
    }

    public String getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }

    public void setPRECIO_UNITARIO(String PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }

    public String getMONTO_DESCUENTO() {
        return MONTO_DESCUENTO;
    }

    public void setMONTO_DESCUENTO(String MONTO_DESCUENTO) {
        this.MONTO_DESCUENTO = MONTO_DESCUENTO;
    }

    public String getPORC_IGV_SIN_DECIMAL() {
        return PORC_IGV_SIN_DECIMAL;
    }

    public void setPORC_IGV_SIN_DECIMAL(String PORC_IGV_SIN_DECIMAL) {
        this.PORC_IGV_SIN_DECIMAL = PORC_IGV_SIN_DECIMAL;
    }

    public String getSUBTOTAL_SIN_DESCUENTO() {
        return SUBTOTAL_SIN_DESCUENTO;
    }

    public void setSUBTOTAL_SIN_DESCUENTO(String SUBTOTAL_SIN_DESCUENTO) {
        this.SUBTOTAL_SIN_DESCUENTO = SUBTOTAL_SIN_DESCUENTO;
    }

    public String getSUBTOTAL_CON_DESCUENTO_MAS_DECIMALES() {
        return SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES;
    }

    public void setSUBTOTAL_CON_DESCUENTO_MAS_DECIMALES(String SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES) {
        this.SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES = SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES;
    }

    public String getMONTO_DESCUENTO_CON_DECIMALES() {
        return MONTO_DESCUENTO_CON_DECIMALES;
    }

    public void setMONTO_DESCUENTO_CON_DECIMALES(String MONTO_DESCUENTO_CON_DECIMALES) {
        this.MONTO_DESCUENTO_CON_DECIMALES = MONTO_DESCUENTO_CON_DECIMALES;
    }

    public String getDESCRIPCION_NOTA() {
        return DESCRIPCION_NOTA;
    }

    public void setDESCRIPCION_NOTA(String DESCRIPCION_NOTA) {
        this.DESCRIPCION_NOTA = DESCRIPCION_NOTA;
    }

    public String getTEMPERATURA() {
        return TEMPERATURA;
    }

    public void setTEMPERATURA(String TEMPERATURA) {
        this.TEMPERATURA = TEMPERATURA;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getPRECIO_DESCUENTO() {
        return PRECIO_DESCUENTO;
    }

    public void setPRECIO_DESCUENTO(String PRECIO_DESCUENTO) {
        this.PRECIO_DESCUENTO = PRECIO_DESCUENTO;
    }

    @Override
    public String toString() {
        return "DocumentoDet{" + "ITEM=" + ITEM + ", CANTIDAD_FAC=" + CANTIDAD_FAC + ", SUBTOTAL_CON_DESCUENTO=" + SUBTOTAL_CON_DESCUENTO + ", TOTAL=" + TOTAL + ", ID_UNIDAD=" + ID_UNIDAD + ", SUBTOTAL_CON_DESCUENTO_IGV=" + SUBTOTAL_CON_DESCUENTO_IGV + ", IGV=" + IGV + ", PORC_IGV=" + PORC_IGV + ", DESCRIPCION_PRODUCTO=" + DESCRIPCION_PRODUCTO + ", PRECIO_UNITARIO=" + PRECIO_UNITARIO + ", MONTO_DESCUENTO=" + MONTO_DESCUENTO + ", PORC_IGV_SIN_DECIMAL=" + PORC_IGV_SIN_DECIMAL + ", SUBTOTAL_SIN_DESCUENTO=" + SUBTOTAL_SIN_DESCUENTO + ", SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES=" + SUBTOTAL_CON_DESCUENTO_MAS_DECIMALES + ", MONTO_DESCUENTO_CON_DECIMALES=" + MONTO_DESCUENTO_CON_DECIMALES + ", DESCRIPCION_NOTA=" + DESCRIPCION_NOTA + ", TEMPERATURA=" + TEMPERATURA + ", API=" + API + ", PRECIO_DESCUENTO=" + PRECIO_DESCUENTO + '}';
    }
    
    
    
    
    
}
