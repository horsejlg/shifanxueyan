package cn.qlt.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.ViolationDao;
import cn.qlt.domain.Violation;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@Service
public class ViolationService {

	@Autowired
	private ViolationDao violationDao;

	@Transactional
	public PageResult findNotPass(Map<String, String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from NotPass where 1=1 /~subject: subject.code = {subject} ~//~term: term.code={term} ~//~student: student.id={student}~/ ", params, violationDao);
		return result;
	}
	
	@Transactional
	public PageResult findGeneralDiscipline(Map<String, String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from GeneralDiscipline where 1=1 /~term: term.code={term} ~//~student: student.id={student}~/", params, violationDao);
		return result;
	}
	
	@Transactional
	public PageResult findSeriousDisciplinary(Map<String, String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from SeriousDisciplinary where 1=1 /~term: term.code={term} ~//~student: student.id={student}~/", params, violationDao);
		return result;
	}
	
	public void saveViolation(Violation violation) {
		violationDao.save(violation);
	}

}
