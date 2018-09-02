package cn.qlt.mvc;

import java.util.ArrayList;
import java.util.HashMap;
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
import cn.qlt.domain.dto.DictTreeNode;
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
	
	@PostMapping("/dicts/{type}/{parent}")
	public List<Dict> findDictByTypeAndParent(@PathVariable("type") String type, @PathVariable("parent") String parent){
		return dictService.getDictForTypeAndParent(type, parent);
	}
	
	@PostMapping("/dictTree/{type}")
	public List<DictTreeNode> findDictTree(@PathVariable("type") String type){
		List<Dict> dicts = dictService.getDictForType(type);
		Map<String, DictTreeNode> nodeMap = new HashMap<String, DictTreeNode>();
		List<DictTreeNode> rootList = new ArrayList<DictTreeNode>();
		List<Dict> bakList = new ArrayList<Dict>();
		for(Dict dict :dicts){
			DictTreeNode dtn = new DictTreeNode(dict);
			if(StringUtils.isEmpty(dict.getParent())){
				rootList.add(dtn);
			}else{
				if(nodeMap.containsKey(dict.getParent())){
					DictTreeNode node = nodeMap.get(dict.getParent());
					List<DictTreeNode> children = node.getChildren();
					if(children == null){
						children = new ArrayList<DictTreeNode>();
						node.setChildren(children);
					}
					children.add(dtn);
				}else{
					bakList.add(dict);
				}
			}
			nodeMap.put(dict.getCode(), dtn);
		}
		for(Dict dict : bakList){
			if(nodeMap.containsKey(dict.getParent())){
				DictTreeNode node = nodeMap.get(dict.getParent());
					List<DictTreeNode> children = node.getChildren();
					if(children == null){
						children = new ArrayList<DictTreeNode>();
						node.setChildren(children);
					}
					children.add(nodeMap.get(dict.getCode()));
			}
		}
		return rootList;
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
		if("_".equals(id))
			id = "";
		dictService.deleteDict(id);
		return true;
	}

}
