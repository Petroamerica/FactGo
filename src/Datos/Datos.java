/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarreda
 */
public class Datos {
    public static String EstadoLogin=null;
    public static List<String> ListPlantas = new ArrayList<>();
    
    public static String FeCia = "";
    public static String FeUsuario = "";
    public static String FeUserPass = "";
    public static String FeCiaCod = "";
    public static String FOperacion = "";
    /***********************************************************************************************/
    //LOS CAMBIOS A REALIZAR PARA EL AMBIENTE DE PRUEBA ESTARAN COMENTADOS
    /*public static String HidroMundoUsername = "20600427734";
    public static String HidroMundoPassword = "1a2466a586351aa1ff358e73437a487944fee1390d054b05688a5224b4dbbcde";
    public static String LinkToken = "https://ose-gw1.efact.pe/api-efact-ose/oauth/token";
    public static String LinkDocument = "https://ose-gw1.efact.pe/api-efact-ose/v1/document";
    public static String LinkRestCDR = "https://ose-gw1.efact.pe/api-efact-ose/v1/cdr/";
    public static String LinkRestPdf = "https://ose-gw1.efact.pe/api-efact-ose/v1/pdf/";
    public static String RutaListaDocumentos = "C:/facturacion/sunatprueba/";
    public static String RutaDocumentos = "C:/facturacion/sunatprueba/";*/
    /****************************************************************************************************************************/
    
     /*-------------------------------------OTROS PARAMETROS----------------------------------------*/
    public static String RutaListaDocumentos = "C:/facturacion/sunat/";
    public static String RutaDocumentos = "C:/facturacion/sunat/";
    public static String RutaDescarga = "C:/facturacion/SunatDescarga/";
    public static String RutaCdr = "C:/facturacion/SunatCdr/listacdr.txt";
    /*---------------------------------------------------------------------------------------------*/
    
    /*----------------------------------LINKS DE CONEXIÓNES-----------------------------------------*/
    public static String Dato_Token = "";
    public static String LinkToken = "https://ose.efact.pe/api-efact-ose/oauth/token";
    public static String LinkDocument = "https://ose.efact.pe/api-efact-ose/v1/document";
    //ESTO NO DESCOMENTAR//public static String LinkApiIP = "http://192.168.1.16:8082";
    public static String LinkRestPdf = "https://ose.efact.pe/api-efact-ose/v1/pdf/";
    public static String LinkRestCDR = "https://ose.efact.pe/api-efact-ose/v1/cdr/";
    /*---------------------------------------------------------------------------------------------*/
    
    /*-----------------------------------DATOS DE LA EMPRESA---------------------------------------*/
    public static String Dato_Authorization = "Basic Y2xpZW50OnNlY3JldA==";
    public static String Dato_grant_type = "password";

    public static String PetroamericaUsername = "20332711157";
    public static String PetroamericaPassword = "08ece4215992569f211ea1774b1b865d679bc8551c794357b99f3ad4ce31e4f6";

    public static String DeltaGasUsername = "20100005485";
    public static String DeltaGasPassword = "139e5d45cb03906ac54cf385614df589349fbb9625f6bfbf85fb06f4813ff053";
    
    public static String HidroMundoUsername = "20600427734";
    public static String HidroMundoPassword = "72e9a02832c0f34849d44299c84501744eae3ce9a87950d9fdb2f960d8492b48";
    
    public static String AmericaGasUsername = "20524417490";
    public static String AmericaGasPassword = "7e6146c262c7504f43743d09f0de48576660c2bdc8207ddff93c374acd5589cb";
    
    public static String PuntoGasUsername = "20602359981";
    public static String PuntoGasPassword = "49d6ae15a451b5c36d49787a66a0f33a093c277fc98ee3218daabb17b8c72c1a";
    
    
    public static String UsernameMail = "infoareasistemas@gmail.com";
    public static String PassWordMail = "infoareasistemas921";
    public static String ToMail = "jbenitezvallejos@petroamerica.com.pe";
}
