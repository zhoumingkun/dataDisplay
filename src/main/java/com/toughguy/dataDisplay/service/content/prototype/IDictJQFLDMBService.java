package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.DictJQFLDMB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 字典表-警情分类代码表 Service层接口类
 * @author zmk
 *
 */
public interface IDictJQFLDMBService extends IGenericService<DictJQFLDMB, Integer>{
	public List<DictJQFLDMB> findAll();
}
