package com.okta.prasad.oktaweb2.model;

public class AccessTokenIntrospect {
	boolean active;
	String scope, username, sub, aud, iss, jti, token_type, client_id, uid;
	int exp, iat;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getIat() {
		return iat;
	}

	public void setIat(int iat) {
		this.iat = iat;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ active:\"").append(active)
				.append("\", scope :\"").append( scope)
				.append("\", username :\"").append( username)
				.append("\", sub :\"").append( sub)
				.append("\", aud :\"").append( aud)
				.append("\", iss :\"").append( iss)
				.append("\", jti :\"").append( jti)
				.append("\", token_type :\"").append( token_type)
				.append("\", client_id :\"").append( client_id)
				.append("\", client_id :\"").append( client_id)
				.append("\", exp :").append( exp)				
				.append(", iat :").append(iat).append("}");
		return builder.toString();
	}
}
