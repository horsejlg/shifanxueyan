package cn.qlt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评测表
 * @author pengno5
 *
 */
@Entity
@Table(name="evaluation2")
@DiscriminatorValue("2")
public class Evaluation2 extends Evaluation{

	/**
	 * 
	 */
	private static final long serialVersionUID = -567500785370321031L;
	
	/**
	 * 基础评测总分
	 */
	@Column(name="baseEvaluationSorce")
	private float baseEvaluationSorce;
	
	@Column(name="baseEvaluationLevel")
	private int baseEvaluationLevel;
	
	@Column(name="baseSource1")
	private float baseSource1;
	
	@Column(name="baseContent1", length=2048)
	private String baseContent1;
	
	@Column(name="baseRemark", length=1024)
	private String baseRemark1;
	
	@Column(name="baseSource2")
	private float baseSource2;
	
	@Column(name="baseContent2", length=2048)
	private String baseContent2;
	
	@Column(name="baseRemark2", length=1024)
	private String baseRemark2;
	
	@Column(name="baseSource3")
	private float baseSource3;
	
	@Column(name="baseContent3", length=2048)
	private String baseContent3;

	@Column(name="baseRemark3", length=1024)
	private String baseRemark3;
	
	@Column(name="baseSource4")
	private float baseSource4;
	
	@Column(name="baseContent4", length=2048)
	private String baseContent4;

	@Column(name="baseRemark4", length=1024)
	private String baseRemark4;
	
	@Column(name="baseSource5")
	private float baseSource5;
	
	@Column(name="baseContent5", length=2048)
	private String baseContent5;

	@Column(name="baseRemark5", length=1024)
	private String baseRemark5;
	
	@Column(name="vetoSource")
	private boolean vetoSource = false;
	
	@Column(name="vetoContent", length=2048)
	private String vetoContent;

	@Column(name="vetoRemark", length=1024)
	private String vetoRemark;

	/**
	 * 发展评测总分(开发环境要屏蔽columnDefinition)
	 */
	@Column(name="growEvaluationSorce")//, columnDefinition="float(10,2) default '0.00'")
	private float growEvaluationSorce;
	
	@Column(name="growEvaluation")
	private String growEvaluation;
	
	@Column(name="growSource1")
	private float growSource1;
	
	@Column(name="growContent1", length=2048)
	private String growContent1;

	@Column(name="growRemark1", length=1024)
	private String growRemark1;
	
	@Column(name="growSource2")
	private float growSource2;
	
	@Column(name="growContent2", length=2048)
	private String growContent2;

	@Column(name="growRemark2", length=1024)
	private String growRemark2;
	
	@Column(name="growSource3")
	private float growSource3;
	
	@Column(name="growContent3", length=2048)
	private String growContent3;

	@Column(name="growRemark3", length=1024)
	private String growRemark3;
	
	@Column(name="growSource4")
	private float growSource4;
	
	@Column(name="growContent4", length=2048)
	private String growContent4;

	@Column(name="growRemark4", length=1024)
	private String growRemark4;
	
	@Column(name="growSource5")
	private float growSource5;
	
	@Column(name="growContent5", length=2048)
	private String growContent5;

	@Column(name="growRemark5", length=1024)
	private String growRemark5;
	
	
	@Column(name="growFull")
	private boolean growFull = false;
	/**
	 * 学习成绩
	 */
	@Column(name="studySorce")
	private float studySorce = 0;
	
	@Column(name="studyContent", length=2048)
	private String studyContent;
	
	@Column(name="studyRemark", length=1024)
	private String studyRemark;
	
	@Column(name="otherSource")
	private float otherSource;
	
	@Column(name="otherContent", length=2048)
	private String otherContent;
	
	@Column(name="otherRemark", length=1024)
	private String otherRemark;
	
	@Column(name="studySum")
	private float studySum;
	
	/**
	 * 自我评价
	 */
	@Column(name="self_evaluation",length=2000)
	private String selfEvaluation;
	
	@Column(name="selfEvaluationDate")
	private Date selfEvaluationDate;
	
	/**
	 * 小组评价
	 */
	@Column(name="groupEvaluation",length=2000)
	private String groupEvaluation;
	
	@Column(name="groupEvaluationDate")
	private Date groupEvaluationDate;


	public float getBaseEvaluationLevel() {
		return baseEvaluationLevel;
	}

	public void setBaseEvaluationLevel(int baseEvaluationLevel) {
		this.baseEvaluationLevel = baseEvaluationLevel;
	}

	public float getBaseEvaluationSorce() {
		return baseEvaluationSorce;
	}

	public void setBaseEvaluationSorce(float baseEvaluationSorce) {
		this.baseEvaluationSorce = baseEvaluationSorce;
	}

	public float getGrowEvaluationSorce() {
		return growEvaluationSorce;
	}

	public void setGrowEvaluationSorce(float growEvaluationSorce) {
		this.growEvaluationSorce = growEvaluationSorce;
	}

	public float getStudySorce() {
		return studySorce;
	}

	public void setStudySorce(float studySorce) {
		this.studySorce = studySorce;
	}

	public String getStudyContent() {
		return studyContent;
	}

	public void setStudyContent(String studyContent) {
		this.studyContent = studyContent;
	}

	public String getStudyRemark() {
		return studyRemark;
	}

	public void setStudyRemark(String studyRemark) {
		this.studyRemark = studyRemark;
	}

	public String getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
	}

	public Date getSelfEvaluationDate() {
		return selfEvaluationDate;
	}

	public void setSelfEvaluationDate(Date selfEvaluationDate) {
		this.selfEvaluationDate = selfEvaluationDate;
	}

	public String getGroupEvaluation() {
		return groupEvaluation;
	}

	public void setGroupEvaluation(String groupEvaluation) {
		this.groupEvaluation = groupEvaluation;
	}

	public Date getGroupEvaluationDate() {
		return groupEvaluationDate;
	}

	public void setGroupEvaluationDate(Date groupEvaluationDate) {
		this.groupEvaluationDate = groupEvaluationDate;
	}

	public float getBaseSource1() {
		return baseSource1;
	}

	public void setBaseSource1(float baseSource1) {
		this.baseSource1 = baseSource1;
	}

	public String getBaseContent1() {
		return baseContent1;
	}

	public void setBaseContent1(String baseContent1) {
		this.baseContent1 = baseContent1;
	}

	public float getBaseSource2() {
		return baseSource2;
	}

	public void setBaseSource2(float baseSource2) {
		this.baseSource2 = baseSource2;
	}

	public String getBaseContent2() {
		return baseContent2;
	}

	public void setBaseContent2(String baseContent2) {
		this.baseContent2 = baseContent2;
	}

	public float getBaseSource3() {
		return baseSource3;
	}

	public void setBaseSource3(float baseSource3) {
		this.baseSource3 = baseSource3;
	}

	public String getBaseContent3() {
		return baseContent3;
	}

	public void setBaseContent3(String baseContent3) {
		this.baseContent3 = baseContent3;
	}

	public float getBaseSource4() {
		return baseSource4;
	}

	public void setBaseSource4(float baseSource4) {
		this.baseSource4 = baseSource4;
	}

	public String getBaseContent4() {
		return baseContent4;
	}

	public void setBaseContent4(String baseContent4) {
		this.baseContent4 = baseContent4;
	}

	public float getBaseSource5() {
		return baseSource5;
	}

	public void setBaseSource5(float baseSource5) {
		this.baseSource5 = baseSource5;
	}

	public String getBaseContent5() {
		return baseContent5;
	}

	public void setBaseContent5(String baseContent5) {
		this.baseContent5 = baseContent5;
	}

	public String getGrowContent1() {
		return growContent1;
	}

	public void setGrowContent1(String growContent1) {
		this.growContent1 = growContent1;
	}

	public String getGrowContent2() {
		return growContent2;
	}

	public void setGrowContent2(String growContent2) {
		this.growContent2 = growContent2;
	}

	public String getGrowContent3() {
		return growContent3;
	}

	public void setGrowContent3(String growContent3) {
		this.growContent3 = growContent3;
	}

	public String getGrowContent4() {
		return growContent4;
	}

	public void setGrowContent4(String growContent4) {
		this.growContent4 = growContent4;
	}

	public String getGrowEvaluation() {
		return growEvaluation;
	}

	public void setGrowEvaluation(String growEvaluation) {
		this.growEvaluation = growEvaluation;
	}


	public float getGrowSource1() {
		return growSource1;
	}

	public void setGrowSource1(float growSource1) {
		this.growSource1 = growSource1;
	}

	public float getGrowSource2() {
		return growSource2;
	}

	public void setGrowSource2(float growSource2) {
		this.growSource2 = growSource2;
	}

	public float getGrowSource3() {
		return growSource3;
	}

	public void setGrowSource3(float growSource3) {
		this.growSource3 = growSource3;
	}

	public float getGrowSource4() {
		return growSource4;
	}

	public void setGrowSource4(float growSource4) {
		this.growSource4 = growSource4;
	}

	public float getGrowSource5() {
		return growSource5;
	}

	public void setGrowSource5(float growSource5) {
		this.growSource5 = growSource5;
	}

	public String getGrowContent5() {
		return growContent5;
	}

	public void setGrowContent5(String growContent5) {
		this.growContent5 = growContent5;
	}

	public String getBaseRemark1() {
		return baseRemark1;
	}

	public void setBaseRemark1(String baseRemark1) {
		this.baseRemark1 = baseRemark1;
	}

	public String getBaseRemark2() {
		return baseRemark2;
	}

	public void setBaseRemark2(String baseRemark2) {
		this.baseRemark2 = baseRemark2;
	}

	public String getBaseRemark3() {
		return baseRemark3;
	}

	public void setBaseRemark3(String baseRemark3) {
		this.baseRemark3 = baseRemark3;
	}

	public String getBaseRemark4() {
		return baseRemark4;
	}

	public void setBaseRemark4(String baseRemark4) {
		this.baseRemark4 = baseRemark4;
	}

	public String getBaseRemark5() {
		return baseRemark5;
	}

	public void setBaseRemark5(String baseRemark5) {
		this.baseRemark5 = baseRemark5;
	}


	public String getGrowRemark1() {
		return growRemark1;
	}

	public void setGrowRemark1(String growRemark1) {
		this.growRemark1 = growRemark1;
	}

	public String getGrowRemark2() {
		return growRemark2;
	}

	public void setGrowRemark2(String growRemark2) {
		this.growRemark2 = growRemark2;
	}

	public String getGrowRemark3() {
		return growRemark3;
	}

	public void setGrowRemark3(String growRemark3) {
		this.growRemark3 = growRemark3;
	}

	public String getGrowRemark4() {
		return growRemark4;
	}

	public void setGrowRemark4(String growRemark4) {
		this.growRemark4 = growRemark4;
	}

	public String getGrowRemark5() {
		return growRemark5;
	}

	public void setGrowRemark5(String growRemark5) {
		this.growRemark5 = growRemark5;
	}

	public float getOtherSource() {
		return otherSource;
	}

	public void setOtherSource(float otherSource) {
		this.otherSource = otherSource;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}

	public boolean isVetoSource() {
		return vetoSource;
	}

	public void setVetoSource(boolean vetoSource) {
		this.vetoSource = vetoSource;
	}

	public String getVetoContent() {
		return vetoContent;
	}

	public void setVetoContent(String vetoContent) {
		this.vetoContent = vetoContent;
	}

	public String getVetoRemark() {
		return vetoRemark;
	}

	public void setVetoRemark(String vetoRemark) {
		this.vetoRemark = vetoRemark;
	}

	public boolean isGrowFull() {
		return growFull;
	}

	public void setGrowFull(boolean growFull) {
		this.growFull = growFull;
	}

	public float getStudySum() {
		return studySum;
	}

	public void setStudySum(float studySum) {
		this.studySum = studySum;
	}
		
}
