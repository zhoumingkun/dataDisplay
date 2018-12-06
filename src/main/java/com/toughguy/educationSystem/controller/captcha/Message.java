package com.toughguy.educationSystem.controller.captcha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.toughguy.educationSystem.util.AliyunMessageUtil;
import com.toughguy.educationSystem.util.MD5Util;



@Controller
@RequestMapping(value="/message")
public class Message {
	
	@ResponseBody
	@RequestMapping(value = "/sendMsg")
	public Map<String, Object> sendMsg(String phoneNumber) throws Exception {
		String randomNum = createRandomNum(6);
		String customCode = MD5Util.MD5Encode(randomNum, "utf8");
		String jsonContent = "{\"code\":\"" + randomNum + "\"}";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("msgSign", "硬汉科技");
		paramMap.put("templateCode", "SMS_151997154");
		paramMap.put("jsonContent", jsonContent);
		SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 5);
		String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("customCode", customCode);
		resultMap.put("tamp", currentTime);
		if(sendSmsResponse.getCode().equals("OK")) {
			return resultMap;
		} else {
			return null;
		}
	}
		/**
		 * 生成随机数
		 * @param num 位数
		 * @return
		 */
		public static String createRandomNum(int num){
		 String randomNumStr = "";
		 for(int i = 0; i < num;i ++){
		  int randomNum = (int)(Math.random() * 10);
		  randomNumStr += randomNum;
		 }
		 return randomNumStr;
		}

}
