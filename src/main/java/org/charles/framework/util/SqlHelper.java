package org.charles.framework.util;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * SQL拼接辅助类
 * @date 2015年3月28日
 * @author YeChao
 */
public class SqlHelper {
	
	public static abstract class Sql{
		protected final String AND = " and ";
		protected final String EQ = " = :";
		protected final String LIKE = " like '%:";
		
		protected String tableName;
		protected StringBuilder fields = new StringBuilder();
		protected StringBuilder conditions = new StringBuilder();
		protected Map<String, Object> params = new LinkedHashMap<String, Object>();
		
		
		public Sql(String tableName){
			this.tableName = tableName;
		}
		
		public abstract String getSql();
		public Map<String, Object> getParams(){
			return params;
		}
		
		protected String getNewKey(String key){
			if(params.containsKey(key)){
				return key + "_" +  NumberUtil.random(4);
			}else{
				return key;
			}
		}
		@Override
		public String toString() {
			return getSql();
		}
	}
	
	/**
	 * 主要拼接update语句
	 * @date 2015年3月28日
	 * @author YeChao
	 */
	public static class Update extends Sql{
		
		public Update(String tableName){
			super(tableName);
		}
		
		public Update set(String key, Object value){
			fields.append(",`" + key + "` = :" + key);
			if((value instanceof String) && StringUtil.isBlank(value)){
				params.put(key, " ");
			}else{
				params.put(key, value);
			}
			return this;
		}
		
		public Update eq(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND + key + EQ + newKey);
			params.put(newKey, value);
			return this;
		}
		
		public Update like(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND + key + LIKE + newKey + "%'");
			params.put(newKey, value);
			return this;
		}
		@Override
		public String getSql(){
			String sql = "update " + tableName + " set " + fields.substring(1) + " where " + conditions.substring(AND.length());
			return sql;
		}

		@Override
		public String toString() {
			String sql = getSql();
			for(Map.Entry<String, Object> p : params.entrySet()){
				sql = sql.replaceAll(":" + p.getKey(), p.getValue() == null ? "null" : p.getValue().toString());
			}
			return sql;
		}
	}
	
	/**
	 * 主要拼接insert语句
	 * @date 2015年3月28日
	 * @author YeChao
	 */
	public static class Insert extends Sql{

		public Insert(String tableName){
			super(tableName);
		}
		
		public Insert value(String key, Object value){
			String newKey = getNewKey(key);
			fields.append(",").append('`').append(key).append('`');
			conditions.append(",:" + newKey);
			if((value instanceof String) && StringUtil.isBlank(value)){
				params.put(newKey, " ");
			}else{
				params.put(newKey, value);
			}
			return this;
		}
		
		@Override
		public String getSql() {
			String sql = "insert into " + tableName + "(" + fields.substring(1) + ") values(" + conditions.substring(1) + ")";
			return sql;
		}

		@Override
		public String toString() {
			String sql = getSql();
			for(Map.Entry<String, Object> p : params.entrySet()){
				sql = sql.replaceAll(":" + p.getKey(), p.getValue() == null ? "null" : p.getValue().toString());
			}
			return sql;
		}
	}
	
	/**
	 * 拼接Delete语句
	 * @date 2015年4月21日
	 * @author YeChao
	 */
	public static class Delete extends Sql{
		
		public Delete(String tableName){
			super(tableName);
		}
		
		public Delete eq(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND +  key +  EQ + newKey);
			params.put(newKey, value);
			return this;
		}
		
		public Delete like(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND +  key +  LIKE + newKey + "%'");
			params.put(newKey, value);
			return this;
		}
		
		public Delete nvl(String key, Object value){
			if(StringUtil.isBlank(value)){
				conditions.append(AND +  key + " is null");
			}else{
				eq(key, value);
			}
			return this;
		}

		@Override
		public String getSql() {
			String sql = "delete from " + tableName + " where " + conditions.substring(AND.length());
			return sql;
		}
	}
	
	/**
	 * 拼接select语句（目前只支持单表简单查询）
	 * @date 2015年4月21日
	 * @author YeChao
	 */
	public static class Select extends Sql{
		private String orderBy = "";
		
		public Select(String tableName){
			super(tableName);
		}
		public Select field(String... key){
			for(String f : key){
				fields.append("," + f);
			}
			
			return this;
		}
		
		public Select eq(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND +  key + EQ + newKey);
			params.put(newKey, value);
			return this;
		}
		
		public Select like(String key, Object value){
			String newKey = getNewKey(key);
			conditions.append(AND + key + LIKE + newKey + "%'");
			params.put(newKey, value);
			return this;
		}
		public Select nvl(String key, Object value){
			if(StringUtil.isBlank(value)){
				conditions.append(AND + key + " is null");
			}else{
				eq(key, value);
			}
			return this;
		}
		
		public Select orderBy(String order){
			this.orderBy = order;
			return this;
		}

		@Override
		public String getSql() {
			String sql = "select " + (fields.length() == 0 ? "*" : fields.substring(1)) + " from " + tableName;
			if(conditions.length() != 0){
				sql = sql + " where " + conditions.substring(AND.length());
			}
			if(!StringUtil.isBlank(orderBy)){
				sql = sql + " order by " + orderBy;
			}
			return sql;
		}
	}
	
	public static Update getUpdate(String tableName){
		return new SqlHelper.Update(tableName);
	}
	
	public static Insert getInsert(String tableName){
		return new SqlHelper.Insert(tableName);
	}
	
	public static Delete getDelete(String tableName){
		return new SqlHelper.Delete(tableName);
	}
	
	public static Select getSelect(String tableName){
		return new SqlHelper.Select(tableName);
	}
}
