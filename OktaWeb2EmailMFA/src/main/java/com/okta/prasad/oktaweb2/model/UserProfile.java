package com.okta.prasad.oktaweb2.model;

public class UserProfile {
	public String firstName, lastName, mobilePhone, secondEmail, login, email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ firstName:\"").append(firstName).append("\", lastName :\"").append(lastName)
				.append("\", mobilePhone :\"").append(mobilePhone).append("\", secondEmail :\"").append(secondEmail)
				.append("\", login :\"").append(login).append("\", email :\"").append(email).append("}");
		return builder.toString();
	}

}
