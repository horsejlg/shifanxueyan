package cn.qlt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="topicLog")
public class TopicLog extends ManagedIdentityDomainObject<TopicLog>{
	
	private static final long serialVersionUID = -3411280406639061149L;

	@Column(name="topicId", nullable=false, length=256)
	private String topicId;
	
	@Column(name="opuserId", nullable=false, length=256)
	private String opuserId;
	
	@Column(name="opuserName", nullable=false, length=256)
	private String opuserName;
	
	@Lob
	@Column(name="content",columnDefinition="TEXT", length=256)
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
	
}
