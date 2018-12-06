package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.persist.content.prototype.ISingleOptionDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 单题测试题选项Dao实现类
 * @author zmk
 *
 */
@Repository
public class SingleOptionImpl extends GenericDaoImpl<SingleOption, Integer> implements ISingleOptionDao{
	

}
