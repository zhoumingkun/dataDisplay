package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.DictLHLXDMB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 字典表-来话类型代码表 Service层接口类
 * @author zmk
 *
 */
public interface IDictLHLXDMBService extends IGenericService<DictLHLXDMB, Integer>{
	public List<DictLHLXDMB> findAll();
}
