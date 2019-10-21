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
	public List<RecJQFLTJB> findAll();
}
