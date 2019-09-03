package com.toughguy.dataDisplay.service.content.prototype;

import java.util.List;

import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

public interface IRecGradeService extends IGenericService<RecGrade, Integer> {
	
	//修改地图等级
	public String alterGrade(List<RecGrade> list);

	public List<RecGrade> selectAll();
	

}
