package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.DictJJLXDMB;
import com.toughguy.dataDisplay.persist.content.prototype.IDictJJLXDMBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 字典表-接警类型代码表Dao实现类
 * @author zmk
 *
 */
@Repository
public class DictJJLXDMBImpl extends GenericDaoImpl<DictJJLXDMB, Integer> implements IDictJJLXDMBDao{
	
	@Override
	public List<DictJJLXDMB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
