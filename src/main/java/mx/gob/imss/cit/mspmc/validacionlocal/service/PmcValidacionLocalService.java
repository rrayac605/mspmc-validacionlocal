package mx.gob.imss.cit.mspmc.validacionlocal.service;

import java.util.List;

import mx.gob.imss.cit.mspmc.validacionlocal.input.ValidacionLocalInput;
import mx.gob.imss.cit.mspmccommons.dto.BitacoraErroresDTO;
import mx.gob.imss.cit.mspmccommons.exception.BusinessException;

public interface PmcValidacionLocalService {

	List<BitacoraErroresDTO> validaRegistro(ValidacionLocalInput validacionLocalInput) throws BusinessException;
	
	List<BitacoraErroresDTO> validaAltaRegistro(ValidacionLocalInput validacionLocalInput) throws BusinessException;

}
