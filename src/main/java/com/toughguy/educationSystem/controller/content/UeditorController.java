package com.toughguy.educationSystem.controller.content;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toughguy.educationSystem.ueditor.ActionEnter;

/**
 * 用于处理关于ueditor插件相关的请求
 * @author BOBO
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {
	
	@RequestMapping(value="/exec")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String rootPath = request.getRealPath("/");
		System.out.println(rootPath);
		try {
			return new ActionEnter(request, rootPath).exec();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
