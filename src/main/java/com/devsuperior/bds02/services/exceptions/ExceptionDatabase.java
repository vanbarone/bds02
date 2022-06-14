package com.devsuperior.bds02.services.exceptions;

public class ExceptionDatabase extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExceptionDatabase(String msg) {
		super(msg);
	}
}
