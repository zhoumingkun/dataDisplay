package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.persist.content.prototype.IXiaoyuanhuangyeDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 校园黄页Dao实现类
 * @author zmk
 *
 */
@Repository
public class XiaoyuanhuangyeImpl extends GenericDaoImpl<Xiaoyuanhuangye, Integer> implements IXiaoyuanhuangyeDao{
	/**
	 * 根据部门名称查询
	 * 
	 * */
	@Override
	public List<Xiaoyuanhuangye> findBySectionName(String sectionName) {		
		return sqlSessionTemplate.selectList(typeNameSpace + ".findBySectionName", sectionName);
		
	}
	

}
