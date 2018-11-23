package cn.qlt.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="message")
public class Message extends ManagedIdentityDomainObject<Message>{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6235342209438275852L;


	public Message() {
		super();
	}


	public Message(User from, User to, int state, String content) {
		super();
		this.from = from;
		this.to = to;
		this.state = state;
		this.content = content;
	}


	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="from_id",nullable=false)
	private User from;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="to_id",nullable=false)
	private User to;
	
	
	@Column(name="state")
	private int state = 0;
	
	
	@Column(name="content",length=4096)
	private String content;


	public User getFrom() {
		return from;
	}


	public void setFrom(User from) {
		this.from = from;
	}


	public User getTo() {
		return to;
	}


	public void setTo(User to) {
		this.to = to;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

}
