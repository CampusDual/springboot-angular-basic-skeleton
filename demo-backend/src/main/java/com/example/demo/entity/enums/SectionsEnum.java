package com.example.demo.entity.enums;

public enum SectionsEnum {
	CONTACTS("CONTACTS");
	private String valor;
	private SectionsEnum(String valor) {
		this.valor = valor;
	}
	public String getValor() {
		return valor;
	}
}
