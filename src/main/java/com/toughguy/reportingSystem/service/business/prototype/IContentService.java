package com.toughguy.reportingSystem.service.business.prototype;
import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.service.prototype.IGenericService;
/**
 * 信息Service层接口类
 * @author zmk
 *
 */
public interface IContentService extends IGenericService<Content, Integer>{
	/**
	 * 根据内容类型查询
	 * */
	public Content findByType(int type);

}
