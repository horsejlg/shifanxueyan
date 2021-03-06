package cn.qlt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.qlt.utils.DomainObject;

@Entity
@Table(name="assistant")
public class Assistant extends DomainObject<Assistant>{
	
	private static final long serialVersionUID = -2097552650372593257L;

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
	
	/**
	 * 身份证
	 */
	@Column(name="idcode", length=18)
	private String idCode;
	
	/**
	 * 政治面貌
	 */
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="politics",nullable=true)
	private Dict politics;
	
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
	 * 性别
	 */
	@Column(name="sex", length=1)
	private String sex;
	
	/**
	 * 奖项
	 */
	@Transient
	private List<Awards> awards = new ArrayList<Awards>();
	
	/**
	 * 研究方向和成果
	 */
	@Column(name="researchResults", length=2000)
	private String researchResults;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="update_time")
	private Date updateTime = new Date();
	
	
	/**
	 * 管理的年级
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinTable(
            name = "ass_grade",
            joinColumns = @JoinColumn(name = "ass_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id")
    )
	private Set<Dict> grades  = new HashSet<Dict>();
	
	/**
	 * 管理的班级
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinTable(
            name = "ass_classess",
            joinColumns = @JoinColumn(name = "ass_id"),
            inverseJoinColumns = @JoinColumn(name = "classes_id")
    )
	private Set<ClassTeam> classess = new HashSet<ClassTeam>();
	
	/**
	 * 辅导员助理
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinTable(
            name = "assistant_helper",
            joinColumns = @JoinColumn(name = "ass_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private Set<User> helpers = new HashSet<User>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
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

	public String getEconomy() {
		return economy;
	}

	public void setEconomy(String economy) {
		this.economy = economy;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Dict getPolitics() {
		return politics;
	}

	public void setPolitics(Dict politics) {
		this.politics = politics;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Awards> getAwards() {
		return awards;
	}

	public void setAwards(List<Awards> awards) {
		this.awards = awards;
	}

	public String getResearchResults() {
		return researchResults;
	}

	public void setResearchResults(String researchResults) {
		this.researchResults = researchResults;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<Dict> getGrades() {
		return grades;
	}

	public void setGrades(Set<Dict> grades) {
		this.grades = grades;
	}

	public Set<ClassTeam> getClassess() {
		return classess;
	}

	public void setClassess(Set<ClassTeam> classess) {
		this.classess = classess;
	}

	public Set<User> getHelpers() {
		return helpers;
	}

	public void setHelpers(Set<User> helpers) {
		this.helpers = helpers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Assistant [id=" + id + ", user=" + user + ", birthday=" + birthday + ", nation=" + nation
				+ ", birthplace=" + birthplace + ", address=" + address + ", economy=" + economy + ", idCode=" + idCode
				+ ", politics=" + politics + ", phone=" + phone + ", qq=" + qq + ", sex=" + sex + ", awards=" + awards
				+ ", researchResults=" + researchResults + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", grades=" + grades + ", classess=" + classess + ", helpers=" + helpers + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assistant other = (Assistant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
