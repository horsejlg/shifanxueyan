package cn.qlt.domain.business;

import java.util.Date;

import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * 
 * 评价项
 * @author zp
 *
 */
public class EvaluationItem extends ManagedIdentityDomainObject<EvaluationItem>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2512278147989237338L;

	/**
	 * 对应评价表id
	 */
	private EvaluationTable evaluation;
	
	/**
	 * 评分
	 */
	private int score=0;
	
	/**
	 * 富文本内容
	 */
	private String content;
	
	/**
	 * 基础性,发展性,学习成绩,学生自我评价,管理员审核评价
	 */
	private String type;
	
	private int index;
	
	private Date createTime = new Date();
	
	private Date lastUpdateTime = new Date();

	public EvaluationTable getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationTable evaluation) {
		this.evaluation = evaluation;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
