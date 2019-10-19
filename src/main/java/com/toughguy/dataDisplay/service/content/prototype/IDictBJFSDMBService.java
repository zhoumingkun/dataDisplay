package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.toughguy.dataDisplay.model.content.DictBJFSDMB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 字典表-报警方式代码表 Service层接口类
 * @author zmk
 *
 */
public interface IDictBJFSDMBService extends IGenericService<DictBJFSDMB, Integer>{
	public List<DictBJFSDMB> findAll();
}
