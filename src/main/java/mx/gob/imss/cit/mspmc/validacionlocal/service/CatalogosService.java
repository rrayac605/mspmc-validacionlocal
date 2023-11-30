package mx.gob.imss.cit.mspmc.validacionlocal.service;

import mx.gob.imss.cit.mspmccommons.dto.ConsecuenciaDTO;
import mx.gob.imss.cit.mspmccommons.dto.LaudoDTO;
import mx.gob.imss.cit.mspmccommons.dto.TipoRiesgoDTO;

public interface CatalogosService {

	TipoRiesgoDTO obtenerTipoRiesgo(String cveTipoRiesgo);

	ConsecuenciaDTO obtenerConsecuencia(String cveConsecuencia);

	LaudoDTO obtenerLaudo(String cveLaudo);	

}
