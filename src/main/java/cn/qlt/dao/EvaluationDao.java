package cn.qlt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.qlt.domain.Evaluation;
import cn.qlt.domain.Evaluation1;
import cn.qlt.utils.BaseRepository;

@Repository
public interface EvaluationDao extends BaseRepository<Evaluation,String>{
	
	@Query("from Evaluation  where status = ?")
	public List<Evaluation> findByStatus(int status);
	
	@Query("from Evaluation1 where status>0 and year.code = ? and author.classes.code = ? and author.specialty.code = ? and author.grade.code=?")
	public List<Evaluation1> findEvaluation1(String year, String classes, String specialty, String grade);

}
