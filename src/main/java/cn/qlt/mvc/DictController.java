package cn.qlt.mvc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Dict;
import cn.qlt.service.DictService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;
import cn.qlt.utils.web.RequestUtil;

@RestController
public class DictController {
	
	@Autowired
	private DictService dictService;
	
	@PostMapping("/dicts")
	public PageResult findDict(HttpServletRequest request){
		Map<String, String> params = RequestUtil.getParams(request);
		return dictService.find(params, SQLUtils.getPageInfo(params));
	}
	
	@PostMapping("/dicts/{type}")
	public List<Dict> findDictByType(@PathVariable("type") String type){
		return dictService.getDictForType(type);
	}
	
	@Auth(role = AuthUtil.SYSTEM)
	@PostMapping("dict")
	public boolean saveDict(@RequestBody Dict dict){
		StringUtils.isEmpty(dict.getCode());
		dictService.saveDict(dict);
		return true;
	}
	
	@Auth(role = AuthUtil.SYSTEM)
	@DeleteMapping("dict/{id}")
	public boolean deleteDict(@PathVariable(name="id") String id){
		dictService.deleteDict(id);
		return true;
	}

}
