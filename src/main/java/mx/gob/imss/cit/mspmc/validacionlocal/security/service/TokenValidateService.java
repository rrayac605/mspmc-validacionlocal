package mx.gob.imss.cit.mspmc.validacionlocal.security.service;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import mx.gob.imss.cit.mspmc.validacionlocal.repository.ParametroRepository;
import mx.gob.imss.cit.mspmc.validacionlocal.security.model.ParametroDTO;
import mx.gob.imss.cit.mspmc.validacionlocal.security.model.TokenInfoModel;

@Service
public class TokenValidateService {
	
	private static final String HEADER = "Authorization";

	private RestTemplate restTemplate;
	
	@Autowired
	ParametroRepository parametroRepository;
	
	public ResponseEntity<TokenInfoModel> validateToken(HttpServletRequest request) {
		setSSLContext();
		HttpHeaders headers = new HttpHeaders();
		String token  = request.getHeader(HEADER);
	    headers.set(HEADER, token);  
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		
		Optional<ParametroDTO> urlTokenInfo = parametroRepository.findOneByCve("urlTokenInfo");
		
		ResponseEntity<TokenInfoModel> response=null;
		try {
			response = restTemplate.exchange(urlTokenInfo.get().getDesParametro(),
					HttpMethod.GET, requestEntity, TokenInfoModel.class);
		} catch (HttpClientErrorException httpce) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN , httpce.getMessage()); 
		}
		return response;
	}
	
	private void setSSLContext() {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		 
		SSLContext sslContext =null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
		 
		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();
		 
		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();
		 
		requestFactory.setHttpClient(httpClient);
		restTemplate = new RestTemplate(requestFactory);
	}
}
