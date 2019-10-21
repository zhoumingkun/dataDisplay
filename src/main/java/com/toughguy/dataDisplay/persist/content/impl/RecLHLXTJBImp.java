package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecLHLXTJBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 统计表表-来话类型统计表 Dao实现类
 * @author zmk
 *
 */
@Repository
public class RecLHLXTJBImp extends GenericDaoImpl<RecLHLXTJB, Integer> implements IRecLHLXTJBDao{
	
	@Override
	public List<RecLHLXTJB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
