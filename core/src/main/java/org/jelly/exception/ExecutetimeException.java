package org.jelly.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * 执行时异常, 它是一个运行时异常。<br>
 * jelly throws ExecutetimeException 通常是基于以下两种情况：<br>
 * 1、逻辑处理<br>
 * 2、将 CheckedException 转换为 UncheckedException, 交由调用者来决定是否捕捉异常
 * @author Lychie Fan
 * @since 1.0.0
 */
public class ExecutetimeException extends RuntimeException {

	private Throwable throwable;
	private static final long serialVersionUID = 6635246863882893402L;
	
	public ExecutetimeException(String message){
		throwable = new RuntimeException(message);
	}

	public ExecutetimeException(Throwable e){
		this.throwable = e;
	}
	
	public ExecutetimeException(Throwable e, String message){
		this.throwable = e;
		System.err.println("info : " + message);
		try {
			Thread.sleep(10);
		} catch (InterruptedException ie) { /* 忽略异常 */ }
	}

	/*
	 * (non-Javadoc)
	 * 重写 RuntimeException 方法, 抛出真实的异常对象的信息, 
	 * 向外部应用隐藏 ExecutetimeException
	 */
	
	@Override
	public String getMessage() {
		return throwable.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return throwable.getLocalizedMessage();
	}

	@Override
	public Throwable getCause() {
		return throwable.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return throwable.initCause(cause);
	}

	@Override
	public String toString() {
		return throwable.toString();
	}

	@Override
	public void printStackTrace() {
		throwable.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		throwable.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		throwable.printStackTrace(s);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return throwable.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		throwable.setStackTrace(stackTrace);
	}
}