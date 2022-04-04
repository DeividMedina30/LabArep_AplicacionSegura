package edu.escuelaing.arep.segurdiadLogin.Servicios;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import static spark.Spark.*;
import com.google.gson.Gson;
import edu.escuelaing.arep.segurdiadLogin.entities.UserDAO;
import edu.escuelaing.arep.segurdiadLogin.entities.User;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.staticfiles.StaticFilesConfiguration;

/**
 * Hello world!
 *
 */
public class SparkWebAppSecure{
    private static PasswordHash passwordHash = new PasswordHash();
    private static UserDAO usuarios = new UserDAO();
    public static void main( String[] args ) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        secure(getKeyStore(), "123456", null, null);
        SecureUrlReader.init();
        port(getPort());
        before("/LoginVerificado/*",SparkWebAppSecure::validarIngresoInvalido);
        before("/index.html", SparkWebAppSecure::validarIngresoLogin);
        StaticFilesConfiguration staticHandler = new StaticFilesConfiguration();
        staticHandler.configure("/");
        before((request, response) -> staticHandler.consume(request.raw(), response.raw()));
        post("/login",SparkWebAppSecure::manipularLogin);
    }

    private static Object manipularLogin(Request request, Response response) {
        request.session(true); //Creamos la session
        Gson gson = new Gson();      //Creamos el gson
        User user = gson.fromJson(request.body(),User.class); //Pasamos de tipo GSON a Objet tipo User
        if(passwordHash.convertirSHA256(user.getPassword()).equals(usuarios.LoadPassByUser(user.getUsuario()))){
            System.out.println("Entro hash");
            request.session().attribute("usr",user.getUsuario());
            request.session().attribute("AUTHORIZED",true);
            response.redirect("LoginVerificado/UsuarioLogin.html");
        }
        else{
            return " ";
        }
        return " ";
    }

    private static void validarIngresoLogin(Request request, Response response) {
        request.session(true);
        if (request.session().isNew()) {
            System.out.println("Entro en Uno");
            request.session().attribute("AUTHORIZED", false);
        }
        boolean auth = request.session().attribute("AUTHORIZED");
        if (auth) {
            System.out.println("Entro en Dos");
            response.redirect("/LoginVerificado/UsuarioLogin.html");
        }
    }

    private static void validarIngresoInvalido(Request request, Response response) {
        request.session(true);
        Session session = request.session();
        boolean newSession = session.isNew();
        if(newSession){
            System.out.println("Entro en tres");
            request.session().attribute("AUTHORIZED",false);
        }else{
            boolean auth=request.session().attribute("AUTHORIZED");
            System.out.println("Entro en Cuatro:" + auth);
            if(!auth) {
                System.out.println("Entro en Cuatro");
                halt(401, "<h1> 401 No esta autorizado para solicitar este recurso. </h1>");
            }};
    }

    /**
     * Método que me permite obtener el KeyStore para realizar la validación segura.
     * @return ruta donde se encuentra mi keyStore
     */
    private static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null){
            return System.getenv("KEYSTORE");
        }
        return "keystores/ecikeystore.p12";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}
