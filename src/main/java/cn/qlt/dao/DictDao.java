package cn.qlt.dao;

import java.util.List;

import cn.qlt.domain.Dict;
import cn.qlt.utils.BaseRepository;

public interface DictDao extends BaseRepository<Dict, String> {

	
	public List<Dict> findByType(String type);
	
	public Dict findByTypeAndLabel(String type, String label);
}
