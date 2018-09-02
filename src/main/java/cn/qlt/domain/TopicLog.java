package cn.qlt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="topicLog")
public class TopicLog extends ManagedIdentityDomainObject<TopicLog>{
	
	private static final long serialVersionUID = -3411280406639061149L;

	@Column(name="topicId", nullable=true, length=256)
	private String topicId;
	
	@Column(name="opuserId", nullable=true, length=256)
	private String opuserId;
	
	@Column(name="opuserName", nullable=true, length=256)
	private String opuserName;
	
	@Type(type="text")
	@Column(name="content")
	private String content;
	
	@Column(name="optime", nullable=false)
	private Date optime = new Date();

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getOpuserId() {
		return opuserId;
	}

	public void setOpuserId(String opuserId) {
		this.opuserId = opuserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public String getOpuserName() {
		return opuserName;
	}

	public void setOpuserName(String opuserName) {
		this.opuserName = opuserName;
	}
	
	public TopicLog() {
		super();
	}

	public TopicLog(String topicId, String opuserId, String opuserName, String content, Date optime) {
		super();
		this.topicId = topicId;
		this.opuserId = opuserId;
		this.opuserName = opuserName;
		this.content = content;
		this.optime = optime;
	}

	@Override
	public String toString() {
		return "TopicLog [topicId=" + topicId + ", opuserId=" + opuserId + ", opuserName=" + opuserName + ", content="
				+ content + ", optime=" + optime + "]";
	}
	
	
	
}
