package com.tyss.test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	
	private String message;
	private Object data;
	private Boolean error;

}
