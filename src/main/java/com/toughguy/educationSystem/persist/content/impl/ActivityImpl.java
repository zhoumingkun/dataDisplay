package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Activity;
import com.toughguy.educationSystem.persist.content.prototype.IActivityDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 校园活动Dao实现类
 * @author zmk
 *
 */
@Repository
public class ActivityImpl extends GenericDaoImpl<Activity, Integer> implements IActivityDao{
	

}
