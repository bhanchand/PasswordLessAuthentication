package com.okta.prasad.oktaweb2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ClientErrorHandler implements ResponseErrorHandler {
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String sResponseBody = "";
		String sResponseCode = response.getStatusCode().toString();
		
		System.out.println("Throwing  ClientHttpResponse Exception : " + sResponseCode);
		sResponseBody = readStream(response.getBody());
		System.out.println("Response Body : " + sResponseBody);
		
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new AppResourceNotFoundException(HttpStatus.NOT_FOUND.toString());
		}
		if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
			throw new AppResourceNotFoundException(HttpStatus.FORBIDDEN.toString());
		}
		// handle other possibilities, then use the catch all...
		throw new AppUnexpectedHttpException(response.getStatusCode().toString());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if ((response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
				|| (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)) {
			return true;
		}
		return false;
	}

	// Format error response
	private String readStream(InputStream stream) {
		StringBuilder builder = new StringBuilder();
		try {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(stream))) {
				String line;
				while ((line = in.readLine()) != null) {
					builder.append(line); // + "\r\n"(no need, json has no line breaks!)
				}
				in.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("JSON: " + builder.toString());
		return builder.toString();
	}

}
