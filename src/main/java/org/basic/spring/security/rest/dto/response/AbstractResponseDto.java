package org.basic.spring.security.rest.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponseDto {
	private String message;

	private int code = HttpStatus.OK.value();

	private String status = HttpStatus.OK.getReasonPhrase();

	public String getMessage() {
		return message;
	}

	public AbstractResponseDto message(String message) {
		this.message = message;
		return this;
	}


	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public ResponseEntity<AbstractResponseDto> send(HttpStatus status) {
		this.code = status.value();
		this.status = status.getReasonPhrase();
		return new ResponseEntity<AbstractResponseDto>(this, status);
	}
	
}
