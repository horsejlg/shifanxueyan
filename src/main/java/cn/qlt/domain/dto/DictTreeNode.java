package cn.qlt.domain.dto;

import java.util.List;

import cn.qlt.domain.Dict;

public class DictTreeNode {
	
	private String id;
	private String text;
	private List<DictTreeNode> children = null;
	
	
	public DictTreeNode(Dict dict) {
		super();
		id = dict.getCode();
		text = dict.getLabel();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<DictTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<DictTreeNode> children) {
		this.children = children;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DictTreeNode){
			return this.id.equals(((DictTreeNode)obj).id);
		}else{
			return false;
		}
	}

}
