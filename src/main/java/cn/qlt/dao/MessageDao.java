package cn.qlt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.qlt.domain.Message;
import cn.qlt.domain.User;
import cn.qlt.utils.BaseRepository;

public interface MessageDao extends BaseRepository<Message, String>{

	//@Query("select new Message(new User(from.id, from.nickName),new User(to.id, to.nickName), state, content) from Message where to = ? and state = ?")
	public List<Message> findByToAndState(User to, int state);
}
