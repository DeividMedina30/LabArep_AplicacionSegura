package edu.escuelaing.arep.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
/**
 * Hello world!
 *
 */
public class Servicios
{
    public static void main( String[] args ) {
        port(getPort());
        secure(getKeyStore(), "123456",getKeyTrustStore(),"123456");
        get("/servicio", Servicios::getHora);
    }

    /**
     * Método que me permite obtener el KeyStore para realizar la validación segura.
     * @return ruta donde se encuentra mi keyStore
     */
    private static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null){
            return System.getenv("KEYSTORE");
        }
        return "keystores/ecikeystoreservicio.p12";
    }

    private static String getKeyTrustStore() {
        if (System.getenv("KEYSTORE") != null){
            return System.getenv("KEYSTORE");
        }
        return "keystores/myTrustStore";
    }


    private static Object getHora(Request request, Response response) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formato.format(date);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }
}
