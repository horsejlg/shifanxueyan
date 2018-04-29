package cn.qlt.dao;

import java.util.List;

import cn.qlt.domain.Awards;
import cn.qlt.domain.User;
import cn.qlt.utils.BaseRepository;

public interface AwardsDao extends BaseRepository<Awards, String> {

	public List<Awards> findByUser(User user);
}
