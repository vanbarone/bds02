package com.devsuperior.bds02.services.exceptions;

public class ExceptionEntityNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExceptionEntityNotFound(String msg) {
		super(msg);
	}
}
