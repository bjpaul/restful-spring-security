package org.basic.spring.security.rest.util;

import org.basic.spring.security.rest.dto.response.ErrorResponseDto;
import org.basic.spring.security.rest.dto.response.SuccessResponseDto;

public class ResponseUtil {

	public static SuccessResponseDto<Object> success(){
		return new SuccessResponseDto<Object>();
	}
	
	public static ErrorResponseDto error(){
		return new ErrorResponseDto();
	}
}
