package com.wishfulcloud.commons.persistent;

/**   
 * @Title: 
 * @Description: 缓存的接口
 * @author wangxuezheng
 * @date 2017年4月13日 上午10:32:18
 * @version V1.0   
 *
 */
public interface CacheDao<T> {
	
	/**
	 * 保存或者更新一个实体
	 * 
	 * 如果seconds参数值不为0的话，则是过期缓存，具有缓存时长
	 * 
	 * @param entity 实体对象
	 * @param id     实体主键
	 * @param seconds 有效时长多少秒
	 *   
	 * @return true 保存或更新成功 
	 * 		   false 保存或更新失败
	 */
	Boolean saveOrUpdate(T entity, String id, int seconds);
	
	/**
	 * 根据类型和id获取一个实体，未获取到返回 null
	 * 
	 * @param clazz 实体.class
	 * @param id    主键id
	 * 
	 * @return T 或着 null
	 */
	T getById(Class<T> clazz, String id);
	
	/**
	 * 根据id删除一个实体对象
	 * 
	 * @param clazz 实体.class
	 * @param id 主键id
	 * @return  true 删除成功
	 * 			false 删除失败
	 * 		
	 */
	Boolean deleteEntity(Class<T> clazz, String id);
	
}
