package com.collin.joyous.web.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeVO {

	private String name;
	private String url;
	private List<TreeVO> children = new ArrayList<TreeVO>();
	private String open;
	private String target;
	private String parent;
	private String id;
	private int seq;
	private String click;
	
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<TreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}
}
