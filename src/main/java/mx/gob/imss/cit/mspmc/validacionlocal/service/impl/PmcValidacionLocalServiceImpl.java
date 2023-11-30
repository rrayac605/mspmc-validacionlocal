package mx.gob.imss.cit.mspmc.validacionlocal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.imss.cit.mspmc.validacionlocal.input.ValidacionLocalInput;
import mx.gob.imss.cit.mspmc.validacionlocal.rules.ValidacionAltaLocalFactory;
import mx.gob.imss.cit.mspmc.validacionlocal.rules.ValidacionLocalFactory;
import mx.gob.imss.cit.mspmc.validacionlocal.service.CatalogosService;
import mx.gob.imss.cit.mspmc.validacionlocal.service.PmcValidacionLocalService;
import mx.gob.imss.cit.mspmc.validacionlocal.utils.DateUtils;
import mx.gob.imss.cit.mspmccommons.dto.BitacoraErroresDTO;
import mx.gob.imss.cit.mspmccommons.dto.RegistroDTO;
import mx.gob.imss.cit.mspmccommons.exception.BusinessException;

@Component
public class PmcValidacionLocalServiceImpl implements PmcValidacionLocalService {

	@Autowired
	private CatalogosService catalogosService;

	@Autowired
	private ValidacionLocalFactory validacionLocalFactory;
	
	@Autowired
	private ValidacionAltaLocalFactory validacionAltaLocalFactory;

	@Override
	public List<BitacoraErroresDTO> validaRegistro(ValidacionLocalInput validacionLocalInput) throws BusinessException {
		Facts facts = new Facts();

		RegistroDTO registroDTO = inputToRegistro(validacionLocalInput);
		obtenerValores(registroDTO);
		List<BitacoraErroresDTO> errores = new ArrayList<BitacoraErroresDTO>();
		facts.put("registroDTO", registroDTO);
		facts.put("errores", errores);

		RulesEngine rulesEngine = new DefaultRulesEngine();

		rulesEngine.fire(validacionLocalFactory.getRules(), facts);
		return errores;
	}
	
	@Override
	public List<BitacoraErroresDTO> validaAltaRegistro(ValidacionLocalInput validacionLocalInput) throws BusinessException {
		Facts facts = new Facts();

		RegistroDTO registroDTO = inputToRegistro(validacionLocalInput);
		obtenerValores(registroDTO);
		List<BitacoraErroresDTO> errores = new ArrayList<BitacoraErroresDTO>();
		facts.put("registroDTO", registroDTO);
		facts.put("errores", errores);

		RulesEngine rulesEngine = new DefaultRulesEngine();

		rulesEngine.fire(validacionAltaLocalFactory.getRules(), facts);
		return errores;
	}

	private RegistroDTO inputToRegistro(ValidacionLocalInput validacionLocalInput) {
		RegistroDTO registroDTO = new RegistroDTO();
		registroDTO.setCveConsecuencia(validacionLocalInput.getConsecuencia());
		registroDTO.setCveTipoRiesgo(validacionLocalInput.getTipoRiesgo());
		registroDTO.setNumLaudo(validacionLocalInput.getLaudo());
		registroDTO.setNumNss(validacionLocalInput.getNss());
		registroDTO.setNomAsegurado(validacionLocalInput.getNombre());
		registroDTO.setRefCurp(validacionLocalInput.getCurp());
		registroDTO.setRefRegistroPatronal(validacionLocalInput.getRp());
		registroDTO.setFecProceso(DateUtils.parserFromDate(validacionLocalInput.getFecProcesoCarga()));
		registroDTO.setFecInicio(DateUtils.parserFromStringInputMS(validacionLocalInput.getFechaInicio()));
		registroDTO.setFecFin(validacionLocalInput.getFechaFin() != null
				? DateUtils.parserFromStringInputMS(validacionLocalInput.getFechaFin())
				: null);
		registroDTO.setFecAccidente(validacionLocalInput.getFechaAccidente() != null
				? DateUtils.parserFromStringInputMS(validacionLocalInput.getFechaAccidente())
				: null);
		registroDTO.setFecAltaRegistro(validacionLocalInput.getFechaAlta() != null
				? DateUtils.parserFromStringInputMS(validacionLocalInput.getFechaAlta())
				: null);
		registroDTO.setPorPorcentajeIncapacidad(validacionLocalInput.getProcentaje());
		registroDTO.setNumDiasSubsidiados(validacionLocalInput.getDiasSubsidiados());
		return registroDTO;
	}

	private void obtenerValores(RegistroDTO registroDTO) {
		registroDTO.setTipoRiesgoDTO(catalogosService.obtenerTipoRiesgo(registroDTO.getCveTipoRiesgo()));
		registroDTO.setConsecuenciaDTO(catalogosService.obtenerConsecuencia(registroDTO.getCveConsecuencia()));
	}

}
