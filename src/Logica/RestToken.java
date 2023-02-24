/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import Datos.Datos;

/**
 *
 * @author kbarreda
 */
public class RestToken {

    public static void ObtenerToken(String Cia) throws UnsupportedEncodingException, IOException {
        String User = "";
        String Pass = "";
        switch (Cia) {
            case "01":
                User = Datos.PetroamericaUsername;
                Pass = Datos.PetroamericaPassword;
                break;
            case "02":
                User = Datos.DeltaGasUsername;
                Pass = Datos.DeltaGasPassword;
                break;
            case "05":
                User = Datos.AmericaGasUsername;
                Pass = Datos.AmericaGasPassword;
                break;
            case "06":
                User = Datos.HidroMundoUsername;
                Pass = Datos.HidroMundoPassword;
                break;
            case "07":
                User = Datos.PuntoGasUsername;
                Pass = Datos.PuntoGasPassword;
                break;

            default:
                System.out.print("RestToken: ERROR TOKEN");                  
                break;
        }
        String resource = Datos.LinkToken;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(resource);
        httpPost.setHeader("Authorization", Datos.Dato_Authorization);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", Datos.Dato_grant_type));
        params.add(new BasicNameValuePair("username", User));
        params.add(new BasicNameValuePair("password", Pass));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpclient.execute(httpPost);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNodeToker = mapper.readTree(EntityUtils.toString(response.getEntity()));
        //ACA OBTIENE EL TOKEN PARA LA SOLICITUD
        Datos.Dato_Token = rootNodeToker.path("access_token").asText();
        System.out.println("--------------------TOKEN------------------------");
        int statusez = response.getStatusLine().getStatusCode();
        System.out.println("STATUS CODE: " + statusez);
        System.out.println("TOKEN:" + Datos.Dato_Token);
        System.out.println("--------------------------------------------");

    }

}
