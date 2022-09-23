package com.example.demo.entity.enums;

public enum ResponseCodeEnum {
	OK("OK"), WARNING("WARNING"), KO("KO");
	private String value;
	private ResponseCodeEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
