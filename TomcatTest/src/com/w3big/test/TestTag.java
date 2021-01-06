package com.w3big.test;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestTag extends SimpleTagSupport{
	
	private String hello;
	
	public void setHello(String hello) {
		this.hello = hello;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("test");
	}
}
