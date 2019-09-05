package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-警情分类统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecJQFLTJBService extends IGenericService<RecJQFLTJB, Integer>{
	
	// 查询各大类的警情分类数量（首页今日）
	public List<RecJQFLTJB> findJQFLNum(String tjTime);
	
	// 查询各大类的警情分类数量总量（首页今日）
	public int findJQFLNumHJ(String tjTime);
	
	// 查询各第二类的警情分类数量（首页今日、昨日、前日）
	public List<RecJQFLTJB> findJQFLsecondNum(String tjTime);
	
	//查询各大类的警情分类数量（二级页面各行政区划今日、昨日、前日）tjTime xzqhdm
	public List<RecJQFLTJB>findJQFLNumXZQH(String tjTime,String xzqhdm);
	
	//查询各大类的警情分类数量总量 （二级页面各行政区划今日、昨日、前日）tjTime xzqhdm
	public int findJQFLNumXZQHHJ(String tjTime,String xzqhdm);
	
	//查询各第二类的警情分类数量（二级页面各行政区划今日、昨日、前日）tjTime xzqhdm
	public List<RecJQFLTJB>findJQFLsecondNumXZQH(String tjTime,String xzqhdm);
}
