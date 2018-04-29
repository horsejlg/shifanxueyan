package test.cn.qlt;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.qlt.domain.User;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.SqlItem;

public class SqlTest {

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String,String>();
		params.put("loginname", "user");
		params.put("nickName", "测试");
		SqlItem markSql = SQLUtils.markSql("select * from user where 1=1 /~loginname: and loginname like '%[loginname]%'~//~nickName: and nickName like '%{nickName}%'~/", params);
		System.out.println(markSql.getSql());
		System.out.println(markSql.getValues());
		
		User user1 = new User("aaa", "bbb", "测试1", new Date(), null);
		User user2 = new User("bbb", null, "测试3", new Date(), null);
		
		
		BeanUtils.copyProperties(user2, user1, "password","roles");
		BeanWrapper bw = new BeanWrapperImpl(user1);
		PropertyDescriptor[] descriptors = bw.getPropertyDescriptors();
		for(PropertyDescriptor descriptor:descriptors){
			System.out.println(descriptor.getName()+":"+bw.getPropertyValue(descriptor.getName()));
		}
	}
}
