package cn.qlt.domain.dto;

import cn.qlt.domain.Dict;

public class DictNodeAndCount extends DictNode {
	
	private long count;
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public DictNodeAndCount(String id, String text, String type, String state, long count) {
		super(id, text, type, state);
		this.count = count;
	}

	public DictNodeAndCount(Dict dict, String state, long count) {
		super(dict, state);
		this.count = count;
	}
	
}
