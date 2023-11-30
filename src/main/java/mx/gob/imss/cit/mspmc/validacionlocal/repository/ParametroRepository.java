package mx.gob.imss.cit.mspmc.validacionlocal.repository;

import java.util.Optional;

import mx.gob.imss.cit.mspmc.validacionlocal.security.model.ParametroDTO;


public interface ParametroRepository {

	Optional<ParametroDTO> findOneByCve(String cveIdParametro);

}
