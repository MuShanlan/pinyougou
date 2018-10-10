package com.pinyougou.cart.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.PageResult;
import com.pinyougou.common.Result;
import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbAddressExample;
import com.pinyougou.user.service.AddressService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/address")
public class AddressController {

	@Reference(timeout = 10000000)
	private AddressService addressService;

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbAddress> findAll(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		return addressService.findAll(name);
	}


	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){
		return addressService.findPage(page, rows);
	}

	/**
	 * 增加
	 * @param address
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbAddress address){
		try {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			address.setUserId(name);

			addressService.add(address);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * @param address
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbAddress address){
		try {
			addressService.update(address);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}

	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbAddress findOne(Long id){
		return addressService.findOne(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			addressService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	/**
	 * 查询+分页
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbAddress address, int page, int rows  ){
		return addressService.findPage(address, page, rows);
	}

	/**
	 * 根据当前登录者用户名获取收货人地址列表
	 * @return
	 */
	@RequestMapping("findAddressByLoginUser")
	public List<TbAddress> findAddressByLoginUser(){
		//1、获取当前登录者用户名
		String loginUser = SecurityContextHolder.getContext().getAuthentication().getName();
		//2、调用Service根据用户名查询列表
		List<TbAddress> addressList = addressService.findAddressByLoginUser(loginUser);
		return addressList;
	}

}