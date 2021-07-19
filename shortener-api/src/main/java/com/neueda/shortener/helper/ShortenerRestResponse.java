package com.neueda.shortener.helper;

import org.springframework.http.HttpStatus;

public class ShortenerRestResponse<E> {
	private HttpStatus statusCode;
	private String responseMessage;
	private E responsePayload;
	
	public ShortenerRestResponse(HttpStatus statusCode, String responseMessage, E responsePayload) {
		this.statusCode = statusCode;
		this.responseMessage = responseMessage;
		this.responsePayload = responsePayload;
	}

	public ShortenerRestResponse() {
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public E getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(E responsePayload) {
		this.responsePayload = responsePayload;
	}

	@Override
	public String toString() {
		return "ShortenerRestResponse [statusCode=" + statusCode + ", responseMessage=" + responseMessage
				+ ", responsePayload=" + responsePayload + "]";
	}
	
}

