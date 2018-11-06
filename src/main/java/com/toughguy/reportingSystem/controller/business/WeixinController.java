package com.toughguy.reportingSystem.controller.business;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.util.HttpUtil;

@RestController
@RequestMapping("/apiwx")
public class WeixinController {
    

	private static Logger log = LoggerFactory.getLogger(WeixinController.class);

	@Value("${wx.appId}")
	private String appId;

	@Value("${wx.appSecret}")
	private String appSecret;

	@Value("${wx.grantType}")
	private String grantType;
	// wx.grantType=authorization_code

	@Value("${wx.requestUrl}")
	private String requestUrl;
	// wx.requestUrl=https://api.weixin.qq.com/sns/jscode2session

	@RequestMapping("/session")
	public Map<String, Object> getSession(@RequestParam(required = true) String code) {
		return this.getSessionByCode(code);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getSessionByCode(String code) {
		String url = this.requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="
				+ grantType;
		// 发送请求
		String data = HttpUtil.get(url);
		log.debug("请求地址：{}", url);
		log.debug("请求结果：{}", data);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> json = null;
		try {
			json = mapper.readValue(data, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 形如{"session_key":"6w7Br3JsRQzBiGZwvlZAiA==","openid":"oQO565cXXXXXEvc4Q_YChUE8PqB60Y"}的字符串
		return json;
	}
}
