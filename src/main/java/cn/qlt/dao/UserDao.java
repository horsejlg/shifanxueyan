package cn.qlt.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.qlt.domain.User;
import cn.qlt.utils.BaseRepository;

@Repository
public interface UserDao extends BaseRepository<User,String> {
	
	@Query("select u from User u join u.roles as r  where r.code=:code order by u.id")
	public List<User> findByRole(@Param("code") String code);

	
	@Query("select distinct u.user from UserAttribute u where u.label= ?")
	public List<User> findByLabel(String label);
}
