package com.collin.joyous.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public abstract class TagBase extends BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String name;
	
	public abstract String buildTag();
	
	public void write(String outStr) throws JspException{
		JspWriter out = this.pageContext.getOut();
        try {
            out.print(outStr);
        } catch (IOException e) {
        	throw new JspException(e.getMessage());
        }
        this.release();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
