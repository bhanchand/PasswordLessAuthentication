package com.okta.prasad.oktaweb2.model;

public class AuthenticationCredential {
	String username, password,grant_type,scope,redirect_uri;
	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*
	AuthenticationOption options = new AuthenticationOption();
	
	public AuthenticationOption getOptions() {
		return options;
	}

	public void setOptions(AuthenticationOption options) {
		this.options = options;
	}

	public class AuthenticationOption {
		boolean multiOptionalFactorEnroll = true, warnBeforePasswordExpired = true;
		public boolean isMultiOptionalFactorEnroll() {
			return multiOptionalFactorEnroll;
		}
		public void setMultiOptionalFactorEnroll(boolean multiOptionalFactorEnroll) {
			this.multiOptionalFactorEnroll = multiOptionalFactorEnroll;
		}
		public boolean isWarnBeforePasswordExpired() {
			return warnBeforePasswordExpired;
		}
		public void setWarnBeforePasswordExpired(boolean warnBeforePasswordExpired) {
			this.warnBeforePasswordExpired = warnBeforePasswordExpired;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ multiOptionalFactorEnroll:\"").append(multiOptionalFactorEnroll).append("\", warnBeforePasswordExpired:\"").append(warnBeforePasswordExpired).append("}");
			return builder.toString();
		}
	}
*/	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ username:\"").append(username).append("\", password :\"")
				.append( password).append("\", grant_type :\"")
				.append( grant_type).append("\", scope :\"")
				.append( scope).append("\", redirect_uri :\"")
				.append( redirect_uri).append("\"}");
		return builder.toString();
	}
}
