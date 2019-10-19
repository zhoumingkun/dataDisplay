package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 字典表-行政区划表 Service层接口类
 * @author zmk
 *
 */
public interface IDictXZQHBService extends IGenericService<DictXZQHB, Integer>{
	public List<DictXZQHB> findAll();
}
