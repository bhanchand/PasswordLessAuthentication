package com.okta.prasad.oktaweb2.model;

public class User {
	UserProfile profile = new UserProfile();
	Credentials credentials = new Credentials();
	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ profile:\"").append(profile).append("\", credentials:\"").append(credentials).append("}");
		return builder.toString();
	}
}
