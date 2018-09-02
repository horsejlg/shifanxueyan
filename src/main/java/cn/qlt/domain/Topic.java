package cn.qlt.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import cn.qlt.utils.DomainObject;
import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="topic")
public class Topic extends ManagedIdentityDomainObject<Topic>{

	private static final long serialVersionUID = -8141225990665755267L;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	/**
	 * 标题
	 */
	@Column(name="title", length=255)
	private String title;
	
	/**
	 * 约定时间
	 */
	@Column(name="promiseTime")
	private Date promiseTime;
	
	/**
	 * 截止时间
	 */
	@Column(name="endTime",nullable=true)
	private Date endTime;
	
	/**
	 * 是否发布 0:否,1:是
	 */
	@Column(name="publish",nullable=true)
	private int publish = 0;
	
	/**
	 * 地点
	 */
	@Column(name="location",nullable=true, length=255)
	private String location;
	
	/**
	 * 参与人员
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "topic_user",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> participants;
	
	/**
	 * 内容
	 */
	@Column(name="content", nullable=true)
	@Type(type="text")
	private String content;
	
	/**
	 * 备注
	 */
	@Column(name="remark", length=255)
	private String remark;
	
	/**
	 * 作者
	 */
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="author_id",nullable=true)
	private User author;
	
	/**
	 * 可见人员
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "topic_visible_user",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> visibleUsers;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPromiseTime() {
		return promiseTime;
	}

	public void setPromiseTime(Date promiseTime) {
		this.promiseTime = promiseTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getPublish() {
		return publish;
	}

	public void setPublish(int publish) {
		this.publish = publish;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<User> getVisibleUsers() {
		return visibleUsers;
	}

	public void setVisibleUsers(List<User> visibleUsers) {
		this.visibleUsers = visibleUsers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Topic [createTime=" + createTime + ", title=" + title + ", promiseTime=" + promiseTime + ", endTime="
				+ endTime + ", publish=" + publish + ", location=" + location + ", participants=" + participants
				+ ", content=" + content + ", remark=" + remark + ", author=" + author + ", visibleUsers="
				+ visibleUsers + "]";
	}
	
}
