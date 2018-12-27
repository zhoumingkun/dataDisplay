package com.toughguy.educationSystem.controller.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.dto.XiaoyuanhuangyeDTO;
import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeOrganization;
import com.toughguy.educationSystem.model.content.Zhengcefagui;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeDepartmentService;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeOrganizationService;
import com.toughguy.educationSystem.util.JsonUtil;

@Controller
@RequestMapping(value = "/xiaoyuanhuangye")
public class XiaoyuanhuangyeController {
	
	@Autowired
	private IXiaoyuanhuangyeOrganizationService xiaoyuanhuangyeOrganizationService;
	@Autowired
	private IXiaoyuanhuangyeDepartmentService xiaoyuanhuangyeDepartmentService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	@RequiresPermissions("xiaoyuanhuangye:save")
	public String save(XiaoyuanhuangyeOrganization xiaoyuanhuangyeOrgainzation,String params) {
		try {
			xiaoyuanhuangyeOrganizationService.save(xiaoyuanhuangyeOrgainzation);
			List<XiaoyuanhuangyeDepartment> xyhyd = new ArrayList<XiaoyuanhuangyeDepartment>();
			if (!StringUtils.isEmpty(params)) {
				xyhyd = JsonUtil.jsonToList(params, XiaoyuanhuangyeDepartment.class);
			}
			for(XiaoyuanhuangyeDepartment x:xyhyd) {
				x.setOrganizationId(xiaoyuanhuangyeOrgainzation.getId());
				xiaoyuanhuangyeDepartmentService.save(x);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody	
	@RequestMapping(value = "/edit")
	@RequiresPermissions("xiaoyuanhuangye:edit")
	public String edit(XiaoyuanhuangyeOrganization xiaoyuanhuangyeOrgainzation,String params) {
		try {
			System.out.println(xiaoyuanhuangyeOrgainzation.getId());
			xiaoyuanhuangyeOrganizationService.update(xiaoyuanhuangyeOrgainzation);
			List<XiaoyuanhuangyeDepartment> xyhyd = new ArrayList<XiaoyuanhuangyeDepartment>();
			List<XiaoyuanhuangyeDepartment> xyhydOld = xiaoyuanhuangyeDepartmentService.findAllDepartment(xiaoyuanhuangyeOrgainzation.getId());
			if (!StringUtils.isEmpty(params)) {
				xyhyd = JsonUtil.jsonToList(params, XiaoyuanhuangyeDepartment.class);
				if(xyhydOld == null) {
					for(XiaoyuanhuangyeDepartment x:xyhyd) {
						x.setOrganizationId(xiaoyuanhuangyeOrgainzation.getId());
						xiaoyuanhuangyeDepartmentService.save(x);
					}
				} else {
					for(int i = 0;i<xyhyd.size();i++) {
						xyhyd.get(i).setOrganizationId(xiaoyuanhuangyeOrgainzation.getId());
						try {
							XiaoyuanhuangyeDepartment old = xyhydOld.get(i);
							xyhyd.get(i).setId(old.getId());
							xiaoyuanhuangyeDepartmentService.update(xyhyd.get(i));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							xiaoyuanhuangyeDepartmentService.save(xyhyd.get(i));
						}
						
					}
				}
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 编辑前获取机构与部门
	 * @param id
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("xiaoyuanhuangye:findAll")
	public XiaoyuanhuangyeDTO findAll(int organizationId) {
		try {
			XiaoyuanhuangyeOrganization xyhyo = xiaoyuanhuangyeOrganizationService.find(organizationId);
			List<XiaoyuanhuangyeDepartment> xyhyd = xiaoyuanhuangyeDepartmentService.findAllDepartment(organizationId);
			XiaoyuanhuangyeDTO xyhyDTO = new XiaoyuanhuangyeDTO();
			xyhyDTO.setArticleSource(xyhyo.getArticleSource());
			xyhyDTO.setAuthor(xyhyo.getAuthor());
			xyhyDTO.setOrganizationName(xyhyo.getOrganizationName());
			xyhyDTO.setType(xyhyo.getType());
			xyhyDTO.setXiaoyuanhuangyeDepartment(xyhyd);
			return xyhyDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody	
	@RequestMapping(value = "/findAllDepartment")
	//@RequiresPermissions("xiaoyuanhuangye:findAllDepartment")
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id) {
		try {
			return xiaoyuanhuangyeDepartmentService.findAllDepartment(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody	
	@RequestMapping(value = "/deleteOrganization")
	@RequiresPermissions("xiaoyuanhuangye:deleteOrganization")
	public String deleteOrganization(int id) {
		try {
			xiaoyuanhuangyeOrganizationService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody	
	@RequestMapping(value = "/deleteDepartment")
	@RequiresPermissions("xiaoyuanhuangye:deleteDepartment")
	public String deleteDepartment(int id) {
		try {
			xiaoyuanhuangyeDepartmentService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("xiaoyuanhuangye:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<XiaoyuanhuangyeOrganization> pg = xiaoyuanhuangyeOrganizationService.findPaginated(map);
			
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
}
