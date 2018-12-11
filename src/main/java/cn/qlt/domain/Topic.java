package cn.qlt.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.qlt.utils.ManagedIdentityDomainObject;
/**
 * 
 * 参与人员和可见人员不要在转json中出现
 * 人员操作和专题操作应该分开处理。
 *
 */
@Entity
@Table(name="topic")
@JsonIgnoreProperties({"participants","visibleUsers"})
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
	 * 是否作业类型主题 0:否,1:是 
	 * 是作业类型的,每个参与人员需要交一篇作业
	 * 否的话,参与人员可以共同编辑专题内容
	 */
	@Column(name="homework",nullable=true)
	private int homework = 0;
	
	/**
	 * 地点
	 */
	@Column(name="location",nullable=true, length=255)
	private String location;
	
	/**
	 * 参与人员
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(
            name = "topic_user",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private Set<User> participants;
	
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
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(
            name = "topic_visible_user",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private Set<User> visibleUsers;
	
	@Column(name="update_time")
	private Date updateTime = new Date();
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="work_type",nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Dict type;
	
	public Dict getType() {
		return type;
	}

	public void setType(Dict type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
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

	public int getHomework() {
		return homework;
	}

	public void setHomework(int homework) {
		this.homework = homework;
	}

	public Set<User> getVisibleUsers() {
		return visibleUsers;
	}

	public void setVisibleUsers(Set<User> visibleUsers) {
		this.visibleUsers = visibleUsers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Topic [createTime=" + createTime + ", title=" + title + ", promiseTime=" + promiseTime + ", endTime="
				+ endTime + ", publish=" + publish + ", homework=" + homework + ", location=" + location
				+ ", participants=" + participants + ", content=" + content + ", remark=" + remark + ", author="
				+ author + ", visibleUsers=" + visibleUsers + ", updateTime=" + updateTime + "]";
	}

	
}
