package cn.qlt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class SQLUtils {

	private static Pattern pattern = Pattern.compile("/~([^:]+):([^~/]+)~/");
	private static Pattern linkParam = Pattern.compile("\\[([^\\]]+)\\]");
	private static Pattern jobParam = Pattern.compile("\\{([^\\}]+)\\}");
	
	public static SqlItem markSql(String sql, Map<String,String> param){
		Matcher matcher = pattern.matcher(sql);
		List<String> list = new ArrayList<String>();
		while(matcher.find()){
			String group = matcher.group();
			String group1 = matcher.group(1);
			String group2 = matcher.group(2);
			if(param.containsKey(group1)&&!StringUtils.isEmpty(param.get(group1))){
				sql = sql.replace(group, group2);
			}else{
				sql = sql.replace(group, "");
			}
		}
		matcher = linkParam.matcher(sql);
		while(matcher.find()){
			String group = matcher.group();
			String group1 = matcher.group(1);
			if(param.containsKey(group1)){
				sql = sql.replace(group, param.get(group1));
			}else{
				throw new NullPointerException(String.format("缺少定义参数[]",group1));
			}
		}
		matcher = jobParam.matcher(sql);
		while(matcher.find()){
			String group = matcher.group();
			String group1 = matcher.group(1);
			if(param.containsKey(group1)){
				sql = sql.replace(group, "?");
				list.add(param.get(group1));
			}else{
				throw new NullPointerException(String.format("缺少定义参数[]",group1));
			}
		}
		return new SqlItem(sql, list);
		
	}
	
	public static PageInfo getPageInfo(Map<String, String> params){
		int page = 1;
		int pageSize = 20;
		if(params.containsKey("page")){
			page = Integer.parseInt(params.get("page"));
		}
		if(params.containsKey("rows")){
			pageSize = Integer.parseInt(params.get("rows"));
		}
		return new PageInfo(page, pageSize);
	}
	
	public static class PageInfo{
		private int page;
		private int pageSize;
		
		public PageInfo(int page, int pageSize) {
			super();
			this.page = page;
			this.pageSize = pageSize;
		}
		public int getPage() {
			return page;
		}
		public int getPageSize() {
			return pageSize;
		}
	}
	
	public static class PageResult{
		private long total;
		private List rows;
		
		public long getTotal() {
			return total;
		}
		public List getRows() {
			return rows;
		}
		
		
		
		public PageResult() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PageResult(long total, List rows) {
			super();
			this.total = total;
			this.rows = rows;
		}
		public void find(PageInfo page, String hql, Map<String, String> params, BaseRepository dao){
			SqlItem sqlItem = markSql(hql, params);
			total =dao.count(sqlItem.getSql(), sqlItem.getValues().toArray());
			rows = dao.findPage(sqlItem.getSql(), page.getPage(), page.getPageSize(), sqlItem.getValues().toArray());
		}
		
	}
	
	public static class SqlItem{
		private String sql;
		private List<String> values;
		
		public SqlItem(String sql, List<String> values) {
			super();
			this.sql = sql;
			this.values = values;
		}
		public String getSql() {
			return sql;
		}
		public List<String> getValues() {
			return values;
		}
	}
}
