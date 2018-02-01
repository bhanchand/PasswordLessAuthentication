package com.okta.prasad.oktaweb2.controller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okta.prasad.oktaweb2.model.AccessTokenIntrospect;
import com.okta.prasad.oktaweb2.service.Mfa;

@RestController
public class DownloadController {

	@Autowired
	Mfa mfa;

	@RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadPDFFile(@RequestParam(value = "accesstoken") String token, @RequestParam(value = "pdf") String pdf)
			throws IOException {

		if (token == null || "".equals(token)) {
			throw new AccessDeniedException("Access Denied : ACCESS TOKEN missing");
		} else {
			AccessTokenIntrospect accessTokenIntrospect = mfa.instrospectAcccessToken(token);
			if (!accessTokenIntrospect.isActive()) {
				throw new AccessDeniedException("Access Denied : Invalid or expired ACCESS TOKEN provided");
			}
		}
		ClassPathResource pdfFile = new ClassPathResource(pdf);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Content-Disposition", "attachment; filename=" + pdfFile.getFilename());
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentLength(pdfFile.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(pdfFile.getInputStream()));
	}
}