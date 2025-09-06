package org.akee.prj25.assessment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PageResponseDto<T> {

	private List<T> data;

	private int page;

	private int size;

	@JsonProperty("total_elements")
	private long totalElements;

	@JsonProperty("total_pages")
	private int totalPages;

	public PageResponseDto(List<T> data, int page, int size, long totalElements, int totalPages) {
		this.data = data;
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

}