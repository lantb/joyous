package com.collin.joyous.web.entity;

import java.io.Serializable;

public class TResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cId;
	private String cUrl;
	private String cName;
	private String cParentId;
	private int cIsParent;
	private int cLevel;
	private int cSeq;
	
	public int getcLevel() {
		return cLevel;
	}
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
	public int getcSeq() {
		return cSeq;
	}
	public void setcSeq(int cSeq) {
		this.cSeq = cSeq;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcUrl() {
		return cUrl;
	}
	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcParentId() {
		return cParentId;
	}
	public void setcParentId(String cParentId) {
		this.cParentId = cParentId;
	}
	public int getcIsParent() {
		return cIsParent;
	}
	public void setcIsParent(int cIsParent) {
		this.cIsParent = cIsParent;
	}

	

}
