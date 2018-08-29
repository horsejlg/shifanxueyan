package cn.qlt.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * @author zp
 * 评论的回复
 */
@Entity
@Table(name="topicReply")
public class TopicReply extends ManagedIdentityDomainObject<TopicReply>{

	private static final long serialVersionUID = 5688111414759138696L;

	@Column(name="topicId", length=24, nullable=false)
	private String topicId;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="content", nullable=true)
	@Type(type="text")
	private String content;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="author_id",nullable=true)
	private User author;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "TopicReply [topicId=" + topicId + ", createTime=" + createTime + ", content=" + content + ", author="
				+ author + "]";
	}
	
}
