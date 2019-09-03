package com.toughguy.dataDisplay.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.persist.content.prototype.IRecGradeDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecGradeService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;


@Service
public class RecGradeServiceImpl extends GenericServiceImpl<RecGrade, Integer> implements IRecGradeService {
	
	@Autowired
	private IRecGradeDao recGradeDao;

	@Override
	public String alterGrade(List<RecGrade> list) {
		// TODO Auto-generated method stub
		
		try{
			for(int i =0;i<list.size();i++) {
				recGradeDao.alterGrade(list.get(i));
			}
			return "{ \"success\" : true }";
		}catch (Exception e) {
			// TODO: handle exception
			return "{ \"success\" : false }";
		}
	}

	@Override
	public List<RecGrade> selectAll() {
		// TODO Auto-generated method stub
		return recGradeDao.selectAll();
	} 

}
