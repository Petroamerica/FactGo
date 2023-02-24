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
public class Documento {
    private String CIA;
    private String ID_PLANTA;
    private String ID_PLANTA_DIRECCION;
    private String ID_TIPO_DOC;
    private String SERIE;
    private String NRO_DOCUMENTO;
    private String SUBTOTAL;
    private String FECHA;
    private String HORA;
    private String ID_MONEDA_DOC;
    private String NRO_DI;
    private String DESCRIPCION_CLIENTE;
    private String ID_DISTRITO;
    private String DIRECCION_CLIENTE;
    private String DEPARTAMENTO_CLIENTE;
    private String PROVINCIA_CLIENTE;
    private String DISTRITO_CLIENTE;
    private String RAZON_SOCIAL_CLIENTE;
    private String EMAIL_CLIENTE;
    private String FECHA_VENCE;
    private String IGV;
    private String PORC_IGV;
    private String TOTAL;
    private String MONEDA_DESCRIPCION;
    private String ID_TIPO_DOC_REF;
    private String SERIE_REF;
    private String NRO_DOCUMENTO_REF;
    private String ID_MOTIVO_TIPO_SUNAT;
    private String DESCRIPCION_NOTA;
    private String MONTO_PER;
    private String TOTAL_MONTO_PER;
    private String RAZON_SOCIAL_TRANSPORTE;
    private String RUC_EMP_TRANSPORTE;
    private String CHOFER_DESCRIP;
    private String ID_CHOFER;
    private String PLACA_TRACTOR;
    private String NRO_AUTORIZACION;
    private String DIRECCION_ORIGEN;
    private String DEPART_ORIGEN;
    private String PROVIN_ORIGEN;
    private String DISTRI_ORIGEN;
    private String UBIGEO_ORIGEN;
    private String PLANTAORIGEN;
    private String DIRECCION_DESTINO;
    private String DISTRI_DESTINO;
    private String PROVIN_DESTINO;
    private String DEPART_DESTINO;
    private String UBIGEO_DESTINO;
    private String CONDICION_PAGO;
    private String NRO_SCOP;
    private String PLACA_CISTERNA;
    private String NRO_CUBICACION;
    private String MONTO_DESCUENTO;
    private String FECHA_SISTEMA;
    private String PORC_PERCEPCION;
    private String ID_CLIENTE;
    private String ID_TIPO_DOC_CREDITO;
    private String NRO_PAGO;
    private String TOTAL_PESO;
    private String FECHAACTUAL;

    private String CANTIDADITEM;
    private String MOTIVO_SUNAT;
    // se agrego de la linea 77 al 86 - 04/02/2022 -
    private String ORDEN_COMPRA;

    // se agrego la linea 79 29/04/2021
    private String ID_PLANTA_REF;

    private String ID_CONDICION_PAGO;

    // se agrego la linea 84 22/07/2022
    private String PORC_NOS_RETIENEN;
    private String FLAG_ES_AGENTE_RETENEDOR;
    private String MONTO_RETENEDOR;
    private String MONTO_PORC_RETENCION;

    // se agergo linea 81 04/08/2022
    private String ID_CLASIFICACION_TIPO_NEGOCIO;
    private String FLAG_CONDICION_PAGO_CREDITO;
    private String MONTO_APLICA_DEBITO_REF;
    private String PER_CORRELATIVO_CANCELACION;
    
    // se agrego el atributo FLAG_CONDICION_PAGO_CREDITO_REF 10/02/2023
    private String FLAG_CONDICION_PAGO_CREDITO_REF;
    
    public String getFLAG_CONDICION_PAGO_CREDITO_REF() {
    	return FLAG_CONDICION_PAGO_CREDITO_REF;
    }

    public void setFLAG_CONDICION_PAGO_CREDITO_REF(String pFLAG_CONDICION_PAGO_CREDITO_REF) {
    	FLAG_CONDICION_PAGO_CREDITO_REF = pFLAG_CONDICION_PAGO_CREDITO_REF;
    }


    public String getPER_CORRELATIVO_CANCELACION() {
        return PER_CORRELATIVO_CANCELACION;
    }

    public void setPER_CORRELATIVO_CANCELACION(String pER_CORRELATIVO_CANCELACION) {
        PER_CORRELATIVO_CANCELACION = pER_CORRELATIVO_CANCELACION;
    }

    public String getID_CLASIFICACION_TIPO_NEGOCIO() {
        return ID_CLASIFICACION_TIPO_NEGOCIO;
    }

    public void setID_CLASIFICACION_TIPO_NEGOCIO(String iD_CLASIFICACION_TIPO_NEGOCIO) {
        ID_CLASIFICACION_TIPO_NEGOCIO = iD_CLASIFICACION_TIPO_NEGOCIO;
    }

    public String getFLAG_CONDICION_PAGO_CREDITO() {
        return FLAG_CONDICION_PAGO_CREDITO;
    }

    public void setFLAG_CONDICION_PAGO_CREDITO(String fLAG_CONDICION_PAGO_CREDITO) {
        FLAG_CONDICION_PAGO_CREDITO = fLAG_CONDICION_PAGO_CREDITO;
    }

    public String getMONTO_APLICA_DEBITO_REF() {
        return MONTO_APLICA_DEBITO_REF;
    }

    public void setMONTO_APLICA_DEBITO_REF(String mONTO_APLICA_DEBITO_REF) {
        MONTO_APLICA_DEBITO_REF = mONTO_APLICA_DEBITO_REF;
    }

    public String getPORC_NOS_RETIENEN() {
        return PORC_NOS_RETIENEN;
    }

    public String getMONTO_PORC_RETENCION() {
        return MONTO_PORC_RETENCION;
    }

    public void setMONTO_PORC_RETENCION(String mONTO_PORC_RETENCION) {
        MONTO_PORC_RETENCION = mONTO_PORC_RETENCION;
    }

    public void setPORC_NOS_RETIENEN(String pORC_NOS_RETIENEN) {
        PORC_NOS_RETIENEN = pORC_NOS_RETIENEN;
    }

    public String getFLAG_ES_AGENTE_RETENEDOR() {
        return FLAG_ES_AGENTE_RETENEDOR;
    }

    public void setFLAG_ES_AGENTE_RETENEDOR(String fLAG_ES_AGENTE_RETENEDOR) {
        FLAG_ES_AGENTE_RETENEDOR = fLAG_ES_AGENTE_RETENEDOR;
    }

    public String getMONTO_RETENEDOR() {
        return MONTO_RETENEDOR;
    }

    public void setMONTO_RETENEDOR(String mONTO_RETENEDOR) {
        MONTO_RETENEDOR = mONTO_RETENEDOR;
    }

    public String getID_CONDICION_PAGO() {
        return ID_CONDICION_PAGO;
    }

    public void setID_CONDICION_PAGO(String iD_CONDICION_PAGO) {
        ID_CONDICION_PAGO = iD_CONDICION_PAGO;
    }

    public String getID_PLANTA_REF() {
        return ID_PLANTA_REF;
    }

    public void setID_PLANTA_REF(String iD_PLANTA_REF) {
        ID_PLANTA_REF = iD_PLANTA_REF;
    }

    public String getORDEN_COMPRA() {
        return ORDEN_COMPRA;
    }

    public void setORDEN_COMPRA(String ORDEN_COMPRA) {
        this.ORDEN_COMPRA = ORDEN_COMPRA;
    }

    public String getMOTIVO_SUNAT() {
        return MOTIVO_SUNAT;
    }

    public void setMOTIVO_SUNAT(String MOTIVO_SUNAT) {
        this.MOTIVO_SUNAT = MOTIVO_SUNAT;
    }

    public String getCANTIDADITEM() {
        return CANTIDADITEM;
    }

    public void setCANTIDADITEM(String CANTIDADITEM) {
        this.CANTIDADITEM = CANTIDADITEM;
    }

    public Documento() {
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

    public String getID_PLANTA_DIRECCION() {
        return ID_PLANTA_DIRECCION;
    }

    public void setID_PLANTA_DIRECCION(String ID_PLANTA_DIRECCION) {
        this.ID_PLANTA_DIRECCION = ID_PLANTA_DIRECCION;
    }

    public String getID_TIPO_DOC() {
        return ID_TIPO_DOC;
    }

    public void setID_TIPO_DOC(String ID_TIPO_DOC) {
        this.ID_TIPO_DOC = ID_TIPO_DOC;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getNRO_DOCUMENTO() {
        return NRO_DOCUMENTO;
    }

    public void setNRO_DOCUMENTO(String NRO_DOCUMENTO) {
        this.NRO_DOCUMENTO = NRO_DOCUMENTO;
    }

    public String getSUBTOTAL() {
        return SUBTOTAL;
    }

    public void setSUBTOTAL(String SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public String getID_MONEDA_DOC() {
        return ID_MONEDA_DOC;
    }

    public void setID_MONEDA_DOC(String ID_MONEDA_DOC) {
        this.ID_MONEDA_DOC = ID_MONEDA_DOC;
    }

    public String getNRO_DI() {
        return NRO_DI;
    }

    public void setNRO_DI(String NRO_DI) {
        this.NRO_DI = NRO_DI;
    }

    public String getDESCRIPCION_CLIENTE() {
        return DESCRIPCION_CLIENTE;
    }

    public void setDESCRIPCION_CLIENTE(String DESCRIPCION_CLIENTE) {
        this.DESCRIPCION_CLIENTE = DESCRIPCION_CLIENTE;
    }

    public String getID_DISTRITO() {
        return ID_DISTRITO;
    }

    public void setID_DISTRITO(String ID_DISTRITO) {
        this.ID_DISTRITO = ID_DISTRITO;
    }

    public String getDIRECCION_CLIENTE() {
        return DIRECCION_CLIENTE;
    }

    public void setDIRECCION_CLIENTE(String DIRECCION_CLIENTE) {
        this.DIRECCION_CLIENTE = DIRECCION_CLIENTE;
    }

    public String getDEPARTAMENTO_CLIENTE() {
        return DEPARTAMENTO_CLIENTE;
    }

    public void setDEPARTAMENTO_CLIENTE(String DEPARTAMENTO_CLIENTE) {
        this.DEPARTAMENTO_CLIENTE = DEPARTAMENTO_CLIENTE;
    }

    public String getPROVINCIA_CLIENTE() {
        return PROVINCIA_CLIENTE;
    }

    public void setPROVINCIA_CLIENTE(String PROVINCIA_CLIENTE) {
        this.PROVINCIA_CLIENTE = PROVINCIA_CLIENTE;
    }

    public String getDISTRITO_CLIENTE() {
        return DISTRITO_CLIENTE;
    }

    public void setDISTRITO_CLIENTE(String DISTRITO_CLIENTE) {
        this.DISTRITO_CLIENTE = DISTRITO_CLIENTE;
    }

    public String getRAZON_SOCIAL_CLIENTE() {
        return RAZON_SOCIAL_CLIENTE;
    }

    public void setRAZON_SOCIAL_CLIENTE(String RAZON_SOCIAL_CLIENTE) {
        this.RAZON_SOCIAL_CLIENTE = RAZON_SOCIAL_CLIENTE;
    }

    public String getEMAIL_CLIENTE() {
        return EMAIL_CLIENTE;
    }

    public void setEMAIL_CLIENTE(String EMAIL_CLIENTE) {
        this.EMAIL_CLIENTE = EMAIL_CLIENTE;
    }

    public String getFECHA_VENCE() {
        return FECHA_VENCE;
    }

    public void setFECHA_VENCE(String FECHA_VENCE) {
        this.FECHA_VENCE = FECHA_VENCE;
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

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getMONEDA_DESCRIPCION() {
        return MONEDA_DESCRIPCION;
    }

    public void setMONEDA_DESCRIPCION(String MODEDA_DESCRIPCION) {
        this.MONEDA_DESCRIPCION = MODEDA_DESCRIPCION;
    }

    public String getID_TIPO_DOC_REF() {
        return ID_TIPO_DOC_REF;
    }

    public void setID_TIPO_DOC_REF(String ID_TIPO_DOC_REF) {
        this.ID_TIPO_DOC_REF = ID_TIPO_DOC_REF;
    }

    public String getSERIE_REF() {
        return SERIE_REF;
    }

    public void setSERIE_REF(String SERIE_REF) {
        this.SERIE_REF = SERIE_REF;
    }

    public String getNRO_DOCUMENTO_REF() {
        return NRO_DOCUMENTO_REF;
    }

    public void setNRO_DOCUMENTO_REF(String NRO_DOCUMENTO_REF) {
        this.NRO_DOCUMENTO_REF = NRO_DOCUMENTO_REF;
    }

    public String getID_MOTIVO_TIPO_SUNAT() {
        return ID_MOTIVO_TIPO_SUNAT;
    }

    public void setID_MOTIVO_TIPO_SUNAT(String ID_MOTIVO_TIPO_SUNAT) {
        this.ID_MOTIVO_TIPO_SUNAT = ID_MOTIVO_TIPO_SUNAT;
    }

    public String getDESCRIPCION_NOTA() {
        return DESCRIPCION_NOTA;
    }

    public void setDESCRIPCION_NOTA(String DESCRIPCION_NOTA) {
        this.DESCRIPCION_NOTA = DESCRIPCION_NOTA;
    }

    public String getMONTO_PER() {
        return MONTO_PER;
    }

    public void setMONTO_PER(String MONTO_PER) {
        this.MONTO_PER = MONTO_PER;
    }

    public String getTOTAL_MONTO_PER() {
        return TOTAL_MONTO_PER;
    }

    public void setTOTAL_MONTO_PER(String TOTAL_MONTO_PER) {
        this.TOTAL_MONTO_PER = TOTAL_MONTO_PER;
    }

    public String getRAZON_SOCIAL_TRANSPORTE() {
        return RAZON_SOCIAL_TRANSPORTE;
    }

    public void setRAZON_SOCIAL_TRANSPORTE(String RAZON_SOCIAL_TRANSPORTE) {
        this.RAZON_SOCIAL_TRANSPORTE = RAZON_SOCIAL_TRANSPORTE;
    }

    public String getRUC_EMP_TRANSPORTE() {
        return RUC_EMP_TRANSPORTE;
    }

    public void setRUC_EMP_TRANSPORTE(String RUC_EMP_TRANSPORTE) {
        this.RUC_EMP_TRANSPORTE = RUC_EMP_TRANSPORTE;
    }

    public String getCHOFER_DESCRIP() {
        return CHOFER_DESCRIP;
    }

    public void setCHOFER_DESCRIP(String CHOFER_DESCRIP) {
        this.CHOFER_DESCRIP = CHOFER_DESCRIP;
    }

    public String getID_CHOFER() {
        return ID_CHOFER;
    }

    public void setID_CHOFER(String ID_CHOFER) {
        this.ID_CHOFER = ID_CHOFER;
    }

    public String getPLACA_TRACTOR() {
        return PLACA_TRACTOR;
    }

    public void setPLACA_TRACTOR(String PLACA_TRACTOR) {
        this.PLACA_TRACTOR = PLACA_TRACTOR;
    }

    public String getNRO_AUTORIZACION() {
        return NRO_AUTORIZACION;
    }

    public void setNRO_AUTORIZACION(String NRO_AUTORIZACION) {
        this.NRO_AUTORIZACION = NRO_AUTORIZACION;
    }

    public String getDIRECCION_ORIGEN() {
        return DIRECCION_ORIGEN;
    }

    public void setDIRECCION_ORIGEN(String DIRECCION_ORIGEN) {
        this.DIRECCION_ORIGEN = DIRECCION_ORIGEN;
    }

    public String getDEPART_ORIGEN() {
        return DEPART_ORIGEN;
    }

    public void setDEPART_ORIGEN(String DEPART_ORIGEN) {
        this.DEPART_ORIGEN = DEPART_ORIGEN;
    }

    public String getPROVIN_ORIGEN() {
        return PROVIN_ORIGEN;
    }

    public void setPROVIN_ORIGEN(String PROVIN_ORIGEN) {
        this.PROVIN_ORIGEN = PROVIN_ORIGEN;
    }

    public String getDISTRI_ORIGEN() {
        return DISTRI_ORIGEN;
    }

    public void setDISTRI_ORIGEN(String DISTRI_ORIGEN) {
        this.DISTRI_ORIGEN = DISTRI_ORIGEN;
    }

    public String getPLANTAORIGEN() {
        return PLANTAORIGEN;
    }

    public void setPLANTAORIGEN(String PLANTAORIGEN) {
        this.PLANTAORIGEN = PLANTAORIGEN;
    }

    public String getDIRECCION_DESTINO() {
        return DIRECCION_DESTINO;
    }

    public void setDIRECCION_DESTINO(String DIRECCION_DESTINO) {
        this.DIRECCION_DESTINO = DIRECCION_DESTINO;
    }

    public String getDEPART_DESTINO() {
        return DEPART_DESTINO;
    }

    public void setDEPART_DESTINO(String DEPART_DESTINO) {
        this.DEPART_DESTINO = DEPART_DESTINO;
    }

    public String getPROVIN_DESTINO() {
        return PROVIN_DESTINO;
    }

    public void setPROVIN_DESTINO(String PROVIN_DESTINO) {
        this.PROVIN_DESTINO = PROVIN_DESTINO;
    }

    public String getDISTRI_DESTINO() {
        return DISTRI_DESTINO;
    }

    public void setDISTRI_DESTINO(String DISTRI_DESTINO) {
        this.DISTRI_DESTINO = DISTRI_DESTINO;
    }

    public String getCONDICION_PAGO() {
        return CONDICION_PAGO;
    }

    public void setCONDICION_PAGO(String CONDICION_PAGO) {
        this.CONDICION_PAGO = CONDICION_PAGO;
    }

    public String getNRO_SCOP() {
        return NRO_SCOP;
    }

    public void setNRO_SCOP(String NRO_SCOP) {
        this.NRO_SCOP = NRO_SCOP;
    }

    public String getPLACA_CISTERNA() {
        return PLACA_CISTERNA;
    }

    public void setPLACA_CISTERNA(String PLACA_CISTERNA) {
        this.PLACA_CISTERNA = PLACA_CISTERNA;
    }

    public String getNRO_CUBICACION() {
        return NRO_CUBICACION;
    }

    public void setNRO_CUBICACION(String NRO_CUBICACION) {
        this.NRO_CUBICACION = NRO_CUBICACION;
    }

    public String getMONTO_DESCUENTO() {
        return MONTO_DESCUENTO;
    }

    public void setMONTO_DESCUENTO(String MONTO_DESCUENTO) {
        this.MONTO_DESCUENTO = MONTO_DESCUENTO;
    }

    public String getFECHA_SISTEMA() {
        return FECHA_SISTEMA;
    }

    public void setFECHA_SISTEMA(String FECHA_SISTEMA) {
        this.FECHA_SISTEMA = FECHA_SISTEMA;
    }

    public String getPORC_PERCEPCION() {
        return PORC_PERCEPCION;
    }

    public void setPORC_PERCEPCION(String PORC_PERCEPCION) {
        this.PORC_PERCEPCION = PORC_PERCEPCION;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getID_TIPO_DOC_CREDITO() {
        return ID_TIPO_DOC_CREDITO;
    }

    public void setID_TIPO_DOC_CREDITO(String ID_TIPO_DOC_CREDITO) {
        this.ID_TIPO_DOC_CREDITO = ID_TIPO_DOC_CREDITO;
    }

    public String getNRO_PAGO() {
        return NRO_PAGO;
    }

    public void setNRO_PAGO(String NRO_PAGO) {
        this.NRO_PAGO = NRO_PAGO;
    }

    public String getTOTAL_PESO() {
        return TOTAL_PESO;
    }

    public void setTOTAL_PESO(String TOTAL_PESO) {
        this.TOTAL_PESO = TOTAL_PESO;
    }

    public String getUBIGEO_ORIGEN() {
        return UBIGEO_ORIGEN;
    }

    public void setUBIGEO_ORIGEN(String UBIGEO_ORIGEN) {
        this.UBIGEO_ORIGEN = UBIGEO_ORIGEN;
    }

    public String getUBIGEO_DESTINO() {
        return UBIGEO_DESTINO;
    }

    public void setUBIGEO_DESTINO(String UBIGEO_DESTINO) {
        this.UBIGEO_DESTINO = UBIGEO_DESTINO;
    }

    public String getFECHAACTUAL() {
        return FECHAACTUAL;
    }

    public void setFECHAACTUAL(String FECHAACTUAL) {
        this.FECHAACTUAL = FECHAACTUAL;
    }

    @Override
    public String toString() {
        return "Documento{" + "CIA=" + CIA + ", ID_PLANTA=" + ID_PLANTA + ", ID_TIPO_DOC=" + ID_TIPO_DOC + ", SERIE="
                + SERIE + ", NRO_DOCUMENTO=" + NRO_DOCUMENTO + ", SUBTOTAL=" + SUBTOTAL + ", FECHA=" + FECHA + ", HORA="
                + HORA + ", ID_MONEDA_DOC=" + ID_MONEDA_DOC + ", NRO_DI=" + NRO_DI + ", DESCRIPCION_CLIENTE="
                + DESCRIPCION_CLIENTE + ", ID_DISTRITO=" + ID_DISTRITO + ", DIRECCION_CLIENTE=" + DIRECCION_CLIENTE
                + ", DEPARTAMENTO_CLIENTE=" + DEPARTAMENTO_CLIENTE + ", PROVINCIA_CLIENTE=" + PROVINCIA_CLIENTE
                + ", DISTRITO_CLIENTE=" + DISTRITO_CLIENTE + ", RAZON_SOCIAL_CLIENTE=" + RAZON_SOCIAL_CLIENTE
                + ", EMAIL_CLIENTE=" + EMAIL_CLIENTE + ", FECHA_VENCE=" + FECHA_VENCE + ", IGV=" + IGV + ", PORC_IGV="
                + PORC_IGV + ", TOTAL=" + TOTAL + ", MONEDA_DESCRIPCION=" + MONEDA_DESCRIPCION + ", ID_TIPO_DOC_REF="
                + ID_TIPO_DOC_REF + ", SERIE_REF=" + SERIE_REF + ", NRO_DOCUMENTO_REF=" + NRO_DOCUMENTO_REF
                + ", ID_MOTIVO_TIPO_SUNAT=" + ID_MOTIVO_TIPO_SUNAT + ", DESCRIPCION_NOTA=" + DESCRIPCION_NOTA
                + ", MONTO_PER=" + MONTO_PER + ", TOTAL_MONTO_PER=" + TOTAL_MONTO_PER + ", RAZON_SOCIAL_TRANSPORTE="
                + RAZON_SOCIAL_TRANSPORTE + ", RUC_EMP_TRANSPORTE=" + RUC_EMP_TRANSPORTE + ", CHOFER_DESCRIP="
                + CHOFER_DESCRIP + ", ID_CHOFER=" + ID_CHOFER + ", PLACA_TRACTOR=" + PLACA_TRACTOR
                + ", NRO_AUTORIZACION=" + NRO_AUTORIZACION + ", DIRECCION_ORIGEN=" + DIRECCION_ORIGEN
                + ", DEPART_ORIGEN=" + DEPART_ORIGEN + ", PROVIN_ORIGEN=" + PROVIN_ORIGEN + ", DISTRI_ORIGEN="
                + DISTRI_ORIGEN + ", UBIGEO_ORIGEN=" + UBIGEO_ORIGEN + ", PLANTAORIGEN=" + PLANTAORIGEN
                + ", DIRECCION_DESTINO=" + DIRECCION_DESTINO + ", DISTRI_DESTINO=" + DISTRI_DESTINO
                + ", PROVIN_DESTINO=" + PROVIN_DESTINO + ", DEPART_DESTINO=" + DEPART_DESTINO + ", UBIGEO_DESTINO="
                + UBIGEO_DESTINO + ", CONDICION_PAGO=" + CONDICION_PAGO + ", NRO_SCOP=" + NRO_SCOP + ", PLACA_CISTERNA="
                + PLACA_CISTERNA + ", NRO_CUBICACION=" + NRO_CUBICACION + ", MONTO_DESCUENTO=" + MONTO_DESCUENTO
                + ", FECHA_SISTEMA=" + FECHA_SISTEMA + ", PORC_PERCEPCION=" + PORC_PERCEPCION + ", ID_CLIENTE="
                + ID_CLIENTE + ", ID_TIPO_DOC_CREDITO=" + ID_TIPO_DOC_CREDITO + ", NRO_PAGO=" + NRO_PAGO
                + ", TOTAL_PESO=" + TOTAL_PESO + '}';
    }

}
