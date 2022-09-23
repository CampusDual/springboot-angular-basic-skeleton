package com.example.demo.rest.response;

import java.io.Serializable;

public class DataSourceRESTResponse<E> implements Serializable{
	private static final long serialVersionUID = -4203868639473444830L;
	public enum Code {
		OK, WARNING, KO;
	}

	private Code responseCode;
	private String responseMessage;
	private E data;
	private Integer totalElements;
	
	public DataSourceRESTResponse() {
		super();
	}
	
	public DataSourceRESTResponse(Integer totalElements, E data) {
		this.totalElements = totalElements;
		this.data= data;
	}
	
	public Integer getTotalElements() {
		return totalElements;
	}
	
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
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
