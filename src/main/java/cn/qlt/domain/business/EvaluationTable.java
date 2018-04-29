package cn.qlt.domain.business;

import java.util.Date;
import java.util.List;

import cn.qlt.utils.ManagedIdentityDomainObject;

public class EvaluationTable extends ManagedIdentityDomainObject<EvaluationTable>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 396354268455217491L;

	private String stuNo;
	
	private String stuName;
	
	/**
	 * 政治面貌
	 */
	private String politicalStatus;
	
	/**
	 * 专业
	 */
	private String specialty;
	
	/**
	 * 年级
	 */
	private String stugrade;
	
	/**
	 * 班级
	 */
	private String stuclass;
	
	/**
	 * 职务
	 */
	private String position;
	
	/**
	 * 评测
	 */
	private List<EvaluationItem> evaluationItems;
	
	/**
	 * 0,未提交;1,已提交;
	 */
	private int status=0;
	
	private Date createTime = new Date();
	
	/**
	 * 1,班级审核通过;0班级审核未通过
	 */
	private int cstatus=0;
	
	/**
	 * 班级审核时间
	 */
	private Date cstatusTime = new Date();
	
	/**
	 * 1管理员审核通过;0管理员审核未通过
	 */
	private int gstatus=0;
	
	/**
	 * 管理员审核时间
	 */
	private Date gstatusTime = new Date();

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getStugrade() {
		return stugrade;
	}

	public void setStugrade(String stugrade) {
		this.stugrade = stugrade;
	}

	public String getStuclass() {
		return stuclass;
	}

	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<EvaluationItem> getEvaluationItems() {
		return evaluationItems;
	}

	public void setEvaluationItems(List<EvaluationItem> evaluationItems) {
		this.evaluationItems = evaluationItems;
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

	public int getCstatus() {
		return cstatus;
	}

	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}

	public Date getCstatusTime() {
		return cstatusTime;
	}

	public void setCstatusTime(Date cstatusTime) {
		this.cstatusTime = cstatusTime;
	}

	public int getGstatus() {
		return gstatus;
	}

	public void setGstatus(int gstatus) {
		this.gstatus = gstatus;
	}

	public Date getGstatusTime() {
		return gstatusTime;
	}

	public void setGstatusTime(Date gstatusTime) {
		this.gstatusTime = gstatusTime;
	}
	
}
