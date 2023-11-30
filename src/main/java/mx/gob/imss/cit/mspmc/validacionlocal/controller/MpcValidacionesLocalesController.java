package mx.gob.imss.cit.mspmc.validacionlocal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.gob.imss.cit.mspmc.validacionlocal.input.ValidacionLocalInput;
import mx.gob.imss.cit.mspmc.validacionlocal.service.PmcValidacionLocalService;
import mx.gob.imss.cit.mspmccommons.dto.BitacoraErroresDTO;
import mx.gob.imss.cit.mspmccommons.dto.ErrorResponse;
import mx.gob.imss.cit.mspmccommons.exception.BusinessException;

@RestController
@Api(value = "Validadion local PMC", tags = { "Validadion local PMC Rest" })
@RequestMapping("/msvalidacionlocal/v1")
public class MpcValidacionesLocalesController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PmcValidacionLocalService pmcValidacionLocalService;

	@RequestMapping("/health/ready")
	@ResponseStatus(HttpStatus.OK)
	public void ready() {
	}

	@RequestMapping("/health/live")
	@ResponseStatus(HttpStatus.OK)
	public void live() {
	}

	@ApiOperation(value = "Validaciones Locales", nickname = "validarLocal", notes = "Consulta movimientos", response = Object.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Respuesta exitosa", response = Object.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "Describe un error general del sistema", response = ErrorResponse.class) })
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object validarLocal(@RequestBody ValidacionLocalInput input,@RequestHeader(value = "Authorization") String token) {
		Object resultado = null;
		try {
			logger.info("MpcArchivosController:buscarEstadoArchivo:try [{}]", input.toString());
			List<BitacoraErroresDTO> listado = pmcValidacionLocalService.validaRegistro(input);
			resultado = new ResponseEntity<Object>(listado, HttpStatus.OK);
			logger.info("MpcArchivosController:buscarEstadoArchivo:returnOk");
		} catch (BusinessException be) {
			logger.info("MpcArchivosController:buscarEstadoArchivo:catch");
			ErrorResponse errorResponse = be.getErrorResponse();

			int numberHTTPDesired = Integer.parseInt(errorResponse.getCode());

			resultado = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(numberHTTPDesired));
			logger.info("MpcArchivosController:buscarEstadoArchivo:numberHTTPDesired");

		}

		logger.info("MpcArchivosController:buscarEstadoArchivo:FinalReturn");
		return resultado;
	}
	
	@ApiOperation(value = "Validaciones Locales", nickname = "validarLocal", notes = "Consulta movimientos", response = Object.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Respuesta exitosa", response = Object.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "Describe un error general del sistema", response = ErrorResponse.class) })
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/validarAlta", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object validarAltaLocal(@RequestBody ValidacionLocalInput input,@RequestHeader(value = "Authorization") String token) {
		Object resultado = null;
		try {
			logger.info("MpcArchivosController:buscarEstadoArchivo:try [{}]", input.toString());
			List<BitacoraErroresDTO> listado = pmcValidacionLocalService.validaAltaRegistro(input);
			resultado = new ResponseEntity<Object>(listado, HttpStatus.OK);
			logger.info("MpcArchivosController:buscarEstadoArchivo:returnOk");
		} catch (BusinessException be) {
			logger.info("MpcArchivosController:buscarEstadoArchivo:catch");
			ErrorResponse errorResponse = be.getErrorResponse();

			int numberHTTPDesired = Integer.parseInt(errorResponse.getCode());

			resultado = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(numberHTTPDesired));
			logger.info("MpcArchivosController:buscarEstadoArchivo:numberHTTPDesired");

		}

		logger.info("MpcArchivosController:buscarEstadoArchivo:FinalReturn");
		return resultado;
	}

}
