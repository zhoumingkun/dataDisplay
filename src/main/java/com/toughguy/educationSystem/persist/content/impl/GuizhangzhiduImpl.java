package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.persist.content.prototype.IGuizhangzhiduDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 规章制度Dao实现类
 * @author zmk
 *
 */
@Repository
public class GuizhangzhiduImpl extends GenericDaoImpl<Guizhangzhidu, Integer> implements IGuizhangzhiduDao{
	/**
	 * 根据标题查询
	 * 
	 * */
	@Override
 	public List<Guizhangzhidu> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTitle", title);
 	}

}
