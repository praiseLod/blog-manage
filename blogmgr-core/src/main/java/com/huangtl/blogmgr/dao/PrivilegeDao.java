package com.huangtl.blogmgr.dao;

import com.huangtl.blogmgr.core.dao.MybatisDao;
import com.huangtl.blogmgr.model.blog.Privilege;

/**
 * 权限持久化接口,查询对象请使用{@code com.huangtl.blogmgr.dao.where.PrivilegeSqlWhere}
 * @author PraiseLord
 * @date 2017年1月10日
 *
 */
public interface PrivilegeDao extends MybatisDao<Privilege> {
	
}
