package recoverpass.apis.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import freemarker.template.Template;
import recoverpass.apis.bean.DtoClaveTemporalPDF;
import recoverpass.apis.res.FreemarkerConfiguration;


public class GeneraPDF {

//	private static ServletContext servletContext;
	
	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(GeneraPDF.class);
	
	/**
	 * Este metodo procesa una plantilla en formato HTML y una ves procesada la
	 * convierte a PDF
	 * 
	 * @param data
	 * @param templateName
	 * @param servletContext
	 * @return
	 * @throws Exception
	 */
	private static ByteArrayOutputStream processTemplate(Map<String, Object> data, String templateName,
			ServletContext servletContext, String nombrePDF) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer out = new StringWriter();
		try {

			log.info("GeneraPDF() - processTemplate(): Obteniendo la plantilla FreeMarker...");
			Template temp = FreemarkerConfiguration.obtenerFreemarker().getTemplate(templateName);
			log.info("GeneraPDF() - processTemplate(): Procesando la plantilla...");
			temp.process(data, out);
			log.info("GeneraPDF() - processTemplate(): Plantilla procesada correctamente...");

			log.info("GeneraPDF() - processTemplate(): Convirtiendo la plantilla en PDF...");
			// Convierto el archivo en HTML en PDF
			log.info("GeneraPDF() - processTemplate(): Inicializando ITextRenderer...");
			ITextRenderer renderer = new ITextRenderer();

			log.info("GeneraPDF() - processTemplate(): leyendo el text");
			String html = out.toString();
			out.close();
			log.info("GeneraPDF() - processTemplate(): leyendo el documento");
			renderer.setDocumentFromString(html);
			log.info("GeneraPDF() - processTemplate(): Diseñando el PDF...");
			renderer.layout();
			log.info("GeneraPDF() - processTemplate(): Creando el PDF...");
			renderer.createPDF(baos);
			
			try(OutputStream ous = new FileOutputStream(new File(Constantes.PATH_PDF_CLAVE+nombrePDF+Constantes.EXTENSION_PDF)) ){//"D:/img/lalalala.pdf" 
				baos.writeTo(ous);
			}
			
			log.info("GeneraPDF() - processTemplate(): Conversion exitosa...");
			
		} catch (Exception e) {
			log.info("GeneraPDF() - processTemplate(): "+ConstantesBD.PROBLEMA_AL_EJECUTAR + e.getMessage());
		} finally {
			if (baos != null)
				baos.close();
			if (out != null)
				out.close();
		}
		return baos;
	}
	
	public static ByteArrayOutputStream obtenerPDF(ServletContext servletContext, String claveTemporal, String nombreApeUsuario, String nombrePDF) {

		try {

			// datosUsuario
			DtoClaveTemporalPDF datosUsuario = new DtoClaveTemporalPDF();
				
			datosUsuario.setNombreCliente(nombreApeUsuario);
			datosUsuario.setClaveTemporal(claveTemporal);

			Map<String, Object> datosTemplate = new HashMap<>();
			datosTemplate.put("datosUsuario", datosUsuario);

			return processTemplate(datosTemplate, "template_recoverpass_bce.html", servletContext, nombrePDF);//Constantes.NOMBRE_PLANTILLA
		} catch (Exception e) {
			log.info("GeneraPDF() - obtenerPDF(): "+ConstantesBD.PROBLEMA_AL_EJECUTAR + e.getCause());
			return null;
		}


	}
	
	public static void agregaPassPDF(String rutNoDig, String nombrePDF) throws DocumentException {
		
			PdfReader reader;
			try {
				String pathPDF=Constantes.PATH_PDF_CLAVE+nombrePDF+Constantes.EXTENSION_PDF;
				String pathPDFpass=Constantes.PATH_PDF_CLAVE+nombrePDF+Constantes.EXTENSION_PDF_PASS;
				reader = new PdfReader(pathPDF);
			       PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pathPDFpass));
			       stamper.setEncryption("password".getBytes(), rutNoDig.getBytes(),PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_256);
			       stamper.close();
			       reader.close();
			       log.info("GeneraPDF() - agregaPassPDF(): Encriptacion de PDF realizada");
			       File archivo = new File(pathPDF);
			     if (archivo.delete())
			      {
				       log.info("GeneraPDF() - agregaPassPDF(): Archivo PDF sin pass ha sido borrado satisfactoriamente");

			      }
			      else {
			    	  log.info("GeneraPDF() - agregaPassPDF(): Archivo PDF sin pass ha sido borrado");
			      }
			       
			       
			} catch (IOException e) {
				log.error("GeneraPDF() - agregaPassPDF(): Ha ocurrido un error al generar nuevo PDF con password: "+e);
			}

	}

	
	
	
}
