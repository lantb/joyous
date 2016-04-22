package com.collin.joyous.web.tag;

import javax.servlet.jsp.JspException;

public interface ITag {

	int doAfterBody() throws JspException;

	int doEndTag() throws JspException;
}
