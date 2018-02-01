package com.okta.prasad.oktaweb2.model;

public class OpenIdJWT {
	String access_token,token_type,expires_in,scope,id_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getId_token() {
		return id_token;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ access_token:\"").append(access_token).append("\", token_type :\"")
				.append( token_type).append("\", expires_in :\"")
				.append( expires_in).append("\", scope :\"")
				.append( scope).append("\", id_token :\"")
				.append( id_token).append("\"}");
		return builder.toString();
	}
}
