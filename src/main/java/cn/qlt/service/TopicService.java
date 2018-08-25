package cn.qlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qlt.dao.TopicDao;

/**
 * @author zp
 * 主题
 */
@Service
public class TopicService {

	@Autowired
	private TopicDao topicDao;
}
