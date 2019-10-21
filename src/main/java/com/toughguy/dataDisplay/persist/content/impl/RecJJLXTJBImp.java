package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecJJLXTJBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 统计表表-接警类型统计表 Dao实现类
 * @author zmk
 *
 */
@Repository
public class RecJJLXTJBImp extends GenericDaoImpl<RecJJLXTJB, Integer> implements IRecJJLXTJBDao{
	
	@Override
	public List<RecJJLXTJB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
