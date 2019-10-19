package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-报警方式统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecBJFSTJBService extends IGenericService<RecBJFSTJB, Integer>{
	public List<RecBJFSTJB> findAll();
}
