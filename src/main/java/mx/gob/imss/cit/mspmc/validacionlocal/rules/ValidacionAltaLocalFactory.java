package mx.gob.imss.cit.mspmc.validacionlocal.rules;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class ValidacionAltaLocalFactory {

	@Setter
	@Getter
	private Rules rules;

	@Autowired
	private ResourceLoader resourceLoader;

	@PostConstruct
	public void creaReglas() {
		MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
		try {
			Resource resource = resourceLoader.getResource("classpath:rules_locales_alta.yml");
			rules = ruleFactory.createRules(new InputStreamReader(resource.getInputStream()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
