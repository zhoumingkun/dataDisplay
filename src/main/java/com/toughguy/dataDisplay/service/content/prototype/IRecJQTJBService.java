package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-警情统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecJQTJBService extends IGenericService<RecJQTJB, Integer>{
	public List<RecJQTJB> findAll();
}
