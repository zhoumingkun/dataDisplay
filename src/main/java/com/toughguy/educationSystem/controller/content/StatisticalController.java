package com.toughguy.educationSystem.controller.content;

import java.util.HashMap;
import java.util.Map;

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
	
	/**
	 * 平台总测试题情况
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/findSum")
	public StatisticalDTO findSum () {
		StatisticalDTO s = new StatisticalDTO();
		Map<String, Object> params = new HashMap<String,Object>();
		int testSum = testService.findTestSum(); //总题数
		int testerSum = accountResultService.findTesterSum(params); //总测试人次
		int testerPassSum = accountResultService.findTesterPassSum(params);  //测试合格人次
		int testerFailureSum = accountResultService.findTesterFailureSum(params);  //测试不合格人次
		s.setTestSum(testSum);
		s.setTesterSum(testerSum);
		s.setTesterPassSum(testerPassSum);
		s.setTesterFailureSum(testerFailureSum);
		return s;
	}
	/**
	 * 某道题的测试情况
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/findSumByTestId")
	public StatisticalDTO findSumByTestId (int testId) {
		StatisticalDTO s = new StatisticalDTO();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("testId", testId);
		int oneTesterSum = accountResultService.findTesterSum(params); //某题的测试人次
		int oneTesterPassSum = accountResultService.findTesterPassSum(params);  //某题测试合格人次
		int oneTesterFailureSum = accountResultService.findTesterFailureSum(params);  //某题测试不合格人次
		s.setOneTesterSum(oneTesterSum);
		s.setOneTesterPassSum(oneTesterPassSum);
		s.setOneTesterFailureSum(oneTesterFailureSum);
		return s;
	}
}
