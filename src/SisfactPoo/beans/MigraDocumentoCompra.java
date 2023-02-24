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
public class MigraDocumentoCompra {
     public String CIA;
    public String ID_PLANTA;
    public String ID_PROVEEDOR;
    public String ID_TIPO_DOC;
    public String SERIE_DOC;
    public String NRO_DOC;
    public String ID_CONDICION_PAGO;
    public String FECHA;
    public String FECHA_VENCIMIENTO;
    public String ID_MONEDA_DOC;
    public String ID_MONEDA_LOCAL;
    public String ID_MONEDA_EXT;
    public String TIPO_CAMBIO;
    public String FACTOR_CONV_DOC_LOCAL;
    public String FACTOR_CONV_DOC_EXT;
    public String FACTOR_CONV_LOCAL_DOC;
    public String FACTOR_CONV_LOCAL_EXT;
    public String TOTAL_PRODUCTO_ANTES_IMP;
    public String SUBTOTAL;
    public String IMPUESTO;
    public String TOTAL;
    public String ID_MOTIVO;
    public String OBSERVACION;
    public String FLAG_AFECTO_IGV;
    public String ID_ESTADO;
    public String FECHA_ENVIO;
    public String USUARIO_ENVIO;
    public String FECHA_SISTEMA;
    public String USUARIO_SISTEMA;
    public String FECHA_MOD;
    public String USUARIO_MOD;
    public String FECHA_ANULA;
    public String USUARIO_ANULA;
    public String VALOR_NO_AFECTO;
    public String OTROS_IMPUESTOS;
    public String VALOR_VENTA;
    public String FISE;
    public String ISC;
    public String RODAJE;
    public String SUBTOTAL_CON_FISE;
    public String MONTO_PERCEPCION;
    public String TOTAL_A_PAGAR;
    public String PLACA_TRACTOR;
    public String PLACA_CISTERNA;
    public String SERIE_PER;
    public String NRO_DOC_PER;
    public String NRO_SCOP;
    //CAMPO NUEVO PARA EL SCOP
    public String NRO_SCOP_VENTA;
    
    public String getNRO_SCOP_VENTA() {
        return NRO_SCOP_VENTA;
    }

    public void setNRO_SCOP_VENTA(String NRO_SCOP_VENTA) {
        this.NRO_SCOP_VENTA = NRO_SCOP_VENTA;
    }
    
    
  //CAMPO NUEVO DE LICENCIA
    public String ID_CHOFER;
    
    //nuevo get y set
    public String getID_CHOFER() {
        return ID_CHOFER;
    }

    public void setID_CHOFER(String ID_CHOFER) {
        this.ID_CHOFER = ID_CHOFER;
    }

    public MigraDocumentoCompra() {
    }

    public String getCIA() {
        return CIA;
    }

    public void setCIA(String CIA) {
        this.CIA = CIA;
    }

    public String getID_PLANTA() {
        return ID_PLANTA;
    }

    public void setID_PLANTA(String ID_PLANTA) {
        this.ID_PLANTA = ID_PLANTA;
    }

    public String getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(String ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getID_TIPO_DOC() {
        return ID_TIPO_DOC;
    }

    public void setID_TIPO_DOC(String ID_TIPO_DOC) {
        this.ID_TIPO_DOC = ID_TIPO_DOC;
    }

    public String getSERIE_DOC() {
        return SERIE_DOC;
    }

    public void setSERIE_DOC(String SERIE_DOC) {
        this.SERIE_DOC = SERIE_DOC;
    }

    public String getNRO_DOC() {
        return NRO_DOC;
    }

    public void setNRO_DOC(String NRO_DOC) {
        this.NRO_DOC = NRO_DOC;
    }

    public String getID_CONDICION_PAGO() {
        return ID_CONDICION_PAGO;
    }

    public void setID_CONDICION_PAGO(String ID_CONDICION_PAGO) {
        this.ID_CONDICION_PAGO = ID_CONDICION_PAGO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getFECHA_VENCIMIENTO() {
        return FECHA_VENCIMIENTO;
    }

    public void setFECHA_VENCIMIENTO(String FECHA_VENCIMIENTO) {
        this.FECHA_VENCIMIENTO = FECHA_VENCIMIENTO;
    }

    public String getID_MONEDA_DOC() {
        return ID_MONEDA_DOC;
    }

    public void setID_MONEDA_DOC(String ID_MONEDA_DOC) {
        this.ID_MONEDA_DOC = ID_MONEDA_DOC;
    }

    public String getID_MONEDA_LOCAL() {
        return ID_MONEDA_LOCAL;
    }

    public void setID_MONEDA_LOCAL(String ID_MONEDA_LOCAL) {
        this.ID_MONEDA_LOCAL = ID_MONEDA_LOCAL;
    }

    public String getID_MONEDA_EXT() {
        return ID_MONEDA_EXT;
    }

    public void setID_MONEDA_EXT(String ID_MONEDA_EXT) {
        this.ID_MONEDA_EXT = ID_MONEDA_EXT;
    }

    public String getTIPO_CAMBIO() {
        return TIPO_CAMBIO;
    }

    public void setTIPO_CAMBIO(String TIPO_CAMBIO) {
        this.TIPO_CAMBIO = TIPO_CAMBIO;
    }

    public String getFACTOR_CONV_DOC_LOCAL() {
        return FACTOR_CONV_DOC_LOCAL;
    }

    public void setFACTOR_CONV_DOC_LOCAL(String FACTOR_CONV_DOC_LOCAL) {
        this.FACTOR_CONV_DOC_LOCAL = FACTOR_CONV_DOC_LOCAL;
    }

    public String getFACTOR_CONV_DOC_EXT() {
        return FACTOR_CONV_DOC_EXT;
    }

    public void setFACTOR_CONV_DOC_EXT(String FACTOR_CONV_DOC_EXT) {
        this.FACTOR_CONV_DOC_EXT = FACTOR_CONV_DOC_EXT;
    }

    public String getFACTOR_CONV_LOCAL_DOC() {
        return FACTOR_CONV_LOCAL_DOC;
    }

    public void setFACTOR_CONV_LOCAL_DOC(String FACTOR_CONV_LOCAL_DOC) {
        this.FACTOR_CONV_LOCAL_DOC = FACTOR_CONV_LOCAL_DOC;
    }

    public String getFACTOR_CONV_LOCAL_EXT() {
        return FACTOR_CONV_LOCAL_EXT;
    }

    public void setFACTOR_CONV_LOCAL_EXT(String FACTOR_CONV_LOCAL_EXT) {
        this.FACTOR_CONV_LOCAL_EXT = FACTOR_CONV_LOCAL_EXT;
    }

    public String getTOTAL_PRODUCTO_ANTES_IMP() {
        return TOTAL_PRODUCTO_ANTES_IMP;
    }

    public void setTOTAL_PRODUCTO_ANTES_IMP(String TOTAL_PRODUCTO_ANTES_IMP) {
        this.TOTAL_PRODUCTO_ANTES_IMP = TOTAL_PRODUCTO_ANTES_IMP;
    }

    public String getSUBTOTAL() {
        return SUBTOTAL;
    }

    public void setSUBTOTAL(String SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    public String getIMPUESTO() {
        return IMPUESTO;
    }

    public void setIMPUESTO(String IMPUESTO) {
        this.IMPUESTO = IMPUESTO;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getID_MOTIVO() {
        return ID_MOTIVO;
    }

    public void setID_MOTIVO(String ID_MOTIVO) {
        this.ID_MOTIVO = ID_MOTIVO;
    }

    public String getOBSERVACION() {
        return OBSERVACION;
    }

    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    public String getFLAG_AFECTO_IGV() {
        return FLAG_AFECTO_IGV;
    }

    public void setFLAG_AFECTO_IGV(String FLAG_AFECTO_IGV) {
        this.FLAG_AFECTO_IGV = FLAG_AFECTO_IGV;
    }

    public String getID_ESTADO() {
        return ID_ESTADO;
    }

    public void setID_ESTADO(String ID_ESTADO) {
        this.ID_ESTADO = ID_ESTADO;
    }

    public String getFECHA_ENVIO() {
        return FECHA_ENVIO;
    }

    public void setFECHA_ENVIO(String FECHA_ENVIO) {
        this.FECHA_ENVIO = FECHA_ENVIO;
    }

    public String getUSUARIO_ENVIO() {
        return USUARIO_ENVIO;
    }

    public void setUSUARIO_ENVIO(String USUARIO_ENVIO) {
        this.USUARIO_ENVIO = USUARIO_ENVIO;
    }

    public String getFECHA_SISTEMA() {
        return FECHA_SISTEMA;
    }

    public void setFECHA_SISTEMA(String FECHA_SISTEMA) {
        this.FECHA_SISTEMA = FECHA_SISTEMA;
    }

    public String getUSUARIO_SISTEMA() {
        return USUARIO_SISTEMA;
    }

    public void setUSUARIO_SISTEMA(String USUARIO_SISTEMA) {
        this.USUARIO_SISTEMA = USUARIO_SISTEMA;
    }

    public String getFECHA_MOD() {
        return FECHA_MOD;
    }

    public void setFECHA_MOD(String FECHA_MOD) {
        this.FECHA_MOD = FECHA_MOD;
    }

    public String getUSUARIO_MOD() {
        return USUARIO_MOD;
    }

    public void setUSUARIO_MOD(String USUARIO_MOD) {
        this.USUARIO_MOD = USUARIO_MOD;
    }

    public String getFECHA_ANULA() {
        return FECHA_ANULA;
    }

    public void setFECHA_ANULA(String FECHA_ANULA) {
        this.FECHA_ANULA = FECHA_ANULA;
    }

    public String getUSUARIO_ANULA() {
        return USUARIO_ANULA;
    }

    public void setUSUARIO_ANULA(String USUARIO_ANULA) {
        this.USUARIO_ANULA = USUARIO_ANULA;
    }

    public String getVALOR_NO_AFECTO() {
        return VALOR_NO_AFECTO;
    }

    public void setVALOR_NO_AFECTO(String VALOR_NO_AFECTO) {
        this.VALOR_NO_AFECTO = VALOR_NO_AFECTO;
    }

    public String getOTROS_IMPUESTOS() {
        return OTROS_IMPUESTOS;
    }

    public void setOTROS_IMPUESTOS(String OTROS_IMPUESTOS) {
        this.OTROS_IMPUESTOS = OTROS_IMPUESTOS;
    }

    public String getVALOR_VENTA() {
        return VALOR_VENTA;
    }

    public void setVALOR_VENTA(String VALOR_VENTA) {
        this.VALOR_VENTA = VALOR_VENTA;
    }

    public String getFISE() {
        return FISE;
    }

    public void setFISE(String FISE) {
        this.FISE = FISE;
    }

    public String getISC() {
        return ISC;
    }

    public void setISC(String ISC) {
        this.ISC = ISC;
    }

    public String getRODAJE() {
        return RODAJE;
    }

    public void setRODAJE(String RODAJE) {
        this.RODAJE = RODAJE;
    }

    public String getSUBTOTAL_CON_FISE() {
        return SUBTOTAL_CON_FISE;
    }

    public void setSUBTOTAL_CON_FISE(String SUBTOTAL_CON_FISE) {
        this.SUBTOTAL_CON_FISE = SUBTOTAL_CON_FISE;
    }

    public String getMONTO_PERCEPCION() {
        return MONTO_PERCEPCION;
    }

    public void setMONTO_PERCEPCION(String MONTO_PERCEPCION) {
        this.MONTO_PERCEPCION = MONTO_PERCEPCION;
    }

    public String getTOTAL_A_PAGAR() {
        return TOTAL_A_PAGAR;
    }

    public void setTOTAL_A_PAGAR(String TOTAL_A_PAGAR) {
        this.TOTAL_A_PAGAR = TOTAL_A_PAGAR;
    }

    public String getPLACA_TRACTOR() {
        return PLACA_TRACTOR;
    }

    public void setPLACA_TRACTOR(String PLACA_TRACTOR) {
        this.PLACA_TRACTOR = PLACA_TRACTOR;
    }

    public String getPLACA_CISTERNA() {
        return PLACA_CISTERNA;
    }

    public void setPLACA_CISTERNA(String PLACA_CISTERNA) {
        this.PLACA_CISTERNA = PLACA_CISTERNA;
    }

    public String getSERIE_PER() {
        return SERIE_PER;
    }

    public void setSERIE_PER(String SERIE_PER) {
        this.SERIE_PER = SERIE_PER;
    }

    public String getNRO_DOC_PER() {
        return NRO_DOC_PER;
    }

    public void setNRO_DOC_PER(String NRO_DOC_PER) {
        this.NRO_DOC_PER = NRO_DOC_PER;
    }

    public String getNRO_SCOP() {
        return NRO_SCOP;
    }

    public void setNRO_SCOP(String NRO_SCOP) {
        this.NRO_SCOP = NRO_SCOP;
    }

    @Override
    public String toString() {
        return "Documento{" + "CIA=" + CIA + ", ID_PLANTA=" + ID_PLANTA + ", ID_PROVEEDOR=" + ID_PROVEEDOR + ", ID_TIPO_DOC=" + ID_TIPO_DOC + ", SERIE_DOC=" + SERIE_DOC + ", NRO_DOC=" + NRO_DOC + ", ID_CONDICION_PAGO=" + ID_CONDICION_PAGO + ", FECHA=" + FECHA + ", FECHA_VENCIMIENTO=" + FECHA_VENCIMIENTO + ", ID_MONEDA_DOC=" + ID_MONEDA_DOC + ", ID_MONEDA_LOCAL=" + ID_MONEDA_LOCAL + ", ID_MONEDA_EXT=" + ID_MONEDA_EXT + ", TIPO_CAMBIO=" + TIPO_CAMBIO + ", FACTOR_CONV_DOC_LOCAL=" + FACTOR_CONV_DOC_LOCAL + ", FACTOR_CONV_DOC_EXT=" + FACTOR_CONV_DOC_EXT + ", FACTOR_CONV_LOCAL_DOC=" + FACTOR_CONV_LOCAL_DOC + ", FACTOR_CONV_LOCAL_EXT=" + FACTOR_CONV_LOCAL_EXT + ", TOTAL_PRODUCTO_ANTES_IMP=" + TOTAL_PRODUCTO_ANTES_IMP + ", SUBTOTAL=" + SUBTOTAL + ", IMPUESTO=" + IMPUESTO + ", TOTAL=" + TOTAL + ", ID_MOTIVO=" + ID_MOTIVO + ", OBSERVACION=" + OBSERVACION + ", FLAG_AFECTO_IGV=" + FLAG_AFECTO_IGV + ", ID_ESTADO=" + ID_ESTADO + ", FECHA_ENVIO=" + FECHA_ENVIO + ", USUARIO_ENVIO=" + USUARIO_ENVIO + ", FECHA_SISTEMA=" + FECHA_SISTEMA + ", USUARIO_SISTEMA=" + USUARIO_SISTEMA + ", FECHA_MOD=" + FECHA_MOD + ", USUARIO_MOD=" + USUARIO_MOD + ", FECHA_ANULA=" + FECHA_ANULA + ", USUARIO_ANULA=" + USUARIO_ANULA + ", VALOR_NO_AFECTO=" + VALOR_NO_AFECTO + ", OTROS_IMPUESTOS=" + OTROS_IMPUESTOS + ", VALOR_VENTA=" + VALOR_VENTA + ", FISE=" + FISE + ", ISC=" + ISC + ", RODAJE=" + RODAJE + ", SUBTOTAL_CON_FISE=" + SUBTOTAL_CON_FISE + ", MONTO_PERCEPCION=" + MONTO_PERCEPCION + ", TOTAL_A_PAGAR=" + TOTAL_A_PAGAR + ", PLACA_TRACTOR=" + PLACA_TRACTOR + ", PLACA_CISTERNA=" + PLACA_CISTERNA + ", SERIE_PER=" + SERIE_PER + ", NRO_DOC_PER=" + NRO_DOC_PER + ", NRO_SCOP=" + NRO_SCOP + '}';
    }
    

}
