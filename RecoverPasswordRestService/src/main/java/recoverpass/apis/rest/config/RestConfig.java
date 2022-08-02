package recoverpass.apis.rest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuracion General de RestEasy, al dejar la URl unicamente con un '/' se
 * asume que directamente el nombre del servicio
 * 
 *
 */
@ApplicationPath("/Empresa/API/V1")
public class RestConfig extends Application {

}
