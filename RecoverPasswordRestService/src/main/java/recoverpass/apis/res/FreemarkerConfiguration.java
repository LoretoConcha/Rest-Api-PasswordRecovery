package recoverpass.apis.res;

import java.io.File;
import java.io.IOException;

import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import recoverpass.apis.helper.Constantes;

/**
 * Clase singleton para la configuracion de FreeMarker
 * 
 *
 */
public class FreemarkerConfiguration {

	private static FreemarkerConfiguration instance = null;

	private static Configuration config = null;

	private void configure() throws IOException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setInterpolationSyntax(Configuration.LEGACY_INTERPOLATION_SYNTAX);
		configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
		configuration.setNumberFormat("computer");
		configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
		
		configuration.setDirectoryForTemplateLoading(new File(Constantes.DIRECTORIO_PLANTILLA));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
		config = configuration;
	}

	public static Configuration obtenerFreemarker() throws IOException {
		if (instance == null) {
			instance = new FreemarkerConfiguration();
			instance.configure();
		}
		return config;
	}

}
