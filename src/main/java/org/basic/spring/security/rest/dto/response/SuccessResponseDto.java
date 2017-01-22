package org.basic.spring.security.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "status", "data", "message" })
public class SuccessResponseDto<T> extends AbstractResponseDto {

	private T data;

	public SuccessResponseDto() {}

	public SuccessResponseDto(T data) {
		this();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public SuccessResponseDto<T> body(T data) {
		this.data = data;
		return this;
	}
}
