package com.wishfulcloud.commons.persistent;

public interface CrudDao<T> {
	
	/**
	 * 插入数据
	 * @param entity
	 * @return  true 成功 false 失败
 	 */
	boolean insert(T entity) throws Exception;
	
	/**
	 * 更新数据（根据主键）
	 * @param entity
	 * @return true 更新成功 false 更新失败
	 */
	boolean update(T entity) throws Exception;	
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	T getById(String id) throws Exception;	
	
	/**
	 * 逻辑删除数据（更新删除标记字段）
	 * @param entity
	 * @return true 成功 false 失败
	 */
	boolean logicDelete(String id) throws Exception;
	
	/**
	 * 物理删除数据
	 * @param entity
	 * @return true 成功 false 失败
	 */
	boolean delete(String id) throws Exception;
	
	/**
	 * 总记录数 
	 * 默认的条件 : del_flag = '0'
	 * 
	 * @return
	 */
	long totalCount() throws Exception;
	
}
