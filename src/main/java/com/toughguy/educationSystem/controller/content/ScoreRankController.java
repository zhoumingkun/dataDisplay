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
import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IScoreRankService;

@Controller
@RequestMapping(value = "/scoreRank")
public class ScoreRankController {
	@Autowired
	private IScoreRankService scoreRankService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("scoreRank:save")
	public String saveScoreRank(ScoreRank scoreResult) {
		try {
			scoreRankService.save(scoreResult);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("scoreRank:edit")
	public String editScoreRank(ScoreRank scoreResult) {
		try {
			scoreRankService.update(scoreResult);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("scoreRank:delete")
	public String deleteScoreRank(int id) {
		try {
			scoreRankService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("scoreRank:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<ScoreRank> pg = scoreRankService.findPaginated(map);
			
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
	//@RequiresPermissions("scoreRank:findAll")
	public List<ScoreRank> findAll() {
		return scoreRankService.findAll();
	}
	
}
