package com.example.demo.rest.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QuerySortPaginationRequest implements Serializable {

	/**
	 * Query para filtrar la consulta.
	 */
	private String query;

	/**
	 * El número de página.
	 */
	private Integer pageIndex;

	/**
	 * El número de elementos de la página.
	 */
	private Integer pageSize;

	/**
	 * La dirección de ordenación de la columna:
	 * <ul>
	 * <li>asc</li>
	 * <li>desc</li>
	 * </ul>
	 */
	private String sortDirection;

	/**
	 * El nombre de uno de los campos de una clase.
	 */
	private String sortColumn;
	
	public QuerySortPaginationRequest() {
		super();
	}
	
	public QuerySortPaginationRequest(Integer pageIndex, Integer pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public QuerySortPaginationRequest(String query, Integer pageIndex, Integer pageSize) {
		this(pageIndex, pageSize);
		this.query = query;
	}

	public QuerySortPaginationRequest(String query, Integer pageIndex, Integer pageSize, String sortDirection,
			String sortColumn) {
		this(query, pageIndex, pageSize);
		this.sortDirection = sortDirection;
		this.sortColumn = sortColumn;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
}
