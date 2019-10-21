package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-来话类型统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecLHLXTJBService extends IGenericService<RecLHLXTJB, Integer>{
	public List<RecLHLXTJB> findAll();
}