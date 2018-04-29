package cn.qlt.dao;

import java.util.List;

import cn.qlt.domain.Sociogram;
import cn.qlt.domain.User;
import cn.qlt.utils.BaseRepository;

public interface SociogramDao extends BaseRepository<Sociogram, String> {

	public List<Sociogram> findByUser(User user);
}
