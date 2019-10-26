package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-警情分类统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecJQFLTJBDao extends IGenericDao<RecJQFLTJB, Integer> {
	
	//查询各大类的警情分类数量（首页今日）
	public List<RecJQFLTJB>  findJQFLNum(String tjTime);
	
	//查询各第二类的警情分类数量（首页今日、昨日、前日）
	public List<RecJQFLTJB>  findJQFLsecondNum(String tjTime);
	
}
