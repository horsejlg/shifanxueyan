package cn.qlt.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.ViolationDao;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@Service
public class ViolationService {

    @Autowired
    private ViolationDao violationDao;
    
    @Transactional
    public PageResult findNotPass(Map<String,String> params, PageInfo page) {
	PageResult result = new PageResult();
	result.find(page, "from NotPass where 1=1 ", params, violationDao);
	return result;
    }
    
}
