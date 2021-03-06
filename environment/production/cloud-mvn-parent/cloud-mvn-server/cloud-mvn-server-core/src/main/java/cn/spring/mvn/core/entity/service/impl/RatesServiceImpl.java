package cn.spring.mvn.core.entity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.spring.mvn.basic.ibatis.IBatisServiceImpl;
import cn.spring.mvn.core.entity.Rates;
import cn.spring.mvn.core.entity.dao.RatesDao;
import cn.spring.mvn.core.entity.service.RatesService;

@Service("RatesService")
public class RatesServiceImpl extends IBatisServiceImpl<Rates> implements RatesService{
	@Resource
	private RatesDao dao;
	
	@Override
	public List<Rates> selectAll() {
		return dao.selectAll();
	}

}
