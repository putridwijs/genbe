package com.putri.genbe.dto;

public class ResponseLengkap {
	private String status;
	private String message;
	private PersonBioPendidikanDto data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PersonBioPendidikanDto getData() {
		return data;
	}

	public void setData(PersonBioPendidikanDto data) {
		this.data = data;
	}

}
