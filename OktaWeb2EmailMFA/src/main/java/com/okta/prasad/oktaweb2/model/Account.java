package com.okta.prasad.oktaweb2.model;

public class Account {
	String id, status, created, activated, statusChanged, lastLogin, lastUpdated, passwordChanged;
	UserProfile profile = new UserProfile();
	Credentials credentials = new Credentials();

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getStatusChanged() {
		return statusChanged;
	}

	public void setStatusChanged(String statusChanged) {
		this.statusChanged = statusChanged;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(String passwordChanged) {
		this.passwordChanged = passwordChanged;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ id:\"").append(id).append("\", status:\"").append(status).append("\", created:\"")
				.append(created).append("\", activated:\"").append(activated).append("\", statusChanged:\"")
				.append(statusChanged).append("\", lastLogin:\"").append(lastLogin).append("\", lastUpdated:\"")
				.append(lastUpdated).append("\", profile:\"").append(profile).append("\", credentials:\"").append(credentials)
				.append("}");
		return builder.toString();
	}
}
