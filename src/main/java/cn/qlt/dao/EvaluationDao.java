package cn.qlt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.domain.Evaluation;
import cn.qlt.domain.Evaluation1;
import cn.qlt.domain.Evaluation2;
import cn.qlt.domain.Evaluation3;
import cn.qlt.utils.BaseRepository;

@Repository
public interface EvaluationDao extends BaseRepository<Evaluation,String>{
	
	@Query("from Evaluation  where status = ?")
	public List<Evaluation> findByStatus(int status);
	
	@Query("from Evaluation1 where status>0 and year.code = ? and author.classes.code = ? and author.specialty.code = ? and author.grade.code=?")
	public List<Evaluation1> findEvaluation1(String year, String classes, String specialty, String grade);
	
	@Query("from Evaluation2 where status >0 and year.code = ?1 and author.specialty.code= ?2 and author.grade.code= ?3 and author.classes.code= ?4 order by sumSorce desc")
	public List<Evaluation2> findEvaluation2(String year, String specialty, String grade, String classes);

	@Query("from Evaluation3 where status >0 and year.code = ?1 and author.specialty.code= ?2 and author.grade.code= ?3 and author.classes.code= ?4 order by sumSorce desc")
	public List<Evaluation3> findEvaluation3(String year, String specialty, String grade, String classes);

	@Modifying
	@Transactional
	@Query("update Evaluation2 set gsIndex = ?1 where id = ?2")
	public void saveGsIndex(int gsIndex, String id);
	
	@Query("from Evaluation2 where id = ?1")
	public Evaluation2 findEvaluation2ById(String id);
	
	@Query("from Evaluation3 where id = ?1")
	public Evaluation3 findEvaluation3ById(String id);
}
