package cn.qlt.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="evaluation")
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("0")
public class Evaluation extends ManagedIdentityDomainObject<Evaluation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -567500785370321031L;
	
	public static String showName(String type){
		switch (type) {
		case "Evaluation1":
			return "齐鲁师范学院 教师教育学院学生综合素质测评";
		default:
			return "综合素质测评";
		}
	}
	
	@Column(name="title", nullable=false, length=256)
	private String title;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="author",nullable=false)
	private User author;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="years",nullable=false)
	private Dict year;
	
	@Column(name="sumSorce")
	private float sumSorce;
	
	@Column(name="gs_index")
	private int gsIndex;
	
	/**
	 * 0,个人未提交;1,个人已提交;2,班委审核;3,辅导员审核;
	 */
	@Column(name="status",nullable=false)
	private int status = 0;
	
	@Column(name="createTime")
	private Date createTime = new Date();

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Dict getYear() {
		return year;
	}

	public void setYear(Dict year) {
		this.year = year;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public float getSumSorce() {
		return sumSorce;
	}

	public void setSumSorce(float sumSorce) {
		this.sumSorce = sumSorce;
	}

	public int getGsIndex() {
		return gsIndex;
	}

	public void setGsIndex(int gsIndex) {
		this.gsIndex = gsIndex;
	}
	
	
}
