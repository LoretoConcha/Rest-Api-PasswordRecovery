/**
 * 
 */
package recoverpass.apis.mgr.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import recoverpass.apis.adapters.DtoRequestDatosUsuario;
import recoverpass.apis.adapters.DtoRequestPreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestRecuperarClaveTemporal;
import recoverpass.apis.adapters.DtoRequestRecuperarReintentos;
import recoverpass.apis.adapters.DtoRequestTienePreguntasSecretas;
import recoverpass.apis.adapters.DtoRequestValidaPreguntas;
import recoverpass.apis.adapters.SalidaDatosUsuario;
import recoverpass.apis.adapters.SalidaPreguntasSecretas;
import recoverpass.apis.adapters.SalidaRecuperarClaveTemporal;
import recoverpass.apis.adapters.SalidaRecuperarReintentos;
import recoverpass.apis.adapters.SalidaTienePreguntasSecretas;
import recoverpass.apis.adapters.SalidaValidaPreguntas;
import recoverpass.apis.bean.ClaveTemporal;
import recoverpass.apis.bean.DefinicionParametro;
import recoverpass.apis.bean.DtoOutAddIntentosClaveTemporal;
import recoverpass.apis.bean.DtoOutClavesTemporales;
import recoverpass.apis.bean.DtoOutUserData;
import recoverpass.apis.bean.DtoOutPreguntasSecretas;
import recoverpass.apis.bean.DtoOutRecuperarClaveTemporal;
import recoverpass.apis.bean.DtoOutRecuperarReintentos;
import recoverpass.apis.bean.DtoOutTienePreguntasSecretas;
import recoverpass.apis.bean.DtoOutValidaPreguntas;
import recoverpass.apis.bean.Informacionemail;
import recoverpass.apis.dao.QueryDao;
import recoverpass.apis.dao.impl.QueryDaoImpl;
import recoverpass.apis.helper.Constantes;
import recoverpass.apis.helper.ConstantesBD;
import recoverpass.apis.helper.EncryptPass;
import recoverpass.apis.helper.GeneraPDF;
import recoverpass.apis.helper.Utiles;
import recoverpass.apis.helper.json.JsonBodyResponse;
import recoverpass.apis.helper.json.JsonHeaderResponse;
import recoverpass.apis.mgr.RecoverPasswordMgr;
import recoverpass.apis.rest.client.MensajeriaRestClient;
import recoverpass.dao.mappers.definition.GetAddIntentosClaveTemporalMapper;
import recoverpass.dao.mappers.definition.GetClavesTemporalesMapper;
import recoverpass.dao.mappers.definition.GetDatosUsuarioMapper;
import recoverpass.dao.mappers.definition.GetPreguntasSecretasMapper;
import recoverpass.dao.mappers.definition.GetRecuperarClaveTemporalMapper;
import recoverpass.dao.mappers.definition.GetRecuperarReintentosMapper;
import recoverpass.dao.mappers.definition.GetTienePreguntasSecretasMapper;
import recoverpass.dao.mappers.definition.GetValidaPreguntasMapper;

/**
 * 
 * @author lconcha
 *
 *
 */
public class RecoverPasswordMgrImpl implements RecoverPasswordMgr {

	private ServletContext servletContext;

	/**
	 * LOGGER
	 */
	private static final Logger log = Logger.getLogger(RecoverPasswordMgrImpl.class);

	private QueryDao queryDao;

	/**
	 * Validate user data in idClient out NameUser, LUsuario,
	 * Email, PassEstado
	 */
	@Override
	public SalidaDatosUsuario datosUsuario(DtoRequestDatosUsuario dtoRequestDatosUsuario) {
		SalidaDatosUsuario salida = new SalidaDatosUsuario();

		log.info("RecoverPasswordMgrImpl - datosUsuario(): Obteniendo datos de usuario rut: "
				+ dtoRequestDatosUsuario.getDtoRequestSetParametros().getDtoRutGenerico().getRutCliente());
		this.queryDao = new QueryDaoImpl();

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
					dtoRequestDatosUsuario.getDtoRequestSetParametros().getDtoRutGenerico().getRutCliente()));

			// Obtengo resultado del SP.
			List<DtoOutUserData> datosUsuario = this.queryDao.queryForProcedureSqlServer(
					Constantes.DATASOURCE_SQLSERVER2, inParams, new GetDatosUsuarioMapper(),
					Constantes.POCEDURE_DATOSUSUARIO);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - datosUsuario(): Error al consultar el servicio");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
			} else {
				log.info("RecoverPasswordMgrImpl - datosUsuario(): Datos obtenidos correctamente");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());

				if (datosUsuario.isEmpty()) {
					salida.getDtoResponseSetResultados()
							.setDtoResponseCodigosOperacion(JsonBodyResponse.errorRegistro());
				} else {
					salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
					salida.getDtoResponseSetResultados().getDtoOutDatosUsuario().addAll(datosUsuario);
				}

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - datosUsuario(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;

	}

	/**
	 * Obtiene y setea cantidad de reintentos para responder preguntas secretas
	 * proceso 1= verificacion, 2=respuestas equivocadas (se incrementa en uno el
	 * reintento)
	 */
	@Override
	public SalidaRecuperarReintentos recuperarReintentos(DtoRequestRecuperarReintentos dtoRequestRecuperarReintentos) {
		SalidaRecuperarReintentos salida = new SalidaRecuperarReintentos();

		log.info(
				"RecoverPasswordMgrImpl - recuperarReintentos(): Recuperando reintentos de contraseña correspondiente a usuario rut : "
						+ dtoRequestRecuperarReintentos.getDtoRequestSetParametros().getDtoRecuperarReintentos()
								.getRutCliente());
		this.queryDao = new QueryDaoImpl();

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, dtoRequestRecuperarReintentos
					.getDtoRequestSetParametros().getDtoRecuperarReintentos().getRutCliente()));
			inParams.put(2, new DefinicionParametro(ConstantesBD.TIPO_INTEGER, Constantes.NUMEROPROCESO));
			// Obtengo resultado del SP.
			List<DtoOutRecuperarReintentos> datosRecuperarReintentos = this.queryDao.queryForProcedureSqlServer(

					Constantes.DATASOURCE_SQLSERVER2, inParams, new GetRecuperarReintentosMapper(),
					Constantes.POCEDURE_RECUPERAREINTENTOS);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - recuperarReintentos(): Error al consultar el servicio");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
			} else {
				log.info("RecoverPasswordMgrImpl - recuperarReintentos(): Datos obtenidos correctamente");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.getDtoResponseSetResultados().getDtoOutRecuperarReintentos().addAll(datosRecuperarReintentos);

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - recuperarReintentos(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;

	}

	/**
	 * Obtiene tienePreguntas=1 si usuario tiene asociadas preguntas secretas, de lo
	 * contrario, no tiene.
	 */
	@Override
	public SalidaTienePreguntasSecretas tienePreguntasSecretas(
			DtoRequestTienePreguntasSecretas dtoRequestTienePreguntasSecretas) {
		SalidaTienePreguntasSecretas salida = new SalidaTienePreguntasSecretas();

		log.info(
				"RecoverPasswordMgrImpl - tienePreguntasSecretas(): Obteniendo resultado que valida si usuario rut: "
						+ dtoRequestTienePreguntasSecretas.getDtoRequestSetParametros().getDtoRutGenerico()
								.getRutCliente()
						+ " tiene preguntas secretas");
		this.queryDao = new QueryDaoImpl();

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
					dtoRequestTienePreguntasSecretas.getDtoRequestSetParametros().getDtoRutGenerico().getRutCliente()));

			// Obtengo resultado del SP.
			List<DtoOutTienePreguntasSecretas> datosTienePreguntas = this.queryDao.queryForProcedureSqlServer(
					Constantes.DATASOURCE_SQLSERVER2, inParams, new GetTienePreguntasSecretasMapper(),
					Constantes.POCEDURE_TIENEPREGUNTAS);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - tienePreguntasSecretas(): Error al consultar el servicio");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());

			} else {
				log.info("RecoverPasswordMgrImpl - tienePreguntasSecretas(): Datos obtenidos correctamente");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.getDtoResponseSetResultados().getDtoOutTienePreguntasSecretas().addAll(datosTienePreguntas);

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - tienePreguntasSecretas(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;
	}

	/**
	 * En caso de que usuario tenga asociadas preguntas secretas se obtiene:
	 * pregunta, id, respuesta, idUsuario
	 */
	@Override
	public SalidaPreguntasSecretas obtenerPreguntasSecretas(DtoRequestPreguntasSecretas dtoRequestPreguntasSecretas) {
		SalidaPreguntasSecretas salida = new SalidaPreguntasSecretas();
		this.queryDao = new QueryDaoImpl();

		log.info(
				"RecoverPasswordMgrImpl - obtenerPreguntasSecretas(): Obteniendo preguntas secretas asociadas a rut cliente: "
						+ dtoRequestPreguntasSecretas.getDtoRequestSetParametros().getDtoRutGenerico().getRutCliente());

		this.queryDao = new QueryDaoImpl();

		try {

			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
					dtoRequestPreguntasSecretas.getDtoRequestSetParametros().getDtoRutGenerico().getRutCliente()));

			// Obtengo resultado del SP.
			List<DtoOutPreguntasSecretas> datosPreguntasSecretas = this.queryDao.queryForProcedureSqlServer(
					Constantes.DATASOURCE_SQLSERVER2, inParams, new GetPreguntasSecretasMapper(),
					Constantes.POCEDURE_OBTENERPREGUNTAS);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - obtenerPreguntasSecretas(): Error al consultar el servicio");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
			} else if (!datosPreguntasSecretas.isEmpty()) {
				log.info("RecoverPasswordMgrImpl - obtenerPreguntasSecretas(): Datos obtenidos correctamente");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
				salida.getDtoResponseSetResultados().getDtoOutPreguntasSecretas().addAll(datosPreguntasSecretas);
			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - obtenerPreguntasSecretas(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;

	}

	/**
	 * Valida la respuesta a las preguntas secretas Llama a servicio de mensajeria
	 * para enviar contraseña temporal por email
	 * MensajeriaRestClient.enviarEmail(infoEmail, claveTemporal);
	 */
	@Override
	public SalidaValidaPreguntas validaPreguntas(DtoRequestValidaPreguntas dtoRequestValidaPreguntas) {
		SalidaValidaPreguntas salida = new SalidaValidaPreguntas();
		boolean salidaTemporal;
		String email = null;
		String nombreUsuario = null;
		String apellidoUsuario = null;
		String nombreApeUsuario = null;
		int largo = 0;
		int largoClavesTemp = 0;
		int largoDatosUsuario = 0;
		String rutUsuario = dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas()
				.getRutCliente();
		String claveAleatoria = null;
		int respuestaUno = 0;
		int respuestaDos = 0;
		int numeroProceso = 2;
		boolean resultadoMensajeria = true;
		String fechaHoy = null;
		int hora = 0;
		int minutos = 0;
		String horaMin = null;
		String reintentos = null;
		String nombrePDFpass = null;
		String nombrePDF = null;
		log.info(
				"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Validando preguntas de seguridad de usuario rut: "
						+ rutUsuario);
		this.queryDao = new QueryDaoImpl();
		int retorno = this.queryDao.codRetorno();

			try {
				LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
				inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutUsuario));
				inParams.put(2, new DefinicionParametro(ConstantesBD.TIPO_INTEGER,
						dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas().getIdPreguntaUno()));
				inParams.put(3, new DefinicionParametro(ConstantesBD.TIPO_INTEGER,
						dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas().getIdPreguntaDos()));
				inParams.put(4, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
						dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas().getRespuestaUno()));
				inParams.put(5, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR,
						dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas().getRespuestaDos()));

				// Obtengo resultado del SP.
				List<DtoOutValidaPreguntas> datosValidaPreguntas = this.queryDao.queryForProcedureSqlServer(
						Constantes.DATASOURCE_SQLSERVER2, inParams, new GetValidaPreguntasMapper(),
						Constantes.POCEDURE_VALIDARPREGUNTAS);

				List<DtoOutClavesTemporales> datosClavesTemporales = null;
				List<DtoOutUserData> datosCorreo = null;
				largo = datosValidaPreguntas.size();
				DtoOutValidaPreguntas itemDato = null;

				for (int i = 0; i < largo; i++) {
					itemDato = new DtoOutValidaPreguntas();
					itemDato = datosValidaPreguntas.get(i);
					respuestaUno = Integer.parseInt(itemDato.getEstadoRespuestaUno());
					respuestaDos = Integer.parseInt(itemDato.getEstadoRespuestaDos());
				}
				// Respuestas a preguntas secretas son correctas cuando el resultado de
				// datosValidaPreguntas es igual a 0 - 0
				if (respuestaUno == 0 && respuestaDos == 0) {
					log.info(
							"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Preguntas y respuestas validadas correctamente");
					// Obtener claves temporales asociadas a usuario
					datosClavesTemporales = this.clavesTemporalesUsuario(rutUsuario);
					largoClavesTemp = datosClavesTemporales.size();
					// Recorrer claves temporales y generar nueva clave temporal que no este
					// repetida
					claveAleatoria = recorreClaves(largoClavesTemp, datosClavesTemporales);
					// Inserta la nueva clave temporal unica a la base de datos
					salidaTemporal = insertarClaveTemporal(rutUsuario, claveAleatoria);

					if (salidaTemporal) {
						log.info(
								"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Resultado de insertar clave temporal: "
										+ salidaTemporal);
						// Obtener correo asociado
						datosCorreo = this.obtenerCorreo(rutUsuario);
						
					
					largoDatosUsuario = datosCorreo.size();

					for (int z = 0; z < largoDatosUsuario; z++) {
						DtoOutUserData datos = new DtoOutUserData();
						datos = datosCorreo.get(z);
						nombreUsuario = datos.getNameUser();
						apellidoUsuario = datos.getLastNameUser();
						email = datos.getEmail();
					}

					log.info("RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Procede a generar documento PDF...");

					nombreApeUsuario = nombreUsuario + " " + apellidoUsuario;
					
					// Genera nombre de archivo PDF
					nombrePDF=Utiles.nombrePDF();
					
					// Genera archivo PDF (indicando contraseña temporal)
					GeneraPDF.obtenerPDF(this.servletContext, claveAleatoria, nombreApeUsuario, nombrePDF);

					// Genera archivo PDF (indicando contraseña temporal) con seguridad de password
					// de apertura de archivo, la cual es el rut del usuario sin digito verificador
					String rutNoDig = rutUsuario.substring(0, rutUsuario.length() - 1);
					GeneraPDF.agregaPassPDF(rutNoDig, nombrePDF);

					// Seteo de datos para envio de email
					ClaveTemporal claveTemporal = new ClaveTemporal();
					claveTemporal.setNombreCliente(nombreApeUsuario);
					claveTemporal.setEnviarEmail(email);
					nombrePDFpass= nombrePDF+Constantes.EXTENSION_PDF_PASS;
					claveTemporal.setNombreArchivo(nombrePDFpass);

					Informacionemail infoEmail = new Informacionemail();

					LocalDateTime ldt = LocalDateTime.now();
					fechaHoy = (DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));

					Calendar calendario = Calendar.getInstance();
					hora = calendario.get(Calendar.HOUR_OF_DAY);
					minutos = calendario.get(Calendar.MINUTE);
					horaMin = hora + ":" + minutos;
					infoEmail.setNombrecliente(nombreApeUsuario);
					infoEmail.setTipoenvio(Constantes.TIPO_ENVIO);
					infoEmail.setFecha(fechaHoy);
					infoEmail.setHora(horaMin);

					log.info("RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Enviando clave temporal a email: "
							+ email);

					// Llamada a servicio de Mensajeria para enviarEmail con datos adjuntos
					resultadoMensajeria = MensajeriaRestClient.enviarEmail(infoEmail, claveTemporal);
					if (resultadoMensajeria) {
						log.info("RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Correo enviado exitosamente");
						 File archivo = new File(Constantes.PATH_PDF_CLAVE+nombrePDFpass);
					     if (archivo.delete())
					      {
						       log.info("GeneraPDF() - agregaPassPDF(): Archivo PDF ha sido eliminado satisfactoriamente");

					      }
				       
					}

				}
					else {
						log.info(
								"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): No se ha podido insertar clave temporal...");
					}
					}  else {
					log.info(
							"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): No hay match entre respuestas y preguntas, se aumenta reintento...");

					reintentos = this.aumentarReintentos(
							dtoRequestValidaPreguntas.getDtoRequestSetParametros().getDtoValidaPreguntas().getRutCliente(),
							numeroProceso);
					if (reintentos != null) {
						log.info(
								"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Reintento aumentado, numero de reintento: "
										+ reintentos);
					} else {
						throw new NullPointerException(
								"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Reintento no añadido, datos: null");
					}

					retorno = -2;
				}

				if (retorno == -2) {
					salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
					salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorPreguntas());
					salida.getDtoResponseSetResultados().getDtoOutValidaPreguntas().addAll(datosValidaPreguntas);

				} else if (this.queryDao.codRetorno() == -1) {
					log.error("RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Error al consultar el servicio");
					salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
					salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
				} else {
					log.info("RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Datos obtenidos correctamente");
					salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
					salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.operacionOk());
					salida.getDtoResponseSetResultados().getDtoOutValidaPreguntas().addAll(datosValidaPreguntas);

				}
			
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - validaPreguntasSeguridad(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;

	}


	/**
	 * setea cantidad de reintentos para responder preguntas secretas 2=respuestas
	 * equivocadas (se incrementa en 1 el reintento)
	 */

	public String aumentarReintentos(String rutCliente, int numeroProceso) {
		List<DtoOutRecuperarReintentos> datosRecuperarReintentos = null;

		this.queryDao = new QueryDaoImpl();
		int largoDatos = 0;
		String reintentos = null;
		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));
			inParams.put(2, new DefinicionParametro(ConstantesBD.TIPO_INTEGER, numeroProceso));
			// Obtengo resultado del SP.
			datosRecuperarReintentos = this.queryDao.queryForProcedureSqlServer(Constantes.DATASOURCE_SQLSERVER2, inParams,
					new GetRecuperarReintentosMapper(), Constantes.POCEDURE_RECUPERAREINTENTOS);

			largoDatos = datosRecuperarReintentos.size();
			for (int z = 0; z < largoDatos; z++) {
				DtoOutRecuperarReintentos datos = new DtoOutRecuperarReintentos();
				reintentos = datos.getReintentos();

			}

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - aumentarReintentos(): Error al consultar el servicio");

			} else if (this.queryDao.codRetorno() != -1 && reintentos != null) {
				log.info("RecoverPasswordMgrImpl - aumentarReintentos(): Datos obtenidos correctamente");
			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - aumentarReintentos(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
		}
		return reintentos;
	}

	/**
	 * Obtiene las claves temporales historicas asociadas a usuario
	 * 
	 * @param rut
	 * @return
	 */
	public List<DtoOutClavesTemporales> clavesTemporalesUsuario(String rut) {
		log.info(
				"RecoverPasswordMgrImpl - clavesTemporalesUsuario(): Obteniendo listado de claves temporales de usuario rut : "
						+ rut);
		this.queryDao = new QueryDaoImpl();
		List<DtoOutClavesTemporales> datosClavesTemporales = null;
		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rut));

			// Obtengo resultado del SP.
			datosClavesTemporales = this.queryDao.queryForProcedureSqlServer(Constantes.DATASOURCE_SQLSERVER2, inParams,
					new GetClavesTemporalesMapper(), Constantes.POCEDURE_LISTARCLAVESTEMP);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - clavesTemporalesUsuario(): Error al consultar el Procedure");
			} else {
				log.info("RecoverPasswordMgrImpl - clavesTemporalesUsuario(): Datos obtenidos correctamente");

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - clavesTemporalesUsuario(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
		}
		return datosClavesTemporales;
	}

	/**
	 * Recorre la lista de claves temporales donde compara la nueva clave temporal
	 * con las existentes en la base de datos, en caso de que encuentre una
	 * coincidencia, vuelve a generar otra hasta llegar a una inexistente
	 * 
	 * @param largo
	 * @param datosClavesTemporales
	 * @return
	 */
	private static String recorreClaves(int largo, List<DtoOutClavesTemporales> datosClavesTemporales) {

		String claveAleatoria = Utiles.calveAleatoria();
		int contadorMatch = 0;
		for (int y = 0; y < largo; y++) {

			DtoOutClavesTemporales claveT = new DtoOutClavesTemporales();
			claveT = datosClavesTemporales.get(y);

			if (claveT.toString().equals(claveAleatoria)) {
				contadorMatch++;
			}
		}

		while (contadorMatch >= 1) {
			claveAleatoria = claveIncorrecta(largo, datosClavesTemporales);
		}

		return claveAleatoria;
	}

	/**
	 * Va asociado a recorreClaves(), donde se ejecuta en caso de que la contraseña
	 * generada ya exista en la base de datos
	 * 
	 * @param largo
	 * @param datosClavesTemporales
	 * @return
	 */
	public static String claveIncorrecta(int largo, List<DtoOutClavesTemporales> datosClavesTemporales) {
		String claveAleatoria = null;
		try {
			claveAleatoria = recorreClaves(largo, datosClavesTemporales);
		} catch (Exception e) {
			log.info("RecoverPasswordMgrImpl - claveIncorrecta(): Ha ocurrido un error: " + e);
		}
		return claveAleatoria;
	}

	/**
	 * Inserta en la base de datos la nueva clave temporal generada y validada
	 * 
	 * @param rutCliente
	 * @param claveTemporal
	 * @return
	 */
	public boolean insertarClaveTemporal(String rutCliente, String claveTemporal) {

		boolean resultadoQueryInserta = true;

		log.info("RecoverPasswordMgrImpl - insertarClaveTemporal(): Insertando clave temporal para usuario rut : "
				+ rutCliente);
		this.queryDao = new QueryDaoImpl();

		try {

			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));
			inParams.put(2, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, claveTemporal));
			inParams.put(3, new DefinicionParametro(ConstantesBD.TIPO_INTEGER, 1));

			// Obtengo resultado del SP.
			resultadoQueryInserta = this.queryDao.queryForProcedureSqlServerUpdate(Constantes.DATASOURCE_SQLSERVER2,
					inParams, Constantes.POCEDURE_INSERTACLAVETEMPORAL);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - insertarClaveTemporal(): Error al consultar el Procedure");
				resultadoQueryInserta = false;
			} else {
				log.info("RecoverPasswordMgrImpl - insertarClaveTemporal(): Datos obtenidos correctamente");

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - insertarClaveTemporal(): Ha ocurrido un error en la ejecucion del Procedure, ERROR = "
							+ e);
		}
		return resultadoQueryInserta;

	}

	/**
	 * Obtiene email asociado al rut cliente
	 * 
	 * @param rutCliente
	 * @return
	 */
	public List<DtoOutUserData> obtenerCorreo(String rutCliente) throws java.lang.NullPointerException {
		List<DtoOutUserData> datosObtenerEmail = null;

		log.info(
				"RecoverPasswordMgrImpl - obtenerCorreo(): Obteniendo email asociado a cliente rut: " + rutCliente);
		this.queryDao = new QueryDaoImpl();

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));

			// Obtengo resultado del SP.
			datosObtenerEmail = this.queryDao.queryForProcedureSqlServer(Constantes.DATASOURCE_SQLSERVER2, inParams,
					new GetDatosUsuarioMapper(), Constantes.POCEDURE_DATOSUSUARIO);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - obtenerCorreo(): Error al consultar el Procedure");
			} else if (datosObtenerEmail!=null && this.queryDao.codRetorno() != -1){
				log.info("RecoverPasswordMgrImpl - obtenerCorreo(): Datos obtenidos correctamente");

			}
			else if(datosObtenerEmail==null){
				throw new NullPointerException(
						"RecoverPasswordMgrImpl - obtenerCorreo(): No tiene correo asociado, correo: null");
			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - obtenerCorreo(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
		}

		return datosObtenerEmail;
	}

	/**
	 * Recurso encargado de obtener clave temporal, validarla con la ingresada por
	 * el usuario y finalmente insertar nueva clave ingresada por usuario luego de
	 * haber sido encriptada
	 */
	public SalidaRecuperarClaveTemporal recoverPassTemp(
			DtoRequestRecuperarClaveTemporal dtoRequestRecuperarClaveTemporal) {
		SalidaRecuperarClaveTemporal salida = new SalidaRecuperarClaveTemporal();

		this.queryDao = new QueryDaoImpl();
		String rutCliente = null;
		String claveTemporal = null;
		String nuevaClave1 = null;
		String nuevaClave2 = null;
		String claveBd = null;
		int intentos = 0;
		long idClaveTemp = 0;
		boolean resultadoActualiza = true;
		String passwordEncrypt = null;
		int retorno = this.queryDao.codRetorno();
		boolean resultadoAddIntento = true;

		rutCliente = dtoRequestRecuperarClaveTemporal.getDtoRequestSetParametros().getDtoRecuperarClaveTemporal()
				.getRutCliente();

		log.info("RecoverPasswordMgrImpl - recuperarClaveTemporal(): Obteniendo clave temporal de usuario rut: "
				+ rutCliente);
		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));

			// Obtengo resultado del SP.
			List<DtoOutRecuperarClaveTemporal> datosRecuperarClaveTemporal = this.queryDao.queryForProcedureSqlServer(
					Constantes.DATASOURCE_SQLSERVER2, inParams, new GetRecuperarClaveTemporalMapper(),
					Constantes.POCEDURE_RECUPERARCLAVETEMPORAL);

			// Contraseñas ingresados por el usuario claveTemporal(llega al email),
			// ClaveNueva1 - ClaveNueva1 (nueva clave definitiva)
			claveTemporal = dtoRequestRecuperarClaveTemporal.getDtoRequestSetParametros().getDtoRecuperarClaveTemporal()
					.getClaveTemporal();
			nuevaClave1 = dtoRequestRecuperarClaveTemporal.getDtoRequestSetParametros().getDtoRecuperarClaveTemporal()
					.getClaveNueva1();
			nuevaClave2 = dtoRequestRecuperarClaveTemporal.getDtoRequestSetParametros().getDtoRecuperarClaveTemporal()
					.getClaveNueva2();

			int sizeLista = datosRecuperarClaveTemporal.size();
			for (int i = 0; i < sizeLista; i++) {
				DtoOutRecuperarClaveTemporal dtoClave = new DtoOutRecuperarClaveTemporal();
				dtoClave = datosRecuperarClaveTemporal.get(i);
				claveBd = dtoClave.getClaveTemporal();
				intentos = dtoClave.getIntentos();
				idClaveTemp = dtoClave.getIdClaveTemporal();

			}
			
			if(claveBd==null) {
				retorno = -1;
			throw new NullPointerException(
					"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Clave BD= null");

			}

			else if (claveBd != null && claveBd.equals(claveTemporal) && intentos <= 3 && nuevaClave1.equals(nuevaClave2)) {
				// Encripta nueva clave definitiva
				passwordEncrypt = EncryptPass.computeHash(nuevaClave1);
				log.info(
						"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Clave temporal ingresada correcta, clave nueva valida");
				log.info(
						"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Se procede a insertar nueva clave en bd");
				// Actualiza nueva clave
				resultadoActualiza = this.actualizarClave(rutCliente, passwordEncrypt);

				// Valida si la clave ha sido actualizada correctamente y procede a anular clave
				// temporal
				retorno = validaClaveActualizada(rutCliente, resultadoActualiza, passwordEncrypt, retorno);

			} else if (!claveBd.equals(claveTemporal)) {

				log.info(
						"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Clave temporal ingresada no coincide con la de base de datos");
				log.info(
						"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Llamado a Procedure para aumentar numero de reintentos... ");


				// Aumenta reintentos de ingreso de clave temporal
				resultadoAddIntento = this.addIntentosClaveTemporal(idClaveTemp);

				if (resultadoAddIntento == true) {
					salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				} else {
					salida.setDtoResponseCodigosEstado(JsonHeaderResponse.dataIncorrecta());
				}
				retorno = -2;

			} else if (!nuevaClave1.equals(nuevaClave2) && claveBd.equals(claveTemporal)) {
				retorno = -3;

			} else if (intentos > 3) {

				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados()
						.setDtoResponseCodigosOperacion(JsonBodyResponse.intentosExcedido());
				retorno = -4;

			}

//			if (queryDao.codRetorno() == -1) {
			if (retorno == -1) {
				log.error("RecoverPasswordMgrImpl - recuperarClaveTemporal(): Error al consultar el servicio");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
			} else if (retorno == -2) {
				log.error("RecoverPasswordMgrImpl - recuperarClaveTemporal(): Contraseña ingresada inválida");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados()
						.setDtoResponseCodigosOperacion(JsonBodyResponse.claveTemporalIncorrecta());
			} else if (retorno == -3) {
				log.error("RecoverPasswordMgrImpl - recuperarClaveTemporal(): Claves nuevas no coinciden");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.nuevaPassError());
			} else if (retorno == -4) {
				log.error(
						"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Ha excedido el máximo de reintentos permitidos");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados()
						.setDtoResponseCodigosOperacion(JsonBodyResponse.intentosExcedido());
			} else if (retorno == 1) {
				log.info("RecoverPasswordMgrImpl - recuperarClaveTemporal(): Clave cambiada correctamente");
				salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
				salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.cambioClaveOk());

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - recuperarClaveTemporal(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
			salida.setDtoResponseCodigosEstado(JsonHeaderResponse.requestOK());
			salida.getDtoResponseSetResultados().setDtoResponseCodigosOperacion(JsonBodyResponse.errorOperacion());
		}
		return salida;
	}

	/**
	 * Valida si la clave ha sido actualizada correctamente y procede a anular la
	 * clave temporal
	 * 
	 * @param rutCliente
	 * @param resultadoActualiza
	 * @param passwordEncrypt
	 * @param retorno
	 * @return
	 */
	private int validaClaveActualizada(String rutCliente, boolean resultadoActualiza, String passwordEncrypt,
			int retorno) {
		boolean resultadoAnula;
		if (resultadoActualiza == true) {
			log.info("RecoverPasswordMgrImpl - validaClaveActualizada(): Clave actualizada correctamente");

			resultadoAnula = this.anularClaveTemporal(rutCliente);
			retorno = retornoValido(resultadoAnula, passwordEncrypt, retorno);

		} else {
			log.info("RecoverPasswordMgrImpl - validaClaveActualizada(): Clave no ha podido ser actualizada");

		}
		return retorno;
	}

	private int retornoValido(boolean resultadoAnula, String passwordEncrypt, int retorno) {
		if (resultadoAnula == true) {
			log.info("RecoverPasswordMgrImpl - retornoValido(): Clave anulada correctamente");

			log.info("RecoverPasswordMgrImpl - retornoValido(): Valor de pass encriptada: " + passwordEncrypt);

			retorno = 1;
		}
		return retorno;
	}

	/**
	 * Aumenta el numero de reintentos cuando el usuario ingresa la contraseña
	 * temporal incorrecta
	 * 
	 * @param idClaveTemporal
	 * @return
	 */
	public boolean addIntentosClaveTemporal(long idClaveTemporal) {
		this.queryDao = new QueryDaoImpl();
		List<DtoOutAddIntentosClaveTemporal> resultadoAddIntento = null;
		String mensaje = null;
		int largoresultadoAddIntento = 0;
		boolean retornoAddIntentos = true;

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_LONG, idClaveTemporal));

			resultadoAddIntento = this.queryDao.queryForProcedureSqlServer(Constantes.DATASOURCE_SQLSERVER2, inParams,
					new GetAddIntentosClaveTemporalMapper(), Constantes.POCEDURE_ADDINTENTOSCLAVETEMPORAL);

			largoresultadoAddIntento = resultadoAddIntento.size();

			for (int l = 0; l < largoresultadoAddIntento; l++) {
				DtoOutAddIntentosClaveTemporal datosAddIntentos = new DtoOutAddIntentosClaveTemporal();
				datosAddIntentos = resultadoAddIntento.get(l);
				mensaje = datosAddIntentos.getMensaje();
			}

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - addIntentosClaveTemporal(): Error al consultar el Procedure");
				retornoAddIntentos = false;

			} else if (this.queryDao.codRetorno() != -1 && mensaje != null && mensaje.equals("Add Exitosa")) {
				log.info("RecoverPasswordMgrImpl - addIntentosClaveTemporal(): Intento añadido correctamente");
				retornoAddIntentos = true;

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - addIntentosClaveTemporal(): Ha ocurrido un error en la ejecucion del procedure, ERROR = "
							+ e);
		}
		return retornoAddIntentos;
	}

	/**
	 * Actualiza nueva clave definitiva en base de datos (se envia encriptada)
	 * 
	 * @param rutCliente
	 * @param nuevaClave
	 * @return
	 */
	public boolean actualizarClave(String rutCliente, String nuevaClave) {

		log.info(
				"RecoverPasswordMgrImpl - actualizarClaveTemporal(): Se actualiza nueva clave definitiva para usuario rut: "
						+ rutCliente);

		this.queryDao = new QueryDaoImpl();
		boolean resultadoQueryActualiza = true;

		try {

			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));
			inParams.put(2, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, nuevaClave));

			resultadoQueryActualiza = this.queryDao.queryForProcedureSqlServerUpdate(Constantes.DATASOURCE_SQLSERVER2,
					inParams, Constantes.POCEDURE_ACTUALIZARCLAVE);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - actualizarClave(): Error al consultar el Procedure");
				resultadoQueryActualiza = false;

			} else {
				log.info("RecoverPasswordMgrImpl - actualizarClave(): Datos actualizados correctamente");

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - actualizarClave(): Ha ocurrido un error en la ejecucion del Procedure, ERROR = "
							+ e);
			resultadoQueryActualiza = false;

		}
		return resultadoQueryActualiza;
	}

	/**
	 * Se ejecuta una vez actualizada la clave temporal a definitiva
	 * 
	 * @param rutCliente
	 * @return
	 */
	public boolean anularClaveTemporal(String rutCliente) {

		log.info("RecoverPasswordMgrImpl - anularClaveTemporal(): Anulando clave temporal para cliente rut: "
				+ rutCliente);

		this.queryDao = new QueryDaoImpl();
		boolean resultadoAnularClave = true;

		try {
			LinkedHashMap<Integer, DefinicionParametro> inParams = new LinkedHashMap<>();
			inParams.put(1, new DefinicionParametro(ConstantesBD.TIPO_VARCHAR, rutCliente));

			resultadoAnularClave = this.queryDao.queryForProcedureSqlServerUpdate(Constantes.DATASOURCE_SQLSERVER2, inParams,
					Constantes.POCEDURE_ANULARCLAVETEMPORAL);

			if (this.queryDao.codRetorno() == -1) {
				log.error("RecoverPasswordMgrImpl - anularClaveTemporal(): Error al consultar el servicio");
			} else {
				log.info("RecoverPasswordMgrImpl - anularClaveTemporal(): clave anulada correctamente");

			}
		} catch (Exception e) {
			log.error(
					"RecoverPasswordMgrImpl - anularClaveTemporal(): Ha ocurrido un error en la ejecucion del servicio, ERROR = "
							+ e);
		}
		return resultadoAnularClave;
	}

}
