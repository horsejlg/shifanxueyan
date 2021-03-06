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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Dict;
import cn.qlt.domain.dto.DictNode;
import cn.qlt.domain.dto.DictNodeAndCount;
import cn.qlt.domain.dto.DictTreeNode;
import cn.qlt.service.DictService;
import cn.qlt.service.TopicService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;
import cn.qlt.utils.web.RequestUtil;

@RestController
public class DictController {

	@Autowired
	private DictService dictService;
	
	@Autowired
	private TopicService topicService;

	@PostMapping("/dicts")
	public PageResult findDict(HttpServletRequest request) {
		Map<String, String> params = RequestUtil.getParams(request);
		return dictService.find(params, SQLUtils.getPageInfo(params));
	}

	@PostMapping("/dicts/{type}")
	public List<Dict> findDictByType(@PathVariable("type") String type, HttpServletRequest request){
		String parent = request.getParameter("p");
		if(StringUtils.isEmpty(parent)){
		return dictService.getDictForType(type);
		}else{
			return dictService.getDictForTypeAndParent(type, parent);
		}
	}

	@RequestMapping("/dictNodes")
	public List<DictNode> findDictByTypeAndParent(String type, String id, String state) {
		if (StringUtils.isEmpty(state))
			state = "closed";
		if (StringUtils.isEmpty(type)) {
			List<DictNode> list = new ArrayList<DictNode>();
			list.add(new DictNode("", "教师教育学院", "grade", "closed"));
			return list;
		} else {
			List<DictNode> list = new ArrayList<DictNode>();
			List<Dict> dicts = dictService.getDictForTypeAndParent(type, id);
			for (Dict dict : dicts) {
				list.add(new DictNode(dict, state));
			}
			return list;
		}
	}

	@RequestMapping("/dictNodesAndCount")
	public List<DictNodeAndCount> dictNodesAndCount(String type, String state) {
		List<DictNodeAndCount> dcs = new ArrayList<>();
		List<Dict> dicts = dictService.getDictForTypeAndParent(type, "");
		for(Dict dict:dicts) {
			long count = topicService.countByType(dict.getCode());
			DictNodeAndCount dc = new DictNodeAndCount(dict, state, count);
			dcs.add(dc);
		}
		return dcs;
	}

	@PostMapping("/dictTree/{type}")
	public List<DictTreeNode> findDictTree(@PathVariable("type") String type) {
		List<Dict> dicts = dictService.getDictForType(type);
		Map<String, DictTreeNode> nodeMap = new HashMap<String, DictTreeNode>();
		List<DictTreeNode> rootList = new ArrayList<DictTreeNode>();
		List<Dict> bakList = new ArrayList<Dict>();
		for (Dict dict : dicts) {
			DictTreeNode dtn = new DictTreeNode(dict);
			if (StringUtils.isEmpty(dict.getParent())) {
				rootList.add(dtn);
			} else {
				if (nodeMap.containsKey(dict.getParent())) {
					DictTreeNode node = nodeMap.get(dict.getParent());
					List<DictTreeNode> children = node.getChildren();
					if (children == null) {
						children = new ArrayList<DictTreeNode>();
						node.setChildren(children);
					}
					children.add(dtn);
				} else {
					bakList.add(dict);
				}
			}
			nodeMap.put(dict.getCode(), dtn);
		}
		for (Dict dict : bakList) {
			if (nodeMap.containsKey(dict.getParent())) {
				DictTreeNode node = nodeMap.get(dict.getParent());
				List<DictTreeNode> children = node.getChildren();
				if (children == null) {
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
	public boolean saveDict(@RequestBody Dict dict) {
		StringUtils.isEmpty(dict.getCode());
		dictService.saveDict(dict);
		return true;
	}

	@Auth(role = AuthUtil.SYSTEM)
	@DeleteMapping("dict/{id}")
	public boolean deleteDict(@PathVariable(name = "id") String id) {
		if ("_".equals(id))
			id = "";
		dictService.deleteDict(id);
		return true;
	}

}
