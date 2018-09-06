package cn.qlt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.qlt.utils.DomainObject;

@Entity
@Table(name="students")
public class Student extends DomainObject<Student>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9179626703949343621L;

	@Id
	@Column(name="id", length=24, nullable=false, unique=true)
	private String id;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="id",nullable=true)
	private User user;
	
	/**
	 * 生日
	 */
	@Column(name="birthday")
	private Date birthday;
	
	/**
	 * 民族
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="nation",nullable=true)
	private Dict nation;
	
	/**
	 * 籍贯
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="birthplace",nullable=true)
	private Dict birthplace;
	
	/**
	 * 家庭住址
	 */
	@Column(name="address", length=255)
	private String address;
	
	
	@Column(name="economy", length=255)
	private String economy;
	
	public String getEconomy() {
		return economy;
	}

	public void setEconomy(String economy) {
		this.economy = economy;
	}

	/**
	 * 身份证
	 */
	@Column(name="idcode", length=18)
	private String idCode;
	
	/**
	 * 受助档次
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="grantee",nullable=true)
	private Dict grantee;
	
	/**
	 * 政治面貌
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="politics",nullable=true)
	private Dict politics;
	
	/**
	 * 班外职务
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="out_job",nullable=true)
	private Dict outJob;
	
	/**
	 * 参加的社团
	 */
	@Column(name="community", length=100)
	private String community;
	
	/**
	 * 宿舍号
	 */
	@Column(name="dorm_code", length=20)
	private String dormCode;
	
	/**
	 * 电话
	 */
	@Column(name="phone", length=13)
	private String phone;
	
	/**
	 * QQ/微信/微博
	 */
	@Column(name="qq", length=20)
	private String qq;
	
	/**
	 * 入党时间
	 */
	@Column(name="join_party")
	private Date joinParty;
	
	/**
	 * 入团时间
	 */
	@Column(name="join_us")
	private Date joinUs;
	
	/**
	 * 性别
	 */
	@Column(name="sex", length=1)
	private String sex;
	
	@Transient
	private List<Sociogram> sociograms = new ArrayList<Sociogram>();
	
	@Transient
	private List<Awards> awards = new ArrayList<Awards>();
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private ClassTeam classTeam;
	
	public ClassTeam getClassTeam() {
		return classTeam;
	}

	public void setClassTeam(ClassTeam classTeam) {
		this.classTeam = classTeam;
	}
	
	
	public User getUser() {
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Dict getNation() {
		return nation;
	}

	public void setNation(Dict nation) {
		this.nation = nation;
	}

	public Dict getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(Dict birthplace) {
		this.birthplace = birthplace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Dict getGrantee() {
		return grantee;
	}

	public void setGrantee(Dict grantee) {
		this.grantee = grantee;
	}

	public Dict getPolitics() {
		return politics;
	}

	public void setPolitics(Dict politics) {
		this.politics = politics;
	}

	public Dict getOutJob() {
		return outJob;
	}

	public void setOutJob(Dict outJob) {
		this.outJob = outJob;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDormCode() {
		return dormCode;
	}

	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getJoinParty() {
		return joinParty;
	}

	public void setJoinParty(Date joinParty) {
		this.joinParty = joinParty;
	}

	public Date getJoinUs() {
		return joinUs;
	}

	public void setJoinUs(Date joinUs) {
		this.joinUs = joinUs;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Sociogram> getSociograms() {
		return sociograms;
	}

	public void setSociograms(List<Sociogram> sociograms) {
		this.sociograms = sociograms;
	}

	public List<Awards> getAwards() {
		return awards;
	}

	public void setAwards(List<Awards> awards) {
		this.awards = awards;
	}
	
}
