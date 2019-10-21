package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecJQTJBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 统计表表-警情统计表 Dao实现类
 * @author zmk
 *
 */
@Repository
public class RecJQTJBImp extends GenericDaoImpl<RecJQTJB, Integer> implements IRecJQTJBDao{
	
	@Override
	public List<RecJQTJB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}