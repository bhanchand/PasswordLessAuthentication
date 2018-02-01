package com.okta.prasad.oktaweb2.model;

public class MFAFactorResponse {
	String id,factorType,provider,vendorName,status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ id:\"").append(id).append("\", factorType :\"").append(factorType).append("\", provider :\"").append(provider).append("\", vendorName :\"").append(vendorName).append("\", status :\"").append(status).append("}");
		return builder.toString();
	}
}
