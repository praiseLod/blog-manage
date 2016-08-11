package com.huangtl.blogmgr.action;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huangtl.blogmgr.core.spring.resolver.Json;
import com.huangtl.blogmgr.core.util.PinYinUtils;
import com.huangtl.blogmgr.dao.where.UserSqlWhere;
import com.huangtl.blogmgr.model.blog.User;
import com.huangtl.blogmgr.model.common.Message;
import com.huangtl.blogmgr.model.common.Page;
import com.huangtl.blogmgr.model.common.Page.Direction;
import com.huangtl.blogmgr.model.extjs.Filter;
import com.huangtl.blogmgr.model.extjs.FilterCollection;
import com.huangtl.blogmgr.model.extjs.Sort;
import com.huangtl.blogmgr.model.extjs.SortCollection;
import com.huangtl.blogmgr.service.UserService;

/**
 * 后台用户url接口
 * @date 2016年7月4日
 * @author PraiseLord
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BlogMgrAction {
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	@Resource
	private UserService userService;
	
	/**
	 * 用户分页查询
	* @param pageNo 当前页号
	 * @param pageSize 每页记录个数 
	 * @param filter 查询过滤器 <br>
	 * <blockquote>
	 *  格式  filter:[{"property":"fName","value":"张三","operator":"like"}] 
	 *  </blockquote>
	 * @param sort 排序方案 <br>
	 * <blockquote>
	 *  格式   sort:[{"property":"fCreateDate","direction":"ASC\DESC"}]
	 * </blockquote>
	 * @return
	 * <pre>
	 * {message: "获取成功个数 15", total: 0, userlist: [], success: true}
	 * </pre>
	 */
	@RequestMapping("paging.data")
	@ResponseBody
	public Object getPaging(Integer pageNo,Integer pageSize,
			FilterCollection filter,SortCollection sort) {
		
		Page<User> page = new Page<>(pageSize, pageNo);
		if(sort.isEmpty()){
			page.addSort("fCreateDate", Direction.DESC);
		}else{
			for (Sort s : sort) {
				page.addSort(s.getProperty(), s.getDirection());
			}
		}
		
		UserSqlWhere whereParam = new UserSqlWhere();
		for (Filter f : filter) {
			whereParam.putFilter(f);
		}
		
		this.userService.getDao().selectPaging(whereParam, page);
		
		JSONObject data = new JSONObject();
		data.put("userlist", page.getPageContent());
		data.put("total", page.getTotal());
		data.put("success", true);
		data.put("message", "获取成功个数 "+page.getPageSize());
		return data;
	}
	
	/**
	 * 单用户查询
	 * @param userId
	 * @return
	 */
	@RequestMapping("get.data")
	@ResponseBody
	public Object getUser(String userId){
		UserSqlWhere whereParam = new UserSqlWhere();
		whereParam.idEqual(userId);
		User user = this.userService.getDao().selectOne(userId);
		if(user==null){return "{}";}
		return user;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @param accountType 参数类型,不指默认为:custom 
	 * <li>custom  自定义
	 * <li>mail    邮箱
	 * <li>phone   手机
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("add.do")
	@ResponseBody
	public Object addUser(String accountType,User user) {
		if(user==null){return Message.error("参数为空");}
		
		Message message = null;
		user.setfId(user.newId());
		user.setfPinYin(PinYinUtils.toPinYin(user.getfName())); 
		user.setfCreateDate(new Date());
		user.setfCreater("root");
		if(accountType.equals("mail")){
			user.setfAccount(user.getfEmail());
			message = user.checkValidity("fAccount");
		}else if(accountType.equals("phone")){
			user.setfAccount(user.getfPhone());
			message = user.checkValidity("fAccount");
		}else{
			message = user.checkValidity();
		}
		
		if(message.isError()){return message;}
		     
		user.setfPassword(DigestUtils.md5Hex(user.getfPassword()));  
			
		return this.userService.addUser(user);
	}
	
	/**
	 * 删除多名用户
	 * @param userIds  格式： 'id,id,id,...'
	 * @return 删除的人数
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Object deleteUser(String userIds){
		if(StringUtils.isBlank(userIds)){
			return Message.error("无效参数!");
		}
		return this.userService.deleteUser(userIds.split(","));
	}
	
	/**
	 * 批量修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping("batchedit.do")
	@ResponseBody
	public Object batchEditUser(@Json List<User> users){
		Message message = null;
		try {
			message = this.userService.batchEditUser(users);
		} catch (Exception e) {
			message = Message.error("修改失败");
			logger.error("",e);
		}
		return message;
	}
	
	/**
	 * 修改单个用户
	 * @param user
	 * @return
	 */
	@RequestMapping("edit.do")
	@ResponseBody
	public Object editUser(User user){
		UserSqlWhere sqlWhere = new UserSqlWhere()
							.idEqual(user.getfId());
		Message message = null;
		
		try {
			message = this.userService.editUser(user, sqlWhere);
		} catch (Exception e) {
			message = Message.error("修改失败");
			logger.error("",e);
		}
		
		return message;
	}
	
}
