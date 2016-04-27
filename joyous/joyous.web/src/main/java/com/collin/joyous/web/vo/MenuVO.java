package com.collin.joyous.web.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private List<MenuVO> childrenList = new ArrayList<MenuVO>();
	private Map<String, MenuVO> childrenMap = new HashMap<String, MenuVO>();//seq,treeVO
	private String open;
	private String target;
	private String parent;
	private String id;
	private int level;
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
	public List<MenuVO> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<MenuVO> childrenList) {
		this.childrenList = childrenList;
	}
	public Map<String, MenuVO> getChildrenMap() {
		return childrenMap;
	}
	public void setChildrenMap(Map<String, MenuVO> childrenMap) {
		this.childrenMap = childrenMap;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
