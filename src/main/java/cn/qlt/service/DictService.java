package cn.qlt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.qlt.dao.DictDao;
import cn.qlt.domain.Dict;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@Service
public class DictService {
	
	@Autowired
	private DictDao dictDao;
	
	@PostConstruct
	public void init(){
		Long count = dictDao.count("from Dict where type=?","type");
		if(count.intValue()==0){
			ObjectMapper mapper = new ObjectMapper();
			try {
				Dict[] dicts = mapper.readValue(getClass().getResourceAsStream("/dict.json"), Dict[].class);
				for(Dict dict:dicts){
					dictDao.save(dict);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Dict> getDictForType(String type){
		return dictDao.findByType(type);
	}

	public PageResult find(Map<String, String> params, PageInfo pageInfo) {
		PageResult result = new PageResult();
		result.find(pageInfo, "from Dict where 1=1 /~type: and type ={type}~/", params, dictDao);
		return result;
	}

	public void saveDict(Dict dict) {
		if(!StringUtils.isEmpty(dict.getParent())){
			Dict parent = dictDao.load(dict.getParent());
			if("open".equals(parent.getState())){
				parent.setState("closed");
				dictDao.save(parent);
			}
		}
		dictDao.save(dict);
	}

	public void deleteDict(String id) {
		Dict load = dictDao.load(id);
		if(!StringUtils.isEmpty(load.getParent())){
			if(dictDao.count("from Dict where parent=?", load.getParent()).intValue()==0){
				Dict parent = dictDao.load(load.getParent());
				parent.setState("open");
				dictDao.save(parent);
			}
		}
		dictDao.delete(id);
	}
	
	public Dict findDict(String code){
		return dictDao.findOne(code);
	}
}
