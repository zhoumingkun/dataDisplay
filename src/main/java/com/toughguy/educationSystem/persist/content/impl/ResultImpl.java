package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Result;
import com.toughguy.educationSystem.persist.content.prototype.IResultDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 心理测试题结果Dao实现类
 * @author zmk
 *
 */
@Repository
public class ResultImpl extends GenericDaoImpl<Result, Integer> implements IResultDao{
	

}
