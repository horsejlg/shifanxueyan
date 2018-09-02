package cn.qlt.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.qlt.utils.DomainObject;

/**
 * @author zp
 * 评论的回复
 */
@Entity
@Table(name="topicReply")
public class TopicReply extends DomainObject<TopicReply>{

	private static final long serialVersionUID = 5688111414759138696L;

	@Id
	@Column(name="id", length=24, nullable=false, unique=true)
	private String id;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="content", nullable=true)
	@Type(type="text")
	private String content;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="author_id",nullable=true)
	private User author;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	
}
