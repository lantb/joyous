package com.collin.joyous.web.tag;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import com.collin.joyous.web.entity.TResource;
import com.collin.joyous.web.service.ITResourceService;
import com.collin.joyous.web.util.JsonUtil;
import com.collin.joyous.web.util.SpringUtil;
import com.collin.joyous.web.vo.TreeVO;
public class TreeTag extends TagBase implements ITag {

	private String width;
	
	private ITResourceService resourceService;
	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		
		this.write(buildTag());
		return EVAL_PAGE;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String buildTag() {
		StringBuffer outStr = new StringBuffer();
		outStr
			.append("	<ul id=\""+this.id+"\" class=\"ztree\" style=\"width:"+this.width+"; overflow:auto;\"></ul>")
			.append("<script type=\"text/javascript\">")
			.append("	var zTreeObj"+this.id+",")
			.append("	setting"+this.id+" = {")
					
			.append(" 	};");
		outStr.append(" var	zTreeNodes"+this.id+" = [")
			.append(JsonUtil.getJsonStr(getTreeVO()))
			.append("	];")
			
			.append("	$(document).ready(function(){")
			.append("		zTreeObj"+this.id+" = $.fn.zTree.init($(\"#"+this.id+"\"), setting"+this.id+", zTreeNodes"+this.id+");")
			.append("	});");
		outStr.append(getClickNodeFunction());
		outStr.append("</script>  ")
		;
		return outStr.toString();
	}

	public Object getTreeVO() {
		String rootId = "1";
		List<TResource> list = getTreeList(rootId);
		int lowLevel = 0;
		Map<Integer,Map<String,TreeVO>> map = new HashMap<Integer,Map<String,TreeVO>>();
		
		lowLevel = bulidTreeLevelMap(list, lowLevel, map);
		
		for (int i = lowLevel; i > 0; i--) {
			Map<String,TreeVO> m = map.get(i);	
			for (Entry<String,TreeVO> e : m.entrySet()) {
				TreeVO t = e.getValue();
				Map<String,TreeVO> mm = map.get(i-1);
				TreeVO tt = mm.get(t.getParent());
				tt.getChildren().add(t);
				
				Collections.sort(tt.getChildren(),getComparator());
			}
		}
		Object obj = map.get(0).get(rootId);
		return obj;
	}

	public int bulidTreeLevelMap(List<TResource> list, int lowLevel,
			Map<Integer, Map<String, TreeVO>> map) {
		for (TResource rr : list) {
			if(rr == null){
				continue;
			}	
			int cLevel = rr.getcLevel();
			if(cLevel > lowLevel){//取得最低的级别
				lowLevel = cLevel;
			}
			TreeVO t = new TreeVO();
			String name = rr.getcName();
			String url = rr.getcUrl();
			String id = rr.getcId();
			t.setName(name);
			t.setParent(rr.getcParentId());
			t.setSeq(rr.getcSeq());
			t.setId(id);
			t.setClick("clickNode('"+url+"');");
			if(map.containsKey(cLevel)){
				Map<String,TreeVO> m = map.get(cLevel);
				m.put(id, t);
			}else{
				Map<String,TreeVO> m = new HashMap<String,TreeVO>();
				m.put(id, t);
				map.put(cLevel, m);
			}
		}
		return lowLevel;
	}

	public Comparator<TreeVO> getComparator() {
		Comparator<TreeVO> comparator = new Comparator<TreeVO>() {
            public int compare(TreeVO s1, TreeVO s2) {
                if (s1.getSeq() != s2.getSeq()) {
                    return s1.getSeq() - s2.getSeq();
                } else{
                	return 0;
                }
            }
        };
		return comparator;
	}

	public List<TResource> getTreeList(String rootId) {
		TResource r = new TResource();
		
		r.setcId(rootId);
		resourceService = SpringUtil.getBean(ITResourceService.class);
		List<TResource> list = resourceService.selectTResource(r);
		return list;
	}

	private StringBuffer getClickNodeFunction() {
		StringBuffer sb = new StringBuffer();
		sb.append("function clickNode(url){")
		.append("if(url=='null' || url == null || url == ''){return;}")
		.append(" parent.index(appRoot+'/'+url); ")
		.append("}");
		return sb;
	}
}
