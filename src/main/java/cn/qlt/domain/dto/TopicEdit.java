package cn.qlt.domain.dto;

import cn.qlt.domain.Topic;

public class TopicEdit {

	private Topic topic;
	
	// 0=无修改权限， 1=参与人权限， 2=作者权限
	private int permissions=0;
	

	public TopicEdit(Topic topic, int permissions) {
		super();
		this.topic = topic;
		this.permissions = permissions;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	
	
	
}
