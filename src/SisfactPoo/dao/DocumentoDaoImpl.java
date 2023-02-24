/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisfactPoo.dao;

import LogicaSql.Sql;
import static LogicaSql.Sql.cadena_conexion;
import SisfactPoo.beans.Documento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarreda
 */
public class DocumentoDaoImpl implements DocumentoDao {

        @Override
        public List<Documento> listar() throws SQLException {
                // ArmarConsulta //200
                String ArmarConsulta = "select top 200 d.CIA, d.ID_PLANTA,pla.DIRECCION AS ID_PLANTA_DIRECCION, CASE WHEN d.ID_TIPO_DOC = 'N/C' THEN 'N-C' WHEN d.ID_TIPO_DOC = 'N/D' THEN 'N-D' ELSE d.ID_TIPO_DOC END AS ID_TIPO_DOC, right(d.SERIE, 3) AS SERIE, d.NRO_DOCUMENTO,\n"
                                + "		d.SUBTOTAL, convert(varchar(10), d.FECHA, 120)AS FECHA,convert(varchar(8), d.FECHA_SISTEMA, 108)as HORA, d.ID_MONEDA_DOC, d.ID_CONDICION_PAGO, \n"
                                + "		c.NRO_DI, c.DESCRIPCION AS DESCRIPCION_CLIENTE,\n"
                                + "		\n"
                                + "		c.ID_DISTRITO,\n"
                                + "		c.DIRECCION AS DIRECCION_CLIENTE,\n"
                                + "		\n"
                                + "		dep.DESCRIPCION AS DEPARTAMENTO_CLIENTE, prov.DESCRIPCION AS PROVINCIA_CLIENTE, dist.DESCRIPCION AS DISTRITO_CLIENTE,\n"
                                + "		isnull(c.RAZON_SOCIAL, c.DESCRIPCION) AS RAZON_SOCIAL_CLIENTE,\n"
                                + "		\n"
                                + "		pv.FAX1 AS EMAIL_CLIENTE, convert(varchar(10), d.FECHA_VENCIMIENTO, 120) AS FECHA_VENCE,\n"
                                + "		convert(varchar, d.IGV)as IGV, convert(varchar, d.PORC_IGV)as PORC_IGV, convert(varchar, d.TOTAL) as TOTAL, m.DESCRIPCION as MONEDA_DESCRIPCION,\n"
                                + "		d.ID_TIPO_DOC_REF, right(d.SERIE_REF, 3) AS SERIE_REF, d.NRO_DOCUMENTO_REF, d.ID_PLANTA_REF, mna.DESCRIPCION AS MOTIVO_TIPO_SUNAT, mna.ID_MOTIVO_TIPO_SUNAT AS ID_MOTIVO_TIPO_SUNAT, dna.DESCRIPCION_NOTA,\n"
                                + "		case when c.ID_TIPO_DI = '01' then '1' else '6' end AS ID_TIPO_DI, isnull(convert(varchar, convert(decimal(13,2), d.MONTO_PER)),0) as MONTO_PER,\n"
                                + "		convert(varchar, convert(decimal(13,2), d.TOTAL+d.MONTO_PER)) as TOTAL_MONTO_PER\n"
                                + "		\n"
                                + "		, et.RAZON_SOCIAL AS RAZON_SOCIAL_TRANSPORTE,\n"
                                // se agrego el campo df.ORDEN_COMPRA en la linea 43, la linea 42 es el bk
                                // + " df.RUC_EMP_TRANSPORTE, ch.DESCRIPCION AS CHOFER_DESCRIP, df.ID_CHOFER,
                                // df.PLACA_TRACTOR, df.NRO_AUTORIZACION_VEHICULO AS 'NRO_AUTORIZACION'\n"
                                + "		df.RUC_EMP_TRANSPORTE, ch.DESCRIPCION AS CHOFER_DESCRIP, df.ID_CHOFER, df.ORDEN_COMPRA, df.PLACA_TRACTOR, df.NRO_AUTORIZACION_VEHICULO AS 'NRO_AUTORIZACION'\n"
                                + "		, df.DIRECCION_ORIGEN, dio.DESCRIPCION as DEPART_ORIGEN, po.DESCRIPCION AS PROVIN_ORIGEN, do.DESCRIPCION AS DISTRI_ORIGEN,dio.ID_DISTRITO as UBIGEO_ORIGEN, pla.DESCRIPCION AS PLANTAORIGEN\n"
                                + "		, df.DIRECCION_DESTINO, did.DESCRIPCION as DISTRI_DESTINO, pd.DESCRIPCION AS PROVIN_DESTINO, dd.DESCRIPCION AS DEPART_DESTINO,did.ID_DISTRITO AS UBIGEO_DESTINO\n"
                                + "		, cpag.DESCRIPCION AS CONDICION_PAGO, df.NRO_SCOP, df.PLACA_CISTERNA , v.NRO_CUBICACION\n"
                                + "		, convert(varchar, ISNULL(d.MONTO_DESCUENTO, 0)) AS MONTO_DESCUENTO,d.FECHA_SISTEMA\n"
                                + "		,case when d.PORC_PERCEPCION=2 then '0.02' when d.PORC_PERCEPCION=1 then '0.01' when d.PORC_PERCEPCION=0.5 then '0.005' end AS PORC_PERCEPCION,d.ID_CLIENTE\n"
                                + "		,(select top 1 canc.ID_TIPO_DOC_CREDITO from CANCELACION_DOCUMENTO canc inner join DOCUMENTO_CREDITO dcred\n"
                                + "		on canc.CIA=dcred.CIA and canc.ID_PLANTA_CREDITO=dcred.ID_PLANTA_CREDITO and canc.ID_TIPO_DOC_CREDITO=dcred.ID_TIPO_DOC_CREDITO\n"
                                + "		and canc.AUTOGENERADO=dcred.AUTOGENERADO\n"
                                + "		where canc.CIA=d.CIA and canc.ID_PLANTA=d.ID_PLANTA and canc.ID_TIPO_DOC=d.ID_TIPO_DOC and canc.SERIE=d.SERIE and canc.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                                + "		order by dcred.FECHA) AS ID_TIPO_DOC_CREDITO\n"
                                + "		,(select top 1 dcred.NRO_PAGO from CANCELACION_DOCUMENTO canc inner join DOCUMENTO_CREDITO dcred\n"
                                + "		on canc.CIA=dcred.CIA and canc.ID_PLANTA_CREDITO=dcred.ID_PLANTA_CREDITO and canc.ID_TIPO_DOC_CREDITO=dcred.ID_TIPO_DOC_CREDITO\n"
                                + "		and canc.AUTOGENERADO=dcred.AUTOGENERADO\n"
                                + "		where canc.CIA=d.CIA and canc.ID_PLANTA=d.ID_PLANTA and canc.ID_TIPO_DOC=d.ID_TIPO_DOC and canc.SERIE=d.SERIE and canc.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                                + "		order by dcred.FECHA) AS NRO_PAGO\n"
                                + "		,convert(varchar,d.TOTAL_PESO) AS TOTAL_PESO,(select COUNT(ITEM) from DOCUMENTO_DET \n"
                                + "		where CIA=d.CIA and ID_PLANTA=d.ID_PLANTA and ID_TIPO_DOC=d.ID_TIPO_DOC \n"
                                + "		and SERIE=d.SERIE and NRO_DOCUMENTO=d.NRO_DOCUMENTO) AS 'CantidadItem', \n"
                                + "		cia.PORC_NOS_RETIENEN, c.FLAG_ES_AGENTE_RETENEDOR, \n"
                                + "		convert(varchar, convert(decimal(13,2), (d.TOTAL+ ISNULL(d.MONTO_PER,0)) * ((100-cia.PORC_NOS_RETIENEN) / 100))) AS  'MONTO_RETENEDOR',\n"
                                + "     convert(varchar, convert(decimal(13,2), (d.TOTAL+ ISNULL(d.MONTO_PER,0)) * (cia.PORC_NOS_RETIENEN / 100))) AS 'MONTO_PORC_RETENCION',\n"
                                + "		pv.ID_CLASIFICA_TIPO_NEGOCIO, \n"
                                + "		d.FLAG_CONDICION_PAGO_CREDITO, \n"

                                /* + "		d.MONTO_APLICA_DEBITO_REF, \n" */
                                + "     case when not (pv.ID_CLASIFICA_TIPO_NEGOCIO = '01' and d2.FLAG_CONDICION_PAGO_CREDITO = '1') then d2.TOTAL else d.MONTO_APLICA_DEBITO_REF end AS 'MONTO_APLICA_DEBITO_REF' ,\n"
                                /* + "		d.PER_CORRELATIVO_CANCELACION \n" */
                                + "     case when not (pv.ID_CLASIFICA_TIPO_NEGOCIO = '01' and d2.FLAG_CONDICION_PAGO_CREDITO = '1') then 1 else d.PER_CORRELATIVO_CANCELACION end AS 'PER_CORRELATIVO_CANCELACION' \n"
                                + "     , d2.FLAG_CONDICION_PAGO_CREDITO as FLAG_CONDICION_PAGO_CREDITO_REF"
                                + "		\n"
                                + "		from DOCUMENTO d inner join CLIENTE c on c.CIA=d.CIA and c.ID_CLIENTE=d.ID_CLIENTE \n"
                                + "		inner join PUNTO_VENTA pv on d.CIA=pv.CIA and d.ID_CLIENTE=pv.ID_CLIENTE and d.ID_PUNTO_VENTA=pv.ID_PUNTO_VENTA \n"
                                + "		inner join DEPARTAMENTO dep on dep.ID_PAIS=c.ID_PAIS and dep.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO\n"
                                + "		inner join PROVINCIA prov on prov.ID_PAIS=c.ID_PAIS and prov.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO and prov.ID_PROVINCIA=c.ID_PROVINCIA\n"
                                + "		inner join DISTRITO dist on dist.ID_PAIS=c.ID_PAIS and dist.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO and dist.ID_PROVINCIA=c.ID_PROVINCIA and dist.ID_DISTRITO=c.ID_DISTRITO\n"
                                + "		inner join MONEDA m on m.ID_MONEDA=d.ID_MONEDA_DOC\n"
                                + "		left join DOCUMENTO_NOTA_AJUSTE dna on dna.CIA=d.cia and dna.ID_PLANTA=d.ID_PLANTA and dna.ID_TIPO_DOC=d.ID_TIPO_DOC\n"
                                + "		and dna.SERIE=d.SERIE and dna.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                                + "		left join MOTIVO_NOTA_AJUSTE mna on mna.ID_TIPO_DOC=d.ID_TIPO_DOC and dna.ID_MOTIVO_NOTA=mna.ID_MOTIVO_NOTA\n"
                                + "		\n"
                                + "		left join DOCUMENTO_FACTURA df on df.CIA=d.cia and df.ID_PLANTA=d.ID_PLANTA and df.ID_TIPO_DOC=d.ID_TIPO_DOC\n"
                                + "		and df.SERIE=d.SERIE and df.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                                + "		left join EMPRESA_TRANSPORTE et on et.CIA=df.CIA and et.RUC_EMP_TRANSPORTE=df.RUC_EMP_TRANSPORTE\n"
                                + "		left join CHOFER ch on ch.CIA=d.CIA and ch.ID_CHOFER=df.ID_CHOFER\n"
                                + "		left join DEPARTAMENTO do on do.ID_PAIS=df.id_pais_origen and do.ID_DEPARTAMENTO=df.id_departamento_origen\n"
                                + "		left join PROVINCIA po on po.ID_PAIS=df.id_pais_origen and po.ID_DEPARTAMENTO=df.id_departamento_origen and po.ID_PROVINCIA=df.id_provincia_origen\n"
                                + "		left join DISTRITO dio on dio.ID_PAIS=df.id_pais_origen and dio.ID_DEPARTAMENTO=df.id_departamento_origen and dio.ID_PROVINCIA=df.id_provincia_origen\n"
                                + "			and dio.ID_DISTRITO=df.ID_DISTRITO_origen\n"
                                + "		left join DEPARTAMENTO dd on dd.ID_PAIS=df.ID_PAIS_DESTINO and dd.ID_DEPARTAMENTO=df.id_departamento_DESTINO\n"
                                + "		left join PROVINCIA pd on pd.ID_PAIS=df.id_pais_DESTINO and pd.ID_DEPARTAMENTO=df.id_departamento_DESTINO and pd.ID_PROVINCIA=df.id_provincia_DESTINO\n"
                                + "		left join DISTRITO did on did.ID_PAIS=df.id_pais_DESTINO and did.ID_DEPARTAMENTO=df.id_departamento_DESTINO and did.ID_PROVINCIA=df.id_provincia_DESTINO\n"
                                + "			and did.ID_DISTRITO=df.ID_DISTRITO_DESTINO\n"
                                + "		inner join PLANTA pla on pla.CIA=d.CIA and pla.ID_PLANTA=d.ID_PLANTA\n"
                                + "		left join CONDICION_PAGO cpag on cpag.CIA=d.CIA and cpag.ID_CONDICION_PAGO=d.ID_CONDICION_PAGO\n"
                                + "		left join VEHICULO v on v.CIA=d.CIA and v.PLACA_TRACTOR=df.PLACA_TRACTOR and v.PLACA_CISTERNA=df.PLACA_CISTERNA\n"
                                + "     left join cierre_diario cx ON d.cia = cx.cia AND d.id_planta=cx.id_planta AND d.fecha=cx.fecha\n"
                                + "     inner join CIA cia ON d.CIA = cia.CIA\n"
                                + "		left join DOCUMENTO d2 on d.CIA=d2.CIA and d.ID_PLANTA_REF=d2.ID_PLANTA and d.ID_TIPO_DOC_REF=d2.ID_TIPO_DOC \n"
                                + "		and d.SERIE_REF=d2.SERIE and d.NRO_DOCUMENTO_REF=d2.NRO_DOCUMENTO and d2.FECHA_SISTEMA>='2022-09-05 07:50:00.000' \n"
                                // + " where d.nro_documento = '00000915' and d.serie = '0018' and d.cia = '06'
                                // and d.id_tipo_doc = 'PER'";
                                // QUERY GENERAL OK
                                + " 	where d.FECHA>='2023-02-07' and d.ID_ESTADO_DOC='01' and d.TICKET_EFACT IS NULL \n"
                                + "     and ( d.ID_TIPO_DOC in ('bol','fac','N/C','N/D') or (d.id_tipo_doc = 'PER' and d.cia = '06' and d.FECHA_SISTEMA >= '2023-02-10 11:34:00.000') )   \n"
                                // 2022
                                // por
                                // 2025
                                + "     and ( (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)='1')  \n"
                                + "     OR (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)!='1' and cx.FLAG_CERRADO ='1')  \n"
                                + "		OR (d.CIA in ('05','06')) )   \n"
                                + "     ORDER BY d.cia,  d.ID_TIPO_DOC, d.FECHA_SISTEMA";

                /*
                 * +
                 * "    where d.FECHA>='2022-09-01' and d.ID_ESTADO_DOC='01' and  d.TICKET_EFACT IS NULL \n"
                 * +
                 * "     and ( d.ID_TIPO_DOC in ('bol','fac','N/C','N/D') or (d.id_tipo_doc = 'PER' and pv.ID_CLASIFICA_TIPO_NEGOCIO = '01' and d.FLAG_CONDICION_PAGO_CREDITO = '1000' and d.cia = '06' and d.FECHA_SISTEMA >= '2022-09-05 07:50:00.000') )  \n"
                 * + "     and ( (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)='1') \n"
                 * +
                 * "     OR (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)!='1' and cx.FLAG_CERRADO ='1') \n"
                 * + "		OR (d.CIA in ('05','06')) )  \n"
                 * + "     ORDER BY d.cia, d.FECHA_SISTEMA";
                 */

                /*
                 * WHERE QUE SERA COPIADO DESPUES
                 * +
                 * "		where d.FECHA>='2020-01-01' and d.ID_ESTADO_DOC='01' and d.ID_TIPO_DOC in ('bol','fac','N/C','N/D') AND TICKET_EFACT IS NULL  \n"
                 * +
                 * "  		and ( (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)='1') \n"
                 * +
                 * "			OR (d.CIA='07' and isnull(pla.FLAG_ENVIO_AUTOMATICO,0)!='1' and cx.FLAG_CERRADO ='1') \n"
                 * + "			OR (d.CIA in ('05','06')) )  \n"
                 * + "		ORDER BY d.cia, d.FECHA_SISTEMA";
                 */

                // ArmarConsulta ( ORIGINAL ) OR (d.CIA in ('05','06')) and d.ID_PLANTA='001'
                /*
                 * String ArmarConsulta =
                 * "select top 10 d.CIA, d.ID_PLANTA,pla.DIRECCION AS ID_PLANTA_DIRECCION, CASE WHEN d.ID_TIPO_DOC = 'N/C' THEN 'N-C' WHEN d.ID_TIPO_DOC = 'N/D' THEN 'N-D' ELSE d.ID_TIPO_DOC END AS ID_TIPO_DOC, right(d.SERIE, 3) AS SERIE, d.NRO_DOCUMENTO,\n"
                 * +
                 * "		d.SUBTOTAL, convert(varchar(10), d.FECHA, 120)AS FECHA,convert(varchar(8), d.FECHA_SISTEMA, 108)as HORA, d.ID_MONEDA_DOC,\n"
                 * + "		c.NRO_DI, c.DESCRIPCION AS DESCRIPCION_CLIENTE,\n"
                 * + "		\n"
                 * + "		c.ID_DISTRITO,\n"
                 * + "		c.DIRECCION AS DIRECCION_CLIENTE,\n"
                 * + "		\n"
                 * +
                 * "		dep.DESCRIPCION AS DEPARTAMENTO_CLIENTE, prov.DESCRIPCION AS PROVINCIA_CLIENTE, dist.DESCRIPCION AS DISTRITO_CLIENTE,\n"
                 * + "		isnull(c.RAZON_SOCIAL, c.DESCRIPCION) AS RAZON_SOCIAL_CLIENTE,\n"
                 * + "		\n"
                 * +
                 * "		pv.FAX1 AS EMAIL_CLIENTE, convert(varchar(10), d.FECHA_VENCIMIENTO, 120) AS FECHA_VENCE,\n"
                 * +
                 * "		convert(varchar, d.IGV)as IGV, convert(varchar, d.PORC_IGV)as PORC_IGV, convert(varchar, d.TOTAL) as TOTAL, m.DESCRIPCION as MONEDA_DESCRIPCION,\n"
                 * +
                 * "		d.ID_TIPO_DOC_REF, right(d.SERIE_REF, 3) AS SERIE_REF, d.NRO_DOCUMENTO_REF, mna.ID_MOTIVO_TIPO_SUNAT AS ID_MOTIVO_TIPO_SUNAT, dna.DESCRIPCION_NOTA,\n"
                 * +
                 * "		case when c.ID_TIPO_DI = '01' then '1' else '6' end AS ID_TIPO_DI, isnull(convert(varchar, convert(decimal(13,2), d.MONTO_PER)),0) as MONTO_PER,\n"
                 * +
                 * "		convert(varchar, convert(decimal(13,2), d.TOTAL+d.MONTO_PER)) as TOTAL_MONTO_PER\n"
                 * + "		\n"
                 * + "		, et.RAZON_SOCIAL AS RAZON_SOCIAL_TRANSPORTE,\n"
                 * +
                 * "		df.RUC_EMP_TRANSPORTE, ch.DESCRIPCION AS CHOFER_DESCRIP, df.ID_CHOFER, df.PLACA_TRACTOR, va.NRO_AUTORIZACION\n"
                 * +
                 * "		, df.DIRECCION_ORIGEN, dio.DESCRIPCION as DEPART_ORIGEN, po.DESCRIPCION AS PROVIN_ORIGEN, do.DESCRIPCION AS DISTRI_ORIGEN,dio.ID_DISTRITO as UBIGEO_ORIGEN, pla.DESCRIPCION AS PLANTAORIGEN\n"
                 * +
                 * "		, df.DIRECCION_DESTINO, did.DESCRIPCION as DISTRI_DESTINO, pd.DESCRIPCION AS PROVIN_DESTINO, dd.DESCRIPCION AS DEPART_DESTINO,did.ID_DISTRITO AS UBIGEO_DESTINO\n"
                 * +
                 * "		, cpag.DESCRIPCION AS CONDICION_PAGO, df.NRO_SCOP, df.PLACA_CISTERNA , v.NRO_CUBICACION\n"
                 * +
                 * "		, convert(varchar, ISNULL(d.MONTO_DESCUENTO, 0)) AS MONTO_DESCUENTO,d.FECHA_SISTEMA\n"
                 * +
                 * "		,case when d.PORC_PERCEPCION=2 then '0.02' when d.PORC_PERCEPCION=1 then '0.01' when d.PORC_PERCEPCION=0.5 then '0.005' end AS PORC_PERCEPCION,d.ID_CLIENTE\n"
                 * +
                 * "		,(select top 1 canc.ID_TIPO_DOC_CREDITO from CANCELACION_DOCUMENTO canc inner join DOCUMENTO_CREDITO dcred\n"
                 * +
                 * "		on canc.CIA=dcred.CIA and canc.ID_PLANTA_CREDITO=dcred.ID_PLANTA_CREDITO and canc.ID_TIPO_DOC_CREDITO=dcred.ID_TIPO_DOC_CREDITO\n"
                 * + "		and canc.AUTOGENERADO=dcred.AUTOGENERADO\n"
                 * +
                 * "		where canc.CIA=d.CIA and canc.ID_PLANTA=d.ID_PLANTA and canc.ID_TIPO_DOC=d.ID_TIPO_DOC and canc.SERIE=d.SERIE and canc.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                 * + "		order by dcred.FECHA) AS ID_TIPO_DOC_CREDITO\n"
                 * +
                 * "		,(select top 1 dcred.NRO_PAGO from CANCELACION_DOCUMENTO canc inner join DOCUMENTO_CREDITO dcred\n"
                 * +
                 * "		on canc.CIA=dcred.CIA and canc.ID_PLANTA_CREDITO=dcred.ID_PLANTA_CREDITO and canc.ID_TIPO_DOC_CREDITO=dcred.ID_TIPO_DOC_CREDITO\n"
                 * + "		and canc.AUTOGENERADO=dcred.AUTOGENERADO\n"
                 * +
                 * "		where canc.CIA=d.CIA and canc.ID_PLANTA=d.ID_PLANTA and canc.ID_TIPO_DOC=d.ID_TIPO_DOC and canc.SERIE=d.SERIE and canc.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                 * + "		order by dcred.FECHA) AS NRO_PAGO\n"
                 * + "		\n"
                 * + "		,convert(varchar,d.TOTAL_PESO) AS TOTAL_PESO\n"
                 * + "		\n"
                 * +
                 * "		from DOCUMENTO d inner join CLIENTE c on c.CIA=d.CIA and c.ID_CLIENTE=d.ID_CLIENTE \n"
                 * +
                 * "		inner join PUNTO_VENTA pv on d.CIA=pv.CIA and d.ID_CLIENTE=pv.ID_CLIENTE and d.ID_PUNTO_VENTA=pv.ID_PUNTO_VENTA \n"
                 * +
                 * "		inner join DEPARTAMENTO dep on dep.ID_PAIS=c.ID_PAIS and dep.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO\n"
                 * +
                 * "		inner join PROVINCIA prov on prov.ID_PAIS=c.ID_PAIS and prov.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO and prov.ID_PROVINCIA=c.ID_PROVINCIA\n"
                 * +
                 * "		inner join DISTRITO dist on dist.ID_PAIS=c.ID_PAIS and dist.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO and dist.ID_PROVINCIA=c.ID_PROVINCIA and dist.ID_DISTRITO=c.ID_DISTRITO\n"
                 * + "		inner join MONEDA m on m.ID_MONEDA=d.ID_MONEDA_DOC\n"
                 * +
                 * "		left join DOCUMENTO_NOTA_AJUSTE dna on dna.CIA=d.cia and dna.ID_PLANTA=d.ID_PLANTA and dna.ID_TIPO_DOC=d.ID_TIPO_DOC\n"
                 * + "		and dna.SERIE=d.SERIE and dna.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                 * +
                 * "		left join MOTIVO_NOTA_AJUSTE mna on mna.ID_TIPO_DOC=d.ID_TIPO_DOC and dna.ID_MOTIVO_NOTA=mna.ID_MOTIVO_NOTA\n"
                 * + "		\n"
                 * +
                 * "		left join DOCUMENTO_FACTURA df on df.CIA=d.cia and df.ID_PLANTA=d.ID_PLANTA and df.ID_TIPO_DOC=d.ID_TIPO_DOC\n"
                 * + "		and df.SERIE=d.SERIE and df.NRO_DOCUMENTO=d.NRO_DOCUMENTO\n"
                 * +
                 * "		left join EMPRESA_TRANSPORTE et on et.CIA=df.CIA and et.RUC_EMP_TRANSPORTE=df.RUC_EMP_TRANSPORTE\n"
                 * + "		left join CHOFER ch on ch.CIA=d.CIA and ch.ID_CHOFER=df.ID_CHOFER\n"
                 * +
                 * "		left join VEHICULO_AUTORIZADO va on va.CIA=d.CIA and va.PLACA_TRACTOR=df.PLACA_TRACTOR and va.PLACA_CISTERNA=df.PLACA_CISTERNA\n"
                 * +
                 * "			and va.ID_ESTADO='01'  and va.ID_TIPO_AUTORIZACION='01' AND VA.FLAG_ENVIO='1' \n"
                 * +
                 * "		left join DEPARTAMENTO do on do.ID_PAIS=df.id_pais_origen and do.ID_DEPARTAMENTO=df.id_departamento_origen\n"
                 * +
                 * "		left join PROVINCIA po on po.ID_PAIS=df.id_pais_origen and po.ID_DEPARTAMENTO=df.id_departamento_origen and po.ID_PROVINCIA=df.id_provincia_origen\n"
                 * +
                 * "		left join DISTRITO dio on dio.ID_PAIS=df.id_pais_origen and dio.ID_DEPARTAMENTO=df.id_departamento_origen and dio.ID_PROVINCIA=df.id_provincia_origen\n"
                 * + "			and dio.ID_DISTRITO=df.ID_DISTRITO_origen\n"
                 * +
                 * "		left join DEPARTAMENTO dd on dd.ID_PAIS=df.ID_PAIS_DESTINO and dd.ID_DEPARTAMENTO=df.id_departamento_DESTINO\n"
                 * +
                 * "		left join PROVINCIA pd on pd.ID_PAIS=df.id_pais_DESTINO and pd.ID_DEPARTAMENTO=df.id_departamento_DESTINO and pd.ID_PROVINCIA=df.id_provincia_DESTINO\n"
                 * +
                 * "		left join DISTRITO did on did.ID_PAIS=df.id_pais_DESTINO and did.ID_DEPARTAMENTO=df.id_departamento_DESTINO and did.ID_PROVINCIA=df.id_provincia_DESTINO\n"
                 * + "			and did.ID_DISTRITO=df.ID_DISTRITO_DESTINO\n"
                 * +
                 * "		inner join PLANTA pla on pla.CIA=d.CIA and pla.ID_PLANTA=d.ID_PLANTA\n"
                 * +
                 * "		left join CONDICION_PAGO cpag on cpag.CIA=d.CIA and cpag.ID_CONDICION_PAGO=d.ID_CONDICION_PAGO\n"
                 * +
                 * "		left join VEHICULO v on v.CIA=d.CIA and v.PLACA_TRACTOR=df.PLACA_TRACTOR and v.PLACA_CISTERNA=df.PLACA_CISTERNA\n"
                 * +
                 * "		where d.CIA in('05','06') and d.FECHA>='2019-08-01' and d.ID_ESTADO_DOC='01' and d.ID_TIPO_DOC in ('bol','fac','N/C','N/D') AND TICKET_EFACT IS NULL ORDER BY d.ID_PLANTA, d.NRO_DOCUMENTO"
                 * ;
                 */// FIN ArmarConsulta ( ORIGINAL )

                List<Documento> documentos = new ArrayList<Documento>();
                Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                Sql.SQLrss = Sql.SQLpss.executeQuery();
                Sql.SQLrsm = Sql.SQLrss.getMetaData();
                while (Sql.SQLrss.next()) {
                        Documento documento = new Documento();
                        documento.setCIA(Sql.SQLrss.getString("CIA"));
                        documento.setID_PLANTA(Sql.SQLrss.getString("ID_PLANTA"));
                        documento.setID_PLANTA_DIRECCION(Sql.SQLrss.getString("ID_PLANTA_DIRECCION"));
                        documento.setID_TIPO_DOC(Sql.SQLrss.getString("ID_TIPO_DOC"));
                        documento.setSERIE(Sql.SQLrss.getString("SERIE"));
                        documento.setNRO_DOCUMENTO(Sql.SQLrss.getString("NRO_DOCUMENTO"));
                        documento.setSUBTOTAL(Sql.SQLrss.getString("SUBTOTAL"));
                        documento.setFECHA(Sql.SQLrss.getString("FECHA"));
                        documento.setHORA(Sql.SQLrss.getString("HORA"));
                        documento.setID_MONEDA_DOC(Sql.SQLrss.getString("ID_MONEDA_DOC"));
                        documento.setNRO_DI(Sql.SQLrss.getString("NRO_DI"));
                        documento.setDESCRIPCION_CLIENTE(
                                        (Sql.SQLrss.getString("DESCRIPCION_CLIENTE")) == null
                                                        ? Sql.SQLrss.getString("DESCRIPCION_CLIENTE")
                                                        : Sql.SQLrss.getString("DESCRIPCION_CLIENTE").replace("&",
                                                                        "&amp;"));
                        documento.setID_DISTRITO(Sql.SQLrss.getString("ID_DISTRITO"));
                        documento.setDIRECCION_CLIENTE(
                                        (Sql.SQLrss.getString("DIRECCION_CLIENTE")) == null
                                                        ? Sql.SQLrss.getString("DIRECCION_CLIENTE")
                                                        : Sql.SQLrss.getString("DIRECCION_CLIENTE").replace("&",
                                                                        "&amp;"));
                        documento.setDEPARTAMENTO_CLIENTE(Sql.SQLrss.getString("DEPARTAMENTO_CLIENTE"));
                        documento.setPROVINCIA_CLIENTE(Sql.SQLrss.getString("PROVINCIA_CLIENTE"));
                        documento.setDISTRITO_CLIENTE(Sql.SQLrss.getString("DISTRITO_CLIENTE"));
                        documento.setRAZON_SOCIAL_CLIENTE(Sql.SQLrss.getString("RAZON_SOCIAL_CLIENTE"));
                        documento.setEMAIL_CLIENTE(
                                        (Sql.SQLrss.getString("EMAIL_CLIENTE")) == null
                                                        ? Sql.SQLrss.getString("EMAIL_CLIENTE")
                                                        : Sql.SQLrss.getString("EMAIL_CLIENTE").replace("&", "&amp;"));
                        documento.setFECHA_VENCE(Sql.SQLrss.getString("FECHA_VENCE"));
                        documento.setIGV(Sql.SQLrss.getString("IGV"));
                        documento.setPORC_IGV(Sql.SQLrss.getString("PORC_IGV"));
                        documento.setTOTAL(Sql.SQLrss.getString("TOTAL"));
                        documento.setMONEDA_DESCRIPCION(Sql.SQLrss.getString("MONEDA_DESCRIPCION"));
                        documento.setID_TIPO_DOC_REF(Sql.SQLrss.getString("ID_TIPO_DOC_REF"));
                        documento.setSERIE_REF(Sql.SQLrss.getString("SERIE_REF"));
                        documento.setID_PLANTA_REF(Sql.SQLrss.getString("ID_PLANTA_REF"));
                        documento.setNRO_DOCUMENTO_REF(Sql.SQLrss.getString("NRO_DOCUMENTO_REF"));
                        documento.setID_MOTIVO_TIPO_SUNAT(Sql.SQLrss.getString("ID_MOTIVO_TIPO_SUNAT"));
                        documento.setDESCRIPCION_NOTA(
                                        (Sql.SQLrss.getString("DESCRIPCION_NOTA")) == null
                                                        ? Sql.SQLrss.getString("DESCRIPCION_NOTA")
                                                        : Sql.SQLrss.getString("DESCRIPCION_NOTA").replace("&",
                                                                        "&amp;"));
                        documento.setMONTO_PER(Sql.SQLrss.getString("MONTO_PER"));
                        documento.setTOTAL_MONTO_PER(Sql.SQLrss.getString("TOTAL_MONTO_PER"));
                        documento.setRAZON_SOCIAL_TRANSPORTE((Sql.SQLrss.getString("RAZON_SOCIAL_TRANSPORTE")) == null
                                        ? Sql.SQLrss.getString("RAZON_SOCIAL_TRANSPORTE")
                                        : Sql.SQLrss.getString("RAZON_SOCIAL_TRANSPORTE").replace("&", "&amp;"));
                        documento.setRUC_EMP_TRANSPORTE(Sql.SQLrss.getString("RUC_EMP_TRANSPORTE"));
                        documento.setCHOFER_DESCRIP(Sql.SQLrss.getString("CHOFER_DESCRIP"));
                        documento.setID_CHOFER(Sql.SQLrss.getString("ID_CHOFER"));
                        documento.setPLACA_TRACTOR(Sql.SQLrss.getString("PLACA_TRACTOR"));
                        documento.setNRO_AUTORIZACION(Sql.SQLrss.getString("NRO_AUTORIZACION"));// TODO
                        documento.setDIRECCION_ORIGEN(Sql.SQLrss.getString("DIRECCION_ORIGEN"));
                        documento.setDEPART_ORIGEN(Sql.SQLrss.getString("DEPART_ORIGEN"));
                        documento.setPROVIN_ORIGEN(Sql.SQLrss.getString("PROVIN_ORIGEN"));
                        documento.setDISTRI_ORIGEN(Sql.SQLrss.getString("DISTRI_ORIGEN"));
                        documento.setUBIGEO_ORIGEN(Sql.SQLrss.getString("UBIGEO_ORIGEN"));
                        documento.setPLANTAORIGEN(Sql.SQLrss.getString("PLANTAORIGEN"));
                        documento.setDIRECCION_DESTINO(
                                        (Sql.SQLrss.getString("DIRECCION_DESTINO")) == null
                                                        ? Sql.SQLrss.getString("DIRECCION_DESTINO")
                                                        : Sql.SQLrss.getString("DIRECCION_DESTINO").replace("&",
                                                                        "&amp;"));
                        documento.setDEPART_DESTINO(Sql.SQLrss.getString("DEPART_DESTINO"));
                        documento.setPROVIN_DESTINO(Sql.SQLrss.getString("PROVIN_DESTINO"));
                        documento.setDISTRI_DESTINO(Sql.SQLrss.getString("DISTRI_DESTINO"));
                        documento.setUBIGEO_DESTINO(Sql.SQLrss.getString("UBIGEO_DESTINO"));
                        documento.setCONDICION_PAGO(Sql.SQLrss.getString("CONDICION_PAGO"));
                        documento.setNRO_SCOP(Sql.SQLrss.getString("NRO_SCOP"));
                        documento.setPLACA_CISTERNA(Sql.SQLrss.getString("PLACA_CISTERNA"));
                        documento.setNRO_CUBICACION(Sql.SQLrss.getString("NRO_CUBICACION"));
                        documento.setMONTO_DESCUENTO(Sql.SQLrss.getString("MONTO_DESCUENTO"));
                        documento.setFECHA_SISTEMA(Sql.SQLrss.getString("FECHA_SISTEMA"));
                        documento.setPORC_PERCEPCION(Sql.SQLrss.getString("PORC_PERCEPCION"));
                        documento.setID_CLIENTE(Sql.SQLrss.getString("ID_CLIENTE"));
                        documento.setID_TIPO_DOC_CREDITO(Sql.SQLrss.getString("ID_TIPO_DOC_CREDITO"));
                        documento.setNRO_PAGO(Sql.SQLrss.getString("NRO_PAGO"));
                        documento.setTOTAL_PESO(Sql.SQLrss.getString("TOTAL_PESO"));
                        documento.setMOTIVO_SUNAT(Sql.SQLrss.getString("MOTIVO_TIPO_SUNAT"));
                        documento.setCANTIDADITEM(Sql.SQLrss.getString("CantidadItem"));
                        documento.setORDEN_COMPRA(Sql.SQLrss.getString("ORDEN_COMPRA"));
                        documento.setID_CONDICION_PAGO(Sql.SQLrss.getString("ID_CONDICION_PAGO"));
                        // SE AGREGO CAMBIOS 22/07/2022
                        documento.setPORC_NOS_RETIENEN(Sql.SQLrss.getString("PORC_NOS_RETIENEN"));
                        documento.setFLAG_ES_AGENTE_RETENEDOR(Sql.SQLrss.getString("FLAG_ES_AGENTE_RETENEDOR"));
                        documento.setMONTO_RETENEDOR(Sql.SQLrss.getString("MONTO_RETENEDOR"));
                        documento.setMONTO_PORC_RETENCION(Sql.SQLrss.getString("MONTO_PORC_RETENCION"));
                        documento.setID_CLASIFICACION_TIPO_NEGOCIO(Sql.SQLrss.getString("ID_CLASIFICA_TIPO_NEGOCIO"));
                        documento.setFLAG_CONDICION_PAGO_CREDITO(Sql.SQLrss.getString("FLAG_CONDICION_PAGO_CREDITO"));
                        documento.setMONTO_APLICA_DEBITO_REF(Sql.SQLrss.getString("MONTO_APLICA_DEBITO_REF"));
                        documento.setPER_CORRELATIVO_CANCELACION(Sql.SQLrss.getString("PER_CORRELATIVO_CANCELACION"));
                        documento.setFLAG_CONDICION_PAGO_CREDITO_REF(Sql.SQLrss.getString("FLAG_CONDICION_PAGO_CREDITO_REF"));

                        documentos.add(documento);
                        // System.out.println(documentos);
                }
                Sql.SQLrss.close();
                Sql.SQLcnu.close();
                Sql.SQLpss.close();
                return documentos;
        }

        @Override
        public List<Documento> listarbajas() throws SQLException {

                // NUEVA QUERY Listar Bajas - 30/01/2023 cambio
                String ArmarConsulta = ""
                                + "SELECT d.CIA,d.ID_PLANTA,d.ID_TIPO_DOC,d.SERIE,d.NRO_DOCUMENTO,CONVERT(VARCHAR(10), d.FECHA, 120) AS FECHA, "
                                + "	CONVERT(VARCHAR(10), GETDATE(), 120)AS FECHAACTUAL "
                                + " "
                                + "from DOCUMENTO d "
                                + " "
                                + "where d.cia in('05','06','07') "
                                + "and d.ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') "
                                + "and d.ID_ESTADO_DOC='02' "
                                + "and (d.TICKET_EFACT IS NOT NULL and d.ID_TIPO_DOC in('n/c','fac','n/d','bol') OR "
                                + "	(d.ID_TIPO_DOC in('per') and d.TICKET_EFACT IS NOT NULL and "
                                + "		(select d2.TICKET_EFACT from DOCUMENTO d2 where d2.CIA=d.cia and d2.ID_PLANTA=d.ID_PLANTA_REF "
                                + "		and d2.ID_TIPO_DOC=d.ID_TIPO_DOC_REF and d2.SERIE=d.SERIE_REF and d2.NRO_DOCUMENTO=d.NRO_DOCUMENTO_REF) IS NOT NULL ) "
                                + "	) "
                                + "AND d.FECHA>='2023-02-06' "
                                + " "
                                + "and (CASE WHEN d.id_tipo_doc ='FAC' OR (d.id_tipo_doc ='N/C' and d.id_tipo_doc_ref='FAC') THEN "
                                + "		case when DATEDIFF(d, d.fecha, GETDATE()) <= 7 then 'ok' else 'no' end "
                                + "	 WHEN d.id_tipo_doc ='BOL' OR (d.id_tipo_doc ='N/C' and d.id_tipo_doc_ref='BOL') THEN "
                                + "		case when DATEDIFF(d, d.fecha, GETDATE()) <= 5 then 'ok' else 'no' end "
                                + "	 ELSE 'ok' "
                                + "	 END ) = 'ok' "
                                + " and isnull(d.RESPONSE_CODE_SUNAT_baja,0)!='1'"
                                + "ORDER BY d.ID_TIPO_DOC";

                // NUEVA QUERY Listar Bajas - 30/01/2023 cambio
                /*
                 * String ArmarConsulta = ""
                 * +
                 * "SELECT d.CIA,d.ID_PLANTA,d.ID_TIPO_DOC,d.SERIE,d.NRO_DOCUMENTO,CONVERT(VARCHAR(10), d.FECHA, 120) AS FECHA, "
                 * + "CONVERT(VARCHAR(10), GETDATE(), 120)AS FECHAACTUAL "
                 * + "from DOCUMENTO d "
                 * + "where d.cia in('05','06','07') "
                 * + "and d.ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') "
                 * + "and d.ID_ESTADO_DOC='02' "
                 * +
                 * "and (d.TICKET_EFACT IS NOT NULL and d.ID_TIPO_DOC in('n/c','fac','n/d','bol') OR "
                 * +
                 * "(d.ID_TIPO_DOC in('per') and (select TICKET_EFACT from DOCUMENTO d2 where d2.CIA=d.cia and d2.ID_PLANTA=d.ID_PLANTA_REF and "
                 * +
                 * "d2.ID_TIPO_DOC=d.ID_TIPO_DOC_REF and d2.SERIE=d.SERIE_REF and d2.NRO_DOCUMENTO=d.NRO_DOCUMENTO_REF ) IS NOT NULL )  ) "
                 * + "AND d.FECHA>='2023-01-30' "
                 * + "and isnull(d.RESPONSE_CODE_SUNAT_baja,0)!='1' "
                 * +
                 * "and (CASE WHEN d.id_tipo_doc ='FAC' OR (d.id_tipo_doc ='N/C' and d.id_tipo_doc_ref='FAC') THEN "
                 * + "case when DATEDIFF(d, d.fecha, GETDATE()) <= 7 then 'ok' else 'no' end "
                 * +
                 * "WHEN d.id_tipo_doc ='BOL' OR (d.id_tipo_doc ='N/C' and d.id_tipo_doc_ref='BOL') THEN "
                 * + "case when DATEDIFF(d, d.fecha, GETDATE()) <= 5 then 'ok' else 'no' end "
                 * + "ELSE 'ok' "
                 * + "END ) = 'ok'";
                 * /*
                 * NUEVO QUERY
                 * String ArmarConsulta =
                 * "SELECT CIA,ID_PLANTA,ID_TIPO_DOC,SERIE,NRO_DOCUMENTO,CONVERT(VARCHAR(10), FECHA, 120) AS FECHA,CONVERT(VARCHAR(10), GETDATE(), 120)AS FECHAACTUAL from DOCUMENTO \n"
                 * +
                 * "where cia in('05','06','07') and ID_TIPO_DOC in('n/c','fac','n/d','bol', 'per') and ID_ESTADO_DOC='02' \n"
                 * +
                 * " and TICKET_EFACT IS NOT NULL AND FECHA>='2020-01-01' and isnull(RESPONSE_CODE_SUNAT_baja,0)!='1' \n"
                 * +
                 * " and ( CASE WHEN id_tipo_doc ='FAC' OR (id_tipo_doc ='N/C' and id_tipo_doc_ref='FAC') THEN \n"
                 * +
                 * "  			case when DATEDIFF(d, fecha, GETDATE()) <= 7 then 'ok' else 'no' end \n"
                 * +
                 * " 		WHEN id_tipo_doc ='BOL' OR (id_tipo_doc ='N/C' and id_tipo_doc_ref='BOL') THEN \n"
                 * +
                 * "				case when DATEDIFF(d, fecha, GETDATE()) <= 5 then 'ok' else 'no' end  \n"
                 * + "			WHEN id_tipo_doc ='PER' THEN \n"
                 * +
                 * "  			case when DATEDIFF(d, fecha, GETDATE()) <= 7 then 'ok' else 'no' end \n"
                 * + "     	ELSE 'ok'  \n"
                 * + "  	  END ) = 'ok' \n";
                 */

                List<Documento> documentos = new ArrayList<>();
                Sql.SQLpss = cadena_conexion().prepareStatement(ArmarConsulta);
                Sql.SQLrss = Sql.SQLpss.executeQuery();
                Sql.SQLrsm = Sql.SQLrss.getMetaData();
                while (Sql.SQLrss.next()) {
                        Documento documento = new Documento();
                        documento.setCIA(Sql.SQLrss.getString("CIA"));
                        documento.setID_PLANTA(Sql.SQLrss.getString("ID_PLANTA"));
                        documento.setID_TIPO_DOC(Sql.SQLrss.getString("ID_TIPO_DOC"));
                        documento.setSERIE(Sql.SQLrss.getString("SERIE"));
                        documento.setNRO_DOCUMENTO(Sql.SQLrss.getString("NRO_DOCUMENTO"));
                        documento.setFECHA(Sql.SQLrss.getString("FECHA"));
                        documento.setFECHAACTUAL(Sql.SQLrss.getString("FECHAACTUAL"));
                        documentos.add(documento);
                }
                Sql.SQLrss.close();
                Sql.SQLcnu.close();
                Sql.SQLpss.close();
                return documentos;
        }

}
