package com.microfocus.ring2parkms.web.services;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class UserSearchCriteria {

	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public boolean isValid() {
		return (StringUtils.hasText(searchText));
	}

	public boolean validate(Errors errors) {
		if (StringUtils.hasText(searchText)) {
			// nothing to do
		}
		return errors.hasErrors();
	}

}
