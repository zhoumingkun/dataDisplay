package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.persist.content.prototype.IGuizhangzhiduDao;
import com.toughguy.educationSystem.service.content.prototype.IGuizhangzhiduService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 规章制度Service实现类
 * @author zmk
 *
 */
@Service
public class GuizhangzhiduServiceImpl extends GenericServiceImpl<Guizhangzhidu, Integer> implements IGuizhangzhiduService{
	/**
	 * 根据标题查询
	 * */
	@Override
 	public List<Guizhangzhidu> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return ((IGuizhangzhiduDao)dao).findByTitle(title);
 	}

}
