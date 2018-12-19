package cn.qlt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.qlt.domain.Message;
import cn.qlt.utils.BaseRepository;

public interface MessageDao extends BaseRepository<Message, String>{

	@Query("SELECT new map(id as id, froms.id as userId, froms.nickName as userName, state as state, content as content) FROM Message m WHERE m.tos.id = :tos and m.state = :state")
	public List<Map<String,Object>> findByTosAndState(@Param("tos") String tos, @Param("state") int state);
}
