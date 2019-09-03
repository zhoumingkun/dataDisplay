package com.toughguy.dataDisplay.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.service.content.prototype.IRecGradeService;

@RestController
@RequestMapping("/redGrade")
public class RecGradeController {
	
	@Autowired
	private IRecGradeService recGradeService;
	
	/**
	 * 修改全省占比
	 * @return
	 */
	@RequestMapping("/alterGrade")
	public String alterGrade(List<RecGrade> list){	
		return recGradeService.alterGrade(list);
	}

	/**
	 * 查询全省占比
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<RecGrade> selectAll(){
		return recGradeService.selectAll();
	}
}
