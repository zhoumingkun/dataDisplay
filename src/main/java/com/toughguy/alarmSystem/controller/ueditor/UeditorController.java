package com.toughguy.alarmSystem.controller.ueditor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toughguy.alarmSystem.ueditor.ActionEnter;


@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController {

	@RequestMapping("/")
	public String index(){
		return "ueditor";
	}

	@Autowired
	private ActionEnter actionEnter;

	@ResponseBody
	@RequestMapping("/exec")
	public String exe(HttpServletRequest request){
		return actionEnter.exec(request);
	}

}