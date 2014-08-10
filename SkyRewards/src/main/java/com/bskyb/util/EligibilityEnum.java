package com.bskyb.util;

public enum EligibilityEnum {
	CUSTOMER_ELIGIBLE("Customer is Eligible"), 
	CUSTOMER_INELIGIBLE("Customer is not Eligibile"), 
	INVALID_ACCOUNT_NUMBER("Invalid Account Number");

	public String description;

	private EligibilityEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
