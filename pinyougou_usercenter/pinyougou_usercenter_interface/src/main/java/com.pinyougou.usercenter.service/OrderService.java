package com.pinyougou.usercenter.service;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbOrder;

import com.pinyougou.common.PageResult;
import com.pinyougou.usercenter.exception.OrderException;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbOrder> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbOrder order);
	
	
	/**
	 * 修改
	 */
	public void update(TbOrder order);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbOrder findOne(Long id);
	
	
	/**
	 *  取消订单
	 * @param id
	 */
	public void delete (Long id) throws OrderException;

	/**
	 * 分页
	 * @param pageNum 当前页码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbOrder order, int pageNum, int pageSize);

	/**
	 * 按订单状态分页查询订单列表
	 * @param loginName 登录用户id
	 * @param searchMap 搜索条件对象
	 * @return
	 */
	Map findPageByOrderStatus(String loginName, Map searchMap);
}
