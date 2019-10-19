package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.DictJQFLDMB;
import com.toughguy.dataDisplay.persist.content.prototype.IDictJQFLDMBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 字典表-警情分类代码表Dao实现类
 * @author zmk
 *
 */
@Repository
public class DictJQFLDMBImpl extends GenericDaoImpl<DictJQFLDMB, Integer> implements IDictJQFLDMBDao{
	
	@Override
	public List<DictJQFLDMB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
