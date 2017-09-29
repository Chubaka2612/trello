package com.trello.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameter {

	private String name;
	private String value;

	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
