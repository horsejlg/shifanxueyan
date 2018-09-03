package cn.qlt.domain.dto;

import cn.qlt.domain.Dict;

public class DictNode {

	private String id;
	private String text;
	private String state;
	private String type;
	
	public DictNode(String id, String text, String type,String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.type = type;
	}

	public DictNode(Dict dict, String state) {
		super();
		id = dict.getCode();
		text = dict.getLabel();
		type = dict.getType();
		this.state = state;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
