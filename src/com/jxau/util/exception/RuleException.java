package com.jxau.util.exception;

//违反业务逻辑的异常
public class RuleException extends RuntimeException{
	//传递过来异常的原因
	public RuleException(String message){
		super(message);
	}
}
