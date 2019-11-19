package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecJQFLTJBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 统计表表-警情分类统计表 Dao实现类
 * @author zmk
 *
 */
@Repository
public class RecJQFLTJBImp extends GenericDaoImpl<RecJQFLTJB, Integer> implements IRecJQFLTJBDao{
	
	@Override
	public List<RecJQFLTJB> findJQFLNum(String tjTime) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLNum",tjTime);
	}
	
	@Override
	public List<RecJQFLTJB> findJQFLsecondNum(String tjTime) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLsecondNum",tjTime);
	}
	
	@Override
	public List<RecJQFLTJB>  findJQFLNumXZQH(Map<String,String> map){
		// TODO Auto-generated method stub
		System.out.println("hahahah"+sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLNumXZQH",map));
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLNumXZQH",map);
	}
	
	@Override
	public List<RecJQFLTJB>  findJQFLsecondNumXZQH(Map<String,String> map){
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLsecondNumXZQH",map);
	}
	
	@Override
	public int findJQFLNumHJ(String tjTime){
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findJQFLNumHJ",tjTime);
	}
	
	@Override
	public int findJQFLNumXZQHHJ(Map<String,String> map){
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findJQFLNumXZQHHJ",map);
	}

	@Override
	public List<RecJQFLTJB> findJQFLWDL(String tjTime) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLWDL",tjTime);
	}

	@Override
	public List<RecJQFLTJB> findJQFLWDLXZQH(Map<String ,String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findJQFLWDLXZQH",map);
	}
	
}
