package cn.qlt.dao;
import org.springframework.stereotype.Repository;

import cn.qlt.domain.Student;
import cn.qlt.utils.BaseRepository;

@Repository
public interface StudentDao extends BaseRepository<Student, String> {

}
