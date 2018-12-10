package com.toughguy.educationSystem.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toughguy.educationSystem.dto.StatisticalDTO;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.ITestService;

@Controller
@RequestMapping(value = "/statistical")
public class StatisticalController {
	
	@Autowired
	private ITestService testService;

	@Autowired
	private IAccountResultService accountResultService;
	
	@ResponseBody	
	@RequestMapping(value = "/findSum")
	public StatisticalDTO findSum () {
		StatisticalDTO s = new StatisticalDTO();
		int testSum = testService.findTestSum(); //总题数
		int testerSum = accountResultService.findTesterSum(); //总测试人次
		int testerPassSum = accountResultService.findTesterPassSum();  //测试合格人次
		int testerFailureSum = accountResultService.findTestFailureSum();  //测试不合格人次
		s.setTestSum(testSum);
		s.setTesterSum(testerSum);
		s.setTesterPassSum(testerPassSum);
		s.setTesterFailureSum(testerFailureSum);
		return s;
	}
}
