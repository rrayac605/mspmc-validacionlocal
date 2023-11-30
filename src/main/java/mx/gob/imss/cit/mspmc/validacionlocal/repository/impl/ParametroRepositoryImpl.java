package mx.gob.imss.cit.mspmc.validacionlocal.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mspmc.validacionlocal.repository.ParametroRepository;
import mx.gob.imss.cit.mspmc.validacionlocal.security.model.ParametroDTO;


@Repository
public class ParametroRepositoryImpl implements ParametroRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Optional<ParametroDTO> findOneByCve(String cveIdParametro) {
		Query query = new Query(Criteria.where("cveIdParametro").is(cveIdParametro));
		ParametroDTO d = this.mongoOperations.findOne(query, ParametroDTO.class);

		Optional<ParametroDTO> parametro = Optional.ofNullable(d);

		return parametro;
	}

}
