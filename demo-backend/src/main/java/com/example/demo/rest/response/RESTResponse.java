package com.example.demo.rest.response;

import java.io.Serializable;

public class RESTResponse<E> implements Serializable {
	private static final long serialVersionUID = -7921493144872181460L;

	public enum Code {
		OK, WARNING, KO;
	}

	private Code responseCode;
	private String responseMessage;
	private E data;

	public RESTResponse() {
		this.responseCode = Code.OK;
		this.responseMessage = "";
	}

	public RESTResponse(String responseMessage) {
		this.responseCode = Code.KO;
		this.responseMessage = responseMessage;
	}

	public RESTResponse(E data) {
		this();
		this.data = data;
	}

	public RESTResponse(Code responseCode, E data) {
		this(data);
		this.responseCode = responseCode;
	}

	public RESTResponse(Code responseCode, String responseMessage, E data) {
		this(responseCode, data);
		this.responseMessage = responseMessage;
	}

	public Code getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Code responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}
