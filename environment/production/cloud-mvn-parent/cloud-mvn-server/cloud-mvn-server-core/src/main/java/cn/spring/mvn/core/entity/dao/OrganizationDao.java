package cn.spring.mvn.core.entity.dao;

import java.util.List;

import cn.spring.mvn.basic.ibatis.IBatisDao;
import cn.spring.mvn.core.entity.Organization;

public interface OrganizationDao extends IBatisDao<Organization>{
	public List<Organization> selectAll();
}
