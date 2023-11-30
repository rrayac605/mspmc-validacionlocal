package mx.gob.imss.cit.mspmc.validacionlocal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.imss.cit.mspmc.validacionlocal.repository.ConsecuenciaRepository;
import mx.gob.imss.cit.mspmc.validacionlocal.repository.LaudoRepository;
import mx.gob.imss.cit.mspmc.validacionlocal.repository.TipoRiesgoRepository;
import mx.gob.imss.cit.mspmc.validacionlocal.service.CatalogosService;
import mx.gob.imss.cit.mspmccommons.dto.ConsecuenciaDTO;
import mx.gob.imss.cit.mspmccommons.dto.LaudoDTO;
import mx.gob.imss.cit.mspmccommons.dto.TipoRiesgoDTO;

@Component
public class CatalogoServiceImpl implements CatalogosService {

	@Autowired
	private LaudoRepository laudoRepository;

	@Autowired
	private ConsecuenciaRepository consecuenciaRepository;

	@Autowired
	private TipoRiesgoRepository tipoRiesgoRepository;

	@Override
	public TipoRiesgoDTO obtenerTipoRiesgo(String cveTipoRiesgo) {

		TipoRiesgoDTO tipoRiesgoDTO = null;
		try {
			tipoRiesgoDTO = tipoRiesgoRepository.findOneByCve(cveTipoRiesgo).get();
		} catch (Exception e) {
			System.out.println("No existe información para el tipo de riesgo: " + cveTipoRiesgo);
		}
		return tipoRiesgoDTO;
	}

	@Override
	public ConsecuenciaDTO obtenerConsecuencia(String cveConsecuencia) {
		ConsecuenciaDTO consecuenciaDTO = null;
		try {
			consecuenciaDTO = consecuenciaRepository.findOneByCve(cveConsecuencia).get();
		} catch (Exception e) {
			System.out.println("No existe información para la consecuencia: " + cveConsecuencia);
		}
		return consecuenciaDTO;
	}

	@Override
	public LaudoDTO obtenerLaudo(String cveLaudo) {
		LaudoDTO laudoDTO = null;
		try {
			laudoDTO = laudoRepository.findOneByCve(cveLaudo).get();
		} catch (Exception e) {
			System.out.println("No existe información para el laudo: " + cveLaudo);
		}
		return laudoDTO;
	}

}
