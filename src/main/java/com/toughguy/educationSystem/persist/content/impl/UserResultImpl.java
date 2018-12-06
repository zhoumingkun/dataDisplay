package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.UserResult;
import com.toughguy.educationSystem.persist.content.prototype.IUserResultDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 用户与结果Dao实现类
 * @author zmk
 *
 */
@Repository
public class UserResultImpl extends GenericDaoImpl<UserResult, Integer> implements IUserResultDao{
	

}
