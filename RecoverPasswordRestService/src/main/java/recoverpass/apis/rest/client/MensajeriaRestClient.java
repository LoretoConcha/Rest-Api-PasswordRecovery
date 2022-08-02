package recoverpass.apis.rest.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import recoverpass.apis.bean.ClaveTemporal;
import recoverpass.apis.bean.Informacionemail;
import recoverpass.apis.bean.InputDtoMail;
import recoverpass.apis.bean.SalidaServicio;
import recoverpass.apis.helper.Constantes;


/**
 *
 */
public class MensajeriaRestClient {

    /**
     * LOG
     */
    private static final Logger log = Logger.getLogger(MensajeriaRestClient.class);

    private static ResteasyClient client = null;

   /**
    * Permite llamar al servicio encargado de enviar correos
    * @param infoEmail
    * @param claveTemporal
    * @return
    */
    public static boolean enviarEmail(Informacionemail infoEmail, ClaveTemporal claveTemporal) {

        log.info("REST CLIENT: Iniciando cliente REST: " + Constantes.ENDPOINT_SERVICIO_MENSAJERIA);
        Response response = null;
        
        try {

            client = new ResteasyClientBuilder().build();

            Builder builder = client.target(Constantes.ENDPOINT_SERVICIO_MENSAJERIA).request();


            InputDtoMail entidadMail = new InputDtoMail();
            
            // Datos de email
            entidadMail.setInformacionemail(infoEmail);
            
            // Datos de clave temporal
            entidadMail.setClaveTemporal(claveTemporal);

            Invocation invocacion = builder.buildPost(Entity.entity(entidadMail, MediaType.APPLICATION_JSON_TYPE));
            
            log.info("Invocando...");
            response = invocacion.invoke();
            log.info("Invocado!");

            int status = response.getStatus();
            log.info("EXIT STATUS CODE: " + status);
            
            // NO OK
            if (status != 200) {
                log.error("Error llamando a servicio  : " + response.getStatus());
                throw new RuntimeException("Error llamando a servicio  : " + response.getStatus());
            }

            // OK
//            EnvioEmailRespuesta res = response.readEntity(EnvioEmailRespuesta.class);
            SalidaServicio res = response.readEntity(SalidaServicio.class);
            
            int codigoServicio = res.getCodigo();
            log.info("Salida : " + res);

            switch (codigoServicio) {
            case 0:
                log.info("Envio Mail OK, se acepta la solicitud para este servicio");
                return true;

            case 101:
                log.error("Envio Mail Invalido, se deniega la solicitud para este servicio");
                return false;

            }

            log.info("REST CLIENT: Resultado de la operacion: " + status);

        } catch (Exception e) {
            log.error("Error al consumir el servicio de email " , e);
        }

        return false;
    }
}

