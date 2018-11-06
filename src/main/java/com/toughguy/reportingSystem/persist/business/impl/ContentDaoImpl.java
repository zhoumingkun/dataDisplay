package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;
import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.persist.business.prototype.IContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 信息Dao实现类
 * @author zmk
 *
 */
@Repository
public class ContentDaoImpl extends GenericDaoImpl<Content, Integer> implements IContentDao{
	
	@Override
	public Content findByType(int type) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByType", type);
	}

}
