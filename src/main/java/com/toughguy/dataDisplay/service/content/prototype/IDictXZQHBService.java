package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 字典表-行政区划表 Service层接口类
 * @author zmk
 *
 */
public interface IDictXZQHBService extends IGenericService<DictXZQHB, Integer>{
	//查询行政区划全部数据
	public List<DictXZQHB> findAll();
	
	//修改行政区划表地图峰值的数据
	public String save(List<DictXZQHB> list);

	//查询各个地市的占比情况
	public Map<String, Object> findMapProportion();
	
	//修改行政区划表地图峰值的数据
	public void updateXZQH(DictXZQHB xzqh);
	
}
