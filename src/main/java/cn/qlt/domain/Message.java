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


	public Message(User froms, User to, int state, String content) {
		super();
		this.froms = froms;
		this.tos = to;
		this.state = state;
		this.content = content;
	}


	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="from_id",nullable=false)
	private User froms;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="to_id",nullable=false)
	private User tos;
	
	
	@Column(name="state")
	private int state = 0;
	
	
	@Column(name="content",length=4096)
	private String content;


	public User getFroms() {
		return froms;
	}


	public void setFroms(User froms) {
		this.froms = froms;
	}


	public User getTos() {
		return tos;
	}


	public void setTos(User to) {
		this.tos = to;
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
