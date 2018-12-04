package com.toughguy.educationSystem.persist.content.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Option;
import com.toughguy.educationSystem.persist.content.prototype.IOptionDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 心理测试题选项Dao实现类
 * @author zmk
 *
 */
@Repository
public class OptionImpl extends GenericDaoImpl<Option, Integer> implements IOptionDao{
	

}
