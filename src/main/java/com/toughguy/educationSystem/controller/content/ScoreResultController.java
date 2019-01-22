package com.toughguy.educationSystem.controller.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.model.content.ScoreResult;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IScoreResultService;

@Controller
@RequestMapping(value = "/scoreResult")
public class ScoreResultController {
	@Autowired
	private IScoreResultService scoreResultService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("scoreResult:save")
	public String saveScoreResult(ScoreResult scoreResult) {
		try {
			scoreResultService.save(scoreResult);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("scoreResult:edit")
	public String editScoreResult(ScoreResult scoreResult) {
		try {
			scoreResultService.update(scoreResult);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("scoreResult:delete")
	public String deleteScoreResult(int id) {
		try {
			scoreResultService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("scoreResult:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<ScoreResult> pg = scoreResultService.findPaginated(map);
			
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", pg.getData());
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("scoreResult:findAll")
	public List<ScoreResult> findAll() {
		return scoreResultService.findAll();
	}
	
}
