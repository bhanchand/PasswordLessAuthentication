package com.okta.prasad.oktaweb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.okta.prasad.oktaweb2.AppResourceForbiddenException;
import com.okta.prasad.oktaweb2.AppResourceNotFoundException;
import com.okta.prasad.oktaweb2.form.AccountForm;
import com.okta.prasad.oktaweb2.model.Account;
import com.okta.prasad.oktaweb2.model.Credentials;
import com.okta.prasad.oktaweb2.model.MFAFactorResponse;
import com.okta.prasad.oktaweb2.model.OpenIdJWT;
import com.okta.prasad.oktaweb2.model.User;
import com.okta.prasad.oktaweb2.model.UserProfile;
import com.okta.prasad.oktaweb2.service.Mfa;

@Controller
public class RegisterController {

	@Autowired
	Mfa mfa;

	@Value("${okta.hardCodedPassWord}")
	final String hardCodedPassWord = null;

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String registerStart(Model model) {
		AccountForm accountForm = new AccountForm();
		model.addAttribute("accountForm", accountForm);
		model.addAttribute("state", "register");
		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST, params = "checkEmail")
	public String checkEmail(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
		model.addAttribute("accountForm", accountForm);
		Account account;
		String email = accountForm.getUserid();

		try {
			account = mfa.getUser(accountForm.getUserid());
			if("DEPROVISIONED".equals(account.getStatus())) {
				model.addAttribute("errorMessage", email
						+ " account is deprovisioned. Please activate the account first.");
				model.addAttribute("state", "register");
				return "register";
			} else {
				OpenIdJWT jwt;
				try {
					jwt = mfa.authenticateUser(email, hardCodedPassWord);
				} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {
					model.addAttribute("errorMessage", "Account " + email + " could not be validated. Please contact SA.");
					return "register";
				}

				model.addAttribute("jwt", jwt.toString());
				model.addAttribute("accesstoken", jwt.getAccess_token());
				model.addAttribute("state", "emailVerified");
				return "register";
			}
			
			
		} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {

			User user = new User();
			UserProfile userProfile = user.getProfile();
			Credentials credentials = user.getCredentials();
			userProfile.setFirstName("hello");
			userProfile.setLastName("hello");
			userProfile.setEmail(email);
			userProfile.setMobilePhone(null);
			userProfile.setLogin(email);

			// credentials = new Credentials();
			credentials.getPassword().setValue(hardCodedPassWord);
			credentials.getRecovery_question().setQuestion("Which vegetable you hate the most");
			credentials.getRecovery_question().setAnswer("brocolii");
			account = mfa.createUser(user);
			
			// Enroll MFA
			mfa.createMFATypeEmail(account.getId(), email);
			model.addAttribute("state", "checkEmail");
			
		}

		//Get Email factor and Send Challenge
		MFAFactorResponse mfaEmailFactorResponse;
		try {
			mfaEmailFactorResponse = mfa.getEmailMFAFactor(account.getId());
			System.out.println("mfa.getEmailMFAFactor = " + mfaEmailFactorResponse);	
			
			System.out.println("idUser = " + account.getId());	
			System.out.println("idFactor = " +  mfaEmailFactorResponse.getId());
			
			System.out.println("mfa.sendChallenge = " + mfa.sendChallenge(account.getId(), mfaEmailFactorResponse.getId()));
		} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("state", "register");
			return "register";
		}

		
		model.addAttribute("state", "checkEmail");
		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST, params = "checkMFA")
	public String checkMFA(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {
		model.addAttribute("accountForm", accountForm);

		Account account;
		String email = accountForm.getUserid();
		String activationCode = accountForm.getCode();

		try {
			account = mfa.getUser(accountForm.getUserid());
		} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {
			model.addAttribute("errorMessage", email
					+ " could not be found. Please check the email address and try again. If this is a new email, please register email first.");
			model.addAttribute("state", "checkEmail");
			return "register";
		}

		MFAFactorResponse mfaEmailFactorResponse;
		try {
			mfaEmailFactorResponse = mfa.getEmailMFAFactor(account.getId());
		} catch (AppResourceNotFoundException e) {
			model.addAttribute("errorMessage",
					"Account " + email + " is not enrolled 'email' as a MFA. Please register your email first.");
			model.addAttribute("state", "checkEmail");
			return "register";
		}

		
		String idFactor = mfaEmailFactorResponse.getId();
		String mfaEmailFactorActivationResponse;
		try {
			mfaEmailFactorActivationResponse = mfa.verifyChallenge(account.getId(), idFactor, activationCode);
		} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {
			model.addAttribute("errorMessage",
					"Account " + email + " with passcode could not be validated. Please check the email and the passcode and try again.");
			model.addAttribute("state", "checkEmail");
			return "register";
		}
		

		OpenIdJWT jwt;
		try {
			jwt = mfa.authenticateUser(email, hardCodedPassWord);
		} catch (AppResourceNotFoundException | AppResourceForbiddenException e) {
			model.addAttribute("errorMessage", "Account " + email + " could not be validated. Please contact SA.");
			model.addAttribute("state", "checkEmail");
			return "register";
		}

		model.addAttribute("jwt", jwt.toString());
		model.addAttribute("accesstoken", jwt.getAccess_token());
		model.addAttribute("state", "emailVerified");

		return "register";
	}
}