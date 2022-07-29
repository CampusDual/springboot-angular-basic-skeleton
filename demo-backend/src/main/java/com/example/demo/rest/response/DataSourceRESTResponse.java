package com.example.demo.rest.response;

public class DataSourceRESTResponse<E> extends RESTResponse<E> {
	private static final long serialVersionUID = -4203868639473444830L;

	private Integer totalElements;
	
	public DataSourceRESTResponse() {
		super();
	}
	
	public DataSourceRESTResponse(Integer totalElements, E data) {
		super(data);
		this.totalElements = totalElements;
	}
	
	public Integer getTotalElements() {
		return totalElements;
	}
	
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
}
