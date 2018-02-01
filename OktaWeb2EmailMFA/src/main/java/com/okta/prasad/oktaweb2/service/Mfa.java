package com.okta.prasad.oktaweb2.service;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.okta.prasad.oktaweb2.AppResourceForbiddenException;
import com.okta.prasad.oktaweb2.AppResourceNotFoundException;
import com.okta.prasad.oktaweb2.ClientErrorHandler;
import com.okta.prasad.oktaweb2.model.AccessTokenIntrospect;
import com.okta.prasad.oktaweb2.model.Account;
import com.okta.prasad.oktaweb2.model.AuthenticationCredential;
import com.okta.prasad.oktaweb2.model.MFAFactor;
import com.okta.prasad.oktaweb2.model.MFAFactorResponse;
import com.okta.prasad.oktaweb2.model.OpenIdJWT;
import com.okta.prasad.oktaweb2.model.PassCode;
import com.okta.prasad.oktaweb2.model.User;

@Service
public class Mfa {

	// Inject via application.properties
	@Value("${okta.url}")
	final String oktaUrl = null;

	@Value("${okta.APIKey}")
	final String oktaKey = null;

	@Value("${okta.oauth.clientid}")
	final String oktaOAuthClientId = null;

	@Value("${okta.oauth.secret}")
	final String oktaOAuthClientSecret = null;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ClientErrorHandler());
		return restTemplate;
	}

	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "SSWS " + oktaKey);
		return headers;
	}

	/* Get User */
	public Account getUser(String userName) throws AppResourceNotFoundException, AppResourceForbiddenException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers());
		ResponseEntity<Account> response = restTemplate().exchange(oktaUrl + "/api/v1/users/" + userName,
				HttpMethod.GET, entity, Account.class);
		Account account = response.getBody();
		return account;
	}

	/* Create User */
	public Account createUser(User user) throws AppResourceNotFoundException, AppResourceForbiddenException {
		HttpEntity<User> entity = new HttpEntity<>(user, headers());
		ResponseEntity<Account> response = restTemplate().exchange(oktaUrl + "/api/v1/users?activate=true",
				HttpMethod.POST, entity, Account.class);
		Account account = response.getBody();
		return account;
	}

	/* Create MFA */
	// Enroll and Auto-Activate Okta Email Factor
	public String createMFATypeEmail(String idUser, String email)
			throws AppResourceNotFoundException, AppResourceForbiddenException {

		MFAFactor mfaFactor = new MFAFactor();
		mfaFactor.setFactorType("email");
		mfaFactor.setProvider("OKTA");
		mfaFactor.getProfile().setEmail(email);

		HttpEntity<MFAFactor> entity = new HttpEntity<>(mfaFactor, headers());
		ResponseEntity<String> response = restTemplate().exchange(
				oktaUrl + "/api/v1/users/" + idUser + "/factors?activate=true", HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	/*
	 * Send Email verification challenge IMPORTANT If you omit passCode in the
	 * request a new OTP is sent to the email address, otherwise the request
	 * attempts to verify the passCode. IMPORTANT Verify Token Factor
	 */
	public String sendChallenge(String idUser, String idFactor)
			throws AppResourceNotFoundException, AppResourceForbiddenException {

		HttpEntity<String> entity = new HttpEntity<>(null, headers());
		ResponseEntity<String> response = restTemplate().exchange(
				oktaUrl + "/api/v1/users/" + idUser + "/factors/" + idFactor + "/verify?tokenLifetimeSeconds=600",
				HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	/* Get Email MFA Factor */
	public MFAFactorResponse getEmailMFAFactor(String idUser)
			throws AppResourceNotFoundException, AppResourceForbiddenException {

		HttpEntity<String> entity = new HttpEntity<>(null, headers());
		ResponseEntity<List<MFAFactorResponse>> mfaResponse = restTemplate().exchange(
				oktaUrl + "/api/v1/users/" + idUser + "/factors", HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<MFAFactorResponse>>() {
				});
		MFAFactorResponse mfaEmail = null;
		List<MFAFactorResponse> mfaList = mfaResponse.getBody();

		for (MFAFactorResponse mfa : mfaList) {
			if (mfa.getFactorType().equals("email")) {
				mfaEmail = mfa;
				break;
			}
		}
		if (mfaEmail == null) {
			throw new AppResourceNotFoundException("Response factor for email not found");
		}

		return mfaEmail;
	}

	

	/* Verify Token Factor */
	public String verifyChallenge(String idUser, String idFactor, String activationCode)
			throws AppResourceNotFoundException, AppResourceForbiddenException {
		PassCode passCode = new PassCode();
		passCode.setPassCode(activationCode);
		HttpEntity<PassCode> entity = new HttpEntity<>(passCode, headers());
		ResponseEntity<String> response = restTemplate().exchange(
				oktaUrl + "/api/v1/users/" + idUser + "/factors/" + idFactor + "/verify", HttpMethod.POST, entity,
				String.class);
		return response.getBody();
	}

	/*
	 * //activate MFA public String activateEmailMFAFactor(String idUser, String
	 * idFactor, String factor) throws AppResourceNotFoundException {
	 * 
	 * PassCode passCode = new PassCode(); passCode.setPassCode(factor);
	 * 
	 * HttpEntity<PassCode> entity = new HttpEntity<>(passCode, headers());
	 * ResponseEntity<String> passCodeResponse = restTemplate().exchange( oktaUrl +
	 * "/api/v1/users/" + idUser + "/factors/" + idFactor + "/lifecycle/activate",
	 * HttpMethod.POST, entity, String.class); return passCodeResponse.getBody(); }
	 */

	/* Authenticate USER */
	public OpenIdJWT authenticateUser(String username, String password)
			throws AppResourceNotFoundException, AppResourceForbiddenException {

		AuthenticationCredential authenticationCredential = new AuthenticationCredential();
		authenticationCredential.setUsername(username);
		authenticationCredential.setPassword(password);
		authenticationCredential.setGrant_type("password");
		authenticationCredential.setScope("openid");
		authenticationCredential.setGrant_type("password");

		String sClientId_ClientSecret = Base64.getEncoder()
				.encodeToString((oktaOAuthClientId + ":" + oktaOAuthClientSecret).getBytes());

		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Accept", "application/json");
		headers1.add("Content-Type", "application/x-www-form-urlencoded");
		headers1.add("Authorization", "Basic " + sClientId_ClientSecret);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", "password");
		params.add("scope", "openid");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers1);

		ResponseEntity<OpenIdJWT> jwt = restTemplate().exchange(oktaUrl + "/oauth2/v1/token", HttpMethod.POST, entity,
				OpenIdJWT.class);

		return jwt.getBody();
	}

	/* Introspect token*/
	public AccessTokenIntrospect instrospectAcccessToken(String token)
			throws AppResourceNotFoundException, AppResourceForbiddenException {
		
		String sClientId_ClientSecret = Base64.getEncoder()
				.encodeToString((oktaOAuthClientId + ":" + oktaOAuthClientSecret).getBytes());

		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Accept", "application/json");
		headers1.add("Content-Type", "application/x-www-form-urlencoded");
		headers1.add("Authorization", "Basic " + sClientId_ClientSecret);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("token", token);
		params.add("token_type_hint", "id_token");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers1);

		ResponseEntity<AccessTokenIntrospect> jwt = restTemplate().exchange(oktaUrl + "/oauth2/v1/introspect", HttpMethod.POST, entity,
				AccessTokenIntrospect.class);

		return jwt.getBody();
		
	}
	
	/* Reset MFA */
	public String resetMFA(String idUser) {

		HttpEntity<String> entity = new HttpEntity<>(null, headers());
		ResponseEntity<String> response = restTemplate().exchange(
				oktaUrl + "/api/v1/users/" + idUser + "/lifecycle/reset_factors", HttpMethod.POST, entity,
				String.class);
		return response.getBody();
	}

	
}
