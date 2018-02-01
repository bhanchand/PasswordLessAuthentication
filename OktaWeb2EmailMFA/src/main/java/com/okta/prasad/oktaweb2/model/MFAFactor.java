package com.okta.prasad.oktaweb2.model;

public class MFAFactor {
	String factorType,provider;
	FactorProfileEmail profile = new FactorProfileEmail();
	
	public String getFactorType() {
		return factorType;
	}

	public void setFactorType(String factorType) {
		this.factorType = factorType;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public FactorProfileEmail getProfile() {
		return profile;
	}

	public void setProfile(FactorProfileEmail profile) {
		this.profile = profile;
	}	
	
	public class FactorProfileEmail{
		String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ email:\"").append(email).append("}");
			return builder.toString();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ factorType:\"").append(factorType).append("\", provider :\"").append(provider).append("\", profile :\"").append(profile).append("}");
		return builder.toString();
	}
}
