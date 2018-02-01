package com.okta.prasad.oktaweb2.model;

public class AuthenticationResponse {
	String expiresAt,status,sessionToken;

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ expiresAt:\"").append(expiresAt).append("\", status:\"").append(status).append("\", sessionToken:\"")
				.append(sessionToken)
				.append("}");
		return builder.toString();
	}
}
