package cn.qlt.dao;

import java.util.List;

import cn.qlt.domain.Message;
import cn.qlt.domain.User;
import cn.qlt.utils.BaseRepository;

public interface MessageDao extends BaseRepository<Message, String>{

	//@Query("SELECT Message( froms , to, m.state, m.content) FROM Message m WHERE m.to = ? and m.state = ?")
	public List<Message> findByTosAndState(User tos, int state);
}
