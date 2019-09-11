package com.toughguy.dataDisplay.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.persist.content.prototype.IRecGradeDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecGradeService;

@RestController
@RequestMapping("/redGrade")
public class RecGradeController {
	
	@Autowired
	private IRecGradeService recGradeService;
	
	@Autowired
	private IRecGradeDao recGradeDao;
	
	/**
	 * 修改全省占比
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/alterGrade")
	public String alterGrade( @RequestBody  List<RecGrade> list){	
		System.out.println("我叫");
//		return recGradeService.alterGrade(list);
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

	/**
	 * 查询全省占比
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAll")
	public List<RecGrade> selectAll(){
		return recGradeService.selectAll();
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public String alterGrade(RecGrade grade){	
		System.out.println("我叫"+grade);
//		return recGradeService.alterGrade(list);
		try{
			recGradeService.updateGrade(grade);
			return "{ \"success\" : true }";
		}catch (Exception e) {
			// TODO: handle exception
			return "{ \"success\" : false }";
		}
	}
}
