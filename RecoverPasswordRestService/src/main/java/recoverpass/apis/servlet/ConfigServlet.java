package recoverpass.apis.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import recoverpass.apis.res.ResourcesConfig;

/**
 * Servlet implementation class ConfigServlet, permite inicializar las
 * properties dentro del aplicativo
 */
@WebServlet(description = "Servlet de configuracion, carga los archivos de propiedades", urlPatterns = { "/ConfigServlet" }, loadOnStartup = 1)
public class ConfigServlet extends HttpServlet {

	private transient Logger logger = null;
	private static final long serialVersionUID = 1L;

	private transient String name = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigServlet() {
		super();
	}

	/**
	 * Servlet que inicia la aplicacion, se usa para la configuracion
	 * 
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {

		name = config.getServletContext().getServletContextName();

		ResourcesConfig.config();
		logger = Logger.getLogger(ConfigServlet.class);

		logger.debug("DEPLOY APPLICATION: " + name);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.debug("UNDEPLOY APPLICATION: " + name);
	}

}
